package com.mztalk.mentor.domain.dto;

import com.mztalk.mentor.domain.Status;
import com.mztalk.mentor.domain.entity.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MentorDto {

    private Long id;
    private Application application;
    private String mentorNickname;
    private Board board;
    private List<Score> scores = new ArrayList<>();
    private List<Mentee> mentees = new ArrayList<>();
    private Status status;

    public Mentor toEntity(){
        Mentor mentor = Mentor.builder()
                .id(id)
                .application(application)
                .mentorNickname(mentorNickname)
                .board(board)
                .scores(scores)
                .mentees(mentees)
                .status(Status.YES)
                .build();
        return mentor;
    }

    public MentorDto(Mentor mentor) {
        this.id = mentor.getId();
        this.application = mentor.getApplication();
        this.mentorNickname = mentor.getMentorNickname();
        this.board = mentor.getBoard();
        this.scores = mentor.getScores();
        this.mentees = mentor.getMentees();
        this.status = mentor.getStatus();
    }

}