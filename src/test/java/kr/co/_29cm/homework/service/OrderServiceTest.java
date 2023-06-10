package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.repository.ProductRepository;
import kr.co._29cm.homework.vo.request.OrderProductRequest;
import kr.co._29cm.homework.vo.response.OrderResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void requestOrder() {
        // given
        List<OrderProductRequest> requestList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        int requestCount = Math.min(productList.size(), 3);
        long sumProductAmount = 0;
        for (int i = 0; i < requestCount; i++) {
            Product product = productList.get(i);
            Long quantity = product.getStock().getQuantity();
            sumProductAmount += product.getPrice() * quantity;
            requestList.add(OrderProductRequest.builder().productId(product.getId()).orderQuantity(quantity).build());
        }

        // when
        Order order = orderService.requestOrder(requestList);
        OrderResponse orderResponse = orderService.selectOrderProductsInformation(order);

        // then
        assertEquals(sumProductAmount, order.getOrderAmount());
        assertEquals(requestCount, orderResponse.getOrderProductsResponseList().size());
        if (sumProductAmount < 50000) {
            assertEquals(sumProductAmount + 2500, order.getTotalAmount());
        } else {
            assertEquals(sumProductAmount, order.getTotalAmount());
        }
    }

    @Test
    void requestOrderMultiThreads() throws InterruptedException {
        // given
        int threadCount = 70;
        long productId = 768848L;
        long productOriginQuantity = 45L;
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<OrderProductRequest> requestList = new ArrayList<>();
        requestList.add(OrderProductRequest.builder().productId(productId).orderQuantity(1).build());

        // when
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    orderService.requestOrder(requestList);
                } catch (RuntimeException re) {
                    if (re instanceof SoldOutException) {
                        System.out.println(re.getMessage());
                    }
                } finally {
                    latch.countDown();
                }
            });
            if (productOriginQuantity > 0) {
                productOriginQuantity = productOriginQuantity - 1;
            }
        }
        latch.await();

        // then
        assertEquals(productOriginQuantity, productRepository.findById(productId).get().getStock().getQuantity());
    }

}