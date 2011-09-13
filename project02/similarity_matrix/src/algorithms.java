import java.util.ArrayList;


public class algorithms {
	public double euclidean(int[] a, int[] b){
		double result =0; 
		ArrayList<Integer> both = both_have(a, b);
		double sum=0; 
		for(int i=0; i<both.size(); i++){
			double sub = a[both.get(i)] - b[both.get(i)]; 
			sum = sum + Math.pow(sub, 2); 
		}
		result  = Math.sqrt(sum);
		return 1/(1 + result);
		
	}
	public double smc(int[] a, int[] b){
		ArrayList<Integer> both = both_have(a, b); 
		int matching_attributes = 0 ; 
		for( int i =0; i<both.size(); i++){
			if (a[both.get(i)] == b[both.get(i)]){
				matching_attributes ++;
			}
		}
		double attributes = both.size(); 
		return matching_attributes/attributes;
	}
	
	public double Jaccard(int[] a, int[] b){
		ArrayList<Integer> both = both_have(a, b); 
		int matching_attributes = 0 ; 
		for( int i =0; i<both.size(); i++){
			if (a[both.get(i)] == b[both.get(i)] && a[both.get(i)] > 0 ){
				matching_attributes ++;
			}
		}
		double attributes = 0;
		for( int i=0; i<both.size(); i++){
			if(a[both.get(i)] != 0)
				attributes++;
			else if(b[both.get(i)] != 0)
				attributes++; 
		}
		return matching_attributes/attributes;	
	}
	public double Pearson(int[] a, int[] b){
		ArrayList<Integer> both = both_have(a, b); 
		double suma = sum(a, both);
		double sumb = sum(b, both); 
		double sumasq = sumsq(a, both); 
		double sumbsq = sumsq(b, both);
		double pSum = sumprod(a, b, both);
		
		double num = pSum - (suma * sumb/both.size()); 
		double den = Math.sqrt((sumasq - Math.pow(suma, 2)/both.size()) * (sumbsq - Math.pow(sumb, 2)/both.size())); 
		
		if (den == 0 )
			return 0 ; 
		else
			return num/den;
		
	}
	public double Cosine(int[] a, int[] b){
		double result =0; 
		ArrayList<Integer> both = both_have(a, b); 
		double dot = dotproduct(a, b, both); 
		result = dot / (mag(a, both) * mag(b, both)); 
		return result;
	}
	
	public ArrayList<Integer> both_have(int a[], int[] b){
		ArrayList<Integer> both = new ArrayList<Integer>(); 
		for(int i=0; i<a.length; i++){
			if(a[i] >= 0 && b[i] >= 0){
				both.add(i);
			}
		}
		return both; 
	}
	public double sum (int[] a, ArrayList<Integer> x){
		double sum = 0 ;
		for (int i=0; i<x.size(); i++){
			sum = sum + a[x.get(i)]; 
		}
	
		return sum; 
	}
	public double sumsq (int[] a, ArrayList<Integer> x){
		double sum = 0 ;
		for (int i=0; i<x.size(); i++){
			sum = sum + Math.pow(a[x.get(i)],2 ); 
		}
	
		return sum; 
	}
	public double sumprod (int[] a, int[] b, ArrayList<Integer> x){
		double sum = 0 ;
		for (int i=0; i<x.size(); i++){
			sum = sum + (a[x.get(i)] * b[x.get(i)]);  
		}
	
		return sum; 
	}
	public double dotproduct(int[] a, int[] b, ArrayList<Integer> x){
		double result = 0;
		for(int i=0; i<x.size(); i++){
			result = result + (a[x.get(i)] * b[x.get(i)]);
		}
		
		return result; 
	}
	public double mag(int[] a, ArrayList<Integer> x){
		double result = 0; 
		for(int i=0; i<x.size(); i++){
			result = result + (a[x.get(i)] * a[x.get(i)]);
		}
		result = Math.sqrt(result);
		return result; 
	}
}
