package edu.gatech.cs6310.projectOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;

public class GBRUtility 
{
	ArrayList<Student> Students;
	ArrayList<Course> Courses;
	Semester Sem;
	int sSize = 0;
	int cSize = 0;
	int mSize = 0;
	GRBModel Model;
	GRBEnv Env;
	GRBVar GVar;
	GRBVar[][][] GConstSet;
	Boolean isEnvSetup = false;
	private static final int MAXIMUM = 2;
	public GBRUtility(ArrayList<Course> lstCourses,ArrayList<Student> lstStudents, Semester sem) 
	{
		try
		{
			Env = new GRBEnv("grb.log");
			Env.set(GRB.IntParam.LogToConsole, 0);
			Model = new GRBModel(Env);
			isEnvSetup = true;
		}
		catch (GRBException e) 
		{
			e.printStackTrace();
		}
		
		Students = lstStudents;
		Courses = lstCourses;
		Sem = sem;
		sSize = lstStudents.size();
		cSize = lstCourses.size();
		mSize = Sem.semesters.size();
	}
	public HashMap<Integer,Float> runGBR() 
	{
		float answer = 0;
		int retval =0;
		if(isEnvSetup)
		{
			try
			{
				if(GRBStart())
				{
					GVar = Model.addVar(0, sSize, 0.0, GRB.INTEGER, "model start");
					Model.update();
					if(initGBRCourseMin())
					{	
						if(initGBRAvailCourses())
						{
							if(initGBRCourseMax())
							{
								if(initGBRCourse())
								{
									if(initGBRPrereqs())
									{
										retval = 0;
									}
									else
									{
										retval = 4;
									}
								}
								else
								{
									retval = 3;
								}
							}
							else
							{
								retval = 2;
							}
						}
						else
						{
							retval = 1;
						}
					}
					Model.optimize();
					answer = (float) Model.get(GRB.DoubleAttr.ObjVal);
				}
				else
				{
					retval = 10;
				}
			} 
			catch (GRBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			retval=11;
		}
		HashMap<Integer,Float> returnValues = new HashMap<Integer,Float>();
		returnValues.put(retval, answer);
		return returnValues;
	}
	
	Boolean initGBRCourseMin()
	{
		Boolean retval = false;
		for(int c=0;c<cSize;c++)
		{
			for(Entry<Integer,String> semester : Sem.semesters.entrySet())
			{
				int m = semester.getKey();
				GRBLinExpr expCapacity = new GRBLinExpr();
				retval = false;
				for(int s=0;s<sSize;s++)
				{
					expCapacity.addTerm(1, GConstSet[s][c][m]);
				}
				try
				{
					Model.addConstr(expCapacity, GRB.LESS_EQUAL, GVar, "capacity");
					retval = true;
				} 
				catch (GRBException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		GRBLinExpr expr = new GRBLinExpr();
		expr.addTerm(1, GVar);
		if(retval)
		{
			try
			{
				Model.setObjective(expr,GRB.MINIMIZE);
				retval = true;
			} 
			catch (GRBException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retval;
	}
	
	Boolean initGBRAvailCourses()
	{
		Boolean retval = false;
		for(int c=0;c<cSize;c++)
		{
			Course co = Courses.get(c);
			for(Entry<Integer,String> semester : Sem.semesters.entrySet()) 
			{
				int m = semester.getKey();
				if(!co.hasSemester(m))
				{
					retval =false;
					GRBLinExpr expAvailCourse = new GRBLinExpr();
					for(int s=0;s<sSize;s++)
					{
						expAvailCourse.addTerm(1, GConstSet[s][c][m]);
					}
					try
					{
						Model.addConstr(expAvailCourse, GRB.EQUAL, 0, c+" "+m);
						retval = true;
					} 
					catch (GRBException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return retval;
	}

	Boolean initGBRCourseMax()
	{
		Boolean retval = false;
		for(int s=0;s<sSize;s++)
		{
			for(Entry<Integer,String> semester : Sem.semesters.entrySet())
			{
				int m = semester.getKey();
				GRBLinExpr expCourseMax = new GRBLinExpr();
				retval = false;
				for(int c=0;c<cSize;c++)
				{
					expCourseMax.addTerm(1, GConstSet[s][c][m]);
				}
				try
				{
					Model.addConstr(expCourseMax, GRB.LESS_EQUAL, MAXIMUM, s + " " +m );
					retval =true;
				} 
				catch (GRBException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return retval;
	}
	
	Boolean initGBRCourse()
	{
		Boolean retval = false;
		for(int s=0;s<sSize;s++)
		{
			Student st = Students.get(s);
			for(int c=0;c<cSize;c++)
			{
				Course co = Courses.get(c);
				GRBLinExpr courseConstraint = new GRBLinExpr();
				for(Entry<Integer,String> semester : Sem.semesters.entrySet())
				{
					int m = semester.getKey();
					courseConstraint.addTerm(1, GConstSet[s][c][m]);
				}
				retval = false;
				if(st.checkCourse(co.CourseId))
				{
					try
					{
						Model.addConstr(courseConstraint, GRB.EQUAL, 1, "initGBRCourse courseConstraint "+s+" "+c);
						retval = true;
					} 
					catch (GRBException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					try
					{
						Model.addConstr(courseConstraint, GRB.EQUAL, 0, "initGBRCourse courseConstraint "+s+" "+c);
						retval = true;
					} 
					catch (GRBException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return retval;
	}
	
	Boolean initGBRPrereqs()
	{
		Boolean retval = false;
		int s=0,c=0,c2=0;
		for(@SuppressWarnings("unused") Student st : Students)
		{
			for(Course co : Courses)
			{
				if(co.Prereqs.size()>0)
				{
					GRBLinExpr prereq1 = new GRBLinExpr();
					GRBLinExpr prereq2 = new GRBLinExpr();
					for(Entry<Integer,String> semester : Sem.semesters.entrySet())
					{
						int m = semester.getKey();
						prereq1.addTerm(m+1, GConstSet[s][c][m]);
					}
					for(@SuppressWarnings("unused") Course cp : co.Prereqs)
					{
						for(Entry<Integer,String> semester : Sem.semesters.entrySet())
						{
							int m = semester.getKey();
							prereq1.addTerm(m+1, GConstSet[s][c2][m]);
							c2++;
						}
						c2=0;
					}
					retval = false;
					try
					{
						Model.addConstr(prereq2, GRB.LESS_EQUAL, prereq1, "initGBRPrereqs prereqs "+s+" "+c);
						retval = true;
					} 
					catch (GRBException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				c++;
			}
			c=0;
			s++;
		}
		return retval;
	}
	
	Boolean GRBStart()
	{
		Boolean retval = false;
		GConstSet = new GRBVar[sSize][cSize][mSize];
		for(int s=0;s<sSize;s++)
		{
			for(int c=0;c<cSize;c++)
			{
				for(Entry<Integer,String> semester : Sem.semesters.entrySet())
				{
					int m = semester.getKey();
					try
					{
						GConstSet[s][c][m] = Model.addVar(0, 1, 0.0, GRB.BINARY, s+" "+c+" "+m);
						retval = true;
					} 
					catch (GRBException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		}
		if(retval)
		{
			try
			{
				Model.update();
				retval = true;
			} 
			catch (GRBException e) 
			{
				// TODO Auto-generated catch block
				retval = false;
				e.printStackTrace();
			}
		}
		return retval;
	}
}
