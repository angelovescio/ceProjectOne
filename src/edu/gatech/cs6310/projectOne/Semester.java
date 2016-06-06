package edu.gatech.cs6310.projectOne;

import java.util.HashMap;

public class Semester
{
	public HashMap<Integer,String> semesters = new HashMap<Integer,String>();
	public Semester(){
		semesters.put(0,"Spring");
		semesters.put(1,"Summer");
		semesters.put(2,"Fall");
		semesters.put(3,"Winter");
	}
}
