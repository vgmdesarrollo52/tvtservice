/**
 * 
 */
package com.ar.vgmsistemas.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * @author eMa
 * 
 */
public interface IGenericDao<T, ID extends Serializable> {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	//Serializable create(T entity) throws Exception;
	
	//void createHibernate(T entity) throws Exception;

	void createForLote(List<T> list, Session metadata) throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T recoveryById(ID id) throws Exception;

	/**
	 * 
	 * @param id
	 * @param lock
	 * @return
	 */
	T recoveryById(ID id, boolean lock);

	/**
	 * 
	 * @return
	 */
	List<T> recoveryAll() throws Exception;

	/**
	 * 
	 * @param exampleInstance
	 * @param excludeProperty
	 * @return
	 */
	//List<T> recoveryByExample(T exampleInstance, List<String> excludeProperty);

	/**
	 * 
	 * @param parameter
	 * @return
	 * @throws Exception
	 */
	List<T> recoveryByParameter(Map<String, Object> parameter) throws Exception;
	List<T> recoveryByParameter(Map<String, Object> parameter, boolean lock) throws Exception;
	void deleteAll(Session session) throws Exception;
	void addColumn(String columnNameAndType)throws Exception;
	
	void persist(T entity);
	void merge(T entity);
	void delete(T entity);
	void flush();

	/**
	 * 
	 * @param entity
	 * @param id
	 * @return
	 */
	//void update(T entity) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 */
	///void deleteByID(ID id) throws Exception;

	/**
	 * 
	 * @param entity
	 * @throws Exception
	 */
	//void delete(T entity) throws Exception;

	//void createForLote(List<T> list) throws Exception;

}
