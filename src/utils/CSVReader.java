package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	
	public static ArrayList<String[]> read (String path){
		ArrayList<String[]> output = new ArrayList<String[]>();
		String line = "";
		String cvsSplitBy = ";";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				output.add(items);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output;
	}

}
