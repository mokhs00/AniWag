package com.aniwag.service;

import org.springframework.stereotype.Service;

import com.aniwag.domain.MemberVO;
import com.aniwag.domain.SignUpDTO;
import com.aniwag.mapper.AppMapper;
import com.aniwag.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class SignServiceImpl implements SignService {

	MemberMapper memberMapper;
	AppMapper appMapper;

	@Override
	public int SignUp(SignUpDTO dto) {
		// TODO Auto-generated method stub

		if (memberMapper.create(dto.getMemberVO()) != 1)
			return -1;
		if (appMapper.create(dto.getAppVO()) != 1)
			return -1;

		return 1;
	}

	@Override
	public int SignIn(MemberVO vo) {
		// TODO Auto-generated method stub

		return vo.getMem_password().equals(memberMapper.read(vo.getMem_id()).getMem_password()) ? 1 : -1;
	}

	@Override
	public int Modify(MemberVO vo) {

		MemberVO tmp = memberMapper.read(vo.getMem_id());
		if (!vo.getMem_password().equals(tmp.getMem_password()))
			return -1;

		return memberMapper.update(vo);
	}

}
