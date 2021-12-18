package com.genderfrender.autoServiceApi.dto;

import com.genderfrender.autoServiceApi.entities.Part;

public class PartForUser
{
	private P	art part;
	private int countOfUse;

	public PartForUser()
	{
		
	}

	public PartForUser(Part part, int countOfUse)
	{
		this.part = part;
		this.countOfUse = countOfUse;
	}

	public Part getPart()
	{
		return part;
	}

	public void setPart(Part part)
	{
		this.part = part;
	}

	public int getCountOfUse()
	{
		return countOfUse;
	}

	public void setCountOfUse(int countOfUse)
	{
		this.countOfUse = countOfUse;
	}
}
