package com.board.back.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.board.back.exception.ResourceNotFoundException;
import com.board.back.repository.BoardRepository;
import com.board.back.util.PagingUtil;
import com.board.back.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	public List<BoardVo> getAllBoard(){
		return boardRepository.findAll();
	}

	public int findAllCount() {
		return (int) boardRepository.count();
	}
	
	// get paging boards data
	public ResponseEntity<Map> getPagingBoard(Integer p_num) {
		Map result = null;
		
		PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
		List<BoardVo> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
		pu.setObjectCountTotal(findAllCount());
		pu.setCalcForPaging();
		
		System.out.println("p_num : "+p_num);
		System.out.println(pu.toString());
		
		if (list == null || list.size() == 0) {
			return null;
		}
		
		result = new HashMap<>();
		result.put("pagingData", pu);
		result.put("list", list);
		
		return ResponseEntity.ok(result);
	}	
		
	public BoardVo createBoard(BoardVo board) {
		String nowTime = format.format (System.currentTimeMillis());
		board.setCreatedTime(nowTime);
		board.setUpdatedTime(nowTime);
		return boardRepository.save(board);
	}
	public ResponseEntity<BoardVo> getBoard(Integer no) {
		BoardVo board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}
	public ResponseEntity<BoardVo> updateBoard(Integer no, BoardVo updatedBoard) {
		String nowTime = format.format (System.currentTimeMillis());
		BoardVo board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(nowTime);	
		
		BoardVo	 endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}
	public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer no) {
		BoardVo board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
