package com.crabapple.util;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import org.apache.log4j.Logger;


public class JdbcHelper {
	// TODO: 需要填充
	private static final String url = "";
	private static final String user = "";
	private static final String password = "";
	
//	private static Logger logger = Logger.getLogger(JdbcHelper.class.getName());
	
	public static Connection getConnection() throws Exception{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance(); 
		Connection conn = DriverManager.getConnection(url, user, password);  
		return conn;
	}
	
	/**
	 * Close the SQL connection
	 * @param connArg  Connection
	 */
	public static void closeConnection(Connection connArg) {
		try {
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
	
	/**
	 * Close the SQL PreparedStatement
	 * @param pStatement  PreparedStatement
	 */
	public static void closePreparedStatement(PreparedStatement pStatement){
		try {
			if (pStatement != null) {
				pStatement.close();
				pStatement = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
	
	/**
	 * Close the SQL ResultSet
	 * @param rs  ResultSet
	 */
	public static void closeResultSet(ResultSet rs){
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}
	
	/**
	 * Close the SQL ResultSet
	 * @param rs  ResultSet
	 */
	public static void close(ResultSet rs, PreparedStatement pStatement, Connection conn){
		closeResultSet(rs);
		closePreparedStatement(pStatement);
		closeConnection(conn);
	}
	
	/**
	 * Close the SQL ResultSet
	 * @param rs  ResultSet
	 */
	public static void close(PreparedStatement pStatement, Connection conn){
		closePreparedStatement(pStatement);
		closeConnection(conn);
	}
	
	public static boolean ignoreSQLException(String sqlState) {
		if (sqlState == null) {
			return false;
		}
		// X0Y32: Jar file already exists in schema
		if (sqlState.equalsIgnoreCase("X0Y32"))
			return true;
		// 42Y55: Table already exists in schema
		if (sqlState.equalsIgnoreCase("42Y55"))
			return true;
		return false;
	}

	public static void printBatchUpdateException(BatchUpdateException b) {
//		logger.error("----BatchUpdateException----");
//		logger.error("SQLState:  " + b.getSQLState());
//		logger.error("Message:  " + b.getMessage());
//		logger.error("Vendor:  " + b.getErrorCode());
		System.err.print("Update counts:  ");
		int[] updateCounts = b.getUpdateCounts();
		for (int i = 0; i < updateCounts.length; i++) {
			System.err.print(updateCounts[i] + "   ");
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				if (ignoreSQLException(((SQLException) e).getSQLState()) == false) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
}
