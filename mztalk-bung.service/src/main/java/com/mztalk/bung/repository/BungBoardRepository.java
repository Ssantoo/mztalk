package com.mztalk.bung.repository;

import com.mztalk.bung.domain.entity.BungAddBoard;
import com.mztalk.bung.domain.entity.BungBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BungBoardRepository extends JpaRepository<BungBoard, Long>, BungBoardRepositoryCustom {

    BungBoard findFirstByOrderByBoardIdDesc();

    BungBoard findBungBoardByBoardId(Long boardId);
}