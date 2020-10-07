package com.aniwag.service;

import com.aniwag.domain.MemberVO;

public interface SignService {

	public int SignUp(MemberVO vo);	
	public int SignIn(MemberVO vo);
	public int Modify(MemberVO vo);
	
		
}
