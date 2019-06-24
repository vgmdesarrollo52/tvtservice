package com.ar.vgmsistemas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversion {
	
	public static String convertDateToString(Date fechaVenta){
		String result = "";
		if(fechaVenta!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			result = sdf.format(fechaVenta);
		}
		return result;
	}
}
