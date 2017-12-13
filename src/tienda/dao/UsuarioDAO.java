package tienda.dao;

import java.util.List;

import tienda.dto.Usuario;

public interface UsuarioDAO {

	public List<Usuario> buscarTodos();

	public Usuario retornaUsuarioBuscadoPorId(String idusuario);
	
	public Usuario retornaUsuario(Usuario usuario);

	public String guardarUsuario(Usuario usuario);

	public String actualizarUsuario(Usuario usuario);
	
	public String eliminarUsuario(String idusuario);

	public String retornaIdUsuario();

	public String comprobarExistenciaUsuario(String alias);

	public String retornaNombresCompletos(String alias);

	public String retornaCedula(String alias);

	public String buscarPorAliasUsuario(String alias, String clave);
}
