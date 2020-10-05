package com.aniwag.mapper;

import com.aniwag.domain.MemberVO;

public interface MemberMapper {

	public int create(MemberVO vo);
	public MemberVO read(String mem_id);
	public int update(MemberVO vo);
	public int delete(String mem_id);
	
}
