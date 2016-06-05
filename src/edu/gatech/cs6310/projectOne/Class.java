package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Class extends ARMS  {
	private UUID ClassId;
	private String ClassName;
	private ArrayList<UUID> CurrentStudents;
	private ArrayList<UUID> PrereqClasses;
	private short MaximumStudents;
	private ArrayList<Short> SemestersOffered;
	
	private Boolean AddStudents(ArrayList<Student> students)
	{
		return true;
	}
	private Boolean RemoveStudents(ArrayList<Student> students)
	{
		return true;
	}
	private Boolean AddToSemester(short semester)
	{
		return true;
	}
	private short CheckMaxStudents()
	{
		return 2;
	}
}
