package com.ar.vgmsistemas.bo;

import java.util.Optional;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ar.vgmsistemas.config.AppConfig;
import com.ar.vgmsistemas.config.SqliteConnectionConfig;
import com.ar.vgmsistemas.dao.filesystem.ManageFilesDao;
import com.ar.vgmsistemas.utils.CodeResult;

@Component
public class CcinfoGeneralBo {
	@Autowired
	private ManageFilesDao manageFilesDao;
	@Value("${talonesReciboParaVendedor}")
	private boolean talonesReciboParaVendedor;
	@Value("${anticipoHabilitado}")
	private boolean anticipoHabilitado;
	@Value("${periodoDescargaRecibo}")
	private int periodoDescargaRecibo;

	


	// BO's
	@Autowired


	static Logger logger = Logger.getLogger(SincronizacionBo.class);
	public static final int CONEXION_EXITOSA = 0;

	private long idSucursalVendedor;


	@Value("${reutilizacionZip}")
	private int segundos;

	public String iniciarSincronizacion(Integer idVendedor, Session metadata, String bd, SqliteConnectionConfig config) throws Exception {		
			logger.info("Iniciando el Proceso de Sincronizacion - Vendedor Nro ".concat(String.valueOf(idVendedor)));

			Map<String, Object> parameter = new HashMap<String, Object>();

			// Recuperar informacion desde la base de datos origen
			logger.info("Realizando consultas a las tablas del servidor");
			logger.info("La sincronizacion se hace con READ UNCOMMITED para todas las consultas salvo para Cuenta Corriente");
			/*	
			// TipoMoneda
			List<TipoMoneda> tiposMonedas = tipoMonedaDao.recoveryAll();
			// TipoMoneda
			tipoMonedaDao.createForLote(tiposMonedas, metadata);
			// Banco
			List<Banco> bancos = bancoDao.recoveryAll();
			// Motivo no pedido
			List<MotivoNoPedido> motivosNoPedido = motivoNoPedidoDao.recoveryAll();		
			
			// Documentos
			documentos = documentoDao.recoveryAll();
			
			// Empresa
			List<Empresa> empresa = empresaDao.recoveryAll();
			
			for (Empresa emp: empresa){
				emp.setAnticipoHabilitado(anticipoHabilitado);
			}
			
			//Valido configuracion de rango horario de trabajo del vendedor
			recursoHumanoBo.validarHorarioTrabajoVendedor(idVendedor);
			// recurso humano
			List<RecursoHumano> recursosHumanos = recursoHumanoDao.recoveryAll();
			//List<Repartidor> repartidores = new ArrayList<Repartidor>(); //= repartidorDao.recoveryAll();
			List<Vendedor> vendedores = vendedorDao.recoveryAll();
			vendedorDao.createForLote(vendedores, metadata);
			List<Repartidor> repartidores = repartidorDao.recoveryAll();
			repartidorDao.createForLote(repartidores, metadata);
			
			// Condicion Venta*/

			// Condicion Iva
			/*List<CategoriaFiscal> categoriasFiscales = categoriaFiscalDao.recoveryAll();
			
			//MOTIVOS AUTORIZACION
			List<MotivoAutorizacion> motivosAutorizacion = motivoAutorizacionDao.recoveryAll();
*/
			// Lista de Precios

			
/*			List<PlanCuenta> planesCuentas = planCuentaDao.recoveryAll();
			
			List<Impuesto> impuestos = impuestoDao.recoveryAll();
			
			//idSucursalVendedor = recursoHumanoDao.recoveryById(Long.parseLong(idVendedor+"")).getIdSucursal();
			
			idSucursalVendedor = recursoHumanoDao.recoveryById(Long.parseLong(idVendedor+"")).getIdSucursal();
			parameter.clear();
			parameter.put("id.idSucursal", idSucursalVendedor);
			if (recursoHumanoDao.recoveryById(Long.parseLong(idVendedor+"")).getIdCategoria() != Long.parseLong(recursoHumanoBo.getCategoriasEmpRepartidor())) {
				parameter.put("snMovil", "S");
			}
			List<ListaPrecioDetalle> listaPrecioDetalles = listaPrecioDetalleDao.recoveryByParameter(parameter);
			
			List<Proveedor> listaProveedores = proveedorDao.recoveryAll();
			*/
			// -------------- GENERO EL ARCHIVO DE BASE DE DATOS SQLITE DEL VENDEDOR ---------------//

			logger.info("Grabo todos los datos en la base de datos SQLITE");
			/*
			// Documentos
			documentoDao.createForLote(documentos, metadata);
			
			getPlanCuentaDao().createForLote(planesCuentas, metadata);
			
			impuestoDao.createForLote(impuestos, metadata);
			
			List<Recibo> recibos = new ArrayList<Recibo>();
			List<ReciboDetalle> pagos = new ArrayList<ReciboDetalle>();
			List<Cheque> cheques = new ArrayList<Cheque>();
			List<Retencion> retenciones = new ArrayList<Retencion>();
			List<Deposito> depositos = new ArrayList<Deposito>();
			List<EntregaCobranza> entregas = new ArrayList<EntregaCobranza>();
			
			//Verifico si está habilitada la sinc de recibos
			String sincRecibos = Optional.ofNullable(empresa.get(0).getSnSincRecibos()).orElse("N");
			if (sincRecibos.equals("S")){
				recibos = reciboDao.recoveryNoRendidos(idVendedor);
				//Inicia la busqueda de los pagos imputados a los recibos
				for (Recibo r : recibos ) { 			
					if (r.getDetalles()!=null && r.getDetalles().size()>0){
						pagos.addAll(r.getDetalles());
					}
				}	
				for (Recibo r : recibos ) { 
					entregas.add(r.getEntregaCobranza());
				}	
				for (EntregaCobranza ec : entregas) { 
					cheques.addAll(ec.getCheques());
					retenciones.addAll(ec.getRetenciones());
					depositos.addAll(ec.getDepositos());
				}	 
			}
			
			// Talones de recibo para el vendedor
			if (talonesReciboParaVendedor || empresa.get(0).getSnReciboProvisorio().equals("S")) {
				Date desde;
				Date hasta;
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.add(Calendar.MONTH, (-1) * periodoDescargaRecibo);
				desde = calendar.getTime();
				calendar.setTime(Calendar.getInstance().getTime());
				calendar.add(Calendar.DATE, 1);
				hasta = calendar.getTime();
				recibos.addAll(reciboDao.recoveryDisponibles(idVendedor, desde, hasta));	
			}
			reciboDao.createForLote(recibos, metadata);
			
			List<CuentaCorriente> cuentas= new ArrayList<CuentaCorriente>();
			for (ReciboDetalle p : pagos ) { 
				p.setRecursoHumano(recibos.get(0).getRecursoHumano());
				if (!cuentas.contains(p.getCuentaCorriente())) {
					CuentaCorriente temp = p.getCuentaCorriente();
					if(empresa.get(0).getIsReciboAfectaCuentaCorriente()!= null && empresa.get(0).getIsReciboAfectaCuentaCorriente().equals("N")){
						temp.corregirSaldo();
					}
					cuentas.add(temp);
				}
			}	
			String sincronizaCCCompleta = empresa.get(0).getSnCtaCteCompleta();
			
			List<CuentaCorriente> cuentasCorrientes = new ArrayList<>();
			if(sincronizaCCCompleta == null || sincronizaCCCompleta.equals("N")) {
				cuentasCorrientes = cuentaCorrienteDao.recoveryByVendedor(idVendedor);
			} else {
				cuentasCorrientes = cuentaCorrienteDao.recoveryByRuta(idVendedor);
			}
			
			for (CuentaCorriente cc: cuentasCorrientes){
				if (!cuentas.contains(cc)) {
					if(empresa.get(0).getIsReciboAfectaCuentaCorriente().equals("N")){
						//cc.corregirSaldo(cuentaCorrienteDao.getSaldoCorregir(cc));
						cc.corregirSaldo();
					}
					cuentas.add(cc);
				}
			}
			
			// Banco
			getBancoDao().createForLote(bancos, metadata);
			// Motivo no pedido
			motivoNoPedidoDao.createForLote(motivosNoPedido, metadata);
			// recurso humano
			recursoHumanoDao.createForLote(recursosHumanos, metadata);
			// Empresa
			empresaDao.createForLote(empresa, metadata);*/
			// Condicion Venta

			// Condicion Iva
			/*categoriaFiscalDao.createForLote(categoriasFiscales, metadata);
			motivoAutorizacionDao.createForLote(motivosAutorizacion, metadata);*/
			// Lista de Precios

/*			// Lista de precios
			listaPrecioDetalleDao.createForLote(listaPrecioDetalles, metadata);
			proveedorDao.createForLote(listaProveedores, metadata);
			//Resumen de Recibos
			cuentaCorrienteDao.createForLote(cuentas, metadata);
			reciboDetalleDao.createForLote(pagos, metadata);
			entregaCobranzaDao.createForLote(entregas, metadata);
			chequeDao.createForLote(cheques, metadata);
			retencionDao.createForLote(retenciones,metadata);
			depositoDao.createForLote(depositos, metadata);
			*/
			return bd;

	}

