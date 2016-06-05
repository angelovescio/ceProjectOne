package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Student extends ARMS  {
	private ArrayList<UUID> ClassesEnrolled;
	private short MaxClassesAvailable;
	private short MaxSemestersAvailable;
	private ArrayList<UUID> SemestersAvailable;
	private ArrayList<UUID> SemestersEnrolled;
	private ArrayList<UUID> PrereqClasses;
	private UUID StudentId;
	private String StudentName;
	private short CheckMaxClasses(){
		return 2;
	}
	private short CheckMaxSemesters(){
		return 2;
	}
	private ArrayList<UUID> GetSemestersEnrolled()
	{
		ArrayList<UUID> ids = new ArrayList<UUID>();
		ids.add(UUID.randomUUID());
		return ids;
	}
	private ArrayList<UUID> GetClassesEnrolled()
	{
		ArrayList<UUID> ids = new ArrayList<UUID>();
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
	private Boolean EnrollClasses(ArrayList<UUID> classes)
	{
		return true;
	}
}
