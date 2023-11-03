package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class commentVO 
{
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
}
