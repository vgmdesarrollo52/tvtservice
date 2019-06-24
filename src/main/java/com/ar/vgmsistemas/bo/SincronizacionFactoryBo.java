package com.ar.vgmsistemas.bo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.ar.vgmsistemas.config.AppConfig;
import com.ar.vgmsistemas.config.SqliteConnectionConfig;

import com.ar.vgmsistemas.dto.Documentos;

import com.ar.vgmsistemas.utils.CategoriaRecursoHumano;

/**
 * @author eMa
 * Esta clase nos va a decir que sincronizacion realizar en base a el vendedor y su categoria
 *
 */
@Controller
public class SincronizacionFactoryBo implements BeanFactoryAware{
	
	private static BeanFactory beanFactory;
//	private static RecursoHumanoBo recursoHumanoBo;
	
	static Logger logger = Logger.getLogger(SincronizacionBo.class);
	
	public static String getSincronizacionFactory(Integer idVendedor, Documentos documentos) throws Exception{
//		recursoHumanoBo = (RecursoHumanoBo) beanFactory.getBean("recursoHumanoBo");
//		RecursoHumano rh2 = recursoHumanoBo.recoveryVendedor(idVendedor);
//		String categoriasEmpRepartidor = recursoHumanoBo.getCategoriasEmpRepartidor();

		String bd = copiarBase(idVendedor);
		SqliteConnectionConfig sqliteConnectionConfig = new SqliteConnectionConfig();
		Session session =sqliteConnectionConfig.getSession(idVendedor);
		if (!bd.equals("")){//Zip nuevo
			try{

	        	SincronizacionBo sincronizacionBo = (SincronizacionBo) beanFactory.getBean("sincronizacionBo");
	        	return sincronizacionBo.iniciarSincronizacion(idVendedor, documentos, session, bd, sqliteConnectionConfig);
			}catch(Exception e){		
				e.printStackTrace();
				sqliteConnectionConfig.close();
				throw new Exception(e); 
			}
		} else {//Reutilizar zip
			SincronizacionGeneralBo sincronizacionGeneralBo = (SincronizacionGeneralBo) beanFactory.getBean("sincronizacionGeneralBo");
			return sincronizacionGeneralBo.FinalSincronizacion(bd, idVendedor, sqliteConnectionConfig);
		}
	}
	
	public static String copiarBase(long idVendedor) throws Exception {
		try {
			// Borro archivo journal
			File journalFile = new File(AppConfig.sqliteUrl + AppConfig.nombreBase + idVendedor + AppConfig.extensionSqlite
					+ "-journal");
			if (journalFile.exists()) {
				System.out.println("Archivo journal de vendedor " + idVendedor
						+ " borrado");
				journalFile.setWritable(true);
				journalFile.delete();
			}

			// Borro archivo zip del vendedor para generar uno nuevo
			String zipDataBase = AppConfig.sqliteUrl + AppConfig.nombreBase
					+ idVendedor + ".zip";
			File zipDatabaseTarget = new File(zipDataBase);
			boolean zipFlag;
			String database ="";
			boolean crearZip = true;
			
//			if (zipDatabaseTarget.exists()) {
//				
//				long fechaUltimaModifEnMilis = zipDatabaseTarget.lastModified();
//				long fechaActualEnMilis = System.currentTimeMillis();
//				
				SincronizacionGeneralBo sincronizacionGeneralBo;
				sincronizacionGeneralBo = (SincronizacionGeneralBo) beanFactory.getBean("sincronizacionGeneralBo");
//				if (fechaUltimaModifEnMilis < fechaActualEnMilis-sincronizacionGeneralBo.getSegundos()*1000){ //Si el zip se modific� hace mas de x minutos
//					//Borro el zip viejo
//					zipDatabaseTarget.setWritable(true);
//					zipFlag = zipDatabaseTarget.delete();
//					System.out.print(zipFlag);
//				} else {
//					//Uso el mismo zip
//					crearZip = false;
//				}
//			}
			
			if (crearZip){
				
				database = AppConfig.sqliteUrl + AppConfig.nombreBase
						+ idVendedor + AppConfig.extensionSqlite;
				File databaseTarget = new File(database);
				// Borro archivo de base de datos del vendedor para generar uno
				// nuevo
				boolean flag;
				if (databaseTarget.exists()) {
					databaseTarget.setWritable(true); 
					flag = databaseTarget.delete();
					System.out.print(flag);
				}
				FileOutputStream destination = new FileOutputStream(database);
				FileInputStream source = new FileInputStream(AppConfig.sqliteUrl + AppConfig.sqliteEmpty + AppConfig.extensionSqlite);
				FileChannel sourceFileChannel = source.getChannel(); 
				FileChannel destinationFileChannel = destination.getChannel();

				long size = sourceFileChannel.size();
				sourceFileChannel.transferTo(0, size, destinationFileChannel);

				destination.close();
				source.close();
			}
			
			//Devuelve la ruta del sqlite si debe crearlo y sino vac�o
			return database;

		} catch (Exception e) {
			String msj = "Problemas al crear Archivo de Base de datos "
					+ e.getMessage();
			logger.error(msj);
			throw new Exception(msj);
		}
	}
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
	      SincronizacionFactoryBo.beanFactory = beanFactory;
	}
//	
//	public RecursoHumanoBo getRecursoHumanoBo() {
//		return recursoHumanoBo;
//	}
//
//	public void setRecursoHumanoBo(RecursoHumanoBo recursoHumanoBo) {
//		SincronizacionFactoryBo.recursoHumanoBo = recursoHumanoBo;
//	}

}
