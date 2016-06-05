package edu.gatech.cs6310.projectOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class ProjectOne {
	public static ArrayList<Course> Courses = new ArrayList<Course>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Database db = new Database("~/ARMS");
		CharSequence cs1 = "Availability";
		CharSequence cs2 = "Prerequisites";
		CharSequence cs3 = "Titles";
		CharSequence cs4 = "Demand";
		File folder = new File("/home/ubuntu");
		File[] listOfFiles = folder.listFiles();
		ArrayList<Avails> avails = new ArrayList<Avails>();
		HashMap<Integer,Integer> prereqs = new HashMap<Integer,Integer>();
		ArrayList<Student> students = new ArrayList<Student>();
 		for (int i = 0; i < listOfFiles.length; i++) 
 		{
			if (listOfFiles[i].isFile()) 
			{
				String name = listOfFiles[i].getName();
				if (name.endsWith("csv"))
				{
					if(name.contains(cs1))
					{
						System.out.println("A " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							if(string.length ==2)
							{
								avails.add(new Avails(Integer.parseInt(string[0]), Integer.parseInt(string[1])));
							}
						}
					}
					else if(name.contains(cs2))
					{
						System.out.println("P " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							if(string.length ==2)
							{
								prereqs.put(Integer.parseInt(string[0]),Integer.parseInt(string[1]));
							}
						}
					}
					else if(name.contains(cs3))
					{
						System.out.println("T " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							if(string.length ==2)
							{
								Course course = new Course(string[1], Integer.parseInt(string[0]));
								Courses.add(course);
							}
						}
					}
					else if(name.contains(cs4))
					{
						System.out.println("D " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							if(string.length ==2)
							{
								if(Integer.parseInt(string[0])==5)
								{
									String brek = "";
								}
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
				}
			}
		}
 		for (Entry m : prereqs.entrySet()) {
 			for (int i=0;i<Courses.size();i++) {
 				Course c = Courses.get(i);
 				Integer p = (Integer)m.getKey();
 				if((Integer)m.getValue()==c.CourseId)
 				{
 					c.AddPrereq(p);
 					Courses.set(i, c);
 					//course.AddPrereq(p);
 				}
			}
		}
 		for (Avails m : avails) {
 			for (int i=0;i<Courses.size();i++) {
 				Course c = Courses.get(i);
 				
 				Integer k = m.courseId;
 				Integer p = m.semesterCode;
 				if(k==c.CourseId)
 				{
 					if(c.SemesterCode.size()>0)
 	 				{
 	 					String otro = "";
 	 				}
 					c.AddSemester(p);
 					Courses.set(i, c);
 				}
			}
		}
 		String test = "";
	}
//	private ArrayList<Course> ProcessCourses(ArrayList<List> course)
//	{
//		ArrayList<Course> retval = new ArrayList<Course>();
//		for (Course c : course) 
//		{
//			
//		}
//		return retval;
//	}
	private static List<String[]> GetItemsFromCsv(File f)
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
