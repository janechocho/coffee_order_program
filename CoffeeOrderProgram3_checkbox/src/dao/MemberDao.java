package dao;

import dto.MemberDto;

//DB와 대화
public interface MemberDao {

	public boolean getId(String id);
	//id 중복체크 확인
	
	public boolean addMember(MemberDto dto); 
	//회원가입
	
	public MemberDto login(String id, String pwd);
	//로그인
}
