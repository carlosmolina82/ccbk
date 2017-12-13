package tienda.dao;

import java.util.List;

import tienda.dto.Rol;

public interface RolDAO {

	public List<Rol> buscarTodos();

	public String guardar(Rol objeto);

	public String actualizar(Rol objeto);

	public String eliminar(String idobjeto);

	public Rol buscarRolPorId(String idobjeto);
}
