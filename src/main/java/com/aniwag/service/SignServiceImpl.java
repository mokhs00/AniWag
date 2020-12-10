package com.aniwag.service;

import org.springframework.stereotype.Service;

import com.aniwag.domain.MemberVO;
import com.aniwag.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class SignServiceImpl implements SignService {

	MemberMapper mapper;	

	@Override
	public int SignUp(MemberVO vo) {
		// TODO Auto-generated method stub

		

		return mapper.create(vo);
	}

	@Override
	public int SignIn(MemberVO vo) {
		// TODO Auto-generated method stub

		return vo.getMem_password().equals(mapper.read(vo.getMem_id()).getMem_password()) ? 1 : -1;
	}

	@Override
	public int Modify(MemberVO vo) {

		MemberVO tmp = mapper.read(vo.getMem_id());
		if (!vo.getMem_password().equals(tmp.getMem_password()))
			return -1;

		return mapper.update(vo);
	}

}
