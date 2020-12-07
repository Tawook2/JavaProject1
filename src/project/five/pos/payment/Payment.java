package project.five.pos.payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import project.five.pos.db.DBManager;

public class Payment {
	
	static Connection conn;
	static PreparedStatement ps;
	static ResultSet rs;
	
	public Payment() {
		
	}
	
	public void selectAllPayment() {
		try {
			conn = DBManager.getConnection();

			ps = conn.prepareStatement("select * from payment");

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.printf("%-15d\t%-10d\t%-10s\t%-10s\t%-10s\n",
						rs.getInt("payment_no"),
						rs.getInt("coupon_no"),
						rs.getString("bank_id"),
						rs.getString("payment_type"),
						rs.getString("payment_date")
						
						);
			}
			rs.close();
			ps.close();
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Payment pm = new Payment();
		pm.selectAllPayment();
		
	}	
}
