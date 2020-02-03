package dao.impl;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDao;
import db.DBClose;
import db.DBConnection;
import dto.MenuDto;
import dto.OrderDto;
import singleton.Singleton;

public class OrderDaoImpl implements OrderDao {

	@Override
	public List<MenuDto> getMenuList() {
		String sql = " SELECT * " + " FROM COFFEE ";

		Connection conn = null; // DB Connection
		PreparedStatement psmt = null; // SQL
		ResultSet rs = null; // result

		System.out.println("sql : " + sql);

		List<MenuDto> list = new ArrayList<MenuDto>();
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				MenuDto dto = new MenuDto(rs.getString(1), // coffee_name,
						rs.getInt(2), // coffee_short,
						rs.getInt(3), // coffee_tall,
						rs.getInt(4));// coffee_grande);
				list.add(dto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}

	@Override
	public MenuDto getMenuPrice(String coff_name) {
		// 선택된 음료의 가격 return하는 메소드
		String sql = " SELECT COFFEE_NAME, COFFEE_SHORT , COFFEE_TALL , COFFEE_GRANDE " + " FROM COFFEE "
				+ " WHERE coffee_name = ? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MenuDto dto = null;
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, coff_name);
			rs = psmt.executeQuery();

			if (rs.next()) {
				int c_short = rs.getInt(2);
				int c_tall = rs.getInt(3);
				int c_grande = rs.getInt(4);

				dto = new MenuDto(coff_name, c_short, c_tall, c_grande);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		return dto;
	}

	@Override
	public boolean insertMenu() {
		int count = 0;
		Singleton s = Singleton.getInstance();
		for (int i = 0; i < s.list.size(); i++) {

			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

			String sql = " INSERT INTO COFFEE_ORDER ( SEQ , MEMBER_ID ,"
					+ " COFFEE_NAME , COFFEE_SIZE , COFFEE_CUP , COFFEE_SYRUP , "
					+ " COFFEE_WHIPPING , COFFEE_SHOT , COFFEE_TOTAL , ORDER_DATE ) "
					+ " VALUES ( SEQ_ORDER.NEXTVAL , ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE ) ";

			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				System.out.println("sql : " + sql);

				psmt.setString(1, s.list.get(i).getId());
				psmt.setString(2, s.list.get(i).getCoffee_name());
				psmt.setString(3, s.list.get(i).getCoffee_size());
				psmt.setInt(4, s.list.get(i).getCup());
				psmt.setString(5, s.list.get(i).getCoffee_syrup());
				psmt.setInt(6, s.list.get(i).getWhipping());
				psmt.setInt(7, s.list.get(i).getShot());
				psmt.setInt(8, s.list.get(i).getTotal());

				count = psmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBClose.close(psmt, conn, rs);
			}
		}
		return count > 0 ? true : false;
	}

	@Override
	public List<OrderDto> getOrderList(String id) {
		String sql = " SELECT * FROM COFFEE_ORDER "
				+ " WHERE MEMBER_ID = ? "
				+ " ORDER BY ORDER_DATE DESC ";

		Connection conn = null; // DB Connection
		PreparedStatement psmt = null; // SQL
		ResultSet rs = null; // result
		
		System.out.println("sql : " + sql);

		List<OrderDto> list = new ArrayList<OrderDto>();
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				OrderDto dto = new OrderDto(rs.getInt(1), // seq,
						rs.getString(2), // id,
						rs.getString(3), // coffee_name,
						rs.getString(4), // coffee_size,
						rs.getInt(5), // cup,
						rs.getString(6), // coffee_syrup,
						rs.getInt(7), // whipping,
						rs.getInt(8), // shot,
						rs.getInt(9), // total,
						rs.getString(10));// order_date)
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}

		return list;
	}

}
