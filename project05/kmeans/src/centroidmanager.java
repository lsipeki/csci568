import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;



public class centroidmanager {
	private int centnum = 0 ;
	private Hashtable<Integer, String[]> data; 
	private int[] max = new int[4];
	private ArrayList<ArrayList<String[]>> centData = new ArrayList<ArrayList<String[]>>();   // each object taht belongs to the centroid 
	//private ArrayList<double[]> closeness = new ArrayList<double[]>();
	private ArrayList<double[]> centpos = new ArrayList<double[]>();
	private ArrayList<double[]> lastpos = new ArrayList<double[]>(); 
	private double SSE = 0; 
	
	
	public centroidmanager(Hashtable<Integer, String[]> iris, int centNum){
		centnum = centNum; 
		data = iris;
		for(int i = 0; i<centnum; i++){
			centData.add(new ArrayList<String[]>());   // creates data sets for each centroid 
		}
		initial_placement();
		while(comp() > .002) {
			lastpos.clear(); 
			for(int i=0; i<centpos.size(); i++){
				double[] value = new double[centpos.get(i).length]; 
				for(int k=0; k < centpos.get(i).length; k++){
					value[k] = centpos.get(i)[k];
				}
				lastpos.add(value);
			}
			findclose();
			newCentLoc(); 
			//System.out.println("Itterate");
		}
	} 
	
	private void initial_placement(){
		Random rand = new Random(); 
		//findmax(); 
		/*for (int i=0; i<centnum; i++){
			double[] initial = {(double)rand.nextInt(max[0]), (double)rand.nextInt(max[1]),(double)rand.nextInt(max[2]),(double)rand.nextInt(max[3])};
			centpos.add(initial);
		*/
		for(int i=0; i<centnum; i++){
			int r = rand.nextInt(150);
			double[] intial = {Double.parseDouble(data.get(r)[0]),Double.parseDouble(data.get(r)[1]),Double.parseDouble(data.get(r)[2]),Double.parseDouble(data.get(r)[3])};
			centpos.add(intial);
			double[] in = {(double)0,(double)0,(double)0,(double)0};
			lastpos.add(in);
		}
	}
	
	private void findmax(){
		for(int i=0; i<data.size(); i++){
			String[] items  = data.get(i);
			for(int x=0; x<4; x++){
				int current = (int)Double.parseDouble(items[x]);
				if(max[x] < current)
					max[x] = (int)current;
			}
		}	
	}
	private void findclose(){
		for(int i=0; i<centData.size(); i++){
			centData.get(i).clear();
		}
		for(int i=0; i<data.size(); i++){
			double[] items = new double[4];
			for(int k=0; k<4; k++){
				items[k] = Double.parseDouble(data.get(i)[k]);
			}
			double[] c = new double[centnum]; 
			for(int l=0; l < centnum; l++){
				c[l]  = euclidean(items, centpos.get(l));	
			}
			centData.get(getmax(c)).add(data.get(i));   // puts the point to the value of the shortest euclidian distance to that centroid's arrray list in Cent Data
			
		}
		
	}
	private void newCentLoc(){
		for(int i =0; i<centnum; i++){
			int tot = 0; 
			double[] sum = new double[4]; 
			for(int k=0; k < centData.get(i).size(); k++){
				sum[0] = sum[0] + Double.parseDouble(centData.get(i).get(k)[0]);
				sum[1] = sum[1] + Double.parseDouble(centData.get(i).get(k)[1]);
				sum[2] = sum[2] + Double.parseDouble(centData.get(i).get(k)[2]);
				sum[3] = sum[3] + Double.parseDouble(centData.get(i).get(k)[3]);
				tot++;
			}
			centpos.get(i)[0] = sum[0] / tot; 
			centpos.get(i)[1] = sum[1] / tot; 
			centpos.get(i)[2] = sum[2] / tot; 
			centpos.get(i)[3] = sum[3] / tot; 
		}
	}
	
	private int getmax(double[] nums){
		double current = 0;
		int index = 0; 
		for(int i=0; i< nums.length; i++){
			if(current < nums[i]){
				index = i; 
				current = nums[i];
			}
		}
		return index;
	}
	
	private double euclidean(double[] a, double[] b){    // euclidean distnace 
		double result =0; 
		double sum=0; 
		for(int i=0; i<a.length; i++){
			double sub = a[i] - b[i];   // getting the difference between the two coensiding attributes 
			sum = sum + Math.pow(sub, 2); 					// raising it to a power of 2 and adding it to the other sums 
		}
		result  = Math.sqrt(sum);						// taking the square root of the sums
		//return  result; 
		return 1/(1 + result);						// normalizing the number 
		
	}
	public double computeSSE(){
		double SSE =0 ; 
		ArrayList<Double> eucDist = new ArrayList<Double>(); 
		for(int i=0; i< centData.size(); i++){
			for(int k=0; k < centData.get(i).size(); k++){
				double a[] = {Double.parseDouble(centData.get(i).get(k)[0]), Double.parseDouble(centData.get(i).get(k)[1]), Double.parseDouble(centData.get(i).get(k)[2]), Double.parseDouble(centData.get(i).get(k)[3])}; 
				eucDist.add(euclidean(a, centpos.get(i))); 	
			}
			
		}
		for(int i=0; i < eucDist.size(); i++){
			SSE = SSE + Math.pow(eucDist.get(i), 2);
		}
		
		return SSE; 
	}
	private double comp(){
		double result =0;
		for(int i=0; i < centpos.size(); i++){
			double dist =0; 
			for(int k=0; k<centpos.get(i).length; k++){
				dist = dist + Math.pow(centpos.get(i)[k] - lastpos.get(i)[k], 2);
			}
			result = result + Math.sqrt(dist); 
		}
		
		return result; 
	}
	
	public ArrayList<ArrayList<String[]>> getResults(){
		return centData; 
	}
	public ArrayList<double[]> getPos(){
		return centpos;
	}
}
