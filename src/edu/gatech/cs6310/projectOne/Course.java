package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course extends ARMS  {
	public int CourseId;
	public ArrayList<Integer> SemesterCode = new ArrayList<Integer>();
	public String CourseName;
	public ArrayList<Integer> Prereqs = new ArrayList<Integer>();
	public Course(String name, int id)
	{
		CourseId = id;
		CourseName = name;
	}
	public void AddPrereq(Integer prereq)
	{
		for (Integer course : Prereqs) 
		{
			if(course == prereq)
			{
				return;
			}
		}
		Prereqs.add(prereq);
	}
	public void AddSemester(int semester)
	{
		for (int s : SemesterCode) 
		{
			if(s == semester)
			{
				return;
			}
		}
		SemesterCode.add(semester);
	}
}
