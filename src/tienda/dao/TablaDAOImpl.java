package tienda.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import tienda.conexion.HibernateUtil;
import tienda.dto.Tabla;

public class TablaDAOImpl implements TablaDAO {

	private Session sesion;

	public TablaDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Tabla> buscarTodos() {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Tabla");
			@SuppressWarnings("unchecked")
			List<Tabla> lT = q.list();
			sesion.getTransaction().commit();
			sesion.close();
			return lT;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

	@Override
	public String guardarTabla(Tabla tabla) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.save(tabla);
			sesion.getTransaction().commit();
			sesion.close();
			return "Guardado";
		} catch (HibernateException e) {
			// TODO: handle exception
			//System.err.println(e.getMessage()+" CAUSA "+e.getCause().getMessage());
			sesion.getTransaction().rollback();
			sesion.close();
			return e.getCause().getMessage();
		}
	}

	@Override
	public String actualizarTabla(Tabla tabla) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.update(tabla);
			sesion.getTransaction().commit();
			sesion.close();
			//System.err.println("DESDE DAO ---------> " + tabla.getIdtabla() + " ---------> " + tabla.getNombretabla());
			return "Actualizado.";
		} catch (HibernateException e) {
			sesion.getTransaction().rollback();
			sesion.close();
			// TODO: handle exception
			return e.getCause().getMessage();
		}
	}

	@Override
	public String eliminarTabla(String idtabla) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Tabla tb;
			tb = (Tabla) sesion.get(Tabla.class, new String(idtabla));
			sesion.delete(tb);
			sesion.getTransaction().commit();
			sesion.close();
			return "Eliminado";
		} catch (HibernateException e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			System.err.println("DESDE DAO ---------> " + idtabla + " ---------> ");
			return e.getCause().getMessage();
		}
	}

	@Override
	public Tabla buscarTablaPorId(String idtabla) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Tabla tb;
			tb = (Tabla) sesion.get(Tabla.class, new String(idtabla));
			sesion.getTransaction().commit();
			sesion.close();
			return tb;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

}
