/*
 *  Date: 2011-12-18 下午12:20:32
 *  Copyright (c) 2011 asiainfo-linkage
 */
package com.csdig.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csdig.db.core.Parameter;
import com.csdig.db.core.QueryRunner;
import com.csdig.db.core.ResultSetHandler;
import com.csdig.db.core.wrappers.NamedParameterStatement;


public class SqlRunner extends QueryRunner {

	private Log log = LogFactory.getLog(SqlRunner.class);

	private Connection conn;

	public SqlRunner(Connection conn) {
		this.conn = conn;
	}

	@Override
	protected void close(Connection conn) throws SQLException {
		ConnectionFactory.releaseConnection(conn);
	}
	
	@Override
	protected Connection prepareConnection() throws SQLException {
		return this.conn;
	}

	@Override
	protected PreparedStatement prepareStatement(Connection conn, String sql)
			throws SQLException {
		log.debug(sql);
		return super.prepareStatement(conn, sql);
	}

	public <T> T query(String sql, Parameter param,
			ResultSetHandler<T> rsh) throws SQLException {
		NamedParameterStatement stmt = null;
		ResultSet rs = null;
		T result = null;
		Connection conn = null;
		try {
			conn = this.prepareConnection();
			stmt = new NamedParameterStatement(conn, sql);
			fillStatement(stmt, param);
			rs = this.wrap(stmt.executeQuery());
			result = rsh.handle(rs);
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				close(rs);
				close(conn);
			} finally {
				if (stmt != null && stmt.getStatement() != null)
					close(stmt.getStatement());
			}
		}

		return result;
	}

	public int update(String sql, Parameter param)
			throws SQLException {
		NamedParameterStatement stmt = null;
		int rows = 0;
		Connection conn = null;
		try {
			conn = this.prepareConnection();
			stmt = new NamedParameterStatement(conn, sql);
			this.fillStatement(stmt, param);
			rows = stmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			if(stmt!=null)
				close(stmt.getStatement());
			close(conn);
		}

		return rows;
	}
	
	public long insert(String sql, Parameter param) throws SQLException {
		NamedParameterStatement stmt = null;
		long id = 0;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			conn = this.prepareConnection();
			stmt = new NamedParameterStatement(conn, sql);
			this.fillStatement(stmt, param);
			stmt.executeUpdate();

			sql = "SELECT LAST_INSERT_ID() AS LII";
			ps = conn.prepareStatement(sql);
			ps.executeQuery(sql);
			rs = ps.getResultSet();
			if (rs.next()) {
				BigDecimal bd = rs.getBigDecimal(1);
				id = bd.longValue();
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (ps != null)
				close(ps);
			if (stmt != null)
				close(stmt.getStatement());
			if (rs != null)
				close(rs);
			close(conn);
		}

		return id;
	}

	
}
