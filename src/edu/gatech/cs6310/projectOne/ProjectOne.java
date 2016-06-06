package edu.gatech.cs6310.projectOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class ProjectOne {
	
	static ArrayList<Course> Courses = new ArrayList<Course>();
	static ArrayList<Avails> avails = new ArrayList<Avails>();
	static HashMap<Integer,Course> prereqs = new HashMap<Integer,Course>();
	static ArrayList<Student> students = new ArrayList<Student>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File folder = new File("/home/ubuntu");
		ParseInputFiles(folder);
 		OrganizeSchedule();
 		FinishAndOutput();
	}
	static void FinishAndOutput()
	{
		Semester sem = new Semester();
		GBRUtility gs = new GBRUtility(Courses,students, sem);
		HashMap<Integer,Float> errAndResult = gs.runGBR();
		for(Entry<Integer,Float> ret : errAndResult.entrySet())
		{
			if(ret.getKey() == 0)
			{
				System.out.printf("X=%.2f", ret.getValue());
			}
			else
			{
				System.out.printf("X=Error Code: %d", ret.getKey());
			}
		}
	}
	static void ParseInputFiles(File folder)
	{
		File[] listOfFiles = folder.listFiles();
		CharSequence cs1 = "Availability";
		CharSequence cs2 = "Prerequisites";
		CharSequence cs3 = "Titles";
		CharSequence cs4 = "Demand";
		List<String[]> prereqContent = new ArrayList<String[]>();
		List<String[]> courseContent = new ArrayList<String[]>();
		List<String[]> studentContent = new ArrayList<String[]>();
		List<String[]> availsContent = new ArrayList<String[]>();
 		for (int i = 0; i < listOfFiles.length; i++) 
 		{
			if (listOfFiles[i].isFile()) 
			{
				String name = listOfFiles[i].getName();
				if (name.endsWith("csv"))
				{
					if(name.contains(cs1))
					{
						availsContent.addAll(GetItemsFromCsv(listOfFiles[i]));
						
					}
					else if(name.contains(cs2))
					{
						prereqContent.addAll(GetItemsFromCsv(listOfFiles[i]));
						
					}
					else if(name.contains(cs3))
					{
						courseContent.addAll(GetItemsFromCsv(listOfFiles[i]));
					}
					else if(name.contains(cs4))
					{
						studentContent.addAll(GetItemsFromCsv(listOfFiles[i]));
					}	
				}
			}
		}
 		OrganizeAvails(availsContent);
 		OrganizeCourses(courseContent);
 		OrganizePrereqs(prereqContent);
 		OrganizeStudents(studentContent);
	}
	static void OrganizeAvails(List<String[]> content)
	{
		for (String[] string : content) 
		{
			if(string.length ==2)
			{
				avails.add(new Avails(Integer.parseInt(string[0]), Integer.parseInt(string[1])));
			}
		}
	}
	static void OrganizeStudents(List<String[]> content)
	{
		for (String[] string : content) 
		{
			if(string.length ==2)
			{
				Boolean alreadyAddedCourse = false;
				Boolean alreadyAddedStudent = false;
				for(Student s : students)
				{
					for(Integer ci : s.DesiredCourseIds)
					{
						if(ci == Integer.parseInt(string[1]) && Integer.parseInt(string[0]) == s.StudentId)
						{
							alreadyAddedCourse = true;
						}
					}
					if(!alreadyAddedCourse && Integer.parseInt(string[0]) == s.StudentId)
					{
						s.AddDesiredCourse(Integer.parseInt(string[1]));
					}
					if(Integer.parseInt(string[0]) == s.StudentId)
					{
						alreadyAddedStudent = true;
					}
				}
				if(!alreadyAddedStudent)
				{
					Student student = new Student(Integer.parseInt(string[0]));
					if(!alreadyAddedCourse)
					{
						student.AddDesiredCourse(Integer.parseInt(string[1]));
					}
					students.add(student);
				}
			}
		}
	}
	static void OrganizeCourses(List<String[]> content)
	{
		for (String[] string : content) 
		{
			if(string.length ==2)
			{
				Course course = new Course(string[1], Integer.parseInt(string[0]));
				Courses.add(course);
			}
		}
	}
	static void OrganizePrereqs(List<String[]> content)
	{
		for (String[] string : content) 
		{
			if(string.length ==2)
			{
				int courseIdBefore = Integer.parseInt(string[0]);
				int courseIdAfter = Integer.parseInt(string[1]);
				int keyBefore = 0;
				int keyAfter = 0;
				int i=0;
				for (Course c : Courses) 
				{
					if(c.CourseId == courseIdAfter)
					{
						keyAfter = i;
					}
					else if(c.CourseId == courseIdBefore)
					{
						keyBefore = i;
					}
				}
				Course after = Courses.get(keyAfter);
				Course before = Courses.get(keyBefore);
				after.AddPrereq(before);
				Courses.set(keyAfter, after);
				
			}
		}
	}
	static void OrganizeSchedule()
	{
 		for (Avails m : avails) {
 			int i=0;
 			for(Course c : Courses)
			{
 				Integer k = m.courseId;
 				Integer p = m.semesterCode;
 				if(k==c.CourseId)
 				{
 					c.AddSemester(p);
 					Courses.set(i, c);
 				}
 				i++;
			}
		}
 		int i=0;
 		for(Student s : students)
 		{
 			for(Integer id : s.DesiredCourseIds)
 			{
 				for(Course c : Courses)
 				{
 					if(id == c.CourseId)
 					{
	 					for(Course p : c.Prereqs)
	 					{
	 						s.AddCourse(p);
	 					}
 					}
 				}
 			}
 			students.set(i, s);
 			i++;
 		}
	}
	static List<String[]> GetItemsFromCsv(File f)
	{
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(f.getAbsolutePath()));
			try {
				return CSVReader.parseLine(r);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return list;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return list;
		}
	}

}
