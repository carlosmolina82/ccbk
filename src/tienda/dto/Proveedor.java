// default package
// Generated 02/10/2017 18:37:43 by Hibernate Tools 5.2.3.Final
package tienda.dto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Proveedor generated by hbm2java
 */
@SuppressWarnings("serial")
public class Proveedor implements java.io.Serializable {

	private String idproveedor;
	private String documento;
	private char tipodoc;
	private String razonsocial;
	private String direccion;
	private String telefono;
	private String celular;
	private String descripcion;
	private Date fechareg;
	private Set<Producto> productos = new HashSet<Producto>(0);

	public Proveedor() {
	}

	public Proveedor(String idproveedor, String documento, char tipodoc, String razonsocial, String direccion,
			String descripcion, Date fechareg) {
		this.idproveedor = idproveedor;
		this.documento = documento;
		this.tipodoc = tipodoc;
		this.razonsocial = razonsocial;
		this.direccion = direccion;
		this.descripcion = descripcion;
		this.fechareg = fechareg;
	}

	public Proveedor(String idproveedor, String documento, char tipodoc, String razonsocial, String direccion,
			String telefono, String celular, String descripcion, Date fechareg, Set<Producto> productos) {
		this.idproveedor = idproveedor;
		this.documento = documento;
		this.tipodoc = tipodoc;
		this.razonsocial = razonsocial;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.descripcion = descripcion;
		this.fechareg = fechareg;
		this.productos = productos;
	}

	public String getIdproveedor() {
		return this.idproveedor;
	}

	public void setIdproveedor(String idproveedor) {
		this.idproveedor = idproveedor;
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

	public String getRazonsocial() {
		return this.razonsocial;
	}

	public void setRazonsocial(String razonsocial) {
		this.razonsocial = razonsocial;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechareg() {
		return this.fechareg;
	}

	public void setFechareg(Date fechareg) {
		this.fechareg = fechareg;
	}

	public Set<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

}
