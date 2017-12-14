package tienda.dao;

import java.util.List;

import tienda.dto.Estado;

public interface EstadoDAO {

	public List<Estado> buscarTodos();

	public String guardar(Estado objeto);

	public String actualizar(Estado objeto);

	public String eliminar(String idobjeto);

	public Estado buscarEstadoPorId(String idobjeto);

	public Estado buscarEstadoPorNombreIdTabla(String nombreestado, String idtabla);

	public String retornaIdEstado();

	public List<Estado> buscarEstadosPorIdTabla(String idtabla);
}
