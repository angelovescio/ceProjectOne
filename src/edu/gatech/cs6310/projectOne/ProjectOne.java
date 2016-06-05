package edu.gatech.cs6310.projectOne;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
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
							for(int j =0;j<string.length;j++)
							{
								System.out.print("\t " + string[j]);
							}
							System.out.println("");
						}
					}
					else if(name.contains(cs2))
					{
						System.out.println("P " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							for(int j =0;j<string.length;j++)
							{
								System.out.print("\t " + string[j]);
							}
							System.out.println("");
						}
					}
					else if(name.contains(cs3))
					{
						System.out.println("T " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							for(int j =0;j<string.length;j++)
							{
								System.out.print("\t " + string[j]);
							}
							System.out.println("");
						}
					}
					else if(name.contains(cs4))
					{
						System.out.println("D " + listOfFiles[i].getName());
						List<String[]> content = GetItemsFromCsv(listOfFiles[i]);
						for (String[] string : content) 
						{
							for(int j =0;j<string.length;j++)
							{
								System.out.print("\t " + string[j]);
							}
							System.out.println("");
						}
					}
					
				}
			}
		}
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
