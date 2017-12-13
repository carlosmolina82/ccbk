package tienda.logica;

import java.util.Date;
import java.util.List;

import tienda.dao.EstadoDAOImpl;
import tienda.dao.RolDAOImpl;
import tienda.dao.TablaDAOImpl;
import tienda.dao.UsuarioDAOImpl;
import tienda.dto.Estado;
import tienda.dto.Rol;
import tienda.dto.Tabla;
import tienda.dto.Usuario;

public class ModelManager {
	TablaDAOImpl tablaDAO;
	EstadoDAOImpl estadoDAO;
	RolDAOImpl rolDAO;
	UsuarioDAOImpl usuarioDAO;

	public ModelManager() {
		// TODO Auto-generated constructor stub
		tablaDAO = new TablaDAOImpl();
		estadoDAO = new EstadoDAOImpl();
		rolDAO = new RolDAOImpl();
		usuarioDAO = new UsuarioDAOImpl();
	}

	/////////////////////// TABLA/////////////////////////////////
	public String guardarTabla(Tabla tabla) {
		tabla.setFechareg(new Date());
		return tablaDAO.guardarTabla(tabla);
	}

	public String actualizarTabla(Tabla tabla) {
		return tablaDAO.actualizarTabla(tabla);
	}

	public String eliminarTabla(String idtabla) {
		return tablaDAO.eliminarTabla(idtabla);
	}

	public Tabla buscarTablaPorId(String idtabla) {
		return tablaDAO.buscarTablaPorId(idtabla);
	}

	public List<Tabla> buscarTodos() {
		return tablaDAO.buscarTodos();
	}

	////////////////////////////////////////////////////////
	/////////////////////// ESTADO/////////////////////////////////
	public String guardar(Estado objeto) {
		objeto.setFechareg(new Date());
		return estadoDAO.guardar(objeto);
	}

	public String actualizar(Estado objeto) {
		return estadoDAO.actualizar(objeto);
	}

	public String eliminar(String idobjeto) {
		return estadoDAO.eliminar(idobjeto);
	}

	public Estado buscarEstadoPorId(String idobjeto) {
		return estadoDAO.buscarEstadoPorId(idobjeto);
	}

	public List<Estado> buscarTodosEstados() {
		return estadoDAO.buscarTodos();
	}

	public String retornaIdEstado() {
		return estadoDAO.retornaIdEstado();
	}

	public List<Estado> buscarEstadosPorIdTabla(String idtabla) {
		return estadoDAO.buscarEstadosPorIdTabla(idtabla);
	}

	public Estado buscarEstadoPorNombreIdTabla(String nombreestado, String idtabla) {
		return estadoDAO.buscarEstadoPorNombreIdTabla(nombreestado, idtabla);
	}

	//////////////////////////////////////////////////////////////
	/////////////////////// ROL///////////////////////////////////
	public String guardar(Rol objeto) {
		objeto.setFecharegr(new Date());
		return rolDAO.guardar(objeto);
	}

	public String actualizar(Rol objeto) {
		return rolDAO.actualizar(objeto);
	}

	public String eliminarRol(String idobjeto) {
		return rolDAO.eliminar(idobjeto);
	}

	public Rol buscarRolPorId(String idobjeto) {
		return rolDAO.buscarRolPorId(idobjeto);
	}

	public List<Rol> buscarTodosRol() {
		return rolDAO.buscarTodos();
	}

	//////////////////////////////////////////////////////////////
	/////////////////////// USUARIO/////////////////////////////////
	public String guardarUsuario(Usuario objeto) {
		objeto.setFecharegu(new Date());
		return usuarioDAO.guardarUsuario(objeto);
	}

	public String actualizarUsuario(Usuario objeto) {
		return usuarioDAO.actualizarUsuario(objeto);
	}

	public String eliminarUsuario(String idobjeto) {
		return usuarioDAO.eliminarUsuario(idobjeto);
	}

	public List<Usuario> buscarTodosUsuarios() {
		return usuarioDAO.buscarTodos();
	}

	public String retornaIdUsuario() {
		return usuarioDAO.retornaIdUsuario();
	}

	public Usuario retornaUsuarioBuscadoPorId(String idusuario) {
		return usuarioDAO.retornaUsuarioBuscadoPorId(idusuario);
	}

	//////////////////////////////////////////////////////////////
}
