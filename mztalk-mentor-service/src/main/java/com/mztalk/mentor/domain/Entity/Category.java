package com.mztalk.mentor.domain.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category {

    @Id@GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @OneToMany
    private List<Board> boards = new ArrayList<>();
}
