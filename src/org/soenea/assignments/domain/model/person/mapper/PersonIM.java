package org.soenea.assignments.domain.model.person.mapper;
import org.soenea.assignments.domain.model.person.IPerson;
import org.soenea.assignments.domain.model.person.Person;
public class PersonIM {
	
    public static IPerson personIM = new Person(null, 0, null, null, null, 0, 0);

    public static boolean has(String userName) throws Exception{
		if(personIM.getUserName()==userName)
			return true;
		return false;
	}
    
	public static boolean has(String userName,String Password) throws Exception{
		if(personIM.getUserName()==userName&&personIM.getPassword()==Password)
			return true;
		return false;
	}

	public static Person get ()
	{
		return (Person) personIM;
	}
	public static void put(IPerson u1)
	{
		personIM = u1;
	}

	public static void put(int version, int groupId) throws Exception {
		// TODO Auto-generated method stub
		personIM.setVersion(version);
		personIM.setGroupId(groupId);
		
	}
}