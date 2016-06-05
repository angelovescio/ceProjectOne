package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course extends ARMS  {
	public int CourseId;
	public static ArrayList<Integer> SemesterCode = new ArrayList<Integer>();
	public String CourseName;
	public static ArrayList<Course> Prereqs = new ArrayList<Course>();
	public Course(String name, int id)
	{
		CourseId = id;
		CourseName = name;
	}
	public void AddPrereq(Course prereq)
	{
		for (Course course : Prereqs) 
		{
			if(course.CourseId == prereq.CourseId)
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
