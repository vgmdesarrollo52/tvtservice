package com.ar.vgmsistemas.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ar.vgmsistemas.config.SqliteConnectionConfig;
import com.ar.vgmsistemas.dto.Documentos;


@Component
public class SincronizacionBo{

	@Value("${periodoDescargaventa}")
	private int periodoDescargaventa;
	@Value("${objetivosDeVenta}")
	private boolean objetivosDeVenta;
	

	

	

	// BO's

	@Autowired
	private ValidacionBo validacionBo;

	@Autowired
	private SincronizacionGeneralBo sincronizacionGeneralBo;


	static Logger logger = Logger.getLogger(SincronizacionBo.class);
	public static final int CONEXION_EXITOSA = 0;
	
	private long idSucursalVendedor;
	public String iniciarSincronizacion(Integer idVendedor, Documentos documentos, Session metadata, String bd, SqliteConnectionConfig config) throws Exception {
		try {		
			bd = sincronizacionGeneralBo.iniciarSincronizacion(idVendedor, metadata, bd, config);	
			logger.info("Iniciando el Proceso de Sincronizacion - Vendedor Nro ".concat(String.valueOf(idVendedor)));

			Date desde;
			Date hasta;
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, (-1) * periodoDescargaventa);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			calendar.set(Calendar.MILLISECOND,0);
			desde = calendar.getTime();
			hasta = Calendar.getInstance().getTime();
			
			Map<String, Object> parameter = new HashMap<String, Object>();		
			
			
			
			
//			if (objetivosDeVenta) {
//				for (Iterator<Cliente> iterator = clientes.iterator(); iterator.hasNext();) {
//					Cliente cliente = iterator.next();				
//					objetivosVenta.addAll(objetivoVentaDao.recoveryByCliente(cliente));
//				}
//			}
//			
//			List<Venta> ventas = ventaDao.recoveryVentasByPeriodo(desde, hasta, parameter);
//			List<VentaDetalle> ventasDetalles = ventaDetalleDao.recoveryVentasDetalleByPeriodo(desde, hasta, parameter);
//							
//			// Ventas cuenta corriente
//			//parameter.clear();
//			//parameter.put("snAnulo", "N");
//			
//			String sincronizaCCCompleta = empresa.getSnCtaCteCompleta();
//			List<Venta> ventasCuentaCorriente = new ArrayList<>();
//			
//			
//			ventasCuentaCorriente = ventaDao.recoveryVentasCuentaCorriente(desde, hasta, parameter);
//			if (ventasCuentaCorriente.size() > 0) {
//				ventas.addAll(ventasCuentaCorriente);
//				List<VentaDetalle> ventaDetalleCC = ventaDetalleDao.recoveryVentasDetalleCuentaCorriente(desde, hasta,	parameter);
//				ventasDetalles.addAll(ventaDetalleCC);
//			}
//				
//			List<DocumentosLista> documentosLista = documentosListaDao.recoveryAll();
			
			/*
			Date fecha = Calendar.getInstance().getTime();
			parameter.clear();
			parameter.put("fecha", fecha);
			parameter.put("idVendedor", idVendedor);
			List<Cheque> cheques = chequeDao.recoveryChequesEnCarteraByRuta(parameter);
			*/ //Comentario lucas cheques
			
			parameter.clear();
			parameter.put("id.idVendedor", idVendedor);
//			List<SecuenciaRuteo> rutas = secuenciaRuteoDao.recoveryByParameter(parameter);	
//			
//			List<DescuentoProveedor> descuentosProveedor = descuentoProveedorDao.recoveryByVendedor(idVendedor);
//			List<DescuentoProveedorGeneral> descuentosProveedorGeneral =descuentoProveedorGeneralDao.recoveryAll();
//			
//			//List<CuentaCorriente> cuentasCorrientes = cuentaCorrienteDao.recoveryByVendedor(idVendedor);
//					
//			idSucursalVendedor = iRecursoHumanoDao.recoveryById(Long.parseLong(idVendedor+"")).getIdSucursal();
//					
//			Sucursal sucursal = sucursalDao.recoveryById(idSucursalVendedor);	
//			long idDeposito;
//			if (empresa.getIsControlDesposito().equals("S")){
//				idDeposito = sucursal.getIdDeposito();
//			}else if(empresa.getIsControlDesposito().equals(Empresa.CONTROL_DEPOSITO_X_COMPROBANTE)){
//				idDeposito = documento.getIdN1();
//			}else{	
//				idDeposito = empresa.getIdSucactiva();
//			}
//			parameter.clear();
//			parameter.put("idDeposito", idDeposito);
//			List<Stock> stockArticulos = stockDao.recoveryByParameter(parameter);
//			parameter.clear();
//			parameter.put("idSucursal", idSucursalVendedor);
//			List<StockCombo> stockCombos = stockComboDao.recoveryByParameter(parameter);
//			
//			List<ArticuloPromocion> articulosPromocion = articuloPromocionDao.recoveryAll();			
//			
//			Map<String, Object> parameter2 = new HashMap<String, Object>();
//			parameter2.clear();
//			parameter2.put("idVendedor", idVendedor);
//			List<VendedorObjetivo> vendedorObjetivos = vendedorObjetivoDao.obtenerByVendedorAndPeriodo(hasta, parameter2);			
//			List<VendedorObjetivoDetalle> vendedorObjetivosDetalles = new ArrayList<VendedorObjetivoDetalle>();
//			
//			if (vendedorObjetivos.size() > 0) {
//				objetivoCoberturaBo.calcularPorcentajeCobertura(vendedorObjetivos, ventas, ventasDetalles, clientes.size());
//				for(VendedorObjetivo vendedorObjetivo: vendedorObjetivos){
//					vendedorObjetivosDetalles.addAll(vendedorObjetivo.getVendedorObjetivoDetalle());
//				}
//			}
//			
//			if (empresaBo.getEmpresa().getIsCobranzaEstrica().equalsIgnoreCase("S")){
//				List<CodigoAutorizacionCobranza> codigos = codigoAutorizacionCobranzaDao.recoveryUsados(idVendedor);
//				codigoAutorizacionCobranzaDao.createForLote(codigos, metadata);
//			}
//			
//			List<RequisitoPromocion> requisitoPromocion = requisitoPromocionDao.recoveryAll();
//			List<Sucursal> sucursales = sucursalDao.recoveryAll();
//			
//			List<AccionesGrupos> accionesGrupos = accionesGruposDao.recoveryByAccionesCom();
//			List<GrupoClientesDetalle> grupoClientesDetalle = grupoClientesDetalleDao.recoveryByRuta(idVendedor);
//			List<AccionesCom> accionesCom = accionesComDao.recoveryAll();
//			
//			//Se podria hacer simplemente recoveryAll en caso de estar seguro que no hay acd para las cuales no existe ac
//			List<AccionesComDetalle> acd = accionesComDetalleDao.recoveryByAccionesCom();
//			
//			List<RangoRentabilidad> rangosRentabilidad = rangoRentabilidadDao.recoveryAll();
			
			
			// -------------- GENERO EL ARCHIVO DE BASE DE DATOS SQLITE DEL VENDEDOR ---------------//
			logger.info("Grabo todos los datos en la base de datos SQLITE");
			
//			condicionRentaDao.createForLote(condicionesRentas, metadata);
//			negocioDao.createForLote(negocios, metadata);
			try {
				//rubroDao.createForLote(rubros, metadata);
			} catch (Exception e) {
				String msj = "Error #1584: id_negocio nulo en la tabla Rubros. Verifique la integridad de la tabla "
						+ e.getMessage() + e.getCause();
				logger.error(msj);
				throw new Exception(msj);
			}
			try {
				//subrubroDao.createForLote(subrubros, metadata);
			} catch (Exception e) {
				String msj = "Error #1584: id_negocio en la tabla Subrubro. Verifique la integridad de la tabla "
						+ e.getMessage() + e.getCause();
				logger.error(msj);
				throw new Exception(msj);
			}
//			articuloDao.createForLote(articulosOrigen, metadata);
//			condicionDirscDao.createForLote(condicionesDirsc, metadata);
//			clienteDao.createForLote(clientes, metadata);
//			comercioLoginDao.createForLote(comercioLoginMovil, metadata);
//			stockDao.createForLote(stockArticulos, metadata);
//			stockComboDao.createForLote(stockCombos, metadata);
//			if (ventas != null) {
//				ventaDao.createForLote(ventas, metadata);
//				ventaDetalleDao.createForLote(ventasDetalles, metadata);
//			}	
//			vendedorObjetivoDao.createForLote(vendedorObjetivos, metadata);
//			accionesGruposDao.createForLote(accionesGrupos, metadata);
//			grupoClientesDetalleDao.createForLote(grupoClientesDetalle, metadata);
//			accionesComDao.createForLote(accionesCom, metadata);
//			accionesComDetalleDao.createForLote(acd, metadata);
//			requisitoPromocionDao.createForLote(requisitoPromocion, metadata);
//			documentosListaDao.createForLote(documentosLista, metadata);	
//			vendedorObjetivoDetalleDao.createForLote(vendedorObjetivosDetalles, metadata);
//			descuentoProveedorGeneralDao.createForLote(descuentosProveedorGeneral, metadata);
//			descuentoProveedorDao.createForLote(descuentosProveedor, metadata);
//			secuenciaRuteoDao.createForLote(rutas, metadata);
//			objetivoVentaDao.createForLote(objetivosVenta, metadata);
//			//chequeDao.createForLote(cheques, metadata); comentario lucas cheques
//			marcaDao.createForLote(marcas, metadata);
//			sucursalDao.createForLote(sucursales, metadata);
//			articuloPromocionDao.createForLote(articulosPromocion, metadata);
//			//cuentaCorrienteDao.createForLote(cuentasCorrientes, metadata);
//			rangoRentabilidadDao.createForLote(rangosRentabilidad, metadata);

		} catch (Exception e) {
			bd = null;
			System.out.print(e.toString());
			logger.error("Error al realizar la sincronizacion");
			throw new Exception("Problemas al Iniciar la Base de datos "
					+ e.toString() + e.getMessage());
		}		
		return sincronizacionGeneralBo.FinalSincronizacion(bd, idVendedor, config);
	}	
	
	public int getPeriodoDescargaventa() {
		return periodoDescargaventa;
	}

	public void setPeriodoDescargaventa(int periodoDescargaventa) {
		this.periodoDescargaventa = periodoDescargaventa;
	}

	

	public ValidacionBo getValidacionBo() {
		return validacionBo;
	}

	public void setValidacionBo(ValidacionBo validacionBo) {
		this.validacionBo = validacionBo;
	}


	public boolean isObjetivosDeVenta() {
		return objetivosDeVenta;
	}

	public void setObjetivosDeVenta(boolean objetivosDeVenta) {
		this.objetivosDeVenta = objetivosDeVenta;
	}



	public SincronizacionGeneralBo getSincronizacionGeneralBo() {
		return sincronizacionGeneralBo;
	}

	public void setSincronizacionGeneralBo(SincronizacionGeneralBo sincronizacionGeneralBo) {
		this.sincronizacionGeneralBo = sincronizacionGeneralBo;
	}

	
	
}
