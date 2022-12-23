package demoAPI.controller;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demoAPI.Entity.LoginEntity;
import demoAPI.Service.LoginService;

@CrossOrigin("*")
@RestController
public class LoginController {


	@Autowired
	LoginService ls ;

	HashMap<String , Integer> map = new HashMap<>();
	HashMap<String, LocalDateTime> mapDt = new HashMap<>();
	
	@PostMapping("/login/get")
	public String getLogin(@RequestBody LoginEntity log)
	{

		LocalDateTime dt = LocalDateTime.now();
		LocalDateTime ldt = null;
		LocalDateTime cdt = null;
		
		String userid = log.getmail_id();
		String pass = log.getPassword();
		System.out.println(userid+" "+pass );
		String status = ls.getlogin(userid, pass);

		int val = 0;
		
		if(map.get(userid) != null)
		{
			val = map.get(userid);
		}

		if(status.equals("login success"))
		{
			if(val < 5)
			{
				map.put(userid, 0);
				return "login success";
			}
			else
			{
				ldt = mapDt.get(userid);
				cdt = LocalDateTime.now();
				
				int check = ldt.compareTo(cdt);
				
				if(check < 0)
				{
					return "login success";
				}
				else
				{
					return "you are blocked";                    
				}
				
			}
		}
		else 
		{
			System.out.println(userid+" "+val);
			if(val>=4)
			{
				map.put(userid, val+1);
				dt = dt.plusDays(1);
				
				mapDt.put(userid, dt);
				System.out.println("inside 4 catched");
				
				return "you are blocked for 24 hours";   
			}
			map.put(userid, val+1);
			return "invalid login details";
		}

	}

	//	@GetMapping("/login/get")
	//	public String getLogin(@RequestParam String userid,@RequestParam String pass)
	//	{
	////		String userid = lg.getUser_id();
	////		String pass = lg.getPassword();
	//		String status = ls.getlogin(userid, pass);
	//		return status;
	//	}

	@PostMapping("/all/logins")
	public List<LoginEntity> getLogins(@RequestBody LoginEntity log)
	{
		List<LoginEntity> list;
		list = ls.getallLogins();
		for(LoginEntity l :list)
		{
			System.out.println("all details "+l.getU_name()+"  "+l.getmail_id()+"  "+l.getPassword());
		}

		return list;
	}

	@PostMapping("/adduser")
	public String adduser(@RequestBody LoginEntity log)
	{
		String u_id = log.getmail_id();
		String pass = log.getPassword();
		String uname = log.getU_name();

		System.out.println(u_id+"   "+pass+"   "+uname);

		String status = ls.addUser(log);
		return status;
	}

	@PostMapping("/get/oneuser")
	public LoginEntity getOneUser(@RequestBody LoginEntity log_ent)
	{
		int id = log_ent.getId();
		System.out.println(" get one function called");
		LoginEntity le = ls.getOneuser(id);
		
		if(le!=null)
		{
			System.out.println(le.getId()+"   "+le.getmail_id()+"   "+le.getU_name());
		}
		
		return le;
	}

	
	@PostMapping("/add/update")
	public String addRecord(@RequestBody LoginEntity le)
	{
		System.out.println(le.getId()+"   "+"id got");
		System.out.println(le.getId()+" = id     "+le.getmail_id()+" =mail_id    "+le.getU_name()+" =uname  "+le.getPassword()+" password");
		
		return ls.addOneRecord(le);
	}
	
	@PostMapping("/delete")
	public String deleteOneRecord(@RequestBody LoginEntity le)
	{
		int id = le.getId();
		ls.deleteRecord(id);
		
		return "deleted";
	}
	
	
}
