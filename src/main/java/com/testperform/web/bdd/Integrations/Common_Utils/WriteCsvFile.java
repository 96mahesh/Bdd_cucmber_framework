package com.testperform.web.bdd.Integrations.Common_Utils;
/*package Utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVWriter;

public class WriteCsvFile {

	public static void writeDataLineByLine(String filePath) 
	{ 
		// first create file object for file placed at location 
		// specified by filepath 
		File file = new File(filePath); 
		try { 
			// create FileWriter object with file as parameter 
			FileWriter outputfile = new FileWriter(file); 

			// create CSVWriter object filewriter object as parameter 
			CSVWriter writer = new CSVWriter(outputfile); 

			// adding header to csv 
			String[] header = { "Name", "Class", "Marks" }; 
			writer.writeNext(header); 

			// add data to csv 
			String[] data1 = { "Aman", "10", "620" }; 
			writer.writeNext(data1); 
			String[] data2 = { "Suraj", "10", "630" }; 
			writer.writeNext(data2); 
			
			 List<String> test = new ArrayList<>();
			    test.add("Word1");
			    test.add("Word2");
			    test.add("Word3");
			    test.add("Word4");
			    
			   // String collect = test.stream().collect(Collectors.joining(","));
			    for (int i = 0; i < test.size(); i++) {
					writer.writeNext(test.get(i));
				}
			   // outputfile.write(collect);
			// closing writer connection 
			writer.close(); 
		} 
		catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
	} 
	
	public static void listData(String filePath) throws IOException {
		FileWriter writer = new FileWriter(filePath);

		
		
	    List<String> test = new ArrayList<>();
	    test.add("Word1");
	    test.add("Word2");
	    test.add("Word3");
	    test.add("Word4");

	    String collect = test.stream().collect(Collectors.joining(","));
	    System.out.println(collect);

	    writer.write(collect);
	    writer.close();
	}
	
	public static void main(String[] args) throws IOException {
		
		WriteCsvFile.listData("./result.csv");
	}

    }*/