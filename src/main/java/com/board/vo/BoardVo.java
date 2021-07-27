package com.board.vo;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BoardVo {
	private Integer no;
	private String type;
	private String title;
	private String contents;
	private String memberNo;
	private String createdTime;
	private String updatedTime;
	private String likes;
	private String counts;
	
}
