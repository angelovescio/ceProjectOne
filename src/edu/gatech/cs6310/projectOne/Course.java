package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

public class Course{
	public int CourseId;
	public ArrayList<Integer> SemesterCode = new ArrayList<Integer>();
	public String CourseName;
	public ArrayList<Course> Prereqs = new ArrayList<Course>();
	public Course(String name, int id)
	{
		CourseId = id;
		CourseName = name;
	}
	public void AddPrereq(Course prereq)
	{
		for(Course c : Prereqs)
		{
			if(c.CourseId == prereq.CourseId)
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
	public Boolean hasSemester(Integer id)
	{
		for (int s : SemesterCode) 
		{
			if(id == s)
			{
				return true;
			}
		}
		return false;
	}
	public String getClassTitle(){
		return CourseName;
	}
}
