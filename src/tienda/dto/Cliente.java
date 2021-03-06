// default package
// Generated 02/10/2017 18:37:43 by Hibernate Tools 5.2.3.Final
package tienda.dto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
@SuppressWarnings("serial")
public class Cliente implements java.io.Serializable {

	private String idcliente;
	private String documento;
	private char tipodoc;
	private String nombres;
	private String apellidos;
	private String direccion;
	private Date fechareg;
	private Set<Facturacab> facturacabs = new HashSet<Facturacab>(0);

	public Cliente() {
	}

	public Cliente(String idcliente, String documento, char tipodoc, String nombres, String apellidos, String direccion,
			Date fechareg) {
		this.idcliente = idcliente;
		this.documento = documento;
		this.tipodoc = tipodoc;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.fechareg = fechareg;
	}

	public Cliente(String idcliente, String documento, char tipodoc, String nombres, String apellidos, String direccion,
			Date fechareg, Set<Facturacab> facturacabs) {
		this.idcliente = idcliente;
		this.documento = documento;
		this.tipodoc = tipodoc;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.fechareg = fechareg;
		this.facturacabs = facturacabs;
	}

	public String getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public char getTipodoc() {
		return this.tipodoc;
	}

	public void setTipodoc(char tipodoc) {
		this.tipodoc = tipodoc;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechareg() {
		return this.fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Set<Facturacab> getFacturacabs() {
		return this.facturacabs;
	}

	public void setFacturacabs(Set<Facturacab> facturacabs) {
		this.facturacabs = facturacabs;
	}

}
