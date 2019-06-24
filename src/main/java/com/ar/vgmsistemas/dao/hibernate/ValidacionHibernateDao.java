package com.ar.vgmsistemas.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.springframework.stereotype.Repository;

import com.ar.vgmsistemas.dao.IValidacionDao;

@Repository
public class ValidacionHibernateDao implements IValidacionDao{

	@Override
	public void validarDestino(Metadata metadata) throws Exception {
		Session session = metadata.getSessionFactoryBuilder().build().openSession();
       	Transaction tx = session.beginTransaction();
		
		//Iterar sobre las queries de la DAO y mientras haya queries ejecutarlas
		//No se como hacer
		
		//Ejecuto los SQL	
		List<String> validaciones = new ArrayList<String>();
		
		String validacion1 = "UPDATE VENTAS_DETALLE "
				+ "SET ID_LISTA = ( "
				+ "SELECT E.ID_LISTA_DEFECTO "
				+ "FROM EMPRESAS E	) "
				+ "WHERE ID_LISTA IS NULL";
		validaciones.add(validacion1);
		
		String validacion2 = "UPDATE COMERCIO "
				+ "SET id_condvta = ( "
				+ "SELECT case when min(id_condvta) is null then "
				+ "("
				+ "SELECT MIN(id_condvta) "
				+ "FROM condvta "
				+ "WHERE condvta.sn_controlfiado = 'N' "
				+ ") else min(id_condvta) end "
				+ "FROM condvta "
				+ "WHERE condvta.sn_controlfiado = 'N' "
				+ "and exists ( "
				+ "select * from condvta cv where id_condvta =condvta.id_condvta "
				+ "and de_convta like '%cont%' ) "
				+ ") "
				+ "WHERE COMERCIO.id_condvta NOT IN "
				+ "(SELECT CONDVTA.id_condvta "
				+ "FROM CONDVTA)";
		validaciones.add(validacion2);
		
		String validacion3 = "UPDATE COMERCIO "
				+ "SET ID_POSTAL = ( "
				+ "SELECT EMPRESAS.ID_POSTAL_DEFECTO "
				+ "FROM EMPRESAS "
				+ ") "
				+ "WHERE COMERCIO.ID_POSTAL IS NULL";
		validaciones.add(validacion3);
		
		String validacion4 = "UPDATE COMERCIO "
				+ "SET pr_limitedisponibilidad = 0 "
				+ "WHERE COMERCIO.pr_limitedisponibilidad IS NULL";
		validaciones.add(validacion4);
		
		String validacion5 = "UPDATE LISTASPRECIOS "
				+ "SET id_lista_base = id_lista "
				+ "WHERE id_lista_base IS NULL and ti_lista NOT IN (1)";
		validaciones.add(validacion5);
		
		String validacion6 = "UPDATE COMERCIO "
				+ "SET ID_LISTA = ( "
				+ "SELECT EMPRESAS.ID_LISTA_DEFECTO "
				+ "FROM EMPRESAS "
				+ ") "
				+ "WHERE COMERCIO.ID_LISTA IS NULL";
		validaciones.add(validacion6);
		
		String validacion7 = "UPDATE LISTASPRECIOS "
				+ "SET ID_LISTA_BASE = ( "
				+ "SELECT MIN(L.ID_LISTA) "
				+ "FROM LISTASPRECIOS L "
				+ "WHERE L.TI_LISTA = 3 "
				+ ") "
				+ "WHERE TI_LISTA = 1 AND ID_LISTA_BASE IS NULL";
		validaciones.add(validacion7);
		
		String validacion8 = "UPDATE VENTAS_DETALLE "
				+ "SET SN_ANULO = 'N' "
				+ "WHERE SN_ANULO IS NULL";
		validaciones.add(validacion8);
		
		System.out.println();
		System.out.println("VALIDACION BD SQLITE");
		System.out.println();
		Iterator<String> iterator = validaciones.iterator();
		String queryString;
		int i = 0;
		while(iterator.hasNext()) {
			queryString = iterator.next();
			i++;
			System.out.println("Ejecutando validacion "+i);
			Query query = session.createNativeQuery(queryString);
			query.executeUpdate();
			System.out.println("Validacion "+i+" exitosa");
			
		}
		session.flush();
    	session.clear();
    	tx.commit();
    	session.close();
		System.out.println("Validacion Finalizada de la BD SQLITE");
	}

}
