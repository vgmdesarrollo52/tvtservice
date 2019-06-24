package com.ar.vgmsistemas.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ar.vgmsistemas.dao.IErrorMovilDao;
import com.ar.vgmsistemas.dto.ErrorMovil;
import com.ar.vgmsistemas.utils.RutasServicios;
import com.fasterxml.jackson.annotation.JsonView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class ErrorController {
	
	@Autowired
	IErrorMovilDao errorMovilDao;
	
	private static int RESULT_OK = 0;
	private static int RESULT_ERROR = 1;
	
	@Transactional
	@JsonView(Integer.class)
	@RequestMapping(value = RutasServicios.REGISTRAR_ERROR_MOVIL, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Integer registrarErrorMovil(String errorString){
		JSONObject jsonObject = new JSONObject(errorString);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
		ErrorMovil errorMovil = gson.fromJson(jsonObject.toString(), ErrorMovil.class);
		errorMovil.setId(null);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Logger.getLogger("errorMovil").error(sdf.format(errorMovil.getFechaRegistro()) + " " + errorMovil.getIdVendedor() + " " + errorMovil.getVersion());
		Logger.getLogger("errorMovil").error(errorMovil.getError());
		int result = RESULT_ERROR;
		try{
			if (!errorMovilDao.existsError(errorMovil.getIdMovil())){
				errorMovilDao.persist(errorMovil);
			}
			result = RESULT_OK;
		} catch (Exception e){
			e.printStackTrace();
		}
		return result;		
	}
}


