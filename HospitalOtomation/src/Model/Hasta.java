package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;

	public Hasta() {
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
	}

	public boolean register(String tcno, String password, String name) {
		String query = "INSERT INTO user" + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		boolean count = true;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno + "'");
			while (rs.next()) {
				count = false;
				Helper.ShowMsg("Bu TC numarasına ait kayıt bulunmaktadır");
				break;
			}
			if (count) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String app_date) {
		String query = "INSERT INTO appointment" + "(doctor_id,hasta_id,doctor_name,hasta_name,app_date) VALUES"
				+ "(?,?,?,?,?)";
		boolean key = false;
		boolean count = true;
		try {
			if (count) {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setInt(2, hasta_id);
				preparedStatement.setString(3, doctor_name);
				preparedStatement.setString(4, hasta_name);
				preparedStatement.setString(5, app_date);
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
	
	public boolean updateWhourStatus(int doctor_id,String wdate)throws SQLException {
		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ?";
		boolean key = false;
		boolean count = true;
		try {
			if (count) {
				st = con.createStatement();
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, "p");
				preparedStatement.setInt(2, doctor_id);
				preparedStatement.setString(3,wdate);
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}

}
