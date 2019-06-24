package com.ar.vgmsistemas.bo;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ar.vgmsistemas.config.AppConfig;
import com.ar.vgmsistemas.config.SqliteConnectionConfig;
//import com.ar.vgmsistemas.dao.IAccionesComDao;
//import com.ar.vgmsistemas.dao.IAccionesComDetalleDao;
//import com.ar.vgmsistemas.dao.IAccionesGruposDao;
//import com.ar.vgmsistemas.dao.IArticuloDao;
//import com.ar.vgmsistemas.dao.IArticuloPromocionDao;
//import com.ar.vgmsistemas.dao.ICajaDao;
//import com.ar.vgmsistemas.dao.IChequeDao;
//import com.ar.vgmsistemas.dao.IClienteDao;
//import com.ar.vgmsistemas.dao.IClienteVendedorDao;
//import com.ar.vgmsistemas.dao.ICodigoAutorizacionCobranzaDao;
//import com.ar.vgmsistemas.dao.IComercioLoginDao;
//import com.ar.vgmsistemas.dao.ICondicionDirscDao;
//import com.ar.vgmsistemas.dao.ICondicionRentaDao;
//import com.ar.vgmsistemas.dao.ICondicionesComercialesDao;
//import com.ar.vgmsistemas.dao.ICondicionesLogisticasDao;
//import com.ar.vgmsistemas.dao.ICondicionesPagosDao;
//import com.ar.vgmsistemas.dao.ICuentaCorrienteDao;
//import com.ar.vgmsistemas.dao.ICuentaPropiaDao;
//import com.ar.vgmsistemas.dao.IDescuentoProveedorDao;
//import com.ar.vgmsistemas.dao.IDescuentoProveedorGeneralDao;
//import com.ar.vgmsistemas.dao.IDocumentoDao;
//import com.ar.vgmsistemas.dao.IDocumentosListaDao;
//import com.ar.vgmsistemas.dao.IEstadosMercaderiaDao;
//import com.ar.vgmsistemas.dao.IGrupoClientesDetalleDao;
//import com.ar.vgmsistemas.dao.IMarcaDao;
//import com.ar.vgmsistemas.dao.INegocioDao;
//import com.ar.vgmsistemas.dao.IObjetivoVentaDao;
//import com.ar.vgmsistemas.dao.IRangoRentabilidadDao;
//import com.ar.vgmsistemas.dao.IRecursoHumanoDao;
//import com.ar.vgmsistemas.dao.IRequisitoPromocionDao;
//import com.ar.vgmsistemas.dao.IRubroClienteDao;
//import com.ar.vgmsistemas.dao.IRubroDao;
//import com.ar.vgmsistemas.dao.ISecuenciaRuteoDao;
//import com.ar.vgmsistemas.dao.IStock;
//import com.ar.vgmsistemas.dao.IStockCombo;
//import com.ar.vgmsistemas.dao.ISubrubroDao;
//import com.ar.vgmsistemas.dao.ISubrubroVendedor;
//import com.ar.vgmsistemas.dao.ISucursalDao;
//import com.ar.vgmsistemas.dao.ITipoClienteDao;
//import com.ar.vgmsistemas.dao.ITipoLetraDao;
//import com.ar.vgmsistemas.dao.IValorDao;
//import com.ar.vgmsistemas.dao.IVendedorObjetivoDao;
//import com.ar.vgmsistemas.dao.IVendedorObjetivoDetalleDao;
//import com.ar.vgmsistemas.dao.IVentaDao;
//import com.ar.vgmsistemas.dao.IVentaDetalleDao;
//import com.ar.vgmsistemas.dao.IZonaDao;
//import com.ar.vgmsistemas.dao.filesystem.ManageFilesDao;
//import com.ar.vgmsistemas.dto.AccionesCom;
//import com.ar.vgmsistemas.dto.AccionesComDetalle;
//import com.ar.vgmsistemas.dto.AccionesGrupos;
//import com.ar.vgmsistemas.dto.Articulo;
//import com.ar.vgmsistemas.dto.ArticuloPromocion;
//import com.ar.vgmsistemas.dto.Caja;
//import com.ar.vgmsistemas.dto.Cheque;
//import com.ar.vgmsistemas.dto.Cliente;
//import com.ar.vgmsistemas.dto.CodigoAutorizacionCobranza;
//import com.ar.vgmsistemas.dto.ComercioLogin;
//import com.ar.vgmsistemas.dto.CondicionDirsc;
//import com.ar.vgmsistemas.dto.CondicionRenta;
//import com.ar.vgmsistemas.dto.CondicionesComerciales;
//import com.ar.vgmsistemas.dto.CondicionesLogisticas;
//import com.ar.vgmsistemas.dto.CondicionesPagos;
//import com.ar.vgmsistemas.dto.CuentaCorriente;
//import com.ar.vgmsistemas.dto.CuentaPropia;
//import com.ar.vgmsistemas.dto.DescuentoProveedor;
//import com.ar.vgmsistemas.dto.DescuentoProveedorGeneral;
//import com.ar.vgmsistemas.dto.Documento;
//import com.ar.vgmsistemas.dto.DocumentosLista;
//import com.ar.vgmsistemas.dto.Empresa;
//import com.ar.vgmsistemas.dto.EstadosMercaderia;
//import com.ar.vgmsistemas.dto.GrupoClientesDetalle;
//import com.ar.vgmsistemas.dto.Marca;
//import com.ar.vgmsistemas.dto.Negocio;
//import com.ar.vgmsistemas.dto.ObjetivoVenta;
//import com.ar.vgmsistemas.dto.RangoRentabilidad;
//import com.ar.vgmsistemas.dto.ReciboDetalle;
//import com.ar.vgmsistemas.dto.RequisitoPromocion;
//import com.ar.vgmsistemas.dto.Rubro;
//import com.ar.vgmsistemas.dto.RubroCliente;
//import com.ar.vgmsistemas.dto.SecuenciaRuteo;
//import com.ar.vgmsistemas.dto.Stock;
//import com.ar.vgmsistemas.dto.StockCombo;
//import com.ar.vgmsistemas.dto.Subrubro;
//import com.ar.vgmsistemas.dto.Sucursal;
//import com.ar.vgmsistemas.dto.TipoCliente;
//import com.ar.vgmsistemas.dto.TipoLetra;
//import com.ar.vgmsistemas.dto.Valores;
//import com.ar.vgmsistemas.dto.VendedorObjetivo;
//import com.ar.vgmsistemas.dto.VendedorObjetivoDetalle;
//import com.ar.vgmsistemas.dto.Venta;
//import com.ar.vgmsistemas.dto.VentaDetalle;
//import com.ar.vgmsistemas.dto.Zona;
import com.ar.vgmsistemas.utils.CodeResult;

