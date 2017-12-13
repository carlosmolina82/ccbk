package tienda.backing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;

import tienda.dto.Estado;
import tienda.dto.Rol;
import tienda.logica.ModelManager;

public class RolBacking {
	ModelManager mm;
	private Rol rolTemp;
	private String idrol;
	private String nombrerol;
	private String nredicion;
	private String mensaje;
	private String idestado;

	private List<SelectItem> selectItemListaEstado;

	private List<Rol> eliminados = new ArrayList<Rol>();
	private List<Rol> lista = new ArrayList<Rol>();

	public RolBacking() {
		// TODO Auto-generated constructor stub
		mm = new ModelManager();
		rolTemp = new Rol();
	}

	public List<Rol> getLista() {
		return lista;
	}

	public void setLista(List<Rol> lista) {
		this.lista = lista;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Rol getRolTemp() {
		return rolTemp;
	}

	public void setRolTemp(Rol rolTemp) {
		this.rolTemp = rolTemp;
	}

	public String getIdrol() {
		return idrol;
	}

	public void setIdrol(String idrol) {
		this.idrol = idrol;
	}

	public String getNombrerol() {
		return nombrerol;
	}

	public void setNombrerol(String nombrerol) {
		this.nombrerol = nombrerol;
	}

	public String getNredicion() {
		return nredicion;
	}

	public void setNredicion(String nredicion) {
		this.nredicion = nredicion;
	}

	public String getIdestado() {
		return idestado;
	}

	public void setIdestado(String idestado) {
		this.idestado = idestado;
	}

	///////////////////////////////////////////////////////////
	// Para cargar listado de estado
	public List<Estado> getListadoEstado() {
		return mm.buscarEstadosPorIdTabla("RO");
	}

	public List<SelectItem> getSelectItemListaEstado() {
		selectItemListaEstado = new ArrayList<SelectItem>();
		List<Estado> lst = getListadoEstado();
		Iterator<Estado> i = lst.iterator();
		while (i.hasNext()) {
			Estado obj = i.next();
			SelectItem si;
			si = new SelectItem(obj.getIdestado(), obj.getNombreestado());
			//System.err.println(obj.getIdestado()+"-------------"+ obj.getNombreestado());
			selectItemListaEstado.add(si);
		}
		return selectItemListaEstado;
	}

	public void setSelectItemListaEstado(List<SelectItem> selectItemListaEstado) {
		this.selectItemListaEstado = selectItemListaEstado;
	}

	public List<Rol> getListadoRol() {
		lista = mm.buscarTodosRol();
		return lista;
	}

	public void guardar() {
		try {
			if (idrol.length() == 2) {
			if (nombrerol.length() > 2) {
				if (!idestado.equals("")) {
					
						rolTemp.setIdrol(idrol);
						rolTemp.setNombrerol(nombrerol);
						rolTemp.setEstado(mm.buscarEstadoPorId(idestado));

						mensaje = mm.guardar(rolTemp);
						// if (mensaje.equals("Guardado")) {
						idrol="";
						nombrerol = "";
						idestado = "";
					
				} else {
					mensaje = "Seleccione el estado.";
				}
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage("Información", mensaje));
			} else {
				mensaje = "El nombre del rol debe ser mayor a 2 caracteres.";
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage("Información", mensaje));
			}
			} else {
				mensaje = "El id, debe ser de 2 caracteres.";
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
			// return mensaje;
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			// return mensaje;
			idrol= "";
			nombrerol = "";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
		}
	}

	public String eliminar() {
		// System.err.println(" DENTRO DE ELIMINAR -----------------------> ");
		for (Rol r : lista) {
			if (r.isSelected()) {
				eliminados.add(r);

			}
			// System.err.println("FOR-----------------------> " +
			// e.getNombreestado());
		}

		if (!eliminados.isEmpty()) {
			for (int i = 0; i < eliminados.size(); i++) {
				// System.err.println("ELIMINADOS -----------------------> " +
				// eliminados.get(i).getIdestado());
				mensaje = mm.eliminarRol(eliminados.get(i).getIdrol());
			}
			eliminados.clear();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", "Las filas seleccionadas se eliminaron."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", "No existen filas seleccionadas."));
		}
		return "regestado";
	}

	public void eliminarFila() {
		try {
			String msg = mm.eliminarRol(rolTemp.getIdrol());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", msg));
			// System.err.println("--------------- DENTRO DE ELIMINAR FILA
			// ------------------ IDTABLA ["
			// + tablaTemp.getIdtabla() + "]");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", e.getMessage() + " " +rolTemp.getIdrol()));
			// System.err.println("--------------- " + e.getMessage() + "
			// ------------------");
		}
	}

	public void limpiar() {
		try {
			mensaje = "";
			idrol="";
			nombrerol = "";
			idestado = "";
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage("Información", tablaTemp.getIdtabla()));

			rolTemp.setIdrol("");
			rolTemp.setNombrerol("");
			rolTemp.setFecharegr(null);

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
		try {
			if (!rolTemp.getIdrol().isEmpty()) {
				// String nombretabla=tablaTemp.getNombretabla();
				mensaje = mm.actualizar(rolTemp);
			} else {
				mensaje = "No existe información para modificar.";
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", e.getMessage()));
		}
	}

	public void actualizarFila(RowEditEvent event) {
		idrol = ((Rol) event.getObject()).getIdrol();
		nombrerol = ((Rol) event.getObject()).getNombrerol();

		// System.err.println("DESDE VISTA ---------> " + nombretabla + "
		// ---------> " + ntedicion);
		if (nredicion.length() > 2) {

			Rol obj = new Rol();
			obj = mm.buscarRolPorId(idrol);
			obj.setNombrerol(nredicion);

			// System.err.println("DESDE ACTUALIZAR ---------> " + idtabla + "
			// ---------> " + nombretabla);
			mensaje = mm.actualizar(obj);
			// ntedicion = "";
			// mensaje = "Dentro de actualizar";
			// System.err.println(mensaje);

			FacesMessage msg = new FacesMessage("Información", mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Información", "Debe ingresar los nuevos datos.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		nredicion = "";
		limpiar();
	}

	public void cancelar(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Información", "Modificación cancelado por el usuario.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
