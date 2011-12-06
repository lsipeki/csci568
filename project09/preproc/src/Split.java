import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Split {
	public String user; 
	public boolean usr; 
	ArrayList<String> track = new ArrayList<String>();
	ArrayList<String> album = new ArrayList<String>();
	ArrayList<String> artist = new ArrayList<String>();
	ArrayList<String> genres = new ArrayList<String>();
	ArrayList<String> unknown = new ArrayList<String>(); 
	public Split(String r){
		System.out.println("Starting split");
		user = ""; 
		read(r); 
		write("Tracks.csv", track);
		write("Albusm.csv", album); 
		write("Artist.csv", artist); 
		write("Genres.csv", genres); 
		write("Unknown.csv", unknown);
	}

	private void read(String filename){
		System.out.println("Starting reading file");
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String str;

			while ((str = in.readLine()) != null) {
				sort(str);

			}

			System.out.println("Your file has been written");  
			in.close();
		} catch (IOException e) {
		}
	}
	private void sort(String in){
		String[] parts = in.split("\\|");
		//System.out.println("looking for tracks");
		if(readsort(parts[1], "artist.txt" )){
			artist.add(in);
			System.out.println("adding to artists");
		}
		else if(readsort(parts[1], "album.txt")){
			album.add(in);		
			System.out.println("adding to albums");
		}
		else if(readsort(parts[1], "track.txt")){
			track.add(in);
			System.out.println("adding to track");
			
		}
		else if(readsort(parts[1], "genre.txt")){
			genres.add(in);
			System.out.println("adding genres");
		}
		else{
			unknown.add(in);
			System.out.println("adding to unknown");
		}
	}
	private boolean readsort(String look, String filename){
		//System.out.println("looking through  " + filename); 
		boolean result=false; 
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String str;

			while ((str = in.readLine()) != null) {
				String[] parts = str.split("\\|");
				if(look.equals(parts[0].trim()) ){
					result = true; 
					//System.out.println("Found Something");
					break; 
				}

			}
			in.close();
		} catch (IOException e) {
		}
		return result;
	}
	private void write(String filename, ArrayList<String> a){
		try {
			FileWriter outFile = new FileWriter(filename);
			PrintWriter out = new PrintWriter(outFile);

			// Also could be written as follows on one line
			// Printwriter out = new PrintWriter(new FileWriter(args[0]));

			// Write text to file
			for(int i=0; i < a.size(); i++){
				out.println(a.get(i));
			}
			System.out.println("finished with " + filename);
			out.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
