package tienda.dao;

import java.util.List;

import tienda.dto.Tabla;

public interface TablaDAO {

	public List<Tabla> buscarTodos();

	public String guardarTabla(Tabla tabla);

	public String actualizarTabla(Tabla tabla);

	public String eliminarTabla(String idtabla);

	public Tabla buscarTablaPorId(String idtabla);
}
