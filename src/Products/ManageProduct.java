package Products;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ManageProduct implements Serializable{
	
	
	int amount;
	String name;
	
   public void serialOut() {
		
		ManageProduct a = new ManageProduct();
		 try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("OutSerial.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(a);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in OutSerial.txt");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}

}
