package Text;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import Products.Agent;

public class Files {
	
	
	String[] content = new String[100];
	
	String[] names = new String[100];
	String[] amount = new String[100];
	 int i = 0;
	
	public String[] getNames() {
		
		return names;
	}
	
	public String[] getAmount() {
		return amount;
	}
	public void read() {
		
		BufferedReader br = null;
       
		try {

			String s;

			br = new BufferedReader(new FileReader("test.txt"));
            
			while ((s = br.readLine()) != null) {
				
				//add items to an array content[]
				content[i] = new String(s);
				i++;
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	
	public void createProducts() {
		
		String[] buffer = new String[2];
		
		int j = 0;

		for(String each:content) {
			
			buffer = each.split(" ");
			names[j] = buffer[0];
			amount[j] = buffer[1];
			j++;
			if(j == i){
				break;
			}
		}
		j = 0;
		System.out.println("for:");
		for(String each:content) {
			j++;
			System.out.println(each);
			if(j == i){
				break;
			}
		}
		
	 
		
	}
	
	
	public void write(byte[] aInput, String aOutputFileName){
	    
	    try {
	      OutputStream output = null;
	      try {
	        output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
	        output.write(aInput);
	      }
	      finally {
	        output.close();
	      }
	    }
	    catch(FileNotFoundException ex){
	     ex.printStackTrace();
	    }
	    catch(IOException ex){
	      ex.printStackTrace();;
	    }
	  }
	  
	

}
