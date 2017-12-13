package tienda.backing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.codec.digest.DigestUtils;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;

import tienda.dto.Estado;
import tienda.dto.Rol;
import tienda.dto.Usuario;
import tienda.funciones.GeneraIdentificador;
import tienda.logica.ModelManager;

public class UsuarioBacking {
	ModelManager mm;
	private Usuario usuarioTemp;
	private String idusuario;
	private String cedulau;
	private String nombresu;
	private String apellidosu;
	private String alias;
	private String clave;
	private String idestado;
	private String idrol;
	private String mensaje;

	private String nombresued;
	private String apellidosued;
	private String aliased;
	private String claveed;

	private List<SelectItem> selectItemListaEstado;
	private List<SelectItem> selectItemListaRol;

	private List<Usuario> eliminados = new ArrayList<Usuario>();
	private List<Usuario> lista = new ArrayList<Usuario>();

	private GeneraIdentificador ident;

	public UsuarioBacking() {
		// TODO Auto-generated constructor stub
		mm = new ModelManager();
		usuarioTemp = new Usuario();
		ident = new GeneraIdentificador();
	}

	public Usuario getUsuarioTemp() {
		return usuarioTemp;
	}

	public void setUsuarioTemp(Usuario usuarioTemp) {
		this.usuarioTemp = usuarioTemp;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getCedulau() {
		return cedulau;
	}

	public void setCedulau(String cedulau) {
		this.cedulau = cedulau;
	}

	public String getNombresu() {
		return nombresu;
	}

	public void setNombresu(String nombresu) {
		this.nombresu = nombresu;
	}

	public String getApellidosu() {
		return apellidosu;
	}

	public void setApellidosu(String apellidosu) {
		this.apellidosu = apellidosu;
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

	public String getIdestado() {
		return idestado;
	}

	public void setIdestado(String idestado) {
		this.idestado = idestado;
	}

	public String getIdrol() {
		return idrol;
	}

	public void setIdrol(String idrol) {
		this.idrol = idrol;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getNombresued() {
		return nombresued;
	}

	public void setNombresued(String nombresued) {
		this.nombresued = nombresued;
	}

	public String getApellidosued() {
		return apellidosued;
	}

	public void setApellidosued(String apellidosued) {
		this.apellidosued = apellidosued;
	}

	public String getAliased() {
		return aliased;
	}

	public void setAliased(String aliased) {
		this.aliased = aliased;
	}

	public String getClaveed() {
		return claveed;
	}

	public void setClaveed(String claveed) {
		this.claveed = claveed;
	}

	public List<Usuario> getEliminados() {
		return eliminados;
	}

	public void setEliminados(List<Usuario> eliminados) {
		this.eliminados = eliminados;
	}

	public List<Usuario> getLista() {
		return lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	///////////////////////////////////////////////////////////
	// Para cargar listado de estado
	public List<Estado> getListadoEstado() {
		return mm.buscarEstadosPorIdTabla("US");
	}

	public List<SelectItem> getSelectItemListaEstado() {
		selectItemListaEstado = new ArrayList<SelectItem>();
		List<Estado> lst = getListadoEstado();
		Iterator<Estado> i = lst.iterator();
		while (i.hasNext()) {
			Estado obj = i.next();
			SelectItem si;
			si = new SelectItem(obj.getIdestado(), obj.getNombreestado());
			// System.err.println(obj.getIdestado()+"-------------"+
			// obj.getNombreestado());
			selectItemListaEstado.add(si);
		}
		return selectItemListaEstado;
	}

	public void setSelectItemListaEstado(List<SelectItem> selectItemListaEstado) {
		this.selectItemListaEstado = selectItemListaEstado;
	}

	// Para cargar listado de rol
	public List<Rol> getListadoRol() {
		return mm.buscarTodosRol();
	}

	public List<SelectItem> getSelectItemListaRol() {
		selectItemListaRol = new ArrayList<SelectItem>();
		List<Rol> lst = getListadoRol();
		Iterator<Rol> i = lst.iterator();
		while (i.hasNext()) {
			Rol obj = i.next();
			SelectItem si;
			si = new SelectItem(obj.getIdrol(), obj.getNombrerol());
			// System.err.println(obj.getIdestado()+"-------------"+
			// obj.getNombreestado());
			selectItemListaRol.add(si);
		}
		return selectItemListaRol;
	}

	public void setSelectItemListaRol(List<SelectItem> selectItemListaRol) {
		this.selectItemListaRol = selectItemListaRol;
	}

	public List<Usuario> getListadoUsuario() {
		lista = mm.buscarTodosUsuarios();
		return lista;
	}

	public void guardar() {
		try {
			if (cedulau.length() == 10) {
				if (nombresu.length() > 1) {
					if (apellidosu.length() > 1) {
						if (!idestado.equals("")) {
							if (!idrol.equals("")) {

								String idu;
								idu = mm.retornaIdUsuario();
								idusuario = ident.generaId5(idu);

								String nArray[] = nombresu.split(" ");
								String aArray[] = apellidosu.split(" ");

								usuarioTemp.setIdusuario(idusuario);
								usuarioTemp.setCedulau(cedulau);
								usuarioTemp.setNombresu(nombresu);
								usuarioTemp.setApellidosu(apellidosu);
								usuarioTemp.setAlias(ident.generarAlias(nArray[0], aArray[0], 0).toLowerCase());
								usuarioTemp.setClave(DigestUtils.md5Hex(cedulau));
								usuarioTemp.setEstado(mm.buscarEstadoPorNombreIdTabla("ACTIVO", "US"));
								//usuarioTemp.setEstado(mm.buscarEstadoPorId(idestado));
								usuarioTemp.setRol(mm.buscarRolPorId(idrol));

								System.err.println(idusuario + " " + cedulau + " " + nombresu + " " + apellidosu + " "
										+ usuarioTemp.getAlias() + " " + usuarioTemp.getClave());

								mensaje = mm.guardarUsuario(usuarioTemp);
								if (mensaje.equals("Guardado")) {
									idusuario = "";
									cedulau = "";
									nombresu = "";
									apellidosu = "";
									alias = "";
									clave = "";
									idestado = "";
									idrol = "";
								}
							} else {
								mensaje = "Seleccione el rol.";
							}

						} else {
							mensaje = "Seleccione el estado.";
						}
					} else {
						mensaje = "Digite el apellido.";
					}
				} else {
					mensaje = "Digite el nombre.";
				}
			} else {
				mensaje = "La cédula debe tener 10 dígitos.";
			}

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
			// return mensaje;
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", mensaje));
		}
	}

	public String eliminar() {
		// System.err.println(" DENTRO DE ELIMINAR -----------------------> ");
		for (Usuario u : lista) {
			if (u.isSelected()) {
				eliminados.add(u);

			}
			// System.err.println("FOR-----------------------> " +
			// e.getNombreestado());
		}

		if (!eliminados.isEmpty()) {
			for (int i = 0; i < eliminados.size(); i++) {
				// System.err.println("ELIMINADOS -----------------------> " +
				// eliminados.get(i).getIdestado());
				mensaje = mm.eliminarUsuario(eliminados.get(i).getIdusuario());
			}
			eliminados.clear();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", "Las filas seleccionadas se eliminaron."));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", "No existen filas seleccionadas."));
		}
		return "regusuario";
	}

	public void eliminarFila() {
		try {
			String msg = mm.eliminarUsuario(usuarioTemp.getIdusuario());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Información", msg));
			// System.err.println("--------------- DENTRO DE ELIMINAR FILA
			// ------------------ IDTABLA ["
			// + tablaTemp.getIdtabla() + "]");
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Información", e.getMessage() + " " + usuarioTemp.getIdusuario()));
			// System.err.println("--------------- " + e.getMessage() + "
			// ------------------");
		}
	}

	public void limpiar() {
		try {
			mensaje = "";
			idusuario = "";
			cedulau = "";
			nombresu = "";
			apellidosu = "";
			alias = "";
			clave = "";
			idestado = "";
			idrol = "";
			// FacesContext.getCurrentInstance().addMessage(null, new
			// FacesMessage("Información", tablaTemp.getIdtabla()));

			usuarioTemp.setIdusuario(idusuario);
			usuarioTemp.setCedulau(cedulau);
			usuarioTemp.setNombresu(nombresu);
			usuarioTemp.setApellidosu(apellidosu);
			usuarioTemp.setAlias(alias);
			usuarioTemp.setClave(clave);
			usuarioTemp.setFecharegu(null);

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
			if (!usuarioTemp.getIdusuario().isEmpty()) {
				mensaje = mm.actualizarUsuario(usuarioTemp);
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
		idusuario = ((Usuario) event.getObject()).getIdusuario();
		nombresued = ((Usuario) event.getObject()).getNombresu();
		apellidosued = ((Usuario) event.getObject()).getApellidosu();
		claveed = ((Usuario) event.getObject()).getClave();

		Usuario obj = new Usuario();
		obj = mm.retornaUsuarioBuscadoPorId(idusuario);
		int cont = 0;

		// System.err.println("DESDE VISTA ---------> " + nombretabla + "
		if (nombresued.length() > 1) {
			obj.setNombresu(nombresued);
			cont++;
		}
		if (apellidosued.length() > 1) {
			obj.setApellidosu(apellidosued);
			cont++;
		}
		if (claveed.length() > 0) {
			obj.setClave(DigestUtils.md5Hex(claveed));
			cont++;
		}

		if (cont == 0) {
			mensaje = "No existe ningún cambio.";
		} else if (cont > 0) {
			mm.actualizarUsuario(obj);
			mensaje = "Campos actualizados.";
		}

		FacesMessage msg = new FacesMessage("Información", mensaje);
		FacesContext.getCurrentInstance().addMessage(null, msg);

		nombresued = "";
		apellidosued = "";
		claveed = "";
		limpiar();
	}

	public void cancelar(RowEditEvent event) {

		FacesMessage msg = new FacesMessage("Información", "Modificación cancelado por el usuario.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
