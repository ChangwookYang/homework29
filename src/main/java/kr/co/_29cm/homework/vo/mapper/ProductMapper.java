package kr.co._29cm.homework.vo.mapper;

import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.vo.response.ProductResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface ProductMapper {
    ProductResponse toProductResponse(Product product);
    List<ProductResponse> toProductResponseList(List<Product> productList);
}
