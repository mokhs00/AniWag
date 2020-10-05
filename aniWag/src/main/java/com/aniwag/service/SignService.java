package com.aniwag.service;

import com.aniwag.domain.MemberVO;
import com.aniwag.domain.SignUpDTO;

public interface SignService {

	public int SignUp(SignUpDTO dto);	
	public int SignIn(MemberVO vo);
	public int Modify(MemberVO vo);
	
		
}
