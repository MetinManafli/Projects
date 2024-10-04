package Helper;

import java.sql.*;

public class DBConnection {
	Connection C = null;

	public DBConnection() {
	}

	public Connection connDb() {
		try {
			this.C = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=1234");
			return C;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return C;
	}
}
