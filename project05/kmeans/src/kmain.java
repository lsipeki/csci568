import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;



public class kmain {
	public static void main(String[] args) {
		Hashtable<Integer, String[]> iris  = fileread("iris.csv");
		centroidmanager kmeans = new centroidmanager(iris, 3);
		ArrayList<ArrayList<String[]>> results = kmeans.getResults(); 
		System.out.println("Clusters: " + results.size());
		for(int i =0; i < results.size(); i++){
			System.out.println("Nubmer in Cluster " + i + " is : " + results.get(i).size());
		}
	}

	
	
	public static Hashtable<Integer, String[]> fileread(String filename){
		Hashtable<Integer, String[]> iris  = new Hashtable<Integer, String[]>();
		
		try {
			BufferedReader br = new BufferedReader( new FileReader(filename));
			String strLine = null;
			int lineNumber = 0;
			
			while( (strLine = br.readLine()) != null)
			{
				
	//			System.out.println(strLine);
				String parts[] = strLine.split(",");
				iris.put(lineNumber, parts);
				lineNumber++;
 
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
