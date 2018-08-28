package org.soenea.assignments.domain.model.invite;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soenea.assignments.domain.model.invite.mapper.InviteMapper;


public class InviteProxy extends DomainObjectProxy<Long, Invite> implements IInvite{
	
	protected InviteProxy(Long id) 
	{
		super(id);
	}

	@Override
	public String getUserId() {
		return getInnerObject().getUserId();
	}

	@Override
	public long getGroupId() {
		return getInnerObject().getGroupId();
	}

	@Override
	public void setUserId(String userId) {
		getInnerObject().setUserId(userId);
	}

	@Override
	public void setGroupId(long groupId) {
		getInnerObject().setGroupId(groupId);
		
	}

	@Override
	protected Invite getFromMapper(Long id) throws MapperException,
			DomainObjectCreationException 
	{
		Invite inv = null;
		try
	    {
			InviteMapper.find(id);
		} 
		 catch (SQLException e) 
		 {
			throw new MapperException(e.getMessage());
		}
		 
		 return inv;
	}

}
