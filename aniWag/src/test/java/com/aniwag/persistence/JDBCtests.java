package com.aniwag.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCtests {
	static {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false","root","1234");
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "carpedm", "carpedm1q");
			log.info(conn);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
