package tienda.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import tienda.conexion.HibernateUtil;
import tienda.dto.Estado;

public class EstadoDAOImpl implements EstadoDAO {

	private Session sesion;

	public EstadoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Estado> buscarTodos() {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Estado");
			@SuppressWarnings("unchecked")
			List<Estado> lE = q.list();
			sesion.getTransaction().commit();
			sesion.close();
			return lE;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

	@Override
	public String guardar(Estado objeto) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.save(objeto);
			sesion.getTransaction().commit();
			sesion.close();
			return "Guardado.";
		} catch (HibernateException e) {
			// TODO: handle exception
			// System.err.println(e.getMessage()+" CAUSA
			// "+e.getCause().getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
			return e.getCause().getMessage();
		}
	}

	@Override
	public String actualizar(Estado objeto) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.update(objeto);
			sesion.getTransaction().commit();
			sesion.close();
			// System.err.println("DESDE DAO ---------> " + tabla.getIdtabla() +
			// " ---------> " + tabla.getNombretabla());
			return "Actualizado.";
		} catch (HibernateException e) {
			sesion.getTransaction().rollback();
			sesion.close();
			// TODO: handle exception
			return e.getCause().getMessage();
		}
	}

	@Override
	public String eliminar(String idobjeto) {
		// TODO Auto-generated method stub
		try {

			// System.err.println("DESDE DAO ------------------- "+idobjeto);
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Estado obj;
			obj = (Estado) sesion.get(Estado.class, new String(idobjeto));

			// System.err.println("DESDE DAO -------------------
			// "+obj.getNombreestado());

			sesion.delete(obj);
			sesion.getTransaction().commit();
			sesion.close();
			return "Eliminado.";
		} catch (HibernateException e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return e.getCause().getMessage();
		}
	}

	@Override
	public Estado buscarEstadoPorId(String idobjeto) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Estado obj;
			obj = (Estado) sesion.get(Estado.class, new String(idobjeto));
			sesion.getTransaction().commit();
			sesion.close();
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

	@Override
	public String retornaIdEstado() {
		// TODO Auto-generated method stub
		String id;
		sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		if (sesion.createQuery("select max(idestado) from Estado").iterate().next() != null) {
			id = ((String) sesion.createQuery("select max(idestado) from Estado").iterate().next()).toString();
		} else {
			id = "0";
		}
		sesion.getTransaction().commit();
		sesion.close();
		return id;
	}

	@Override
	public List<Estado> buscarEstadosPorIdTabla(String idtabla) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Estado where idtabla='" + idtabla + "'");
			@SuppressWarnings("unchecked")
			List<Estado> lE = q.list();
			sesion.getTransaction().commit();
			sesion.close();
			return lE;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

	@Override
	public Estado buscarEstadoPorNombreIdTabla(String nombreestado, String idtabla) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion
					.createQuery("From Estado where nombreestado='" + nombreestado + "' and idtabla='" + idtabla + "'");
			Estado e = (Estado) q.uniqueResult();
			sesion.getTransaction().commit();
			sesion.close();
			//System.err.println("ESTADO----------------- "+e.getNombreestado());
			return e;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

}
