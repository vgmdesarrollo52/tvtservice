package com.ar.vgmsistemas.dao.hibernate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.ar.vgmsistemas.dao.IErrorMovilDao;
import com.ar.vgmsistemas.dto.ErrorMovil;


@Repository
public class ErrorMovilHibernateDao extends GenericHibernateDao<ErrorMovil, Long> implements IErrorMovilDao{
	
	public boolean existsError(String idMovil) throws Exception {
		String sqlExistsError = "SELECT 1"
				+ "	FROM error_movil"
				+ "	WHERE id_movil = :idMovil";
		Query query = getEntityManager().createNativeQuery(sqlExistsError);
		query.setParameter("idMovil", idMovil);
		
		boolean exists = (query.getResultList().size() > 0);
		return exists;
	}
	
}
