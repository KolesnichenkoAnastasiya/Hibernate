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

        final ProductDao productDao= new ProductDao(entityManagerFactory);
//        productDao.init();

//        productDao.findById(2L);

//        System.out.println(productDao.findById(2L));

//        productDao.findAll();

//        System.out.println(productDao.countAllProduct());

//        productDao.printAllProduct();

//        productDao.deleteById(2L);

//        productDao.printAllProduct();

//        Product newProd500 = new Product( 500L, "save product 500", 500);
//
//        productDao.saveOrUpdate(newProd500);

//        productDao.printAllProduct();
//
//        Product newProd1 = new Product( 1L, "save product 1", 1);
//
//        productDao.saveOrUpdate(newProd1);
//
//        productDao.printAllProduct();

        entityManager.close();

        entityManagerFactory.close();
}
}