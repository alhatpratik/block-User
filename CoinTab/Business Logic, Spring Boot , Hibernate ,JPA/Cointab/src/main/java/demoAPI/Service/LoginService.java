package demoAPI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import demoAPI.Entity.LoginEntity;
import demoAPI.Repositories.Login;

@Service
public class LoginService {
	
	@Autowired(required=true)
	Login l;
	
	public String getlogin(String user,String pass)
	{
		String status = "invalid login details";
		
		List<LoginEntity> login_list = l.findAll();
		
		for(LoginEntity ll : login_list)
		{
			if(ll.getmail_id().equals(user) && ll.getPassword().equals(pass))
			{
				status = "login success";
				break;
			}
		}
		
		return status;
	}

	public List<LoginEntity> getallLogins()
	{
		return(l.findAll());
	}
	
	
	public String addUser(LoginEntity obj)
	{
		String status = "Registration failed";
		
//		obj.setPassword(pass);
//		obj.setU_name(u_name);
//		obj.setUser_id(user);
		
		LoginEntity l_obj = l.save(obj);
		
		if(l_obj != null)
		{
			return status = "registered successful";
		}
		else
		{
			return status;
		}
	}
	
	
	public LoginEntity getOneuser(int id)
	{
		LoginEntity log_Ent = null;
		List<LoginEntity> list = l.findAll();
		
		System.out.println(id+" id got in controller");
		for(LoginEntity le : list)
		{
			System.out.println(le.getId()+"   inside login service");
			if(le.getId()==id)
			{
				System.out.println("inside if block");
				log_Ent = le;
				break;
			}
		}
		
		return log_Ent;
	}
	
	
	public String addOneRecord(LoginEntity le)
	{
		
		
		int userid = le.getId();
		String username = le.getU_name();
		String pass = le.getPassword();
		String mail = le.getmail_id();
//		l.delete(le);
		
		System.out.println(userid+"  id  "+"username = "+username+" "+pass+"   password in addonerecord  "+" mail "+mail);
		
		l.addData(userid,username,pass,mail);
		
//		if(ent != null)
//		{
			return "added successfully";
//		}
//		else
//		{
//			return "Not added";
//		}
		
	}
	
	
	public void deleteRecord(int id)
	{
		l.deleteRecord(id);
	}
	
	
}