@Component
public class CcinfoBo{

	@Value("${periodoDescargaventa}")
	private int periodoDescargaventa;
	@Value("${objetivosDeVenta}")
	private boolean objetivosDeVenta;
	
	
//	@Autowired
//	private INegocioDao negocioDao;
//	
//	@Autowired
//	private IRubroDao rubroDao;
//	
//	@Autowired
//	private ISubrubroDao subrubroDao;
//	
//	@Autowired
//	private IArticuloDao articuloDao;
//	
//	@Autowired
//	private IEstadosMercaderiaDao estadosMercaderiaDao;
//	
//	@Autowired
//	private IValorDao valorDao;
//	
//	@Autowired
//	private ICajaDao cajaDao;
//	
//	@Autowired
//	private IZonaDao zonaDao;
//	
//	@Autowired
//	private ITipoClienteDao tipoClienteDao;
	
//	@Autowired
//	private ITipoLetraDao tipoLetraDao;
//	
//	@Autowired
//	private ICuentaPropiaDao cuentaPropiaDao;
//	
//	@Autowired
//	private ICondicionesPagosDao condicionesPagosDao;
//	
//	@Autowired
//	private ICondicionesLogisticasDao condicionesLogisticasDao;
//	
//	@Autowired
//	private ICondicionesComercialesDao condicionesComercialesDao;
//	
//	@Autowired
//	private IDocumentoDao documentosDao;
//	
//	@Autowired
//	private IRubroClienteDao rubroClienteDao;
	
	/*@Autowired
	private IAccionesComDao accionesComDao;
	
	@Autowired
	private IAccionesComDetalleDao accionesComDetalleDao;
	
	@Autowired
	private IAccionesGruposDao accionesGruposDao;
		
	@Autowired
	private IArticuloPromocionDao articuloPromocionDao;
	
	@Autowired
	private IChequeDao chequeDao;
	
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IComercioLoginDao comercioLoginDao;
	
	
	@Autowired	
	private ICondicionDirscDao condicionDirscDao;
	
	@Autowired
	private ICondicionRentaDao condicionRentaDao;
	
	@Autowired
	private IDescuentoProveedorDao descuentoProveedorDao;
	
	@Autowired
	private IDescuentoProveedorGeneralDao descuentoProveedorGeneralDao;*/
	
//	@Autowired
//	private IDocumentosListaDao documentosListaDao;
	
