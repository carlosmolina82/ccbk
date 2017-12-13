package tienda.backing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;

import tienda.dto.Estado;
import tienda.dto.Tabla;
import tienda.funciones.GeneraIdentificador;
import tienda.logica.ModelManager;

public class EstadoBacking {
	ModelManager mm;
	private Estado estadoTemp;
	private String idestado;
	private String nombreestado;
	private String needicion;
	private String mensaje;
	private String idtabla;

	private List<SelectItem> selectItemListaTabla;

	private List<Estado> eliminados = new ArrayList<Estado>();
	private List<Estado> lista = new ArrayList<Estado>();
	private GeneraIdentificador gen;

	public EstadoBacking() {
		// TODO Auto-generated constructor stub
		mm = new ModelManager();
		estadoTemp = new Estado();
		gen = new GeneraIdentificador();
	}

	public List<Estado> getLista() {
		return lista;
	}

	public void setLista(List<Estado> lista) {
		this.lista = lista;
	}

	public Estado getEstadoTemp() {
		return estadoTemp;
	}

	public void setEstadoTemp(Estado estadoTemp) {
		this.estadoTemp = estadoTemp;
	}

	public String getIdestado() {
		return idestado;
	}

	public void setIdestado(String idestado) {
		this.idestado = idestado;
	}

	public String getNombreestado() {
		return nombreestado;
	}

	public void setNombreestado(String nombreestado) {
		this.nombreestado = nombreestado;
	}

	public String getNeedicion() {
		return needicion;
	}

	public void setNeedicion(String needicion) {
		this.needicion = needicion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdtabla() {
		return idtabla;
	}

	public void setIdtabla(String idtabla) {
		this.idtabla = idtabla;
	}

	///////////////////////////////////////////////////////////
	// Para cargar listado de tabla
	public List<Tabla> getListadoTabla() {
		return mm.buscarTodos();
	}

	public List<SelectItem> getSelectItemListaTabla() {
		selectItemListaTabla = new ArrayList<SelectItem>();
		List<Tabla> lst = getListadoTabla();
		Iterator<Tabla> i = lst.iterator();
		while (i.hasNext()) {
			Tabla obj = i.next();
			SelectItem si;
			si = new SelectItem(obj.getIdtabla(), obj.getNombretabla());
			selectItemListaTabla.add(si);
		}
		return selectItemListaTabla;
	}

	public void setSelectItemListaTabla(List<SelectItem> selectItemListaTabla) {
		this.selectItemListaTabla = selectItemListaTabla;
	}

	public List<Estado> getListadoEstado() {
		lista = mm.buscarTodosEstados();
		return lista;
	}

	public void guardar() {
		try {
			if (nombreestado.length() > 2) {
				if (!idtabla.equals("")) {

					String ides = mm.retornaIdEstado();
					idestado = gen.generaId3(ides);

					if (idestado.length() == 3) {
						estadoTemp.setIdestado(idestado);
						estadoTemp.setNombreestado(nombreestado);
						estadoTemp.setTabla(mm.buscarTablaPorId(idtabla));

						mensaje = mm.guardar(estadoTemp);
						// if (mensaje.equals("Guardado")) {
						idestado = "";
						nombreestado = "";
						idtabla="";
						//estadoTemp=null;
					} else {
						mensaje = "Verificar id de Estado.";
					}
				} else {
					mensaje = "Seleccione la Referencia.";
				}
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage("Información", mensaje));
			} else {
				mensaje = "El nombre de la tabla debe ser mayor a 2 caracteres.";
				// FacesContext.getCurrentInstance().addMessage(null, new
				// FacesMessage("Información", mensaje));
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
			// return mensaje;
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			// return mensaje;
			idestado = "";
			nombreestado = "";
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
		}
	}

	public String eliminar() {
		//System.err.println(" DENTRO DE ELIMINAR -----------------------> ");
		for (Estado e : lista) {
			if (e.isSelected()) {
				eliminados.add(e);
				
			}
			//System.err.println("FOR-----------------------> " +
			 //e.getNombreestado());
		}

		if (!eliminados.isEmpty()) {
			for (int i = 0; i < eliminados.size(); i++) {
				//System.err.println("ELIMINADOS -----------------------> " +
					//	eliminados.get(i).getIdestado());
				mensaje = mm.eliminar(eliminados.get(i).getIdestado());
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
			String msg = mm.eliminar(estadoTemp.getIdestado());

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", msg));
			// System.err.println("--------------- DENTRO DE ELIMINAR FILA
			// ------------------ IDTABLA ["
			// + tablaTemp.getIdtabla() + "]");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", e.getMessage() + " " + estadoTemp.getIdestado()));
			// System.err.println("--------------- " + e.getMessage() + "
			// ------------------");
		}
	}

	public void limpiar() {
		try {
			mensaje = "";
			idestado = "";
			nombreestado = "";
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage("Información", tablaTemp.getIdtabla()));

			estadoTemp.setIdestado("");
			estadoTemp.setNombreestado("");
			estadoTemp.setFechareg(null);

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
			if (!estadoTemp.getIdestado().isEmpty()) {
				// String nombretabla=tablaTemp.getNombretabla();
				mensaje = mm.actualizar(estadoTemp);
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
		idestado = ((Estado) event.getObject()).getIdestado();
		nombreestado = ((Estado) event.getObject()).getNombreestado();

		// System.err.println("DESDE VISTA ---------> " + nombretabla + "
		// ---------> " + ntedicion);
		if (needicion.length() > 2) {

			Estado es = new Estado();
			es = mm.buscarEstadoPorId(idestado);
			es.setNombreestado(needicion);

			// System.err.println("DESDE ACTUALIZAR ---------> " + idtabla + "
			// ---------> " + nombretabla);
			mensaje = mm.actualizar(es);
			// ntedicion = "";
			// mensaje = "Dentro de actualizar";
			// System.err.println(mensaje);

			FacesMessage msg = new FacesMessage("Información", mensaje);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Información", "Debe ingresar los nuevos datos.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		needicion = "";
		limpiar();
	}

	public void cancelar(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Información", "Modificación cancelado por el usuario.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
