package ru.geekbrains;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.homework.CustomerDao;
import ru.geekbrains.homework.ProductDao;
import ru.geekbrains.model.Customer;
import ru.geekbrains.model.Product;
import ru.geekbrains.model.Purchase_history;

public class Main {
public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        final ProductDao productDao= new ProductDao(entityManagerFactory);
//
//        final CustomerDao customerDao= new CustomerDao(entityManagerFactory);
//
//        productDao.init();
//
//        customerDao.init();
//
//        Purchase_history purchase_history1 = new Purchase_history(new Product("prod", 85),
//                85, new Customer("name", "surname"));
//
//        Purchase_history purchase_history2 = new Purchase_history(new Product("prod22", 50000),
//                10, new Customer("name888888888", "surname9999"));
//
//        entityManager.getTransaction().begin();
//
//        entityManager.persist(purchase_history1);
//
//        entityManager.getTransaction().commit();
//
//        entityManager.getTransaction().begin();
//
//
//        entityManager.persist(purchase_history2);

        entityManager.close();

        entityManagerFactory.close();
}
}