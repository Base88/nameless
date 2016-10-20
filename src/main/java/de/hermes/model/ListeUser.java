package de.hermes.model;

import java.util.ArrayList;

public class ListeUser
{
	private ArrayList<User> listeUser = new ArrayList<User>();

	public ListeUser()
	{
	}

	public void addUser(User user)
	{
		listeUser.add(user);
	}

	public ArrayList<User> getListeUser()
	{
		return listeUser;
	}

	public void setListeUser(ArrayList<User> listeUser)
	{
		this.listeUser = listeUser;
	}

	@Override
	public String toString()
	{
		return "ListeUser [listeUser=" + listeUser + "]";
	}
}