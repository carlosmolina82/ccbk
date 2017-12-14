package tienda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import tienda.dto.Usuario;
import tienda.conexion.HibernateUtil;

public class UsuarioDAOImpl implements UsuarioDAO {
	private Session sesion;

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Usuario");
			@SuppressWarnings("unchecked")
			List<Usuario> lUs = q.list();
			sesion.getTransaction().commit();
			sesion.close();
			return lUs;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.close();
			return null;
		}

	}

	@Override
	public Usuario retornaUsuarioBuscadoPorId(String idusuario) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Usuario u;
			u = (Usuario) sesion.get(Usuario.class, new String(idusuario));
			sesion.getTransaction().commit();
			sesion.close();
			return u;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.close();
			return null;
		}
	}

	@Override
	public String guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.save(usuario);
			sesion.getTransaction().commit();
			sesion.close();
			return "Guardado";
		} catch (Exception e) {
			// TODO: handle exception
			sesion.close();
			System.err.println( "DAO----------------- "+e.getCause().getMessage());
			return "error";
		}
	}

	@Override
	public String actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.update(usuario);
			sesion.getTransaction().commit();
			sesion.close();
			return "Actualizado";
		} catch (Exception e) {
			// TODO: handle exception
			sesion.close();
			return e.getCause().getMessage();
		}
	}

	@Override
	public String retornaIdUsuario() {
		// TODO Auto-generated method stub
		String idu;
		sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		if (sesion.createQuery("select max(idusuario) From Usuario").iterate().next() != null) {
			idu = ((String) sesion.createQuery("select max(idusuario) From Usuario").iterate().next()).toString();
		} else {
			idu = "0";
		}
		sesion.getTransaction().commit();
		sesion.close();
		return idu;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String comprobarExistenciaUsuario(String alias) {
		// TODO Auto-generated method stub
		try {
			String usu;
			List<Usuario> lUsu;
			// Usuario u;
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Usuario where upper(alias) like upper('" + alias + "')");
			lUsu = q.list();
			if (lUsu.size() > 0) {
				usu = "existe";
			} else {
				usu = "noexiste";
			}
			sesion.getTransaction().commit();
			sesion.close();
			return usu;
		} catch (Exception e) {
			return "error";
		}
	}

	public String retornaCedulaUsuario(String aliasusuario) {
		try {
			String ced;
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			if (!sesion.createQuery("select cedulau From Usuario where upper(alias) like upper('" + aliasusuario + "')")
					.list().isEmpty()) {
				ced = ((String) sesion
						.createQuery(
								"select cedulau From Usuario where upper(alias) like upper('" + aliasusuario + "')")
						.iterate().next()).toString();
			} else {
				ced = "noexiste";
			}
			sesion.getTransaction().commit();
			sesion.close();
			return ced;
		} catch (Exception e) {
			return "noexiste";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String retornaNombresCompletos(String alias) {
		// TODO Auto-generated method stub
		try {
			String usu;
			List<Usuario> lUsu;
			Usuario u;
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Usuario where upper(alias) like upper('" + alias + "')");
			lUsu = q.list();
			if (lUsu.size() > 0) {
				u = (Usuario) sesion.createQuery("From Usuario where upper(alias) like upper('" + alias + "')")
						.iterate().next();
				usu = u.getNombresCompletos();
			} else {
				usu = "noexiste";
			}

			sesion.getTransaction().commit();
			sesion.close();
			// System.err.println("Usu: " + usu);
			return usu;
		} catch (Exception e) {
			System.err.println("Error al obtener usuarios: ---> " + e.getMessage());
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String buscarPorAliasUsuario(String alias, String clave) {
		// TODO Auto-generated method stub
		try {
			String val;
			List<Usuario> lUsu;
			Usuario u;
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Usuario where upper(alias) like upper('" + alias
					+ "') and upper(clave) like upper('" + clave + "')");
			lUsu = q.list();
			if (lUsu.size() > 0) {
				u = (Usuario) sesion.createQuery("From Usuario where upper(alias) like upper('" + alias
						+ "') and upper(clave) like upper('" + clave + "')").iterate().next();
				if (u.getEstado().getNombreestado().equals("ACTIVO")) {
					switch (u.getRol().getIdrol()) {
					case "AD":
						val = "administrador";
						break;
					case "RE":
						val = "reporte";
						break;
					case "DO":
						val = "cajero";
						break;
					default:
						val = u.getRol().getIdrol();
						break;
					}
				} else {
					val = "Usuario deshabilitado, comuniquese con el Administrador.";
				}
			} else {
				val = "Verifique que el usuario y la contraseña sean los correctos.";
			}

			sesion.getTransaction().commit();
			sesion.close();
			// System.err.println("Retorna Rol: -----> " + val);
			return val;
		} catch (Exception e) {
			// System.err.println("Error Retorna Rol: -----> " +
			// e.getMessage());
			return "error";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String retornaCedula(String alias) {
		// TODO Auto-generated method stub
		try {
			String usu;
			List<Usuario> lUsu;
			Usuario u;
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Usuario where upper(alias) like upper('" + alias + "')");
			lUsu = q.list();
			if (lUsu.size() > 0) {
				u = (Usuario) sesion.createQuery("From Usuario where upper(alias) like upper('" + alias + "')")
						.iterate().next();
				usu = u.getCedulau();
			} else {
				usu = "noexiste";
			}

			sesion.getTransaction().commit();
			sesion.close();
			// System.err.println("Usu: " + usu);
			return usu;
		} catch (Exception e) {
			// System.err.println("Error al obtener usuarios: ---> " +
			// e.getMessage());
			return "error";
		}
	}

	@Override
	public Usuario retornaUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Usuario u = null;
		sesion = HibernateUtil.getSessionFactory().openSession();
		String sql = "From Usuario where alias='" + usuario.getAlias() + "'";
		try {
			sesion.beginTransaction();
			u = (Usuario) sesion.createQuery(sql).uniqueResult();
			sesion.getTransaction().commit();
			sesion.close();
		} catch (Exception e) {
			// TODO: handle exception
			sesion.beginTransaction().rollback();
		}
		return u;
	}

	@Override
	public String eliminarUsuario(String idusuario) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Usuario u;
			u = (Usuario) sesion.get(Usuario.class, new String(idusuario));
			sesion.delete(u);
			sesion.getTransaction().commit();
			sesion.close();
			return "Eliminado.";
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return e.getCause().getMessage();
		}
	}

}