	/*@Autowired
	private IGrupoClientesDetalleDao grupoClientesDetalleDao;
	
	@Autowired
	private IMarcaDao marcaDao;
	
	
	
	
	@Autowired
	private IObjetivoVentaDao objetivoVentaDao;
	
	@Autowired
	private IRangoRentabilidadDao rangoRentabilidadDao;
	
	
	
	@Autowired
	private IRequisitoPromocionDao requisitoPromocionDao;
	
	@Autowired
	private ISecuenciaRuteoDao secuenciaRuteoDao;
	
	
	
	@Autowired
	private IVentaDao ventaDao;
	
	@Autowired
	private IVentaDetalleDao ventaDetalleDao;
	
	@Autowired
	private IVendedorObjetivoDao vendedorObjetivoDao;
	
	@Autowired
	private IClienteVendedorDao clienteVendedorDao;
	
	@Autowired
	private IStockCombo stockComboDao;
	
	@Autowired
	private IStock stockDao;
	
	@Autowired
	private ISubrubroVendedor subrubroVendedorDao;
	
	@Autowired
	private ISucursalDao sucursalDao;
	
	@Autowired
	private ICuentaCorrienteDao cuentaCorrienteDao;
	
	@Autowired
	private IVendedorObjetivoDetalleDao vendedorObjetivoDetalleDao;
	
	@Autowired
	IRecursoHumanoDao iRecursoHumanoDao;*/

//	// BO's
//	@Autowired
//	private RubroBo rubroBo;
//	@Autowired
//	private SubrubroBo subrubroBo;
////	@Autowired
////	private ArticuloBo articuloBo;
	@Autowired
	private SincronizacionGeneralBo sincronizacionGeneralBo;
//	
//	/*@Autowired
//	private ClienteBo clienteBo;
//	@Autowired
//	private RecursoHumanoBo recursoHumanoBo;
//	@Autowired
//	private EmpresaBo empresaBo;
//	@Autowired
//	private ValidacionBo validacionBo;
//	@Autowired
//	private SecuenciaRuteoBo secuenciaRuteoBo;
//	@Autowired
//	private SincronizacionGeneralBo sincronizacionGeneralBo;
//	
//	
//	@Autowired
//	private DocumentosListaBo documentosListaBo;
//	@Autowired
//	private ObjetivoCoberturaBo objetivoCoberturaBo;*/
//
	static Logger logger = Logger.getLogger(SincronizacionBo.class);
//	public static final int CONEXION_EXITOSA = 0;
//	
//	
	public String iniciarSincronizacion(Integer idSucursal, Session metadata, String bd, SqliteConnectionConfig config) throws Exception {
		
		try {		
			bd = sincronizacionGeneralBo.iniciarSincronizacion(idSucursal, metadata, bd, config);
			logger.info("Iniciando el Proceso de Sincronizacion - Vendedor Nro "/*.concat(String.valueOf(idVendedor))*/);


			
			Map<String, Object> parameter = new HashMap<String, Object>();
			
			
			
		} catch (Exception e) {
			bd = null;
			System.out.print(e.toString());
			logger.error("Error al realizar la sincronizacion");
			throw new Exception("Problemas al Iniciar la Base de datos "
					+ e.toString() + e.getMessage());
		}	
	
		
		return sincronizacionGeneralBo.FinalSincronizacion(bd, idSucursal, config);
	}
	
//		
//	public int getPeriodoDescargaventa() {
//		return periodoDescargaventa;
//	}
//
//	public void setPeriodoDescargaventa(int periodoDescargaventa) {
//		this.periodoDescargaventa = periodoDescargaventa;
//	}
//
//	
//	public SubrubroBo getSubrubroBo() {
//		return subrubroBo;
//	}
//
//	public void setSubrubroBo(SubrubroBo subrubroBo) {
//		this.subrubroBo = subrubroBo;
//	}
//
//	public RubroBo getRubroBo() {
//		return rubroBo;
//	}
//
//	public void setRubroBo(RubroBo rubroBo) {
//		this.rubroBo = rubroBo;
//	}
//
//	public ArticuloBo getArticuloBo() {
//		return articuloBo;
//	}
//
//	public void setArticuloBo(ArticuloBo articuloBo) {
//		this.articuloBo = articuloBo;
//	}
//
//	
//	public IArticuloDao getArticuloDao() {
//		return articuloDao;
//	}
//
//	public void setArticuloDao(IArticuloDao articuloDao) {
//		this.articuloDao = articuloDao;
//	}
//
//	
//	public INegocioDao getNegocioDao() {
//		return negocioDao;
//	}
//
//	public void setNegocioDao(INegocioDao negocioDao) {
//		this.negocioDao = negocioDao;
//	}
//
//	
//	public IRubroDao getRubroDao() {
//		return rubroDao;
//	}
//
//	public void setRubroDao(IRubroDao rubroDao) {
//		this.rubroDao = rubroDao;
//	}
//
//	
//	public ISubrubroDao getSubrubroDao() {
//		return subrubroDao;
//	}
//
//	public void setSubrubroDao(ISubrubroDao subrubroDao) {
//		this.subrubroDao = subrubroDao;
//	}

	
	public boolean isObjetivosDeVenta() {
		return objetivosDeVenta;
	}

	public void setObjetivosDeVenta(boolean objetivosDeVenta) {
		this.objetivosDeVenta = objetivosDeVenta;
	}
}
