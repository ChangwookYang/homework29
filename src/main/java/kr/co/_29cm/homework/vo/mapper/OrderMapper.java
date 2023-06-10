package kr.co._29cm.homework.vo.mapper;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderProducts;
import kr.co._29cm.homework.vo.response.OrderProductsResponse;
import kr.co._29cm.homework.vo.response.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order, List<OrderProductsResponse> orderProductsResponseList);

    OrderProductsResponse toOrderProductsResponse(OrderProducts orderProducts);
    List<OrderProductsResponse> toOrderProductsResponseList(List<OrderProducts> orderProductsList);
}
