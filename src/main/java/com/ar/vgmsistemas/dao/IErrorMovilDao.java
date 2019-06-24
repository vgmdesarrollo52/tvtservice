package com.ar.vgmsistemas.dao;

import com.ar.vgmsistemas.dto.ErrorMovil;

public interface IErrorMovilDao extends IGenericDao<ErrorMovil, Long>{
	
	boolean existsError(String idMovil) throws Exception;
	
}
