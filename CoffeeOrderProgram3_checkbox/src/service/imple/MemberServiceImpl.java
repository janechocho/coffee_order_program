package service.imple;

import dao.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.MemberDto;
import service.MemberService;

//Service : controller와 dao의 중간자 
public class MemberServiceImpl implements MemberService {

	MemberDao dao = new MemberDaoImpl();
	// MemberDao : 인터페이스, MemberDaoImple : MemberDao를 상속받은 클래스

	@Override
	public boolean getId(String id) {
		
		return dao.getId(id);
	}

	@Override
	public boolean addMember(MemberDto dto) {
		return dao.addMember(dto);
	}

	@Override
	public MemberDto login(String id, String pwd) {
		return dao.login(id, pwd);
	}
	
	
	
	
}
