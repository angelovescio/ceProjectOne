package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Student extends ARMS  {
	private List<UUID> ClassesEnrolled;
	private short MaxClassesAvailable;
	private short MaxSemestersAvailable;
	private List<UUID> SemestersAvailable;
	private List<UUID> SemestersEnrolled;
	private UUID StudentId;
	private String StudentName;
	private short CheckMaxClasses(){
		return 2;
	}
	private short CheckMaxSemesters(){
		return 2;
	}
	private List<UUID> GetSemestersEnrolled()
	{
		List<UUID> ids = new ArrayList<UUID>();
		ids.add(UUID.randomUUID());
		return ids;
	}
	private List<UUID> GetClassesEnrolled()
	{
		List<UUID> ids = new ArrayList<UUID>();
		ids.add(UUID.randomUUID());
		return ids;
	}
	private String GetStudentName()
	{
		return "vesh";
	}
	private UUID GetStudentId()
	{
		return UUID.randomUUID();
	}
	private UUID CreateStudentId()
	{
		return UUID.randomUUID();
	}
	private Boolean EnrollClasses(List<UUID> classes)
	{
		return true;
	}
}
