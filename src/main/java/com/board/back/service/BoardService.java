package com.board.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.board.back.repository.BoardRepository;
import com.board.back.vo.BoardVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public List<BoardVo> getAllBoard(){
		return boardRepository.findAll();
	}

	public BoardVo createBoard(BoardVo board) {
		return boardRepository.save(board);
	}
}
