package ru.geekbrains.model;

import jakarta.persistence.*;
import jdk.jfr.Category;
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
    @Table(name = "products")
    @NamedQueries({
            @NamedQuery(name = "findAllProduct", query = "Select p from Product p"),
            @NamedQuery(name = "countAllProduct", query = "Select count(p) from Product p"),
            @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id_product = :id_product")
    })
    public class Product {
        @Id
        @Column(name="id_product")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id_product;
        @Column(name="title_product", nullable = false, unique = true)
        private String title;
        @Column(name="price_product", nullable = false)
        private int price;

        @OneToMany(mappedBy = "product",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
        private List<Purchase_history> purchase_history;

        public Product ( String title, int price) {
            this.title = title;
            this.price = price;
        }
}
