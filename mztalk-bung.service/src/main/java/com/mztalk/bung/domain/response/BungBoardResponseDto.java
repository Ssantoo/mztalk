package com.mztalk.bung.domain.response;

import com.mztalk.bung.domain.entity.BungBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BungBoardResponseDto {

    private String boardId;
    private String imageUrl;
    private String imageName;
    private String objectKey;
    private String count;
    private String title;
    private String category;
    private String content;
    private String writer;
    private long nowGroup;
    private long fullGroup;
    private String deadlineDate;
    private String createDate;


    public BungBoardResponseDto(BungBoard bungBoard, String imageUrl, String imageName) {
        this.boardId = String.valueOf(bungBoard.getBoardId());
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.count = String.valueOf(bungBoard.getBoardCount());
        this.title = bungBoard.getBoardTitle();
        this.content = bungBoard.getBoardContent();
        this.category = bungBoard.getCategory();
        this.writer = bungBoard.getBoardWriter();
//        this.nowGroup = bungBoard.getNowGroup();
        this.fullGroup = bungBoard.getFullGroup();
        this.deadlineDate = String.valueOf(bungBoard.getDeadlineDate());
        this.createDate = String.valueOf(bungBoard.getCreateDate());
    }
}
