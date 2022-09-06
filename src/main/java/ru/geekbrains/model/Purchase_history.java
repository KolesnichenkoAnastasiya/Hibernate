package ru.geekbrains.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purchase_history")
@NoArgsConstructor

public class Purchase_history {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_history;

    @ManyToOne(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    @ManyToOne(cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;
    private int quantity;


    public Purchase_history(Product prod, int quantity, Customer customer) {
        this.product=prod;
        this.quantity=quantity;
        this.customer=customer;
    }
}
