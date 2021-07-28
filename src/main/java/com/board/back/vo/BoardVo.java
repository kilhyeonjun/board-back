package com.board.back.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@DynamicInsert 
@DynamicUpdate 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer no;
	private String type;
	private String title;
	private String contents;
	private Integer memberNo;
	private String createdTime;
	private String updatedTime;
	private Integer likes;
	private Integer counts;
	
}
