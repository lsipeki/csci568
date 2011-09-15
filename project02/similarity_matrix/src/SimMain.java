

public class SimMain {
	public static void main(String[] arg){
		int[] a  = {10, 4, 5, 6, 7, 9 , -1};    // negative represents a missing attirbute in that area
		int[] b  = {10, 4, 5, 6, 7, 9, 10};
		int[] c  = {6, 4, 5, 1, 5, 1};
		int[] bina = {1, 0, 1, 1, 0, 1, 0};
		int[] binb = {1, 0, 1, 1, 0, 1, 0};
		int[] binc = {1, 1, 0, 0, 0 ,0, 0}; 
		
		
		algorithms alg = new algorithms(); 
		
		System.out.println("The Inputs for the Euclidian Distance, Cosine Similarity and the Pearson Correlation are: "); 
		printarray(a);
		printarray(b);
		printarray(c);
		
		System.out.println("The Euclidian Distance between 1 and 2 is : "  + alg.euclidean(a, b));
		System.out.println("The Euclidian Distance between 1 and 3 is : "  + alg.euclidean(a, c));
		System.out.println(); 
		System.out.println("The Cosine Distance between 1 and 2 is : "  + alg.Cosine(a, b));
		System.out.println("The Cosine Distance between 1 and 3 is : "  + alg.Cosine(a, c));
		System.out.println(); 
		System.out.println("The Pearson Correlation between 1 and 2 is : " + alg.Pearson(a, b) );
		System.out.println("The Pearson Correlation between 1 and 3 is : " + alg.Pearson(a, c) );
		System.out.println(); 
		System.out.println("The Inputs for the SMC and Jaccard Coefficient are: "); 
		printarray(bina);
		printarray(binb);
		printarray(binc);
		System.out.println("The SMC correlation between 1 and 2 is : " + alg.smc(bina, binb));
		System.out.println("The SMC correlation between 1 and 3 is : " + alg.smc(bina, binc));
		System.out.println(); 
		System.out.println("The Jaccard coefficient between 1 and 2 is : " + alg.Jaccard(bina, binb));
		System.out.println("The Jaccard coefficient between 1 and 3 is : " + alg.Jaccard(bina, binc));
	}
	public static void printarray(int[] a){
		System.out.print("{");
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] );
			if(i != a.length-1)
				System.out.print(" , ");
		}
		System.out.println("}");
	}
}
