package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


import dto.MemberDto;
import dto.OrderDto;
import service.MemberService;
import service.imple.MemberServiceImpl;
import singleton.Singleton;
import view.loginView;
import view.signView;



//Controller는 Service와만 대화 
public class MemberController {

	MemberService memServ = new MemberServiceImpl();
	//MemberService : 인터페이스, MemberServiceImple : MemberService를 상속받은 클래스 
	//먼저 생성을 해둔다음에 접근
	//컨트롤러 생성 -> 컨트롤러안에서 MeberServiceImple 생성 -> MemberServiceImple에서 Dao생성
	
	public void login() {
		//로그인 화면으로 이동할때
		new loginView();
	}
	
	public void regi() {
		//회원가입하는 화면으로 이동할때
		new signView();
	}
	
	public void regiAf(String id, String pwd, String name, String email) {
		//회원가입 이후 (DB에 넣는 메소드)
		boolean b = memServ.addMember(new MemberDto(id, pwd, name, email, 3));
		
		if(b) {
			//정상적으로 회원가입이 성공후 login()로 이동
			JOptionPane.showMessageDialog(null, "회원가입 성공!");
			login();
		} else {
			//회원가입이 실패했으면 다시 회원가입을 하게끔 regi()로 이동
			JOptionPane.showMessageDialog(null, "회원가입 실패!");
			regi();
		}
	}
	
	public void loginAf(String id, String pwd) {
		MemberDto dto = memServ.login(id, pwd);
		if(dto == null) {
			//DB에서 못찾았을때 = 로그인 실패
			JOptionPane.showMessageDialog(null, "ID나 Password가 틀렸습니다.");
			login();
		} else {
			//DB에서 찾았을때 = 로그인 성공
			JOptionPane.showMessageDialog(null, dto.getId()+"님 환영합니다.");
			//id를 저장 -> (web) session, 지금은 Singleton loginId에 저장
			Singleton s = Singleton.getInstance();
			s.setLoginId(dto.getId());
			List<OrderDto> list = new ArrayList<OrderDto>();
			s.list = list;
			
			//order controller 내 OrderView로 이동
			s.orderCtrl.orderMenu();
		}
	}
	
	public boolean idCheck(String id) {
		return memServ.getId(id);
	}
	
	
	
	
}
