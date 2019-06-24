/**
 * 
 */
package com.ar.vgmsistemas.dto;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author pablo
 *
 */
/*@XmlRootElement(name="pkDocumento")
@XmlAccessorType(XmlAccessType.NONE)*/
@Embeddable
public class PkDocumento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5966053595981650798L;
	//@XmlElement(name="idDocumento", required=false)
	@Column(name = "id_doc")
	private String idDocumento;
	//@XmlElement(name="idLetra", required=false)
	@Column(name = "id_letra")
	private String idLetra;
	//@XmlElement(name="puntoVenta", required=false)
	@Column(name = "id_ptovta")
	private long puntoVenta;
	/**
	 * @return the idDocumento
	 */
	public String getIdDocumento() {
		return this.idDocumento;
	}
	/**
	 * @param idDocumento the idDocumento to set
	 */
	public void setIdDocumento(final String idDocumento) {
		this.idDocumento = idDocumento;
	}
	/**
	 * @return the idLetra
	 */
	public String getIdLetra() {
		return this.idLetra;
	}
	/**
	 * @param idLetra the idLetra to set
	 */
	public void setIdLetra(final String idLetra) {
		this.idLetra = idLetra;
	}
	/**
	 * @return the puntoVenta
	 */
	public long getPuntoVenta() {
		return this.puntoVenta;
	}
	/**
	 * @param puntoVenta the puntoVenta to set
	 */
	public void setPuntoVenta(long puntoVenta) {
		this.puntoVenta = puntoVenta;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.idDocumento == null) ? 0 : this.idDocumento
						.hashCode());
		result = prime * result
				+ ((this.idLetra == null) ? 0 : this.idLetra.hashCode());
		result = prime * result
				+ (int) (this.puntoVenta ^ (this.puntoVenta >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PkDocumento))
			return false;
		PkDocumento other = (PkDocumento) obj;
		if (this.idDocumento == null) {
			if (other.idDocumento != null)
				return false;
		} else if (!this.idDocumento.equals(other.idDocumento))
			return false;
		if (this.idLetra == null) {
			if (other.idLetra != null)
				return false;
		} else if (!this.idLetra.equals(other.idLetra))
			return false;
		if (this.puntoVenta != other.puntoVenta)
			return false;
		return true;
	}

}
