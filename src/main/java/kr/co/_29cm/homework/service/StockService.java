package kr.co._29cm.homework.service;

import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.domain.Stock;
import kr.co._29cm.homework.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class StockService {

    private final StockRepository stockRepository;

    @Transactional
    public void decrease(final Product product, final long quantity) {
        Stock stock = stockRepository.findById(product.getStock().getId())
                .orElseThrow(() -> new RuntimeException("상품의 재고가 없습니다."));
        stock.decrease(quantity);

        stockRepository.save(stock);
    }
}
