package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.exception.SoldOutException;
import kr.co._29cm.homework.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void decrease() {
        Product product = productRepository.findAll().get(0);

        long originQuantity = product.getStock().getQuantity();
        long orderQuantity = 1L;
        stockService.decrease(product, orderQuantity);

        long afterOrderQuantity = product.getStock().getQuantity();

        assertEquals(originQuantity - orderQuantity, afterOrderQuantity);
    }

    @Test
    public void decreaseSoldOutException() {
        Product product = productRepository.findAll().get(0);

        // when
        // then
        assertThrows(SoldOutException.class,
                () -> stockService.decrease(product, Long.MAX_VALUE));

    }
}