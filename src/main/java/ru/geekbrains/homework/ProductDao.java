package ru.geekbrains.homework;

import jakarta.persistence.EntityManager;
import ru.geekbrains.model.Product;
import java.util.List;

public class ProductDao {

    public static void init(EntityManager em) {
        em.getTransaction().begin();
        em.persist(new Product("Product 1", 1000));
        em.persist(new Product("Product 2", 2000));
        em.persist(new Product("Product 3", 3000));
        em.persist(new Product("Product 4", 4000));
        em.persist(new Product("Product 5", 5000));
        em.getTransaction().commit();
    }
    public static Product findById(EntityManager em, Long id){
        Product product = em.find(Product.class, id);
        return product;
    }
    public static List<Product> findAll(EntityManager em){
        List<Product> products = em.createQuery("select p from Product p", Product.class)
                .getResultList();
        return products;
    }
    public static void printAllProd (EntityManager em){
        for (Product product : findAll(em)) {
            System.out.println(product);
        }
    }
    public static void deleteById(EntityManager em, Long id){
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
    }
    public static void saveOrUpdate(EntityManager em, Product product) {
       em.getTransaction().begin();
       if (product.getId() == null) {
           em.persist(product);
       } else {
           em.merge(product);
       }
       em.getTransaction().commit();
    }
}

