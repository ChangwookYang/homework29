package kr.co._29cm.homework.runner;

import kr.co._29cm.homework.constant.Constants;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.vo.request.OrderProductRequest;
import kr.co._29cm.homework.vo.response.OrderProductsResponse;
import kr.co._29cm.homework.vo.response.OrderResponse;
import kr.co._29cm.homework.vo.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static kr.co._29cm.homework.constant.Constants.EMPTY;
import static kr.co._29cm.homework.constant.Constants.SPACE;

@RequiredArgsConstructor
@ConditionalOnProperty(value = "console-input.enabled", matchIfMissing = true)
@Component
public class ConsoleInputRunner implements CommandLineRunner {

    private final OrderService orderService;
    private final ProductService productService;

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
            String command = scanner.nextLine();
            if (Constants.ORDER.equals(command)) {

                try {
                    // 상품 전체 목록 출력
                    printAllProductList();

                    List<OrderProductRequest> orderProductRequestList = new ArrayList<>();
                    while (true) {
                        System.out.print("상품번호 : ");
                        String productId = scanner.nextLine();
                        if (productId.equals(EMPTY) || productId.equals(SPACE)) {
                            // 주문 요청
                            Order order = orderService.requestOrder(orderProductRequestList);

                            // 주문내역 출력
                            printOrderResponse(orderService.selectOrderProductsInformation(order));
                            break;
                        } else {
                            // 주문상품 입력
                            System.out.print("수량 : ");
                            String orderQuantity = scanner.nextLine();
                            orderProductRequestList.add(getOrderProductRequest(productId, orderQuantity));
                        }
                    }
                } catch (RuntimeException re) {
                    System.out.println(re.getClass().getSimpleName() + " 발생. " + re.getMessage());
                } catch (Exception e) {
                    System.out.println("Exception 발생.");
                }

            } else if (Constants.QUIT.equals(command)) {
                System.out.println("고객님의 주문 감사합니다.");
                break;
            }
        }
    }

    private void printAllProductList() {
        System.out.println("상품번호\t\t상품명\t\t판매가격\t\t재고");
        List<ProductResponse> productResponseList = productService.findAllProducts();
        for (ProductResponse productResponse : productResponseList) {
            System.out.print(productResponse.getProductId() + "\t\t");
            System.out.print(productResponse.getProductName() + "\t\t");
            System.out.print(productResponse.getPrice() + "\t\t");
            System.out.print(productResponse.getQuantity());
            System.out.println();
        }
    }

    private OrderProductRequest getOrderProductRequest(String productId, String orderQuantity) {
        return OrderProductRequest.builder()
                .productId(Long.parseLong(productId))
                .orderQuantity(Long.parseLong(orderQuantity))
                .build();
    }

    private void printOrderResponse(OrderResponse orderResponse) {
        System.out.println("주문 내역 : ");
        printLine();
        for (OrderProductsResponse orderProductsResponse : orderResponse.getOrderProductsResponseList()) {
            System.out.println(orderProductsResponse.getOrderProductsFormat());
        }
        printLine();
        System.out.println("주문금액: " + getPriceFormat(orderResponse.getOrderAmount()));
        if (orderResponse.getDeliveryFee() > 0) {
            printLine();
            System.out.println("배송비: " + getPriceFormat(orderResponse.getDeliveryFee()));
        }
        printLine();
        System.out.println("지불금액: " + getPriceFormat(orderResponse.getTotalAmount()));
        printLine();
    }

    private String getPriceFormat(long price) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");
        return decimalFormat.format(price);
    }

    private void printLine() {
        System.out.println("---------------------------------------");
    }
}
