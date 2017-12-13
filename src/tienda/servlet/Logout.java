package tienda.servlet;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Ya sea que el método sea por GET o POST, cerraremos la sesion.
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// PrintWriter out = response.getWriter();
		HttpSession sesion = request.getSession(true);

		// Cerrar sesion
		sesion.invalidate();
		response.sendRedirect("login.xhtml");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession sesion = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		response.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
		//System.err.println("Desde el doPost de Logout: "+req.getContextPath() + "/login.xhtml");
		sesion.invalidate();
	}
}
