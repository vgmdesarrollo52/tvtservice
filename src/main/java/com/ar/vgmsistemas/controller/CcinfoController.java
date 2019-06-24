package com.ar.vgmsistemas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ar.vgmsistemas.bo.CcinfoBo;
import com.ar.vgmsistemas.bo.CcinfoFactoryBo;

import com.ar.vgmsistemas.bo.SincronizacionBo;

import com.ar.vgmsistemas.bo.SincronizacionFactoryBo;

import com.ar.vgmsistemas.config.SqliteConnectionConfig;
//import com.ar.vgmsistemas.dao.DaoFactoryContextHolder;
import com.ar.vgmsistemas.utils.CodeResult;
import com.ar.vgmsistemas.utils.RutasServicios;

@RestController
public class CcinfoController {
	
	@Autowired
	CcinfoBo ccinfoBo;	
	
	 @Value("#{'${vendedoresHabilitados}'.split(',')}") 
	 private List<Integer> vendedores;
	
	private static final String KEY_ID_DOCUMENTO = "idDocumento";
	private static final String KEY_PUNTO_VENTA = "puntoVenta";
	private static final String KEY_ID = "id.";

	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	@RequestMapping(value = RutasServicios.INICIAR_CCINFO, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String iniciarCcinfo(@RequestParam Integer idSucursal) throws Exception {
		
		JSONObject pkJson = new JSONObject(idSucursal);
		
		return CcinfoFactoryBo.getCcinfoFactory(idSucursal);							
			
			//TODO mirar si se puede cambiar
			/*SincronizacionBo sincronizacionBo = (SincronizacionBo) this.beanFactory.getBean("sincronizacionBo");
			return sincronizacionBo.iniciarSincronizacion(idVendedor);
			*/
		
	}

	
}
