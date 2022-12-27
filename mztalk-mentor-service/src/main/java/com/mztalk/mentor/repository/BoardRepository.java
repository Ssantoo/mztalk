package com.mztalk.mentor.repository;

import com.mztalk.mentor.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardRepositoryCustom{
    @Query("select b from Board b join fetch b.mentor m where b.status ='YES' and b.id =:id")
    Board findBoardByBoardId(@Param("id") Long id);

}
