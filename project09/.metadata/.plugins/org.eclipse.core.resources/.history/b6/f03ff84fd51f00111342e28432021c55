import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Similarity {
	ArrayList<ArrayList<Integer[]>> normdata = new ArrayList<ArrayList<Integer[]>>();
	ArrayList<String[]> similarities = new ArrayList<String[]>();

	public Similarity(String file) {
		readfile("genres.csv");
	}
	private void compare(){
		for(int)
	}
	
	
	
	
	
	
	private double euclidean(int[] a, int[] b){    // euclidean distnace 
		double result =0; 
		
		double sum=0; 
		for(int i=0; i<both.size(); i++){
			double sub = a[both.get(i)] - b[both.get(i)];   // getting the difference between the two coensiding attributes 
			sum = sum + Math.pow(sub, 2); 					// raising it to a power of 2 and adding it to the other sums 
		}
		result  = Math.sqrt(sum);						// taking the square root of the sums 
		return 1/(1 + result);						// normalizing the number 
		
	}
	private void normalize(ArrayList<String[]> a) {
		ArrayList<Integer[]> norm = new ArrayList<Integer[]>();
		int high = -1;
		int low = 111;
		for (int i = 0; i < a.size(); i++) {
			int rating = Integer.parseInt(a.get(i)[2]);
			int title = Integer.parseInt(a.get(i)[1]);
			int id = Integer.parseInt(a.get(i)[2]);
			if (rating < low) {
				low = rating;
			}
			if (rating > high) {
				high = rating;
			}
			Integer[] x = { id, title, rating };
			norm.add(x);
		}
		double scale = 100 / (high - low);
		for (int j = 0; j < norm.size(); j++) {
			norm.get(j)[2] = (int) ((norm.get(j)[2] - low) * scale);
		}
		normdata.add(norm);
	}

	private void readfile(String filename) {
		String current_user = "";
		ArrayList<String[]> user = new ArrayList<String[]>();

		System.out.println("Starting reading file");
		try {
			BufferedReader in = new BufferedReader(new FileReader(filename));
			String str;

			while ((str = in.readLine()) != null) {
				String parts[] = str.split("\\|");
				if (!current_user.equals(parts[0])) {
					if (!current_user.equals(" ")) {
						normalize(user);
						user.clear();
					}
					current_user = parts[0];
				} else {
					user.add(parts);
				}

			}

			System.out.println("Your file has been written");
			in.close();
		} catch (IOException e) {
		}
	}
}
