package edu.gatech.cs6310.projectOne;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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
		String firstArg = "";
		if (args.length > 1) {
		    try {
		        firstArg = new String(args[1]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + args[1] + " must be an file path.");
		        System.exit(1);
		    }
		}
		File folder = new File(firstArg.replaceFirst("^~",System.getProperty("user.home")));
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
