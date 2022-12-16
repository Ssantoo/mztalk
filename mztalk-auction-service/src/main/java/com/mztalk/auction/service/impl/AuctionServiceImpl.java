package com.mztalk.auction.service.impl;

import com.mztalk.auction.domain.dto.BoardDto;
import com.mztalk.auction.domain.entity.Board;
import com.mztalk.auction.repository.BoardRepository;
import com.mztalk.auction.repository.impl.CustomAuctionRepositoryImpl;
import com.mztalk.auction.service.AuctionService;
import com.netflix.discovery.converters.Auto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final BoardRepository boardRepository;

    @Transactional
    @Override
    public Long insertBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity()).getBId();
    }

    @Override
    public int updateBoard(Long bId, BoardDto boardDto) {
        return boardRepository.boardUpdate(bId, boardDto);
    }


}