package tienda.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;
import java.io.*;

public class Obtener_Imagen extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Obtener_Imagen(){
		
	}
	
	public void init() throws ServletException {
	}

	public void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
	}

	public void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		String idmorador = (String) request.getParameter("idmorador");
		String directorio = "C:\\Fotografias\\";
		String url;
		try {
			// primeramente, comprobamos que se ha pasado el ID, si no existe no
			// se devuelve foto
			if (idmorador == null)
				throw new Exception("Seleccione morador.");
			else {
				try {
					// leemos el fichero del ftp,usando el metodo que traduce el
					// path
					url = directorio + idmorador + ".jpg";
					File f = new File(url);
					// RandomAccessFile raf = new
					// RandomAccessFile(utils.constantes.ConstantesRID.FILE_PATH+myID+".jpg",
					// "r");
					RandomAccessFile raf = new RandomAccessFile(url, "r");
					FileInputStream fis = new FileInputStream(f);
					FileReader fr = new FileReader(f);
					// int i;
					byte b[] = new byte[(int) f.length()];
					raf.read(b);

					// cabecera
					response.setHeader("Content-Type", "img/jpg");
					response.setIntHeader("Content-Length", (int) f.length());
					// response.setHeader("Accept-Ranges", "bytes");

					// lo escribimos
					OutputStream out = response.getOutputStream();
					out.write(b);
					out.close();
					raf.close();
					fis.close();
					fr.close();

				} catch (FileNotFoundException fe) {
					throw new Exception();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// salida a pagina de error y devolver foto por defecto
			// leemos el fichero del ftp por defecto
			url = directorio + idmorador + ".jpg";
			File f = new File(url); // Const..Default  Es
									// un _String con la
									// url
			RandomAccessFile raf = new RandomAccessFile(url, "r");
			FileInputStream fis = new FileInputStream(f);

			FileReader fr = new FileReader(f);
			// int i;
			byte b[] = new byte[(int) f.length()];
			raf.read(b);

			// cabecera
			response.setHeader("Content-Type", "img/jpg");
			response.setIntHeader("Content-Length", (int) f.length());
			// response.setHeader("Accept-Ranges", "bytes");

			// lo escribimos
			OutputStream out = response.getOutputStream();
			out.write(b);
			out.close();
			raf.close();
			fis.close();
			fr.close();
		} // catch
	} // doGet
}
