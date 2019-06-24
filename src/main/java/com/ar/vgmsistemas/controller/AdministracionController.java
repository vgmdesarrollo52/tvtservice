package com.ar.vgmsistemas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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

//import com.ar.vgmsistemas.bo.DocumentoBo;
//import com.ar.vgmsistemas.bo.RecursoHumanoBo;
//import com.ar.vgmsistemas.bo.SincronizacionBo;
//import com.ar.vgmsistemas.bo.SincronizacionCobradorBo;
//import com.ar.vgmsistemas.bo.SincronizacionFactoryBo;
//import com.ar.vgmsistemas.bo.SincronizacionRepartidorBo;
//import com.ar.vgmsistemas.bo.SincronizacionVendedorHaciendaBo;
//import com.ar.vgmsistemas.dao.DaoFactoryContextHolder;
//import com.ar.vgmsistemas.dto.Documento;
import com.ar.vgmsistemas.utils.CodeResult;
import com.ar.vgmsistemas.utils.RutasServicios;

@RestController
public class AdministracionController {
	
//	@Autowired
//	private DocumentoBo documentoBo;
//	@Autowired
//	private RecursoHumanoBo recursoHumanoBo;	
//	@Autowired
//	SincronizacionRepartidorBo sincronizacionRepartidorBo;
//	@Autowired
//	SincronizacionVendedorHaciendaBo sincronizacionVendedorHaciendaBo;
//	@Autowired
//	SincronizacionCobradorBo sincronizacionCobradorBo;
//	@Autowired
//	SincronizacionBo sincronizacionBo;	
	
	 @Value("#{'${vendedoresHabilitados}'.split(',')}") 
	 private List<Integer> vendedores;
	
	private static final String KEY_ID_DOCUMENTO = "idDocumento";
	private static final String KEY_PUNTO_VENTA = "puntoVenta";
	private static final String KEY_ID = "id.";

//	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
//	@RequestMapping(value = RutasServicios.INICIAR_SINCRONIZACION, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody String iniciarSincronizacion(@RequestParam String pkDocumento, @RequestParam Integer idVendedor) throws Exception {
//		
//		JSONObject pkJson = new JSONObject(pkDocumento);
//		
//		long puntoVenta = pkJson.getLong(KEY_PUNTO_VENTA);
//		String idDoc = pkJson.getString(KEY_ID_DOCUMENTO);
//				
//		boolean isValidVendedor = recursoHumanoBo.isActivo(Long.parseLong(idVendedor+""));
//		boolean isVendedorHabilitado = vendedores.contains(idVendedor);
//		if (isValidVendedor && isVendedorHabilitado){
//			
//			Map<String, Object> parameter = new HashMap<String, Object>();
//			parameter.clear();
//			parameter.put(KEY_ID+KEY_ID_DOCUMENTO, idDoc);
//			parameter.put(KEY_ID+KEY_PUNTO_VENTA, puntoVenta);
//			//Documento documento;
//			try{
//				documento = documentoBo.recoveryByParameter(parameter).get(0);
//			}catch(Exception e){
//				Logger.getLogger(SincronizacionBo.class).error("No se pudo recuperar el documento configurado en el movil. IdVendedor: "+idVendedor);
//				throw new Exception("No se pudo recuperar el documento configurado en el movil. Excepcion: " + e.getMessage() + " " + e.getCause());
//			}
//			
//			return SincronizacionFactoryBo.getSincronizacionFactory(idVendedor, documento);						
//			
//			//TODO mirar si se puede cambiar
//			/*SincronizacionBo sincronizacionBo = (SincronizacionBo) this.beanFactory.getBean("sincronizacionBo");
//			return sincronizacionBo.iniciarSincronizacion(idVendedor);
//			*/
//		} else {
//			if (!isValidVendedor){
//				Logger.getLogger(SincronizacionBo.class).error("Vendedor id: "+ idVendedor +" no valido");
//			}
//			if(!isVendedorHabilitado){
//				Logger.getLogger(SincronizacionBo.class).error("Vendedor id: "+ idVendedor +" no habilitado en application.properties");
//			}
//			return CodeResult.RESULT_VENDEDOR_INVALID_S;
//		}
//	}

//	public DocumentoBo getDocumentoBo() {
//		return documentoBo;
//	}
//
//	public void setDocumentoBo(DocumentoBo documentoBo) {
//		this.documentoBo = documentoBo;
//	}
//
//	public RecursoHumanoBo getRecursoHumanoBo() {
//		return recursoHumanoBo;  
//	}
//
//	public void setRecursoHumanoBo(RecursoHumanoBo recursoHumanoBo) {
//		this.recursoHumanoBo = recursoHumanoBo;
//	}
}
