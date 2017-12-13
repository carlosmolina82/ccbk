package tienda.servlet;

import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tienda.dao.UsuarioDAOImpl;

//@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * En este proyecto; este servlet no recibe ni debe recibir nada por
		 * GET, asi que si se lleva a entrar al servelt usando el metodo GET
		 * solamente redireccion al index.jsp
		 */
		response.sendRedirect("login.xhtml");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession respuesta = request.getSession(true);
		String usuario = request.getParameter("alias");
		String clave = request.getParameter("clave");
		// String hash = request.getParameter("hash");
		String claveusuario = DigestUtils.md5Hex(clave);
		@SuppressWarnings("unused")
		String ncs = request.getParameter("ncs");

		// String ses=respuesta.getId();
		// System.err.println("PRUEBAS---> "+respuesta.getId());

		// campos vacios
		if (usuario.isEmpty() || clave.isEmpty()) {
			response.sendRedirect("login.xhtml");
			respuesta.setAttribute("ncs", "Ingrese el usuario y la clave.");
		} else {// primer else
			// Cuando el usuario no esta registrado en la base de datos
			// clave hash Cemq-1982
			if (usuario.equals("administrador") && claveusuario.equals("5f69e0c7207062260717db383422ae97")) {
				respuesta.setAttribute("usuario", usuario);
				// respuesta.setAttribute("clave", clave);
				respuesta.setAttribute("hash", claveusuario);
				respuesta.setAttribute("ncs", "ADMINISTRADOR DEL SISTEMA");
				respuesta.setAttribute("rol", "administrador");
				// redirige a la pagina principal del sistema
				response.sendRedirect("index.xhtml");
			} else {// segundo else
				// No hay campos vacios obtengo informacion de base de datos
				UsuarioDAOImpl usu = new UsuarioDAOImpl();
				String nc, val;
				// nc = usu.comprobarExistenciaUsuario(usuario);
				nc = usu.retornaNombresCompletos(usuario);
				if (!nc.equals("noexiste")) {
					if (!nc.equals("error")) {
						respuesta.setAttribute("ncs", nc);

						// val = usu.buscarPorCedulaUsu(usuario, clave);
						val = usu.buscarPorAliasUsuario(usuario, claveusuario);
						// validacion de usuario y rol
						switch (val) {
						case "administrador":
							respuesta.setAttribute("usuario", usuario);
							// respuesta.setAttribute("clave", clave);
							respuesta.setAttribute("hash", claveusuario);
							respuesta.setAttribute("rol", val);
							// redirecciona a la pagina principal del
							// sistema
							response.sendRedirect("index.xhtml");
							break;
						case "reporte":
							respuesta.setAttribute("usuario", usuario);
							// respuesta.setAttribute("clave", clave);
							respuesta.setAttribute("hash", claveusuario);
							respuesta.setAttribute("rol", val);
							// redirecciona a la pagina de informacion del
							// usuario
							// logueado
							response.sendRedirect("reportes/rgu.jsp");
							break;
						case "cajero":
							respuesta.setAttribute("usuario", usuario);
							// respuesta.setAttribute("clave", clave);
							respuesta.setAttribute("hash", claveusuario);
							respuesta.setAttribute("rol", val);
							// redirecciona a la pagina principal
							response.sendRedirect("reportes/repdocente.xhtml");
							break;
						default:
							// redirecciona a la pagina de logueo
							respuesta.setAttribute("ncs", val);
							response.sendRedirect("login.xhtml");
							break;
						}

					} else {
						// cuando nc retorna vacio, posiblemente error en la
						// base de datos
						respuesta.setAttribute("ncs", "Usuario o clave incorrectos. Intente nuevamente.");
						response.sendRedirect("login.xhtml");
					}
				} else {
					// usuario no existe
					respuesta.setAttribute("ncs", "Usuario no registrado. Intente nuevamente.");
					response.sendRedirect("login.xhtml");
				}
			} // fin del segundo else
		} // fin del primer else
	}// fin del doPost
}// fin de la clase