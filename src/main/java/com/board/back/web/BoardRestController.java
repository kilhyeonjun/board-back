package com.board.back.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.service.BoardService;
import com.board.back.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:3000") // CORS �����ذ�
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
	@GetMapping("/board/{no}")
	public ResponseEntity<BoardVo> getBoardByNo(@PathVariable Integer no) {
		return boardService.getBoard(no);
	}
	@PutMapping("/board/{no}")
	public ResponseEntity<BoardVo> updateBoardByNo(
			@PathVariable Integer no, @RequestBody BoardVo board){
		
		return boardService.updateBoard(no, board);
	}
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.deleteBoard(no);
	}
}
