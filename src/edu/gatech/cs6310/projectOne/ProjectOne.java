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
import java.util.Vector;


public class ProjectOne {

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
		ArrayList<Map<Integer,String>> titles = new ArrayList<Map<Integer,String>>();
		ArrayList<Map<Integer,Integer>> avails = new ArrayList<Map<Integer,Integer>>();
		ArrayList<Map<Integer,Integer>> prereqs = new ArrayList<Map<Integer,Integer>>();
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
								Map<Integer,Integer> m = new HashMap<Integer,Integer>();
								m.put(Integer.parseInt(string[0]), Integer.parseInt(string[1]));
								avails.add(m);
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
								prereqs.add(new HashMap<Integer,Integer>(Integer.parseInt(string[0]),Integer.parseInt(string[1])));
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
								Map<Integer,String> m = new HashMap<Integer,String>();
								m.put(Integer.parseInt(string[0]), string[1]);
								titles.add(m);
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
 		String test = "";
	}
	private ArrayList<Student> ProcessStudents(ArrayList<Student> students)
	{
		ArrayList<Student> retval = new ArrayList<Student>();
		for (Student student : students) 
		{
			
		}
		return retval;
	}
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
