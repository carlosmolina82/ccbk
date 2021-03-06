// default package
// Generated 02/10/2017 18:37:43 by Hibernate Tools 5.2.3.Final
package tienda.dto;

import java.util.Date;

/**
 * Usuario generated by hbm2java
 */
@SuppressWarnings("serial")
public class Usuario implements java.io.Serializable {

	private String idusuario;
	private Estado estado;
	private Rol rol;
	private String cedulau;
	private String nombresu;
	private String apellidosu;
	private String alias;
	private String clave;
	private Date fecharegu;
	private boolean selected;

	public Usuario() {
	}

	public Usuario(String idusuario, Estado estado, Rol rol, String cedulau, String nombresu, String apellidosu,
			String alias, String clave, Date fecharegu) {
		this.idusuario = idusuario;
		this.estado = estado;
		this.rol = rol;
		this.cedulau = cedulau;
		this.nombresu = nombresu;
		this.apellidosu = apellidosu;
		this.alias = alias;
		this.clave = clave;
		this.fecharegu = fecharegu;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getCedulau() {
		return this.cedulau;
	}

	public void setCedulau(String cedulau) {
		this.cedulau = cedulau;
	}

	public String getNombresu() {
		return this.nombresu;
	}

	public void setNombresu(String nombresu) {
		this.nombresu = nombresu;
	}

	public String getApellidosu() {
		return this.apellidosu;
	}

	public void setApellidosu(String apellidosu) {
		this.apellidosu = apellidosu;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFecharegu() {
		return this.fecharegu;
	}

	public void setFecharegu(Date fecharegu) {
		this.fecharegu = fecharegu;
	}

	public String getNombresCompletos() {
		return nombresu + " " + apellidosu;
	}
}
