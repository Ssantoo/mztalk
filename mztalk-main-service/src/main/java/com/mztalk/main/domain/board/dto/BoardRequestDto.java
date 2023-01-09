package com.mztalk.main.domain.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mztalk.main.domain.board.Board;
import com.mztalk.main.domain.reply.Reply;
import com.mztalk.main.status.BoardStatus;
import com.mztalk.main.status.PrivacyStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {


    private Long id; //글번호

    private String title; //글제목

    private String content; //글내용

    private BoardStatus status; //글상태

    private Long own; //페이지주인

    private PrivacyStatus privacy; //글공개범위

    private String writer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastModifiedDate;

    private String serviceName;

    public Board toEntity(){
        return Board.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .status(BoardStatus.YES)
                .own(own)
                .privacy(privacy)
                .build();
    }






}
