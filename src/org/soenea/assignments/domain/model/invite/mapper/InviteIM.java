package org.soenea.assignments.domain.model.invite.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.soenea.assignments.domain.model.invite.Invite;

public class InviteIM {
	
private HashMap<Long,Invite> knownInvites;
	
	public InviteIM()
	{
		knownInvites = new HashMap<Long,Invite>();
	}
	
	public void put(long id, Invite val)
	{
		knownInvites.put(id, val);
	}
	
	public Invite get(long id)
	{
		return knownInvites.get(id);
	}
	
	public List<Invite> getAll()
	{
		List<Invite> iList = new ArrayList<Invite>();
		Collection iList1 = knownInvites.values();
		Iterator<Invite> Invites = iList1.iterator();
		//Iterate through invites.
		while(Invites.hasNext())
		{
			iList.add(Invites.next());
		}
			 
		
		return iList;
	}

}
