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
		SchedulePrep prep = new SchedulePrep(folder);
		HashMap<Integer,Float> errAndResult = prep.RunPrep();
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
}
