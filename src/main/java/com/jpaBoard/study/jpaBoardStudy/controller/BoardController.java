package com.jpaBoard.study.jpaBoardStudy.controller;

import com.jpaBoard.study.jpaBoardStudy.entity.Board;
import com.jpaBoard.study.jpaBoardStudy.repsitory.BoardRepository;
import com.jpaBoard.study.jpaBoardStudy.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    private BoardRepository boardRepository;
    private BoardValidator boardValidator;


    @Autowired
    public BoardController(BoardRepository boardRepository,
                           BoardValidator boardValidator) {
        this.boardRepository = boardRepository;
        this.boardValidator = boardValidator;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        }else {
            model.addAttribute("board", boardRepository.findById(id).orElse(null));
        }

        return "board/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board, BindingResult bindingResult) {

        boardValidator.validate(board, bindingResult);

        if(bindingResult.hasErrors()) {
            return "board/form";
        }

        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
