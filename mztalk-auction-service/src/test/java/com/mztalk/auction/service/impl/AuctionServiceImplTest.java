package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.entity.Price;
import com.mztalk.auction.repository.PriceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuctionServiceImplTest {

    @Autowired
    private AuctionServiceImpl auctionService;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    public void before(){
        Price price = new Price(1L, "AAA", 100);
        priceRepository.saveAndFlush(price);
    }

    @AfterEach
    public void after(){ priceRepository.deleteAll();}

    @Test
    public void 동시에_요청() throws InterruptedException {
        //100개의 요청을 위해 쓰레드 만들기
        int threadCount = 100;

        //비동기로 실행하는 작업을 단순화하여 사용할 수 있게 도와주는 자바의 API
        ExecutorService executorService = Executors.newFixedThreadPool(32);

        //100개의 요청이 끝날때까지 기다려야함으로
        CountDownLatch latch = new CountDownLatch(threadCount);

        for(int i = 0; i< threadCount; i++) {
            executorService.submit(() -> {
                try {
                    auctionService.priceUpdate(1L, 200);
                } finally {
                    latch.countDown();
                }
            });
        }

        //수행중인 작업을 완료될때까지 떄기해주는 클래스
        latch.await();

        Price price = priceRepository.findById(1L).orElseThrow();

        // 100 -> 200
        assertEquals(200, price.getCurrentPrice());

    }




}