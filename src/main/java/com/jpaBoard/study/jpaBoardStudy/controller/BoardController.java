package com.jpaBoard.study.jpaBoardStudy.controller;

import com.jpaBoard.study.jpaBoardStudy.entity.Board;
import com.jpaBoard.study.jpaBoardStudy.repsitory.BoardRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(required = false) String keyword) {
        List<Board> boards;
        if (keyword == null) {
            boards = boardRepository.findAll();
        } else {
            boards = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        }
        model.addAttribute("boards", boards);
        model.addAttribute("keyword", keyword);
        return "board/list";
    }
    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Long boardId) throws NotFoundException {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(NotFoundException::new);
        model.addAttribute("board", board);
        return "board/detail";
    }

}
