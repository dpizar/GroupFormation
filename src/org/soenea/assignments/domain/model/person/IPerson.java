package org.soenea.assignments.domain.model.person;

public interface IPerson {

	public String getUserName() throws Exception;
	public void setUserName(String userName)throws Exception;
	public String getPassword()throws Exception;
	public void setPassword(String password) throws Exception;
	public String getFirstName()throws Exception;
	public void setFirstName(String firstName)throws Exception;
	public String getLastName()throws Exception;
	public void setLastName(String lastName)throws Exception;
	public int getUserType()throws Exception;
	public void setUserType(int userType)throws Exception;
	public int getVersion()throws Exception;
	public void setVersion(int version)throws Exception;
	public int getGroupId()throws Exception;
	public void setGroupId(int groupId)throws Exception;
}
