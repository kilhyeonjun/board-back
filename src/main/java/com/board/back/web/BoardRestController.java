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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.back.service.BoardService;
import com.board.back.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:3000") // CORS ï¿½ï¿½ï¿½ï¿½ï¿½Ø°ï¿½
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardRestController {

	private final BoardService boardService;
	
	// get paging board # ÆäÀÌÂ¡ Ã³¸®¸¦ ÇÒ ¼ö ÀÖµµ·Ï ¼öÁ¤
	@GetMapping("/board")
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;
		
		return boardService.getPagingBoard(p_num);
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
