package com.mztalk.auction.domain.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    @ManyToOne
    @JoinColumn(name = "boardId", nullable=false)
    @JsonManagedReference
    private Board board;

    private String buyer;

    private Integer currentPrice;


    public Integer getCurrentPrice(){
        return currentPrice;
    }


    public void increase(Integer currentPrice){
        if(this.currentPrice -currentPrice <0){
            throw new RuntimeException("입찰가가 현재가보다 낮습니다.");
        }
        this.currentPrice = currentPrice;
    }

    public Price(Long priceId, String buyer, Integer currentPrice) {
        this.priceId = priceId;
        this.buyer = buyer;
        this.currentPrice = currentPrice;
    }




}
