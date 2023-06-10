package kr.co._29cm.homework.vo.response;

import kr.co._29cm.homework.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderProductsResponse {
    private final String productName;
    private final long orderQuantity;

    @Builder
    public OrderProductsResponse(Product product, long orderQuantity) {
        this.productName = product.getName();
        this.orderQuantity = orderQuantity;
    }

    public String getOrderProductsFormat() {
        return productName + " - " + orderQuantity + "개";
    }
}
