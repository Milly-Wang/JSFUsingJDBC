import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ReferencedBean;

@ManagedBean
@ReferencedBean
public class User {
	String UserName;
	String email;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean save() {
		int result = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emp21","root","YAya0408");
			PreparedStatement ps = con.prepareStatement("insert into user(name,email) values(?,?)");
			ps.setString(1, this.getUserName());
			ps.setString(2, this.getEmail());
			result = ps.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return result==1;
	}
	public String submit() {
		if(this.save()) {
			return "response.xhtml";
		}
		return "home.xhtml";
	}
}
