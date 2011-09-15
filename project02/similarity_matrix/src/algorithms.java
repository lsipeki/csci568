import java.util.ArrayList;


public class algorithms {
	public double euclidean(int[] a, int[] b){    // euclidean distnace 
		double result =0; 
		ArrayList<Integer> both = both_have(a, b);   // this function finds the common areas of both parties 
		double sum=0; 
		for(int i=0; i<both.size(); i++){
			double sub = a[both.get(i)] - b[both.get(i)];   // getting the difference between the two coensiding attributes 
			sum = sum + Math.pow(sub, 2); 					// raising it to a power of 2 and adding it to the other sums 
		}
		result  = Math.sqrt(sum);						// taking the square root of the sums 
		return 1/(1 + result);						// normalizing the number 
		
	}
	public double smc(int[] a, int[] b){
		ArrayList<Integer> both = both_have(a, b);    // finding the attributes that they both have 
		int matching_attributes = 0 ;   			// initializing attributes that they both have in common
		for( int i =0; i<both.size(); i++){				
			if (a[both.get(i)] == b[both.get(i)]){			// goes through array and finds matching attribtues 
				matching_attributes ++;
			}
		}
		double attributes = both.size(); 			// divide the matching attirbues by the size 
		return matching_attributes/attributes;
	}
	
	public double Jaccard(int[] a, int[] b){
		ArrayList<Integer> both = both_have(a, b); 			// finding the attributes that they both have 
		int matching_attributes = 0 ; 
		for( int i =0; i<both.size(); i++){
			if (a[both.get(i)] == b[both.get(i)] && a[both.get(i)] > 0 ){    // finding all matching possitive attribues 
				matching_attributes ++;
			}
		}
		double attributes = 0;							// finds all attribues 
		for( int i=0; i<both.size(); i++){
			if(a[both.get(i)] != 0)					// finds all attributes with a value on either side that is present 
				attributes++;
			else if(b[both.get(i)] != 0)
				attributes++; 
		}
		return matching_attributes/attributes;	
	}
	public double Pearson(int[] a, int[] b){
		ArrayList<Integer> both = both_have(a, b); // finding the attributes that they both have 
		double suma = sum(a, both);					// sum of a attributes 
		double sumb = sum(b, both); 				// sum of b attributes 
		double sumasq = sumsq(a, both); 			// the sum of squares of a's attributes 
		double sumbsq = sumsq(b, both);				// the sum of squares of b's attributes
		double pSum = sumprod(a, b, both);			// sum of the product of the two data arrays 
		
		double num = pSum - (suma * sumb/both.size());         // takes the product sum and divides it by the multiple of the sums divided by the size 
		double den = Math.sqrt((sumasq - Math.pow(suma, 2)/both.size()) * (sumbsq - Math.pow(sumb, 2)/both.size()));  // takes the square root fo the squares of the sums mius the sums sqared and devided by size 
		
		if (den == 0 )      // returns 0 if the den is 0 
			return 0 ; 
		else
			return num/den;     // returns Pearson Correlation 
		
	}
	public double Cosine(int[] a, int[] b){
		double result =0; 
		ArrayList<Integer> both = both_have(a, b); 		// finding the attributes that they both have
		double dot = dotproduct(a, b, both); 			// finds the dot product of the two arrays 
		result = dot / (mag(a, both) * mag(b, both)); 		// divides dot product by the magnitude a times magnitude b
		return result;					
	}
	
	public ArrayList<Integer> both_have(int a[], int[] b){      // this function finds all the values in which both arrays have a number that's not negative 
		ArrayList<Integer> both = new ArrayList<Integer>(); 
		for(int i=0; i<a.length; i++){
			if(a[i] >= 0 && b[i] >= 0){
				both.add(i);
			}
		}
		return both; 
	}
	public double sum (int[] a, ArrayList<Integer> x){			// reuturns the sum of all vlaues in the array 
		double sum = 0 ;
		for (int i=0; i<x.size(); i++){
			sum = sum + a[x.get(i)]; 
		}
	
		return sum; 
	}
	public double sumsq (int[] a, ArrayList<Integer> x){		// returns the sum of all squared attributes of a data array 
		double sum = 0 ;
		for (int i=0; i<x.size(); i++){
			sum = sum + Math.pow(a[x.get(i)],2 ); 
		}
	
		return sum; 
	}
	public double sumprod (int[] a, int[] b, ArrayList<Integer> x){			// returns the sum of all vlaues of a and b sequentially multiplied togeter (a1*b1 + a2*b2  ... ) 
		double sum = 0 ;
		for (int i=0; i<x.size(); i++){
			sum = sum + (a[x.get(i)] * b[x.get(i)]);  
		}
	
		return sum; 
	}
	public double dotproduct(int[] a, int[] b, ArrayList<Integer> x){     // returns the dot product of two arrays 
		double result = 0;
		for(int i=0; i<x.size(); i++){
			result = result + (a[x.get(i)] * b[x.get(i)]);
		}
		
		return result; 
	}
	public double mag(int[] a, ArrayList<Integer> x){   			// returns the magnitude of the array 
		double result = 0; 
		for(int i=0; i<x.size(); i++){
			result = result + (a[x.get(i)] * a[x.get(i)]);
		}
		result = Math.sqrt(result);
		return result; 
	}
}
