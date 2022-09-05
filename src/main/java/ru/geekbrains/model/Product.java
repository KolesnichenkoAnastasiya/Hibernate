package ru.geekbrains.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    @Table(name = "products")
    public class Product {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String title;

        @Column(nullable = false)
        private int price;

        public Product (String title, int price) {
            this.title = title;
            this.price = price;
        }

        public Product (Long id, String title, int price) {
            this.id=id;
            this.title = title;
            this.price = price;
        }

    }