	protected String FinalSincronizacion(String bd, long idVendedor, SqliteConnectionConfig config) throws Exception {
		String sDigestZip = CodeResult.ERROR_SINCRONIZACION;
		if (bd != null) {
			// Comprimo el archivo de base de datos
			sDigestZip = createZipFile(idVendedor, bd);

		}
		logger.info("Finalizo la sincronizacion del rrhh: " + idVendedor + ". Resultado: " + sDigestZip + ".");
		config.close();
		return sDigestZip;
	}

	public String createZipFile(long idVendedor, String bd) throws Exception {
		String zipFile = AppConfig.sqliteUrl + "ccinfo-" + idVendedor + ".zip";
		String nameFile = AppConfig.nombreBase + idVendedor + AppConfig.extensionSqlite;

		if (!bd.equals("")) { // Si viene vacío es porque se reutiliza el zip creado hace poco
			manageFilesDao.createZip(bd, zipFile, nameFile);
		}

		InputStream input = new FileInputStream(zipFile);
		MessageDigest digester = MessageDigest.getInstance("MD5");
		byte[] bytes = new byte[8192];
		int byteCount;
		while ((byteCount = input.read(bytes)) > 0) {
			digester.update(bytes, 0, byteCount);
		}
		byte[] digest = digester.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			String h = Integer.toHexString(0xFF & digest[i]);
			while (h.length() < 2)
				h = "0" + h;
			hexString.append(h);
		}
		input.close();
		return hexString.toString();
	}

	public String createDataBaseSqlite(String bd) throws Exception {
		InputStream input = new FileInputStream(bd);
		MessageDigest digester = MessageDigest.getInstance("MD5");
		byte[] bytes = new byte[8192];
		int byteCount;
		while ((byteCount = input.read(bytes)) > 0) {
			digester.update(bytes, 0, byteCount);
		}
		byte[] digest = digester.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			String h = Integer.toHexString(0xFF & digest[i]);
			while (h.length() < 2)
				h = "0" + h;
			hexString.append(h);
		}
		input.close();
		// sDigest de preventa.sqlite
		return hexString.toString();
	}

	public ManageFilesDao getManageFilesDao() {
		return manageFilesDao;
	}

	public void setManageFilesDao(ManageFilesDao manageFilesDao) {
		this.manageFilesDao = manageFilesDao;
	}

	

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	/*
	 * public String copiarBase(long idVendedor) throws Exception {
	 * logger.info("Copiando el archivo de base de datos preventa-empty.sqlite");
	 * try { // Borro archivo journal File journalFile = new
	 * File(AppConfig.sqliteUrl+ AppConfig.nombreBase + idVendedor + "-journal"); if
	 * (journalFile.exists()) { System.out.println("Archivo journal de vendedor " +
	 * idVendedor + " borrado"); journalFile.delete(); }
	 * 
	 * // Borro archivo zip del vendedor para generar uno nuevo String zipDataBase =
	 * AppConfig.sqliteUrl + AppConfig.nombreBase + idVendedor + ".zip"; File
	 * zipDatabaseTarget = new File(zipDataBase); boolean zipFlag; if
	 * (zipDatabaseTarget.exists()) { zipDatabaseTarget.setWritable(true); zipFlag =
	 * zipDatabaseTarget.delete(); System.out.print(zipFlag); }
	 * 
	 * String database = AppConfig.sqliteUrl + AppConfig.nombreBase + idVendedor +
	 * AppConfig.extensionSqlite; File databaseTarget = new File(database); // Borro
	 * archivo de base de datos del vendedor para generar uno // nuevo boolean flag;
	 * if (databaseTarget.exists()) { flag = databaseTarget.delete();
	 * System.out.print(flag); } FileOutputStream destination = new
	 * FileOutputStream(database); FileInputStream source = new
	 * FileInputStream(AppConfig.sqliteUrl + AppConfig.sqliteEmpty +
	 * AppConfig.extensionSqlite); FileChannel sourceFileChannel =
	 * source.getChannel(); FileChannel destinationFileChannel =
	 * destination.getChannel();
	 * 
	 * long size = sourceFileChannel.size(); sourceFileChannel.transferTo(0, size,
	 * destinationFileChannel);
	 * 
	 * logger.info("Copiado de la base de datos preventa-empty.sqlite exitosa");
	 * destination.close(); source.close(); return database;
	 * 
	 * } catch (Exception e) {
	 * logger.info("Error. No se pudo crear el archivo de base de datos"); throw new
	 * Exception("Problemas al crear Archivo de Base de datos " + e.getMessage()); }
	 * }
	 */

}
