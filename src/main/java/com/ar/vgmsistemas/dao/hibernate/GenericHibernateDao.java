/**
 * 
 */
package com.ar.vgmsistemas.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.proxy.HibernateProxy;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author eMa
 *
 */
	public abstract class GenericHibernateDao<T, ID extends Serializable> implements
		com.ar.vgmsistemas.dao.IGenericDao<T, ID> {

	/*@Autowired
	private SessionFactory sessionFactory;*/
	
	@PersistenceContext
	protected EntityManager em;
	
	public void setEntityManager(EntityManager entityManager) {
	    this.em = entityManager;
	}
	public EntityManager getEntityManager() {
	   	return em;
	}
	
	private Class<T> _persistentClass;
	
    @SuppressWarnings("unchecked")
    public GenericHibernateDao() {
    	ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
        _persistentClass = (Class<T>) thisType.getActualTypeArguments()[0];
    }

    public Class<T> getPersistentClass() {
        return _persistentClass;
    }
    
    /*protected Session getSession() {
    	try {
    		return sessionFactory.getCurrentSession();
    	} catch (HibernateException e) {
    		return sessionFactory.openSession();
    	}
		//return this.sessionFactory.getCurrentSession();
	}*/
	
    /*@Override
    public Serializable create(T entity) throws Exception {
    	//return getSession().save(entity);
    	return 0;
    }*/
    
    @Override
    public void persist(T entity){
    	getEntityManager().persist(entity);
    }
    
    @Override
    public void merge(T entity){
    	getEntityManager().merge(entity);
    }
    
    @Override
    public void delete(T entity){
    	getEntityManager().remove(entity);
    }
    
    @Override
    public void flush(){
    	getEntityManager().flush();
    }
    
    @Override
	public T recoveryById(ID id) {
		//return (T) getSession().get(getPersistentClass(), id);
    	return (T) getEntityManager().find(getPersistentClass(), id);
	}
    
    @Override
	public T recoveryById(ID id, boolean lock) {
    	return null;
    	/*getSession().beginTransaction().
		return (T) getSession().get(getPersistentClass(), id);*/
	}
    
    public void addColumn(String columnNameAndType)throws Exception{
		String sql = "ALTER TABLE " + getPersistentClass().getAnnotation(Table.class).name() + " ADD " + columnNameAndType;
		getEntityManager().createNativeQuery(sql).executeUpdate();
	}
    
	@Override
    public List<T> recoveryAll() {
    	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
    	CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
    	Root<T> root = criteria.from(getPersistentClass());
    	criteria.select(root);
    	TypedQuery<T> findAll = getEntityManager().createQuery(criteria);
    	//getSession().sessionWithOptions().autoClose(false);
        return findAll.getResultList();
    }
	
	@Override
	public List<T> recoveryByParameter(Map<String, Object> parameter) throws Exception{
	CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
	   	CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
	   	Root<T> root = criteria.from(getPersistentClass());
	   	Predicate predicate = builder.conjunction();
	       for (Map.Entry<String, Object> entry : parameter.entrySet()) {
	       	//JPA Criteria building does not define support for path expressions.
	       	//O sea que si viene un parametro como "id.idArticulos" hay que tratarlo de manera distinta, ya que no se admite hacer "id.idArticulos" = valor
	       	String key = entry.getKey();
	       	List<String> elements = new ArrayList<String>();
	       	Predicate newPredicate;
	       	
	       	String element;
	       	
	       	while (key.indexOf('.') > 0){
	       	element = key.substring(0, key.indexOf('.'));
	       	elements.add(element);
	       	key = key.substring(key.indexOf('.')+1);
	       	} 
	       	elements.add(key);
	       	
	       	Path<String> path = null;
	       	for (String e: elements){
	       	if (path == null)
	       	path = root.get(e);
	       	else{
	       	path = path.get(e);
	       	}
	       	}
	       	
	       	newPredicate = builder.equal( path, entry.getValue() );	       	
	       	predicate = builder.and(predicate, newPredicate);
	       }
	       criteria.where(predicate);
	       TypedQuery<T> findByParameter = getEntityManager().createQuery(criteria);
	       return findByParameter.getResultList();
	}
	
	@Override
	public List<T> recoveryByParameter(Map<String, Object> parameter, boolean lock) throws Exception{
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
    	CriteriaQuery<T> criteria = builder.createQuery(getPersistentClass());
    	Root<T> root = criteria.from(getPersistentClass());
    	Predicate predicate = builder.conjunction();
        for (Map.Entry<String, Object> entry : parameter.entrySet()) {
        	//JPA Criteria building does not define support for path expressions.
        	//O sea que si viene un parametro como "id.idArticulos" hay que tratarlo de manera distinta, ya que no se admite hacer "id.idArticulos" = valor
        	String key = entry.getKey();
        	Predicate newPredicate;
        	if (key.indexOf('.') > 0){
        		String parent = key.substring(0, key.indexOf('.'));
        		String child = key.substring(key.indexOf('.')+1);
        		newPredicate = builder.equal( root.get( parent ).get( child ), entry.getValue() );
        	} else {
        		newPredicate = builder.equal(root.get(entry.getKey()), entry.getValue());
        		
        	}
        	predicate = builder.and(predicate, newPredicate);
        }
        criteria.where(predicate);
        TypedQuery<T> findByParameter = getEntityManager().createQuery(criteria);
        return findByParameter.getResultList();
	}
	
		
	@SuppressWarnings("unchecked")
	@Override
	public void createForLote(List<T> list, Session session) throws Exception {
        try{
	       	Transaction tx = session.beginTransaction();
	    	int size = 50;
	    	if(list.size() < size){
	    		size = list.size();
	    	}
	    	for ( int i=0; i<list.size(); i++ ) {
	    	    T entity = list.get(i);
	    	    if (entity instanceof HibernateProxy) {
	    	    	entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
	                    .getImplementation();
	    	    }
	    	    session.save(entity);
	    	    if ( i % size == 0 ) { //20, same as the JDBC batch size
	    	        //flush a batch of inserts and release memory:
	    	    	session.flush();
	    	    	session.clear();
	    	    }
	    	}
	    	   
	    	tx.commit();
        }catch(Exception e){
        	session.disconnect();
        	session.close();
        	e.printStackTrace();
        	throw new Exception("Error al grabar en sqlite");
        } 
    }
	
	public static Object initializeProxt(Object object){
		Object objeto = object;
		if (object instanceof HibernateProxy) {
			objeto = ((HibernateProxy) object).getHibernateLazyInitializer()
            .getImplementation();
    	}
		return objeto;
	}
	
	public void deleteAll(Session session) throws Exception{
		try{
	       	Transaction tx = session.beginTransaction();
	       	String sql = "DELETE FROM " + getPersistentClass().getName() + "";
	       	Query query = session.createQuery(sql);
	       	query.executeUpdate();
	       	tx.commit();
		}catch(Exception e){
        	session.disconnect();
        	session.close();
        	e.printStackTrace();
        	throw new Exception("Error al borrar en sqlite");
        } 		
	}
}
