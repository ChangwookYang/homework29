package kr.co._29cm.homework.service;

import kr.co._29cm.homework.repository.ProductRepository;
import kr.co._29cm.homework.vo.mapper.ProductMapper;
import kr.co._29cm.homework.vo.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<ProductResponse> findAllProducts() {
        return productMapper.toProductResponseList(productRepository.findAllProducts());
    }
}
