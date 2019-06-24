package com.ar.vgmsistemas.config;

//import java.nio.channels.ShutdownChannelGroupException;
//import java.util.List;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.persistence.PersistenceException;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.sql.DataSource;
//
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.ar.vgmsistemas.bo.SenderMailBo;
//import com.ar.vgmsistemas.dao.ILocalidadDao;
//import com.ar.vgmsistemas.dao.IProvinciaDao;
//import com.ar.vgmsistemas.dto.Localidad;
//import com.ar.vgmsistemas.dto.Provincia;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Component
//public class MyServletContextListener implements ServletContextListener {
//
//	@Autowired
//	private ILocalidadDao localidadDaoOrigenNOLOCK;
//	@Autowired
//	private IProvinciaDao provinciaDaoOrigenNOLOCK;
//	@Override
//	public void contextDestroyed(ServletContextEvent arg0) {
//		//arg0.getServletContext().get
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent arg0){
//		WebApplicationContextUtils
//        .getRequiredWebApplicationContext(arg0.getServletContext())
//        .getAutowireCapableBeanFactory()
//        .autowireBean(this);
//		renewLocalidades();
//	}
//	
//	public void renewLocalidades(){
//		List<Localidad> localidades = null;
//		List<Provincia> provincias = null;
//		try{
//			provincias = provinciaDaoOrigenNOLOCK.recoveryAll();
//			localidades = localidadDaoOrigenNOLOCK.recoveryLocalidadBySecuencia();
//		}catch (PersistenceException se){
//			String asd = se.getCause().getCause().getMessage();
//			if(asd.equals("El nombre de columna 'id_secuencia' no es válido.")){
//				try {
//					localidades = localidadDaoOrigenNOLOCK.recoveryAll();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		SqliteConnectionConfig sqliteConnectionConfig = new SqliteConnectionConfig();
//		Session session =sqliteConnectionConfig.getSession();
//		try{
//			localidadDaoOrigenNOLOCK.deleteAll(session);
//			provinciaDaoOrigenNOLOCK.deleteAll(session);
//			provinciaDaoOrigenNOLOCK.createForLote(provincias, session);
//			localidadDaoOrigenNOLOCK.createForLote(localidades, session);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		sqliteConnectionConfig.close();
//	}
//
//	public ILocalidadDao getLocalidadDaoOrigenNOLOCK() {
//		return localidadDaoOrigenNOLOCK;
//	}
//
//	public void setLocalidadDaoOrigenNOLOCK(ILocalidadDao localidadDaoOrigenNOLOCK) {
//		this.localidadDaoOrigenNOLOCK = localidadDaoOrigenNOLOCK;
//	}
//
//	public IProvinciaDao getProvinciaDaoOrigenNOLOCK() {
//		return provinciaDaoOrigenNOLOCK;
//	}
//
//	public void setProvinciaDaoOrigenNOLOCK(IProvinciaDao provinciaDaoOrigenNOLOCK) {
//		this.provinciaDaoOrigenNOLOCK = provinciaDaoOrigenNOLOCK;
//	}
//}
