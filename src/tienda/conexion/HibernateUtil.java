package tienda.conexion;

import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			Configuration configuracion = new Configuration();
			configuracion.configure("conexion.cfg.xml");
			StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
					.applySettings(configuracion.getProperties());
			sessionFactory = configuracion.buildSessionFactory(ssrb.build());
		} catch (Throwable e) {
			// TODO: handle exception
			System.err.println("Falla al crear el SessionFactory Inicial: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() throws HibernateException {
		Session session;
		session = getSessionFactory().openSession();
		return session;
	}

}
