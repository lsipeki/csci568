import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;




public class kmain {
	public static void main(String[] args) {
		System.out.println("Enter the number of clusters to form: "); 
		int in = 0; 
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String input = reader.readLine();
			in = Integer.parseInt(input);
			
		} catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Input by user was not a number.");
        }
		Hashtable<Integer, String[]> iris  = fileread("iris.csv");
		centroidmanager kmeans = new centroidmanager(iris, in);
		ArrayList<ArrayList<String[]>> results = kmeans.getResults(); 
		ArrayList<double[]> finalpos = kmeans.getPos(); 
		System.out.println("SEE for Clusters is : " + kmeans.computeSSE());
		System.out.println(); 
		System.out.println("Clusters: " + results.size());
		
		
		for(int i =0; i < results.size(); i++){
			System.out.println(); 
			System.out.println(); 
			System.out.println("Cluster: "  +  (i+1));
			System.out.println("Number in Cluster " + (i+1) + " is : " + results.get(i).size());
			System.out.println("Spacial Length : " +  finalpos.get(i)[0]);
			System.out.println("Spacial Width : " +  finalpos.get(i)[1]);
			System.out.println("Petal Length : " +  finalpos.get(i)[2]);
			System.out.println("Petal Width : " +  finalpos.get(i)[3]);
			System.out.println(); 
			
			
			
		}
	}
	

	public static double[] averages(ArrayList<String[]> data){
		double[] avg = new double[4]; 
		int counter = 0; 
		for(int i=0 ; i < data.size(); i++){
			avg[0] = avg[0] + Double.parseDouble(data.get(i)[0]);
			avg[1] = avg[1] + Double.parseDouble(data.get(i)[1]);
			avg[2] = avg[2] + Double.parseDouble(data.get(i)[2]);
			avg[3] = avg[3] + Double.parseDouble(data.get(i)[3]);
			counter++; 
		}
		avg[0] = avg[0]/counter;
		avg[1] = avg[1]/counter;
		avg[2] = avg[2]/counter;
		avg[3] = avg[3]/counter;
		
		
		return avg; 
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
