package ru.geekbrains.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Customer")
@NamedQueries({
        @NamedQuery(name = "findAllCustomer", query = "Select c from Customer c"),
        @NamedQuery(name = "countAllCustomer", query = "Select count(c) from Customer c"),
        @NamedQuery(name = "deleteCustomerById", query = "delete from Customer c where c.id_customer = :id_customer")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_customer")
    private Long id_customer;
    @Column(nullable = false, unique = false)
    private String customerName;
    @Column(nullable = false, unique = false)
    private String customerSurname;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Purchase_history> products;
    public Customer (String customerName, String customerSurname){
        this.customerName=customerName;
        this.customerSurname=customerSurname;
    }
}

