package kr.co._29cm.homework.vo.response;

import kr.co._29cm.homework.domain.Product;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponse {
    private final long productId;
    private final String productName;
    private final long price;
    private final long quantity;

    @Builder
    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getStock().getQuantity();
    }
}
