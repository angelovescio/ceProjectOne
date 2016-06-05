package edu.gatech.cs6310.projectOne;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CSVReader {
	/**
	* returns a row of values as a list
	* returns null if you are past the end of the input stream
	*/
	public static List<String[]> parseLine(BufferedReader r) throws Exception {
		List<String[]> items = new ArrayList<String[]>();
		String line = "";
		do{
			ArrayList<Integer> commas = new ArrayList<Integer>();
			ArrayList<Integer> quotes = new ArrayList<Integer>();
			ArrayList<Integer> badCommas = new ArrayList<Integer>();
	    	line = r.readLine();
	    	if(line == null || line == "")
	    	{
	    		break;
	    	}
	    	for(int i =0; i < line.length();i++)
	    	{
	    		if (line.charAt(i)=='\"')
	    		{
	    			quotes.add(i);
	    		}
	    		else if (line.charAt(i)==',')
	    		{
	    			commas.add(i);
	    		}
	    	}
	    	CharSequence chs = "CS 6505 Computability";
	    	if(line.contains(chs))
	    	{
	    		String test = "";
	    	}
	    	if(quotes.size()%2==0)
	    	{
	    		Integer[] cs = commas.toArray(new Integer[0]);
	    		Integer[] qs = quotes.toArray(new Integer[0]);
		    	for (int j=0;j<qs.length;j+=2) {
					for(int k=0;k<cs.length;k++)
					{
						if(qs[j]<cs[k] && qs[j+1]>cs[k])
						{
							badCommas.add(cs[k]);
						}
					}
				}
	    	}
	    	StringBuilder sb = new StringBuilder(line);
	    	for (Integer integer : badCommas) {
				sb.setCharAt(integer, ' ');
			}
	    	if(line != null && line != "")
	    	{
	    		String newLine = sb.toString();
	    		String[] contents =  newLine.split(",");
	    		CharSequence spaces = "  ";
	    		CharSequence space = ", ";
	    		for(int l=0;l<contents.length;l++)
	    		{
	    			contents[l] = contents[l].replace(spaces, space);
	    		}
	    		items.add(contents);
	    	}
	    }while(line != null && line != "");
		return items;
	}

}
