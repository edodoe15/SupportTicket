package com.ptf.rs.tiket.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class TiketDatabase {

	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static Connection conn = null;

	private static final String connStr = "jdbc:mysql://localhost:3306/tiky";
	private static final String username = "root";
	private static final String password = "edodoe2";

	public static void dbConnect() throws SQLException, ClassNotFoundException {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("You need MySql JDBC Driver?");
			e.printStackTrace();
			throw e;
		}

		try {
			conn = DriverManager.getConnection(connStr, username, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console" + e);
			e.printStackTrace();
			throw e;
		}
	}

	public static void dbDisconnect() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static ResultSet dbExecuteQuery(String queryStatement) throws SQLException, ClassNotFoundException {
		Statement statement = null;
		ResultSet resultSet = null;
		CachedRowSet cachedRowSet = null;
		try {
			dbConnect();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(queryStatement);
			cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
			cachedRowSet.populate(resultSet);

		} catch (SQLException e) {
			System.out.println("Problem occurred at executeQuery operation : " + e);
			throw e;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			dbDisconnect();
		}
		return cachedRowSet;
	}

	public static void dbExecuteUpdate(String sqlStatement) throws SQLException, ClassNotFoundException {
		Statement statement = null;
		try {
			dbConnect();
			statement = conn.createStatement();
			statement.executeUpdate(sqlStatement);
		} catch (SQLException e) {
			System.out.println("Problem occurred at executeUpdate operation : " + e);
			throw e;
		} finally {
			if (statement != null) {
				statement.close();
			}
			dbDisconnect();
		}
	}
}


