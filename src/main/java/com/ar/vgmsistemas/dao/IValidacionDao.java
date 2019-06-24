package com.ar.vgmsistemas.dao;

import org.hibernate.boot.Metadata;

public interface IValidacionDao {

	public void validarDestino(Metadata metadata)throws Exception;
}
