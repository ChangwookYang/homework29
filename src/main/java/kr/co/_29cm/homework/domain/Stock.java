package kr.co._29cm.homework.domain;

import kr.co._29cm.homework.exception.SoldOutException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(name = "stocks")
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @Builder
    public Stock(Long quantity) {
        this.quantity = quantity;
    }

    public void decrease(final long orderQuantity) {
        if (this.quantity - orderQuantity < 0) {
            throw new SoldOutException();
        }

        this.quantity = this.quantity - orderQuantity;
    }
}
