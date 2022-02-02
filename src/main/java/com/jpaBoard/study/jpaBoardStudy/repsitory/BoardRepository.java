package com.jpaBoard.study.jpaBoardStudy.repsitory;

import com.jpaBoard.study.jpaBoardStudy.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
