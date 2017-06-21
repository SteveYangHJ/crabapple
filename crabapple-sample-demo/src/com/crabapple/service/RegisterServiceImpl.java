package com.crabapple.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.crabapple.entity.Account;
import com.crabapple.util.JdbcHelper;

public class RegisterServiceImpl {
	
	public void register(final Account account){
		// 需要连接数据库
//		String query = "Insert into account(username,password) values(?,?)";
//		Connection conn = null;
//		PreparedStatement ps = null;
//
//		try {
//			conn = JdbcHelper.getConnection();
//			ps = conn.prepareStatement(query);
//			ps.setString(1, account.getUsername());
//			ps.setString(2, account.getPassword());
//			ps.executeQuery();
//		} catch (Exception e) {
//			e.printStackTrace(System.err);
//		} finally {
//			JdbcHelper.close(ps, conn);
//		}
	}

}
