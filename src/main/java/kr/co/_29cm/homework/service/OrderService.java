package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderProducts;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.repository.OrderRepository;
import kr.co._29cm.homework.repository.ProductRepository;
import kr.co._29cm.homework.vo.mapper.OrderMapper;
import kr.co._29cm.homework.vo.request.OrderProductRequest;
import kr.co._29cm.homework.vo.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    private final StockService stockService;

    @Transactional
    public Order requestOrder(final List<OrderProductRequest> orderProductRequestList) {
        if (orderProductRequestList.isEmpty()) {
            throw new RuntimeException("주문 상품이 없습니다.");
        }

        Order order = Order.builder().build();

        for (OrderProductRequest request : orderProductRequestList) {
            final Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException(request.getProductId() + " 일치하는 상품이 없습니다."));
            final long orderQuantity = request.getOrderQuantity();

            stockService.decrease(product, orderQuantity);

            order.getOrderProducts().add(
                    OrderProducts.builder().product(product).order(order).orderQuantity(orderQuantity).build()
            );
        }

        order.calculateOrderAmount();

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public OrderResponse selectOrderProductsInformation(final Order order) {

        return orderMapper.toOrderResponse(
                order,
                orderMapper.toOrderProductsResponseList(order.getOrderProducts())
        );
    }
}
