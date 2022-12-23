package demoAPI.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(uniqueConstraints = {
		@UniqueConstraint(
				name="mail",columnNames = "mail_id"
				)
})
public class LoginEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	
	@Column
	String mail_id;
	
	@Column
	String password;
	
	
	@Column(unique = true)
	String u_name;

	public LoginEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginEntity(int id, String mail_id, String password, String u_name) {
		super();
		this.id = id;
		this.mail_id = mail_id;
		this.password = password;
		this.u_name = u_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getmail_id() {
		return mail_id;
	}

	public void setmail_id(String mail_id) {
		this.mail_id = mail_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	
}
