package kr.co._29cm.homework.vo.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderProducts;
import kr.co._29cm.homework.vo.response.OrderProductsResponse;
import kr.co._29cm.homework.vo.response.OrderProductsResponse.OrderProductsResponseBuilder;
import kr.co._29cm.homework.vo.response.OrderResponse;
import kr.co._29cm.homework.vo.response.OrderResponse.OrderResponseBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T01:11:43+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.11 (JetBrains s.r.o.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse toOrderResponse(Order order, List<OrderProductsResponse> orderProductsResponseList) {
        if ( order == null && orderProductsResponseList == null ) {
            return null;
        }

        OrderResponseBuilder orderResponse = OrderResponse.builder();

        if ( order != null ) {
            orderResponse.order( order );
        }
        if ( orderProductsResponseList != null ) {
            List<OrderProductsResponse> list = orderProductsResponseList;
            if ( list != null ) {
                orderResponse.orderProductsResponseList( new ArrayList<OrderProductsResponse>( list ) );
            }
        }

        return orderResponse.build();
    }

    @Override
    public OrderProductsResponse toOrderProductsResponse(OrderProducts orderProducts) {
        if ( orderProducts == null ) {
            return null;
        }

        OrderProductsResponseBuilder orderProductsResponse = OrderProductsResponse.builder();

        orderProductsResponse.product( orderProducts.getProduct() );
        if ( orderProducts.getOrderQuantity() != null ) {
            orderProductsResponse.orderQuantity( orderProducts.getOrderQuantity() );
        }

        return orderProductsResponse.build();
    }

    @Override
    public List<OrderProductsResponse> toOrderProductsResponseList(List<OrderProducts> orderProductsList) {
        if ( orderProductsList == null ) {
            return null;
        }

        List<OrderProductsResponse> list = new ArrayList<OrderProductsResponse>( orderProductsList.size() );
        for ( OrderProducts orderProducts : orderProductsList ) {
            list.add( toOrderProductsResponse( orderProducts ) );
        }

        return list;
    }
}
