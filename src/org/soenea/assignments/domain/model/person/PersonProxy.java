package org.soenea.assignments.domain.model.person;

import org.soenea.assignments.domain.model.person.mapper.PersonMapper;

public class PersonProxy implements IPerson {

	private String userName;
    private Person realperson;	
	

	public PersonProxy(String userName) {
		super();
		this.userName = userName;
	}
	public Person getRealPerson() throws Exception
    {
    	if(realperson==null)
    		realperson = PersonMapper.findUser(userName);
		return realperson;
    }
	public String getUserName() throws Exception {
		return getRealPerson().getUserName();
	}
	public void setUserName(String userName) throws Exception {
		getRealPerson().setUserName(userName);
	}
	public String getPassword() throws Exception {
		return getRealPerson().getPassword();
	}
	public void setPassword(String password) throws Exception {
		getRealPerson().setPassword(password);
	}
	public String getFirstName() throws Exception {
		return getRealPerson().getFirstName();
	}
	public void setFirstName(String firstName) throws Exception {
		getRealPerson().setFirstName(firstName);
	}
	public String getLastName() throws Exception {
		return getRealPerson().getLastName();
	}
	public void setLastName(String lastName) throws Exception {
		getRealPerson().setLastName(lastName);
	}
	public int getUserType() throws Exception {
		return getRealPerson().getUserType();
	}
	public void setUserType(int userType) throws Exception {
		getRealPerson().setUserType(userType);
	}
	public int getVersion() throws Exception {
		return getRealPerson().getVersion();
	}
	public void setVersion(int version) throws Exception {
		getRealPerson().setVersion(version);
	}
	public int getGroupId() throws Exception {
		return getRealPerson().getGroupId();
	}
	public void setGroupId(int groupId) throws Exception {
		getRealPerson().setGroupId(groupId);
	}

	
}
