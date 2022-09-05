package ru.geekbrains;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.homework.ProductDao;
import ru.geekbrains.model.Product;

public class Main {
public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = new Configuration()
        .configure("hibernate.cfg.xml")
        .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        ProductDao.init(entityManager);
//
//        ProductDao.findById(entityManager, 2L);
//
//        System.out.println(ProductDao.findById(entityManager, 2L));
//
//        ProductDao.findAll(entityManager);
//
//        ProductDao.printAllProd(entityManager);
//
//        ProductDao.deleteById(entityManager, 2L);
//
//        ProductDao.printAllProd(entityManager);
//
//        Product newProd500 = new Product( 500L, "save product 500", 500);
//
//        ProductDao.saveOrUpdate(entityManager, newProd500);
//
//        ProductDao.printAllProd(entityManager);
//
//        Product newProd1 = new Product( 1L, "update product 1", 1);
//
//        ProductDao.saveOrUpdate(entityManager, newProd1);
//
//        ProductDao.printAllProd(entityManager);

        entityManager.close();

        entityManagerFactory.close();
}
}