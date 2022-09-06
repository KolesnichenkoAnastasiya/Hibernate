package ru.geekbrains.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ru.geekbrains.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductDao {
    private final EntityManagerFactory emFactory;
    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }
    public void init() {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Product("Product 1", 1000));
        em.persist(new Product("Product 2", 2000));
        em.persist(new Product("Product 3", 3000));
        em.persist(new Product("Product 4", 4000));
        em.persist(new Product("Product 5", 5000));
        em.getTransaction().commit();
    }
    public Optional<Product> findById(Long id){
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Product.class, id)));
    }
    public List<Product> findAll(){
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllProduct", Product.class).getResultList());
    }

    public void printAllProduct(){
        for(Product product: findAll()){
            System.out.println(product);
        }
    }
    public Long countAllProduct(){
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("countAllProduct", Long.class).getSingleResult());
    }
    public void saveOrUpdate (Product product){
        executeInTransaction(entityManager -> {
            if(product.getId()==null){
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        });
    }
    public void deleteById (Long id){
        executeInTransaction(entityManager-> entityManager.createNamedQuery("deleteProductById")
                .setParameter("id", id)
                .executeUpdate());
    }
    private  <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactory.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

