package kr.co._29cm.homework.vo.response;

import kr.co._29cm.homework.domain.Order;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderResponse {

    private final long orderAmount;
    private final long deliveryFee;
    private final long totalAmount;
    private final List<OrderProductsResponse> orderProductsResponseList;

    @Builder
    public OrderResponse(Order order,
                         List<OrderProductsResponse> orderProductsResponseList) {
        this.orderAmount = order.getOrderAmount();
        this.deliveryFee = order.getDeliveryFee();
        this.totalAmount = order.getTotalAmount();
        this.orderProductsResponseList = orderProductsResponseList;
    }
}
