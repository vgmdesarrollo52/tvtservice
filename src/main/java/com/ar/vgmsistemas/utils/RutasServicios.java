package com.ar.vgmsistemas.utils;

public class RutasServicios {
	public static final String BASE_WS = "/servicios";
	public static final String BASE_WS_ARTICULOSuLTRAWEB = "/ArticulosUltrawebWs";
	public static final String GET_ARTICULO_BY_ID = BASE_WS + "/ArticuloWs/getArticuloById";
	public static final String GET_ARTICULOS = BASE_WS + "/ArticuloWs/getArticulos";
	public static final String GET_ARTICULOS_BY_SEGMENTO = BASE_WS + "/ArticuloWs/getArticulosBySegmento";
	public static final String GET_ARTICULOS_BY_PARAMETER = BASE_WS + "/ArticuloWs/getArticulosByParameter";
	public static final String GET_STOCK = BASE_WS + "/ArticuloWs/getStock";
	public static final String INICIAR_SINCRONIZACION = BASE_WS + "/AdministracionWs/iniciarSincronizacion";
	public static final String ENVIAR_RECIBO = BASE_WS + "/ReciboWs/enviarRecibo";
	public static final String ENVIAR_NOPEDIDO = BASE_WS + "/NoPedidoWs/enviarNoPedido";
	public static final String UPDATE_CORRDENADAS = BASE_WS + "/ClienteWs/updateCoordenadas";
	public static final String VALIDAR_VENDEDOR = BASE_WS + "/VendedorWs/validarVendedor";
	public static final String ENVIAR_UBICACION_GEOGRAFICA = BASE_WS + "/UbicacionWs/enviarCoordenadas";
	public static final String RECOVERY_CC_BY_CLIENTE = BASE_WS + "/CuentaCorrienteWs/recoverybyCliente";
	public static final String GET_COTIZACION = BASE_WS + "/TipoMonedaWs/getCotizacion";
	public static final String ENVIAR_PEDIDO = BASE_WS + "/VentaWs/createPedido";
	public static final String ENVIAR_PEDIDOS = BASE_WS + "/VentaWs/createPedidos";
	public static final String ANULAR_PEDIDO = BASE_WS + "/VentaWs/anularVenta";
	//public static final String GET_ARTICULOS_LISTA = "/ArticulosListaWs/recoveryAllArticulosLista";
	public static final String GET_ARTICULOS_ULTRAWEB = BASE_WS + BASE_WS_ARTICULOSuLTRAWEB + "/recoveryAllArticulos";
	public static final String GET_STOCK_ULTRAWEB = BASE_WS + BASE_WS_ARTICULOSuLTRAWEB + "/recoveryStock";
	/*public static final String GET_SUCURSALES_ULTRAWEB = "/SucursalesUltrawebWs/recoveryAllSucursal";
	public static final String GET_CLIENTES_ULTRAWEB = "/ClientesUltrawebWs/recoveryAllCliente";
	public static final String GET_RUBROS_ULTRAWEB = "/ClientesUltrawebWs/recoveryAllRubro";
	public static final String GET_LISTAS_PRECIO_ULTRAWEB = "/ListaPrecioUltrawebWs/recoveryAllListaPrecio";*/
	public static final String LOGIN_ULTRAWEB = BASE_WS + "/ComercioLoginUltrawebWs/login";
	public static final String CREAR_VENTA = BASE_WS + "/VentaUltrawebWs/createVenta";
	public static final String UPDATE_HOJA_DETALLE = BASE_WS + "/RepartoWs/updateHojaDetalle";
	public static final String UPDATE_HOJA_DETALLE_CREDITO = BASE_WS + "/RepartoWs/updateHojaDetalleCredito";
	public static final String LISTADO_DEUDAS = BASE_WS + "/ClienteWs/getDeudas";
	public static final String CREAR_AUDITORIA_GPS = BASE_WS + "/AuditoriaGpsWs/createAuditoriaGps";
	public static final String CREAR_ENTREGA = BASE_WS + "/RendicionWs/createEntrega";
	public static final String CREAR_EGRESO = BASE_WS + "/RendicionWs/createEgreso";
	public static final String REGISTRAR_ERROR_MOVIL = BASE_WS + "/ErrorWs/registrarError";
	
	//---------------------------SERVICIOS CIBOULETTE-------------------------------------------
	public static final String REGISTRO_CLIENTE = BASE_WS + "/RegistroCliente";
	public static final String REGISTRO_DIRECCION = BASE_WS + "/RegistroDireccion";
	public static final String GET_DIRECCIONES = BASE_WS + "/getDirecciones";
	public static final String RECOVERY_ARTICULOS_POR_TIPO_LISTA = BASE_WS + "/RecoveryArticulosPorTipoLista";
	public static final String CREAR_PEDIDO_SEMANAL = BASE_WS + "/crearPedidoSemanal";
	public static final String CREAR_PEDIDO_DIARIO = BASE_WS + "/crearPedidoDiario";
	public static final String GET_SEGMENTOS = BASE_WS + "/getSegmentos";
	public static final String GET_STOCK_CLIENTE = BASE_WS + "/getStockCliente";
	public static final String RECUPERAR_CONTRASENA = BASE_WS + "/recuperarContrasena";
	
	//---------------------------SERVICIOS CCINFO-------------------------------------------
	public static final String CCINFO = "/ccinfo";
	public static final String INICIAR_CCINFO = CCINFO + "/iniciarCcinfo";
	public static final String GET_PROVINCIAS_CCINFO = CCINFO + "/recoveryAllProvincias";
	public static final String GET_LOCALIDADES_CCINFO = CCINFO + "/recoveryAllLocalidades";
	public static final String GET_PROVEEDORES_CCINFO = CCINFO + "/recoveryAllProveedores";
	public static final String GET_PLANCUENTA_CCINFO = CCINFO + "/recoveryAllPlanCuentas";
	public static final String GET_SUBRUBROS_CCINFO = CCINFO + "/recoveryAllSubRubros";
	public static final String GET_LINEAS_CCINFO = CCINFO + "/recoveryAllLineas";
	public static final String GET_ARTICULOS_CCINFO = CCINFO + "/recoveryAllArticulos";
	public static final String GET_CTACTE_CUIT_CCINFO = CCINFO + "/recoveryCtaCteCuit";
	public static final String GET_CTACTE_DNI_CCINFO = CCINFO + "/recoveryCtaCteDni";
	public static final String GET_STOCK_CCINFO = CCINFO + "/getStockId";
}