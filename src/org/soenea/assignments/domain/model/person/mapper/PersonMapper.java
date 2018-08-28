package org.soenea.assignments.domain.model.person.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
import org.soenea.assignments.services.person.TDG.PersonTDG;

public class PersonMapper {
	public static Person authenticate(String userName, String password) throws Exception {
		if(PersonIM.get()!=null)
			if(PersonIM.has(userName,password))
				return (Person) PersonIM.get();
		ResultSet rs = PersonTDG.authenticate(userName, password);
		if(rs.next()){			
			int version=rs.getInt("version");
			String fName=rs.getString("firstName");
			String lName=rs.getString("lastName");
			int gId=rs.getInt("groupId");
			int uType=rs.getInt("userType");
			Person u1 = new Person(userName,version,password,fName,lName,gId,uType);
			PersonIM.put(u1);
			return u1;
		}
		else{
			return null;
		}
	}

	public static ArrayList<IPerson> findGrpUser(int groupId) throws SQLException{
		ArrayList<IPerson> allUsers = new ArrayList<IPerson>();
		ResultSet rs = PersonTDG.FindGrpUser(groupId);
		while(rs.next()) {
				String userName=rs.getString("userName");
				int version=rs.getInt("version");
				String password=rs.getString("password");
				String fName=rs.getString("firstName");
				String lName=rs.getString("lastName");
				int gId=rs.getInt("groupId");
				int uType=rs.getInt("userType");
				Person u1 = new Person(userName,version,password,fName,lName,gId,uType);
				allUsers.add(u1);
		}
		return allUsers;
	}
	
	public static ArrayList<IPerson> findGrpUser2(String personName,int groupId) throws SQLException{
		ArrayList<IPerson> allUsers = new ArrayList<IPerson>();
		ResultSet rs = PersonTDG.FindGrpUser2(personName,groupId);
		while(rs.next()) {
				String userName=rs.getString("userName");
				int version=rs.getInt("version");
				String password=rs.getString("password");
				String fName=rs.getString("firstName");
				String lName=rs.getString("lastName");
				int gId=rs.getInt("groupId");
				int uType=rs.getInt("userType");
				Person u1 = new Person(userName,version,password,fName,lName,gId,uType);
				allUsers.add(u1);
		}
		return allUsers;
	}
	
	public static Person findUser(String userName) throws Exception{
		if(PersonIM.get()!=null)
			if(PersonIM.has(userName))
				return PersonIM.get();
		ResultSet rs = PersonTDG.findUser(userName);
		if(rs.next()){			
			int version=rs.getInt("version");
			String password=rs.getString("password");
			String fName=rs.getString("firstName");
			String lName=rs.getString("lastName");
			int gId=rs.getInt("groupId");
			int uType=rs.getInt("userType");
			Person u1 = new Person(userName,version,password,fName,lName,gId,uType);
			PersonIM.put(u1);
			return u1;
		}
		return null;
	}
	public static ArrayList<IPerson> findAllNoGroupPeople() throws SQLException{
		ArrayList<IPerson> allUsers = new ArrayList<IPerson>();
		ResultSet rs = PersonTDG.FindAllGroupIdZeroUser();
		while(rs.next()) {
			if(rs.getInt("userType")!=0)
			{
				String userName=rs.getString("userName");
				int version=rs.getInt("version");
				String password=rs.getString("password");
				String fName=rs.getString("firstName");
				String lName=rs.getString("lastName");
				int gId=rs.getInt("groupId");
				int uType=rs.getInt("userType");
				Person u1 = new Person(userName,version,password,fName,lName,gId,uType);
				allUsers.add(u1);
			}
		}
		return allUsers;
	}
	
	public static ArrayList<IPerson> findAll() throws SQLException{
		ArrayList<IPerson> allUsers = new ArrayList<IPerson>();
		ResultSet rs = PersonTDG.findAllUsers();
		while(rs.next()) {
			String userName=rs.getString("userName");
			int version=rs.getInt("version");
			String password=rs.getString("password");
			String fName=rs.getString("firstName");
			String lName=rs.getString("lastName");
			int gId=rs.getInt("groupId");
			int uType=rs.getInt("userType");
			Person u1 = new Person(userName,version,password,fName,lName,gId,uType);
			allUsers.add(u1);
		}
		return allUsers;
	}

	public static void insertUser(Person u1) throws SQLException {
		PersonTDG.insertUser(u1.getUserName(),u1.getVersion(),u1.getPassword(),u1.getFirstName(),u1.getLastName(),u1.getGroupId(),u1.getUserType());
	}
	public static void update(Person u1) throws Exception {
		PersonTDG.update(u1.getUserName(),u1.getVersion(),u1.getPassword(),u1.getFirstName(),u1.getLastName(),u1.getGroupId(),u1.getUserType());
        PersonIM.put(u1);	
	}
	// out of our use case
	/*
	public static void updateUser(User u1) {
		PersonTDG.updateUser(u1.getUserName(),u1.getVersion(),u1.getPassword(),u1.getFirstName(),u1.getLastName(),u1.getGroupId(),u1.getUserType());
	}
	 */

	public static void deleteUser(Person u1) throws SQLException {
		PersonTDG.deleteUser(u1.getUserName(),u1.getVersion());
		if(PersonIM.get()==u1)
			PersonIM.put(null);
	}
	public static void RemovePersonGroup(int version,int groupId) throws Exception{
		PersonTDG.RemovePersonGroup(version,  groupId);
		if(PersonIM.get().getGroupId()==groupId)
			PersonIM.put(version,groupId);
	}
	public static int getGroupId(IPerson user) throws Exception {
		if(PersonIM.get()!=null)
			if(PersonIM.get()==user)
				return PersonIM.get().getGroupId();
		ResultSet rs=PersonTDG.getGroupId(user.getUserName());
		int gId = 0;
		if(rs.next()){
			gId=rs.getInt("groupId");
		}
		return gId;
	}
}
