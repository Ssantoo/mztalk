package com.mztalk.main.controller;


import com.mztalk.main.domain.dto.CMRespDto;
import com.mztalk.main.domain.dto.ReplyRequestDto;
import com.mztalk.main.domain.entity.Reply;
import com.mztalk.main.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/story")
public class ReplyApiController {

    private final ReplyService replyService;

//    @PostMapping("/api/reply")
//    public ResponseEntity<?> saveReply(@Validated @RequestBody ReplyRequestDto replyRequestDto,  BindingResult bindingResult){
//
//        replyService.saveReply(replyRequestDto);
//        return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기성공", ), HttpStatus.CREATED);
//    @PostMapping("/{id}")
//    public Long saveReply(@PathVariable("id") Long id, ReplyRequestDto replyRequestDto){
//
//        return replyService.replySave(id, replyRequestDto);
//    }


    //댓글쓰기
    @PostMapping("/board/{id}/reply")
    public Long replySave(@PathVariable("id") Long id, @RequestBody ReplyRequestDto replyRequestDto){

       return replyService.replySave(id, replyRequestDto);

//        return new ResponseEntity<>(new CMRespDto<>(1, "댓글쓰기성공", result), HttpStatus.CREATED);
    }

    //댓글삭제
    @DeleteMapping("/board/{id}/reply/{replyId}")
    public Long deleteScore(
            @PathVariable("id") Long id,
            @PathVariable("replyId") Long ReplyId
    ){
       return replyService.deleteReply(id, ReplyId);
    }



}