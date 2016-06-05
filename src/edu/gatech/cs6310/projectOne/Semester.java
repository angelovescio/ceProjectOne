package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Semester extends ARMS  {
	private ArrayList<UUID> ClassesAvailable;
	private short MaxClassesAvailable;
	private UUID SemesterId;
	private Boolean AddClasses(ArrayList<UUID> classes)
	{
		return true;
	}
	private Boolean RemoveClasses(List<UUID> classes)
	{
		return true;
	}
	private ArrayList<UUID> GetSemesterIds()
	{
		ArrayList<UUID> ids = new ArrayList<UUID>();
		ids.add(UUID.randomUUID());
		return ids;
	}
}
