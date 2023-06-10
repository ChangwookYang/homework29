package kr.co._29cm.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @Builder
    public Product(String name, Long price, Stock stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
