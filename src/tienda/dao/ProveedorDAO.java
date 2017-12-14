package tienda.dao;

import java.util.List;

import tienda.dto.Proveedor;

public interface ProveedorDAO {

	public List<Proveedor> buscarTodos();

	public String guardarProveedor(Proveedor prov);

	public String actualizarProveedor(Proveedor prov);

	public String eliminarProveedor(String idprov);

	public Proveedor buscarProveedorPorId(String idprov);
	
	public String retornaIdProveedor();
}
