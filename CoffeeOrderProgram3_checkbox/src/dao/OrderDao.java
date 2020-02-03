package dao;

import java.util.List;

import dto.MenuDto;
import dto.OrderDto;

public interface OrderDao {
	public List<MenuDto> getMenuList(); 	//메뉴판
	public List<OrderDto> getOrderList(String id); 	//주문내역
	
	public MenuDto getMenuPrice(String coff_name);
	public boolean insertMenu(); //주문하기
	
	
}
