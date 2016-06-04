package edu.gatech.cs6310.projectOne;

import java.util.List;
import java.util.UUID;

public class Class extends ARMS  {
	private UUID ClassId;
	private String ClassName;
	private List<UUID> CurrentStudents;
	private short MaximumStudents;
	private List<Short> SemestersOffered;
	
	private Boolean AddStudents(List<Student> students)
	{
		return true;
	}
	private Boolean RemoveStudents(List<Student> students)
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
