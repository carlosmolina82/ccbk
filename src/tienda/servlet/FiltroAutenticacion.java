package tienda.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroAutenticacion
 */
@WebFilter({ "/FiltroAutenticacion", "*.xhtml" })
public class FiltroAutenticacion implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroAutenticacion() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession sesion = req.getSession(true);
		String requestUrl = req.getRequestURL().toString();
		String[] urlPermitidaSinSesion = new String[] { "/faces/login.xhtml" };
		boolean redireccionarPeticion;
		if (sesion.getAttribute("usuario") == null) {
			redireccionarPeticion = true;
			for (String item : urlPermitidaSinSesion) {
				if (requestUrl.contains(item)) {
					redireccionarPeticion = false;
					break;
				}
			}
		} else {
			redireccionarPeticion = false;
		}
		if (redireccionarPeticion) {
			res.sendRedirect(req.getContextPath() + "/faces/login.xhtml");
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
