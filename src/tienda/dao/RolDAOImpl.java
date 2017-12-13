package tienda.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import tienda.conexion.HibernateUtil;
import tienda.dto.Rol;

public class RolDAOImpl implements RolDAO {

	private Session sesion;

	public RolDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Rol> buscarTodos() {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q = sesion.createQuery("From Rol");
			@SuppressWarnings("unchecked")
			List<Rol> l = q.list();
			sesion.getTransaction().commit();
			sesion.close();
			return l;
		} catch (Exception e) {
			// TODO: handle exception
			sesion.getTransaction().rollback();
			sesion.close();
			return null;
		}
	}

	@Override
	public String guardar(Rol objeto) {
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
	public String actualizar(Rol objeto) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.update(objeto);
			sesion.getTransaction().commit();
			sesion.close();
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
			Rol obj;
			obj = (Rol) sesion.get(Rol.class, new String(idobjeto));

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
	public Rol buscarRolPorId(String idobjeto) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Rol obj;
			obj = (Rol) sesion.get(Rol.class, new String(idobjeto));
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

}
