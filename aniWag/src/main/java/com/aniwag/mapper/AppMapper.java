package com.aniwag.mapper;

import com.aniwag.domain.AppVO;

public interface AppMapper {

	public int create(AppVO vo);
	public int read(String mem_id);
	public int update(AppVO vo);
	public int delete(String mem_id);
	
	
}
