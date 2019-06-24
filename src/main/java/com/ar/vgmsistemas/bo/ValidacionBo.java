package com.ar.vgmsistemas.bo;

import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ar.vgmsistemas.dao.IValidacionDao;

@Component
public class ValidacionBo {

	@Autowired
	IValidacionDao validacionDao;
	
	public void validarDestino(Metadata metadata) throws Exception{
		//Llamar a la dao y pedirle que valide
		validacionDao.validarDestino(metadata);	
	}

	public IValidacionDao getValidacionDao() {
		return validacionDao;
	}

	public void setValidacionDao(IValidacionDao validacionDao) {
		this.validacionDao = validacionDao;
	}
}
