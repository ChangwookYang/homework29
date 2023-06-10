package kr.co._29cm.homework.vo.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.vo.response.ProductResponse;
import kr.co._29cm.homework.vo.response.ProductResponse.ProductResponseBuilder;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-07T01:11:43+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.11 (JetBrains s.r.o.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.product( product );

        return productResponse.build();
    }

    @Override
    public List<ProductResponse> toProductResponseList(List<Product> productList) {
        if ( productList == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( productList.size() );
        for ( Product product : productList ) {
            list.add( toProductResponse( product ) );
        }

        return list;
    }
}
