package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class School extends ARMS {
	private short MaxSemestersAvailable;
	private short SemestersAvailable;
	private short StudentsAtSchool;
	
	private Boolean AddSemester(short semester)
	{
		return true;
	}
	private Boolean CheckConflictingStudentID(ArrayList<Student> students)
	{
		return true;
	}
	private ArrayList<Short> GetSemestersAvailable()
	{
		ArrayList<Short> semesters = new ArrayList<Short>();
		semesters.add((short) 0);
		return semesters;
	}
	private ArrayList<UUID> GetStudentIds()
	{
		ArrayList<UUID> ids = new ArrayList<UUID>();
		ids.add(UUID.randomUUID());
		return ids;
	}
	private Boolean RemoveSemester(short semester)
	{
		return true;
	}
}
