package kr.co._29cm.homework.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Long orderQuantity;

    private Long orderProductsPrice;

    @Builder
    public OrderProducts(Order order, Product product, Long orderQuantity) {
        this.order = order;
        this.product = product;
        this.orderQuantity = orderQuantity;
        this.orderProductsPrice = product.getPrice() * orderQuantity;
    }

}
