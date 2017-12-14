package tienda.backing;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.context.RequestContext;

import tienda.dao.UsuarioDAOImpl;

@SuppressWarnings("serial")
public class LoginBacking implements Serializable {

	private String alias;
	private String clave;

	public LoginBacking() {
		// TODO Auto-generated constructor stub
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void login(ActionEvent event) {
		RequestContext context = RequestContext.getCurrentInstance();
		FacesMessage message;
		boolean loggedIn;

		String claveusuario = DigestUtils.md5Hex(clave);
		String ruta = "";

		if (alias.isEmpty() || clave.isEmpty()) {
			loggedIn = false;
			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Ingrese el usuario y/o la contraseña.");

		} else {// primer else
			// Cuando el usuario no está registrado en la base de datos
			// clave hash Cemq-1982
			if (alias.equals("administrador") && claveusuario.equals("5f69e0c7207062260717db383422ae97")) {
				loggedIn = true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", alias);

				message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", alias);

				ruta = MyUtil.baseurl() + "index.xhtml";
				// System.err.println("RUTA: -------------------- " + ruta);

			} else {// segundo else
				// No hay campos vacios obtengo información de base de datos
				UsuarioDAOImpl usu = new UsuarioDAOImpl();
				String nc, val;
				// nc = usu.comprobarExistenciaUsuario(usuario);
				nc = usu.retornaNombresCompletos(alias);
				if (!nc.equals("noexiste")) {
					if (!nc.equals("error")) {
						val = usu.buscarPorAliasUsuario(alias, claveusuario);
						//System.err.println("VAL RETORNADO: " + val);
						// validación de usuario y rol
						switch (val) {
						case "administrador":

							loggedIn = true;
							FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",
									alias);
							message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", alias);
							ruta = MyUtil.baseurl() + "index.xhtml";
							break;

						default:
							loggedIn = false;
							message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de logueo",
									"Usuario o clave incorrectos. Intente nuevamente.");
							break;
						}

					} else {
						// cuando nc retorna vacio, posiblemente error en la
						// base de datos
						// respuesta.setAttribute("ncs", "Usuario o contraseña
						// incorrectos. Intente nuevamente.");
						// response.sendRedirect("login.xhtml");
						loggedIn = false;
						message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de logueo",
								"Error en la consulta.");
					}
				} else {
					// usuario no existe
					// respuesta.setAttribute("ncs", "Usuario no registrado.
					// Intente nuevamente.");
					// response.sendRedirect("login.xhtml");
					loggedIn = false;
					message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de logueo",
							"Usuario no registrado. Intente nuevamente.");
				}
			} // fin del segundo else
		} // fin del primer else

		FacesContext.getCurrentInstance().addMessage(null, message);
		context.addCallbackParam("loggedIn", loggedIn);
		context.addCallbackParam("ruta", ruta);
		// System.err.println("INGRESO ------------------------------------- " +
		// ruta);
	}

	public void logout(ActionEvent event) {
		String ruta = MyUtil.baseurl() + "login.xhtml";
		RequestContext context = RequestContext.getCurrentInstance();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
		sesion.invalidate();
		context.addCallbackParam("loggedOut", true);
		context.addCallbackParam("ruta", ruta);
		// System.err.println("CIERRE ------------------------------------- " +
		// ruta);
	}
}
