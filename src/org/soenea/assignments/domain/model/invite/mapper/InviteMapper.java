package org.soenea.assignments.domain.model.invite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;

import org.soenea.assignments.domain.model.invite.Invite;
import org.soenea.assignments.services.invite.TDG.InviteTDG;
import org.soenea.assignments.domain.model.invite.mapper.InviteMapper;

public class InviteMapper {
	
	private static InviteIM imap = new InviteIM();
	private static boolean loadedAll = false;

	public static Invite find(long id) throws SQLException, DomainObjectNotFoundException
	{
		Invite inv = imap.get(id);

		if(inv == null)
		{

			ResultSet rs = InviteTDG.find(id);

			if(rs.next())
			{
				//inv = new Invite( rs.getLong("i.id"), rs.getLong("i.userid"), rs.getInt("i.groupid"),rs.getInt("i.version") );
				inv = getInvite(rs);
			}
			else
			{
				throw new DomainObjectNotFoundException("Cannot find a user with id " + id);
			}
			rs.close();
		}
		return inv;
	}

	private static Invite getInvite(ResultSet rs) throws SQLException {
		//Invite( long id, long userId, long groupId, int version)
		//"SELECT i.id, i.groupid, i.userid, i.version FROM "
		Invite result = new Invite(
				rs.getLong("i.inviteId"),
				rs.getInt("i.version"),
				rs.getString("i.userid"),
				rs.getLong("i.groupid")				
				);

		//Checks to see if the invite isn't already in the identity map first...
		if(imap.get(result.getId()) == null)
			imap.put(result.getId(), result);

		return result;
	}

	public static List<Invite> findAll() throws SQLException
	{
		List<Invite> inviteList = null;


		if(!loadedAll)
		{
			ResultSet rs = InviteTDG.findAll();
			inviteList = new ArrayList<Invite>();

			//This while adds users to the list
			while(rs.next())
			{
				//inviteList.add( new Invite(rs.getLong("i.id"), rs.getLong("i.userid"), rs.getInt("i.groupid"),rs.getInt("i.version")) );
				inviteList.add(getInvite(rs));
			}
			rs.close();
			loadedAll = true;
		}
		else
		{
			inviteList = imap.getAll();
		}

		return inviteList;
	}
	
	public static int insert(Invite invite) throws SQLException
	{
			return InviteTDG.insert( invite.getUserId(), invite.getGroupId() );
	}

	public static int delete(Invite invite) throws SQLException
	{
			return InviteTDG.delete(invite.getVersion(),invite.getUserId(), invite.getGroupId());
	}

}
