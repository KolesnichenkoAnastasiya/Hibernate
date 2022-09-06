package ru.geekbrains.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ru.geekbrains.model.Customer;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class CustomerDao {
    private final EntityManagerFactory emFactoryCust;
    public CustomerDao(EntityManagerFactory emFactory) {
        this.emFactoryCust = emFactory;
    }
    public void init() {
        EntityManager em = emFactoryCust.createEntityManager();
        em.getTransaction().begin();
        em.persist(new Customer("Customer_name_1", "Customer_surname_1"));
        em.persist(new Customer("Customer_name_2", "Customer_surname_2"));
        em.persist(new Customer("Customer_name_3", "Customer_surname_3"));
        em.persist(new Customer("Customer_name_4", "Customer_surname_4"));
        em.persist(new Customer("Customer_name_5", "Customer_surname_5"));
        em.getTransaction().commit();
    }

    public Optional<Customer> findById(Long id){
        return executeForEntityManager(entityManager ->
                Optional.ofNullable(entityManager.find(Customer.class, id)));
    }
    public List<Customer> findAllCustomer(){
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("findAllCustomer", Customer.class).getResultList());
    }

    public void printAllCustomer(){
        for(Customer customer: findAllCustomer()){
            System.out.println(customer);
        }
    }

    public Long countAllCustomer(){
        return executeForEntityManager(entityManager ->
                entityManager.createNamedQuery("countAllCustomer", Long.class).getSingleResult());
    }

    public void saveOrUpdate (Customer customer){
        executeInTransaction(entityManager -> {
            if(customer.getId_customer()==null){
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
            }
        });
    }

    public void deleteCustomerById (Long id){
        executeInTransaction(entityManager-> entityManager.createNamedQuery("deleteCustomerById")
                .setParameter("id_customer", id)
                .executeUpdate());
    }
    private  <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = emFactoryCust.createEntityManager();
        try {
            return function.apply(em);
        } finally {
            em.close();
        }
    }
    private void executeInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = emFactoryCust.createEntityManager();
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
