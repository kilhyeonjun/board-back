package com.board.back.web;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.service.BoardService;
import com.board.back.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:3000") // CORS 문제해결
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardRestController {

	private final BoardService boardService;
	
	@GetMapping("/board")
	public List<BoardVo> getAllBoard(){
		
		return boardService.getAllBoard();
	}
	
	@PostMapping("/board")
	public BoardVo createBoard(@RequestBody BoardVo board) {
		return boardService.createBoard(board);
	}
}
