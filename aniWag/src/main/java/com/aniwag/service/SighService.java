package com.aniwag.service;

import com.aniwag.domain.MemberVO;
import com.aniwag.domain.SighUpDTO;

public interface SighService {

	public void SighUp(SighUpDTO dto);	
	public void SighIn(MemberVO vo);
	public void Modify(MemberVO vo);
	
	
	
}
