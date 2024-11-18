package com.testperform.web.bdd.Integrations.Common_Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.google.common.io.Files;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;

public class CSV_Reader {

	public String csv_reader(int index) throws IOException, FileNotFoundException {
		String value = "";

		try {

			FileReader filereader = new FileReader(
					(System.getProperty("user.dir") + "\\src\\test\\resources\\files\\csv1.csv"));

			// create csvReader object and it skip first Line
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			// array list to store all the values in csv file
			List<String[]> allData = csvReader.readAll();
			// This array list will store the final values
			List<String> list = new ArrayList<>();
			// print Data
			for (String[] row : allData) {
				for (String cell : row) {

					System.out.println(cell);
					list.add(cell);
				}
				System.out.println();
			}
			for (int i = 0; i < list.size(); i++) {
				System.out.println("The array list values are" + list.get(i));

			}
			value = list.get(index);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

}
