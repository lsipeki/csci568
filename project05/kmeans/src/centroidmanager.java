import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;



public class centroidmanager {
	private Hashtable<Integer, String[]> data; 
	private int[] max = new int[4];
	private ArrayList<ArrayList<String[]>> centData = new ArrayList<ArrayList<String[]>>();   // each object taht belongs to the centroid 
	//private ArrayList<double[]> closeness = new ArrayList<double[]>();
	private ArrayList<double[]> centpos = new ArrayList<double[]>();
	
	public centroidmanager(Hashtable<Integer, String[]> iris, int centNum){
		data = iris;
		for(int i = 0; i<centNum; i++){
			centData.add(new ArrayList<String[]>());   // creates datasets for each centroid 
		}
	} 
	
	public void initial_placement(int centNum){
		Random rand = new Random(); 
		findmax(); 
		for (int i=0; i<centNum; i++){
			double[] initial = {(double)rand.nextInt(max[0]), (double)rand.nextInt(max[1]),(double)rand.nextInt(max[2]),(double)rand.nextInt(max[3])};
			centpos.add(initial);
		}
	}
	
	public void findmax(){
		Set a = data.entrySet();
		Iterator i = a.iterator();
		while(i.hasNext()){
			Map.Entry m =(Map.Entry)i.next();
			
			String[] items = (String[])m.getValue();
			for(int x=0; x<4; x++){
				int current = Integer.parseInt(items[x]);
				if(max[x] < current)
					max[x] = (int)current;
			}
		}	
	}
	public void findclose(int centNum){
		for(int i=0; i<centData.size(); i++){
			centData.get(i).clear();
		}
		for(int i=0; i<data.size(); i++){
			double[] items = new double[4];
			for(int k=0; k<4; k++){
				items[k] = Double.parseDouble(data.get(i)[k]);
			}
			double[] c = new double[centNum]; 
			for(int l=0; l < centNum; l++){
				c[l]  = euclidean(items, centpos.get(l));	
			}
			centData.get(getshort(c)).add(data.get(i));   // puts the point to the value of the shortest euclidian distance to that centroid's arrray list in Cent Data
			
		}
		
	}
	public void newCentLoc(int centNum){
		for(int i =0; i<centNum; i++){
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
	
	public int getshort(double[] nums){
		double current = 100000;
		int index = 0; 
		for(int i=0; i< nums.length; i++){
			if(current > nums[i]){
				index = i; 
				current = nums[i];
			}
		}
		return index;
	}
	
	public double euclidean(double[] a, double[] b){    // euclidean distnace 
		double result =0; 
		double sum=0; 
		for(int i=0; i<a.length; i++){
			double sub = a[i] - b[i];   // getting the difference between the two coensiding attributes 
			sum = sum + Math.pow(sub, 2); 					// raising it to a power of 2 and adding it to the other sums 
		}
		result  = Math.sqrt(sum);						// taking the square root of the sums
		return  result; 
		//return 1/(1 + result);						// normalizing the number 
		
	}
	
	
}
