package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.MemberDao;
import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

//저장소. db와 대화하는 class ( MemberDaoImpl )
public class MemberDaoImpl implements MemberDao {

	@Override
	public boolean getId(String id) {
		//id중복체크
		String sql = " SELECT ID "
				+ " FROM MEMBER "
				+ " WHERE ID = ?";
		
		Connection conn = null;						// DB Connection
		PreparedStatement psmt = null;				// SQL
		ResultSet rs = null;						// result
	
		boolean findid = false;
		System.out.println("sql: "+sql);
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				//id가 이미 존재함. = 데이터가 있을때 
				findid = true;	
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		//이미 있으면 true, 없으면 false
		return findid;
	}

	@Override
	public boolean addMember(MemberDto dto) {
		//DB에 추가하는 작업
		String sql = " INSERT INTO MEMBER (ID, PWD, NAME, EMAIL, AUTH)"
				+ "	VALUES (?, ?, ?, ?, 3) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		System.out.println("sql: "+sql);
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getEmail());
			
			
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		
		return count>0?true:false;
	}

	@Override
	public MemberDto login(String id, String pwd) {
		String sql = " SELECT ID, NAME, EMAIL, AUTH "
				+ " FROM MEMBER "
				+ " WHERE ID = ? AND PWD = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MemberDto dto = null;
		
		System.out.println("sql : "+sql);
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			psmt.setString(2, pwd.trim());
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				String _id = rs.getString(1);	// id
				String _name = rs.getString(2);	// name
				String _email = rs.getString(3);// email
				int auth = rs.getInt(4);	// auth
				
				dto = new MemberDto(_id, null, _name, _email, auth);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(psmt, conn, rs);
		}
		
		return dto;
	}
	
	
	

}
