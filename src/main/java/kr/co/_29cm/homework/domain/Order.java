package kr.co._29cm.homework.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static kr.co._29cm.homework.constant.Constants.DELIVERY_FEE;
import static kr.co._29cm.homework.constant.Constants.DELIVERY_FREE_ORDER_AMOUNT;

@Getter
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderAmount;

    private Long deliveryFee;

    private Long totalAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderProducts> orderProducts = new ArrayList<>();

    @Builder
    public Order() {
    }

    public void calculateOrderAmount() {
        this.orderAmount = orderProducts.stream().mapToLong(OrderProducts::getOrderProductsPrice).sum();
        if (this.orderAmount < DELIVERY_FREE_ORDER_AMOUNT) {
            this.totalAmount = this.orderAmount + DELIVERY_FEE;
            this.deliveryFee = DELIVERY_FEE;
        } else {
            this.totalAmount = this.orderAmount;
            this.deliveryFee = 0L;
        }
    }
}
