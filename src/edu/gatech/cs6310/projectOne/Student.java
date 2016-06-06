package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.HashMap;

public class Student
{
	public int StudentId;
	public HashMap<Integer,Course> CourseIds = new HashMap<Integer,Course>();
	public ArrayList<Integer> DesiredCourseIds = new ArrayList<Integer>();
	public Student(int id)
	{
		StudentId = id;
	}
	public void AddCourse(Course c)
	{
		CourseIds.put(c.CourseId,c);
	}
	public void AddDesiredCourse(int id)
	{
		for (Integer course : DesiredCourseIds) 
		{
			if(course == id)
			{
				return;
			}
		}
		DesiredCourseIds.add(id);
	}
	public Boolean checkCourse(Integer course)
	{
		for (Integer c : CourseIds.keySet()) {
			if(c == course)
			{
				return true;
			}
		}
		return false;
	}
}
