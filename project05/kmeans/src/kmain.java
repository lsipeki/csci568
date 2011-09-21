import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;



public class kmain {
	public static void main(String[] args) {
		Hashtable<Integer, String[]> iris  = fileread("iris.csv");
		
	}

	public static Hashtable<Integer, String[]> fileread(String filename){
		Hashtable<Integer, String[]> iris  = new Hashtable<Integer, String[]>();
		
		try {
			BufferedReader br = new BufferedReader( new FileReader(filename));
			String strLine = null;
			int lineNumber = 0;
			
			while( (strLine = br.readLine()) != null)
			{
				lineNumber++;
				System.out.println(strLine);
				String parts[] = br.readLine().split(",");
				iris.put(lineNumber, parts);
				
 
			}
		} 
 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iris;
	}
}
