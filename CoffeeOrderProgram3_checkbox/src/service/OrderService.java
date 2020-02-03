package service;

import java.util.List;

import dto.MenuDto;
import dto.OrderDto;

public interface OrderService {

	public List<MenuDto> getMenuList(); 
	public List<OrderDto> getOrderList(String id);
	
	public MenuDto getMenuPrice(String coff_name);
	public boolean insertMenu();
	
}
