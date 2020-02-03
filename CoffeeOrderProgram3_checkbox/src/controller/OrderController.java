package controller;

import java.util.List;

import javax.swing.JOptionPane;

import dto.MenuDto;
import dto.OrderDto;
import service.OrderService;
import service.imple.OrderServiceImpl;
import singleton.Singleton;
import view.CartView;
import view.MenuView;
import view.OrderView;
import view.PaymentView;

public class OrderController {

	OrderService orderServ = new OrderServiceImpl();

	
	public List<MenuDto> getMenuList() {
		List<MenuDto> list = orderServ.getMenuList();
		new MenuView(list);
		
		return list;
		
	}
	
	public MenuDto getMenuPrice(String coff_name) {
		MenuDto dto = orderServ.getMenuPrice(coff_name);
		return dto;
	}
	
	
	
	public void orderMenu() {
		new OrderView();
	}
	
	public void cartView() {
		new CartView();
	}
	
	
	
	public List<OrderDto> getOrderList() {
		Singleton s = Singleton.getInstance();
		List<OrderDto> list = orderServ.getOrderList(s.getLoginId());
		new PaymentView(list);
		
		return list;
		
	}
	

	public void orderAf() {
		
		boolean b = orderServ.insertMenu();
		
		if(b) {
			JOptionPane.showMessageDialog(null, "성공적으로 추가되었습니다");
			//주문내역 list
			getOrderList();
		} else {
			JOptionPane.showMessageDialog(null, "추가되지 못했습니다");
			orderMenu();
		}
	}
	

	
	

}
