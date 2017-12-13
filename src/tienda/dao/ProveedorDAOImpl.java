package tienda.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import tienda.conexion.HibernateUtil;
import tienda.dto.Proveedor;

public class ProveedorDAOImpl implements ProveedorDAO {

	private Session sesion;

	public ProveedorDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> buscarTodos() {
		try{
			List<Proveedor> lP;
			sesion=HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Query q=sesion.createQuery("From Proveedor");
			lP=q.list();
			sesion.getTransaction().commit();
			sesion.close();
			return lP;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@Override
	public String guardarProveedor(Proveedor prov) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.save(prov);
			sesion.getTransaction().commit();
			sesion.close();
			return "Guardado";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getCause().getMessage();
		}
	}

	@Override
	public String actualizarProveedor(Proveedor prov) {
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			sesion.update(prov);
			sesion.getTransaction().commit();
			sesion.close();
			return "Actualizado";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getCause().getMessage();
		}
	}

	@Override
	public String eliminarProveedor(String idprov) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();
			Proveedor pr;
			pr = (Proveedor) sesion.get(Proveedor.class, new String(idprov));
			sesion.delete(pr);
			sesion.getTransaction().commit();
			sesion.close();
			return "Eliminado";
		} catch (Exception e) {
			// TODO: handle exception
			return e.getCause().getMessage();
		}
	}

	@Override
	public Proveedor buscarProveedorPorId(String idprov) {
		// TODO Auto-generated method stub
		try {
			sesion = HibernateUtil.getSessionFactory().openSession();
			sesion.beginTransaction();

			Proveedor pr;
			pr = (Proveedor) sesion.get(Proveedor.class, new String(idprov));
			sesion.getTransaction().commit();
			sesion.close();
			return pr;
		} catch (Exception e) {
			// TODO: handle exception
			e.getCause().getMessage();
			return null;
		}
	}

	@Override
	public String retornaIdProveedor() {
		// TODO Auto-generated method stub
		String idpr;
		sesion = HibernateUtil.getSessionFactory().openSession();
		sesion.beginTransaction();
		if (sesion.createQuery("select max(idproveedor) From Proveedor").iterate().next() != null) {
			idpr = ((String) sesion.createQuery("select max(idproveedor) From Proveedor").iterate().next()).toString();
		} else {
			idpr = "0";
		}
		sesion.getTransaction().commit();
		sesion.close();
		return idpr;
	}

}
