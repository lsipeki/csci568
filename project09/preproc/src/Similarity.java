import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Similarity {
	ArrayList<ArrayList<Integer[]>> normdata = new ArrayList<ArrayList<Integer[]>>();
	ArrayList<String> similarities = new ArrayList<String>();
	ArrayList<Integer[]> rate_same = new ArrayList<Integer[]>();

	public Similarity(String file) {
		readfile("Artist.csv");
		compare();
		find_similar();
	}
	private void compare(){
		System.out.println(normdata.size());
		for(int i=0; i<normdata.size(); i++){
			for(int j=0; j<normdata.size(); j++){
				int counter = 0; 
				if(j > i){
					for(int k=0; k<normdata.get(i).size(); k++){
						for(int l=0; l<normdata.get(j).size(); l++){
							if(normdata.get(i).get(k)[1].equals( normdata.get(j).get(l)[1])){
								counter++; 
							}
						}
					}
				}
				if(counter > 10){
					Integer[] r = {i, j, counter};  
					rate_same.add(r); 
				}
			}
		}
	}


	private void find_similar(){
		System.out.println("Starting similarity");
		for(int i=0; i<rate_same.size(); i++){
			double score = euclidean(normdata.get(rate_same.get(i)[0]), normdata.get(rate_same.get(i)[1]));
			String result = normdata.get(rate_same.get(i)[0]).get(0)[0] +  " and " +normdata.get(rate_same.get(i)[1]).get(0)[0] + " have rated " + rate_same.get(i)[2] + "same songs and have gotten a socre of " + score + " in similarity"; 
			similarities.add(result);
			System.out.println(result);
		}
	}



	public double euclidean(ArrayList<Integer[]> a , ArrayList<Integer[]> b){    // euclidean distnace 
		double result =0; 
		double sum = 0; 
		for(int i=0; i<a.size(); i++){
			for(int j=0; j<b.size(); j++){
				if(a.get(i)[1].equals(b.get(j)[1])){
					double sub = a.get(i)[2] + b.get(j)[2];
					sum = sum + Math.pow(sub, 2); 					// raising it to a power of 2 and adding it to the other sums 
				}
			}
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
			int id = Integer.parseInt(a.get(i)[0]);
			if (rating < low) {
				low = rating;
			}
			if (rating > high) {
				high = rating;
			}
			Integer[] x = { id, title, rating };
			norm.add(x);
		}
		if(high != low){
			double scale = 100 / (high - low);
			for (int j = 0; j < norm.size(); j++) {
				norm.get(j)[2] = (int) ((norm.get(j)[2] - low) * scale);
			}
		}
		normdata.add(norm);
		//System.out.println(norm);
		//System.out.println("Normalized");
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

			System.out.println("Your file has read completely");
			in.close();
		} catch (IOException e) {
		}
	}
}
