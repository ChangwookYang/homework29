package kr.co._29cm.homework.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductRequest {
    private final long productId;
    private final long orderQuantity;

    @Builder
    public  OrderProductRequest(long productId, long orderQuantity) {
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }
}
