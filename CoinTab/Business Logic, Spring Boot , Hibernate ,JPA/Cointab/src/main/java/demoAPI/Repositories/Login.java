package demoAPI.Repositories;


import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import demoAPI.Entity.LoginEntity;

@Service
public interface Login extends JpaRepository<LoginEntity, Id>{

	@Transactional
	@Modifying
	@Query( value = "update demoAPI.Entity.LoginEntity set password =:pass, u_name=:username,mail_id=:mail where id=:userid" )
	void addData(int userid, String username,String pass,String mail);

	@Transactional
	@Modifying
	@Query(value="delete from demoAPI.Entity.LoginEntity where id=:id")
	void deleteRecord(int id);



}
