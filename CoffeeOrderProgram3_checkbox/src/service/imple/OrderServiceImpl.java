package service.imple;

import java.util.List;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import dto.MenuDto;
import dto.OrderDto;
import service.OrderService;

public class OrderServiceImpl implements OrderService {

	OrderDao dao = new OrderDaoImpl();

	@Override
	public List<MenuDto> getMenuList() {
		return dao.getMenuList();
	}

	@Override
	public MenuDto getMenuPrice(String coff_name) {
		return dao.getMenuPrice(coff_name);
	}

	@Override
	public boolean insertMenu() {
		return dao.insertMenu();
	}

	@Override
	public List<OrderDto> getOrderList(String id) {
		return dao.getOrderList(id);
	}
	
	
	
}
