package com.ar.vgmsistemas.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Provincias")
@XmlAccessorType(XmlAccessType.FIELD)
public class Provincias {

	@XmlElement(name="Provincia")
	private List<Provincia> provincias;

	/**
	 * @return the provincias
	 */
	public List<Provincia> getProvincias() {
		if(provincias==null){
			provincias = new ArrayList<Provincia>();
		}
		return provincias;
	}

	/**
	 * @param provincias the provincias to set
	 */
	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}
	
}
