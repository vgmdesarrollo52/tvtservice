package com.ar.vgmsistemas.utils;

public class Matematica {
	
	public static float Round(float numero,int digitos){
		int cifras =(int) Math.pow(10,digitos);
		numero = numero * cifras;
		long tmp = Math.round(numero);
	    return (float) tmp/cifras;
	}
	
	public static float restarPorcentaje(float numero, float porciento){
		return ((100 - porciento) / 100) * numero;
	}

}
