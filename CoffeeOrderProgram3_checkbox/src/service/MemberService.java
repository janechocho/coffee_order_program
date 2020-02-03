package service;

import dto.MemberDto;

public interface MemberService {
	//인터페이스를 사용하면 prototype(메소드의 사양)을 한눈에 볼수있음.

	
	public boolean getId(String id); 
	public boolean addMember(MemberDto dto);
	public MemberDto login(String id, String pwd);
}
