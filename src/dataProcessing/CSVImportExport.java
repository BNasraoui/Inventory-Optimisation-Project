package dataProcessing;

/**
 * the CSVImportExport class deals with importing and exporting manifests, 
 * importing the initial item properties, and importing sales logs
 * 
 *  @author Benjamin Nasraoui
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import delivery.Truck;
import stock.Item;
import stock.Manifest;
import stock.Stock;

import java.io.PrintWriter;
import java.io.PrintWriter;

public class CSVImportExport {
	
	private ArrayList<Item> itemProperties;
	private ArrayList<String> manifestNames;
	private ArrayList<String> manifestQuantity;
	private ArrayList<String> removedItems;
	private ArrayList<String> removedQuantity;
	
	/**
	 * 
	 * @param currentInventory
	 * 		- current store stock to be updated
	 * @param filePath
	 * 		- file path of the sales log desired to be imported
	 * @return
	 * 		- the updated store stock
	 */
	public Stock importSalesLog(Stock currentInventory, String filePath) { 
		
		BufferedReader br = null;
		String currentLine = "";
		String csvSplit = ",";
		
		try
		{
			br = new BufferedReader(new FileReader (filePath));
			while(br.readLine() != null) {
				String[] currentItem = currentLine.split(csvSplit);
				
				if(!currentLine.startsWith(">")) {
					removedItems.add(currentItem[0]);
					removedQuantity.add(currentItem[1]);
				}		
			}
			
			//currentInventory.remove(removedItems, removedQuantity);;
				
		} catch (FileNotFoundException e) {
	         e.printStackTrace();
		 	} catch (IOException e) {
	         e.printStackTrace();
		 	} finally {
		 		if (br != null) {
		 			try {
		 				br.close();
		 			} catch (IOException e) { 
		 				e.printStackTrace();}
		 			}
		 	}
		return currentInventory;
	}
	
	/**
	 * 
	 * @param manifest
	 * 		- the manifest to be exported to a csv file
	 */
	public void ExportManifest(Manifest manifest) {
		
		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new File("manifest.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder str = new StringBuilder();
		ArrayList<Truck> truckList = manifest.getTruckList();
		
		for(Truck truck : truckList) {
			
			ArrayList<Item> items = truck.getCargo().getItemList();
			int[] quantity = truck.getCargo().getQuantityArray();
			
			if(truck.isTempControlled()) {str.append(">Refrigerated");}
			else {str.append(">Ordinary");}
			
			str.append("\n");
			
			for(int i = 0; i < items.size(); i++) 
			{
				str.append(items.get(i).getName());
				str.append(",");
				str.append(String.valueOf(quantity[i]));
				str.append("\n");
			}
		}
		
		printWriter.write(str.toString());
		printWriter.close();
	}
} 
