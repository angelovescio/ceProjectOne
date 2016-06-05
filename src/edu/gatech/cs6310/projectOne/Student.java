package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Student extends ARMS  {
	public int StudentId;
	public ArrayList<Integer> CourseIds = new ArrayList<Integer>();
	public ArrayList<Integer> DesiredCourseIds = new ArrayList<Integer>();
	public Student(int id)
	{
		StudentId = id;
	}
	public void AddCourse(int id)
	{
		CourseIds.add(id);
	}
	public void AddDesiredCourse(int id)
	{
		DesiredCourseIds.add(id);
	}
	public void ProcessSchedule(ArrayList<Course> courses)
	{
		for (Course course : courses) {
			for (Course prereq : Course.Prereqs)
			{
				
			}
		}
	}
}
