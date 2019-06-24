package com.ar.vgmsistemas.config;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SqliteConnectionConfig {

	private SessionFactory session;
	private StandardServiceRegistry standardServiceRegistry;
	public synchronized StandardServiceRegistry getConecction(String parameter){
		Map<String, String> conf = new HashMap<String, String>();	    
	    conf.put("hibernate.dialect", "com.ar.vgmsistemas.dao.hibernate.dialect.SQLiteDialect");
	    conf.put("hibernate.connection.driver_class", "org.sqlite.JDBC");
	    conf.put("hibernate.connection.url", "jdbc:sqlite:" + AppConfig.sqliteUrl + "ccinfo-" + parameter + ".sqlite");
	    conf.put("hibernate.connection.username", "");
	    conf.put("hibernate.connection.password", "");
	    conf.put("hibernate.jdbc.batch_size","1000");
	    conf.put("hibernate.show_sql","true");
	    standardServiceRegistry = new StandardServiceRegistryBuilder()
	    		.applySettings(conf)
	            .build();
	    return standardServiceRegistry ;
	}
	public synchronized Session getSession(){
		Metadata metadata = new MetadataSources( getConecction("empty") )
        		.addResource("com/ar/vgmsistemas/dto/mapping/Localidad.hbm.xml")
        		.addResource("com/ar/vgmsistemas/dto/mapping/Provincia.hbm.xml")
        .getMetadataBuilder()
        .build();
		
		session = metadata.getSessionFactoryBuilder().build();
        return session.openSession();
	}
	
	public synchronized Session getSession(long idVendedor){
		Metadata metadata = new MetadataSources( getConecction(idVendedor+"") )
        		//.addResource("com/ar/vgmsistemas/dto/mapping/DocumentosTipo.hbm.xml")
        .getMetadataBuilder()
        .build();
		session = metadata.getSessionFactoryBuilder().build();
        return session.openSession();
	}
	
	public void close(){
		if(session!= null && session.isOpen())
			session.close();
		if(standardServiceRegistry!= null)
			StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
	}
}
