import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;


public class wmain {

	public static void main(String args[]){
		Hashtable<Integer, String[]> winners = fileread("winners_losers.csv"); 
		String[] coll = {"name", "result"}; 
		features a = new features(winners, coll); 
		Hashtable<Integer, String[]> results = a.getNewData() ;
		coll = a.getcol(); 
		
		System.out.println("columns: "); 
		for(int i=0; i<coll.length; i++){
			System.out.println( i + " : " + coll[i]); 
		} 
		writeFile(results, "newdata.csv");

	}



	public static void writeFile(Hashtable<Integer, String[]> n , String Filename){
		try
		{
		    FileWriter writer = new FileWriter(Filename);
		    
		    for(int i=0; i < n.size(); i++){
		    	for(int k =0; k < n.get(i).length; k++){
		    		writer.append(n.get(i)[k]);
		    		if(k < n.get(i).length - 1)
		    			 writer.append(',');
		    	}
		    	writer.append('\n'); 
		    }
		    
	 
		    writer.flush();
		    writer.close();
		}
		catch(IOException e)
		{
		     e.printStackTrace();
		} 

	}
	public static Hashtable<Integer, String[]> fileread(String filename){
		Hashtable<Integer, String[]> winners  = new Hashtable<Integer, String[]>();

		try {
			BufferedReader br = new BufferedReader( new FileReader(filename));
			String strLine = null;
			int lineNumber = 0;

			while( (strLine = br.readLine()) != null)
			{

				//			System.out.println(strLine);
				String parts[] = strLine.split(",");
				winners.put(lineNumber, parts);
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
		return winners;
	}
}
