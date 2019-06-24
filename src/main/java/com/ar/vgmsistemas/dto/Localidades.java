package com.ar.vgmsistemas.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Localidades")
@XmlAccessorType(XmlAccessType.FIELD)
public class Localidades {
	
	@XmlElement(name="Localidad")
	private List<Localidad> localidades;

	/**
	 * @return the localidades
	 */
	public List<Localidad> getLocalidades() {
		if(localidades == null){
			localidades = new ArrayList<Localidad>();
		}
		return localidades;
	}

	/**
	 * @param localidades the localidades to set
	 */
	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}

}
