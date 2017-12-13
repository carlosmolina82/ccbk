package tienda.backing;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;

import tienda.dto.Tabla;
import tienda.logica.ModelManager;

public class TablaBacking {
	ModelManager mm;
	private Tabla tablaTemp;
	private String idtabla;
	private String nombretabla;
	private String ntedicion;
	private String mensaje;

	private List<Tabla> eliminados = new ArrayList<Tabla>();
	private List<Tabla> lista = new ArrayList<Tabla>();

	public TablaBacking() {
		// TODO Auto-generated constructor stub
		mm = new ModelManager();
		tablaTemp = new Tabla();
	}

	public List<Tabla> getLista() {
		return lista;
	}

	public void setLista(List<Tabla> lista) {
		this.lista = lista;
	}

	public String getNtedicion() {
		return ntedicion;
	}

	public void setNtedicion(String ntedicion) {
		this.ntedicion = ntedicion;
	}

	public Tabla getTablaTemp() {
		return tablaTemp;
	}

	public void setTablaTemp(Tabla tablaTemp) {
		this.tablaTemp = tablaTemp;
	}

	public String getIdtabla() {
		return idtabla;
	}

	public void setIdtabla(String idtabla) {
		this.idtabla = idtabla;
	}

	public String getNombretabla() {
		return nombretabla;
	}

	public void setNombretabla(String nombretabla) {
		this.nombretabla = nombretabla;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	///////////////////////////////////////////////////////////
	public List<Tabla> getListadoTabla() {
		lista = mm.buscarTodos();
		return lista;
	}

	public void guardar() {
		try {
			if (idtabla.length() == 2) {
				if (nombretabla.length() > 2) {
					tablaTemp.setIdtabla(idtabla);
					tablaTemp.setNombretabla(nombretabla);
					mensaje = mm.guardarTabla(tablaTemp);
					// if (mensaje.equals("Guardado")) {
					idtabla = "";
					nombretabla = "";

					//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
				} else {
					mensaje = "El nombre de la tabla debe ser mayor a 2 caracteres.";
					//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
				}
			} else {
				mensaje = "El id debe contener 2 caracteres.";
				//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
			// return mensaje;
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			// return mensaje;
			idtabla = "";
			nombretabla = "";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
		}
	}

	public String eliminar() {
		for (Tabla t : lista) {
			if (t.isSelected()) {
				eliminados.add(t);
			}
			// System.err.println("FOR-----------------------> " +
			// t.getNombretabla());
		}

		if (!eliminados.isEmpty()) {
			for (int i = 0; i < eliminados.size(); i++) {
				mensaje = mm.eliminarTabla(eliminados.get(i).getIdtabla());
			}
			eliminados.clear();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", "Las filas seleccionadas se eliminaron."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", "No existen filas seleccionadas."));
		}
		return "regtabla";
	}

	public void eliminarFila() {
		try {
			String msg = mm.eliminarTabla(tablaTemp.getIdtabla());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", msg));
			//System.err.println("--------------- DENTRO DE ELIMINAR FILA ------------------ IDTABLA ["
				//	+ tablaTemp.getIdtabla() + "]");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", e.getMessage() + " " + tablaTemp.getIdtabla()));
			//System.err.println("--------------- " + e.getMessage() + " ------------------");
		}
	}

	public void limpiar() {
		try {
			mensaje = "";
			idtabla = "";
			nombretabla = "";
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage("Información", tablaTemp.getIdtabla()));

			tablaTemp.setIdtabla("");
			tablaTemp.setNombretabla("");
			tablaTemp.setFechareg(null);

			// System.err.println("--------------- DENTRO LIMPIAR
			// ------------------ IDTABLA ["+tablaTemp.getIdtabla()+"]");

		} catch (Exception e) {
			// TODO: handle exception
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage("Información", e.getMessage()+"
			// "+tablaTemp.getIdtabla()));
			// System.err.println("--------------- "+e.getMessage()+"
			// ------------------");
		}
	}

	public void actualizar() {
		try{
			if(!tablaTemp.getIdtabla().isEmpty()){
				//String nombretabla=tablaTemp.getNombretabla();
				mensaje=mm.actualizarTabla(tablaTemp);
			}else{
				mensaje="No existe información para modificar.";
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
		}catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", e.getMessage()));
		}
	}

	public void actualizarFila(RowEditEvent event) {
		idtabla = ((Tabla) event.getObject()).getIdtabla();
		nombretabla = ((Tabla) event.getObject()).getNombretabla();

		// System.err.println("DESDE VISTA ---------> " + nombretabla + "
		// ---------> " + ntedicion);
		if (ntedicion.length() > 2) {

			Tabla tbl = new Tabla();
			tbl = mm.buscarTablaPorId(idtabla);
			tbl.setNombretabla(ntedicion);

			// System.err.println("DESDE ACTUALIZAR ---------> " + idtabla + "
			// ---------> " + nombretabla);
			mensaje = mm.actualizarTabla(tbl);
			// ntedicion = "";
			// mensaje = "Dentro de actualizar";
			// System.err.println(mensaje);

			FacesMessage msg = new FacesMessage("Información", mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Información", "Debe ingresar los nuevos datos.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		ntedicion = "";
		limpiar();
	}

	public void cancelar(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Información", "Modificación cancelado por el usuario.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
