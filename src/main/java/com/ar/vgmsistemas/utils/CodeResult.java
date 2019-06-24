package com.ar.vgmsistemas.utils;

public class CodeResult {
	public static final int RESULT_OK = 0;
	public static final int RESULT_ERROR = 1;
	public static final int RESULT_MONTO_DISPONIBLE_CERO_RECIBO_BO = 2;	
	public static final int RESULT_NO_SE_ENCONTRO_UN_COMPROBANTE_EN_CTACTE_RECIBO_BO = 3;
	public static final int RESULT_ALGUN_COMPROBANTE_TIENE_SALDO_CERO = 4;
	public static final int RESULT_CHEQUE_YA_EXISTE = 5;
	public static final int RESULT_RETENCION_YA_EXISTE = 6;
	public static final int RESULT_VENDEDOR_INVALID = 101;
	public static final int RESULT_PEDIDOS_NO_AUTORIZADO = 102;
	public static final int RESULT_RECIBO_YA_IMPUTADO = 103; //lo dejo para posterior uso.
	public static final int RESULT_PEDIDO_EXISTENTE = 7; // lo dejo para posterior uso.
	public static final int RESULT_LISTA_PRECIOS_EXPIRADA = 8;
	public static final String ERROR_SINCRONIZACION = "errorSincronizacion";
	public static final String RESULT_VENDEDOR_INVALID_S = "vendedorNoValido";
}
