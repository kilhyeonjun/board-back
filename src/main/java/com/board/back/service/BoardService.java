package com.board.back.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.board.back.repository.BoardRepository;
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

	public BoardVo createBoard(BoardVo board) {
		String nowTime = format.format (System.currentTimeMillis());
		board.setCreatedTime(nowTime);
		board.setUpdatedTime(nowTime);
		return boardRepository.save(board);
	}
}
