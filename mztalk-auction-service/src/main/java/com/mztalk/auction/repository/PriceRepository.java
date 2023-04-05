package com.mztalk.auction.repository;

import com.mztalk.auction.domain.dto.PriceDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long>, CustomAuctionRepository {

//    List<Price> findByBoardId(Long bId);
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from Price p where p.priceId = :priceId")
    Price findByIdWithPessimisticLock(Long priceId);
}
