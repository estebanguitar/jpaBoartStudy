package com.jpaBoard.study.jpaBoardStudy.repsitory;

import com.jpaBoard.study.jpaBoardStudy.entity.Board;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
//  @Query(nativeQuery = true, value = "SELECT * FROM board WHERE upper(title) like '%#{titleKeyword}%' or upper(content) like")
  List<Board> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
}
