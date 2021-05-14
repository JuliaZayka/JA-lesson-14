package ua.lviv;

import java.util.Arrays; 
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Application {
	public static void main(String[] args) {

		// create configuartion object with credentionls to DB
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		
		Item item = new Item(100);
		Cart cart = new Cart(10, "name1");
		Cart cart2 = new Cart(20, "name2");
		Cart cart3 = new Cart(30, "name3");
		Cart cart4 = new Cart(40, "name4");
		item.setCarts(new HashSet<>(Arrays.asList(cart, cart2)));
		session.persist(item);

		transaction.commit();
		session.close();

	}

}

