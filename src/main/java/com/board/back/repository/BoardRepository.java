package com.board.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.board.back.vo.BoardVo;

public interface BoardRepository extends JpaRepository<BoardVo, Integer>{

}
