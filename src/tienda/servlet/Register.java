package tienda.servlet;

//import beans.*;
//import clases.*;
//import ec.cabildo.modelo.dao.*;
//import java.util.regex.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * En este proyecto; este servlet no recibe ni debe recibir nada por
		 * GET, asi que si se lleva a entrar al servelt usando el metodo GET
		 * solamente redireccion al index.jsp
		 */
		response.sendRedirect("index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession respuesta = request.getSession(true);
		PrintWriter out = response.getWriter();
		// Declaro e inicio las variables
		String nombreUsuario = request.getParameter("name");
		// String emailUsuario = request.getParameter("email");
		//String cedula = request.getParameter("cedula");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("password2");
		//Pattern p = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		// Matcher m = p.matcher(emailUsuario);
		// Validador v = new Validador();
		//Dao d = new Dao();
		// Comienzo con las validaciones
		/*
		 * Podemos hacer un monton de validaciones, por ejemplo: Campos no
		 * vacios, direccion de email valida, nombre de usuario y contrasenia sin
		 * espacios y/o caracteres especiales.
		 */

		// campos vacios
		if (nombreUsuario.isEmpty() || password.isEmpty()) {
			respuesta.setAttribute("error", "Hay campos vacios");
		} else if (password.equals(confirm_password)) {
			try {
				// d.conectar();
				//UsuariosDAOImpl usu = new UsuariosDAOImpl();
				//String val;
				/*val = usu.findByCedulaUsu(cedula,"","");
				if (val.equals("ok")) {
					response.sendRedirect("/menu/index.jsp");
				} else {

					// Llegado a este punto significa que todo esta correcto,
					// por
					// lo tanto ingreso a la DB
					// d.registerUser(emailUsuario, password, nombreUsuario);
					response.sendRedirect("register.jsp");
					respuesta.setAttribute("error", null);
				}*/
				//d.desconectar();
			} catch (Exception e) {
				out.println("Ocurrio la sig exception: " + e);
			}
		} else {
			respuesta.setAttribute("error", "Las claves no son iguales");
		}

		response.sendRedirect("register.jsp");

	}

}