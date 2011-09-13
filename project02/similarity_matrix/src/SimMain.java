

public class SimMain {
	public static void main(String[] arg){
		int[] a  = {10, 4, 5, 6, 7, 9};
		int[] b  = {10, 4, 5, 6, 7, 9};
		int[] c  = {6, 4, 5, 1, 5, 1};
		
		
		algorithms alg = new algorithms(); 
		
		System.out.println(alg.euclidean(a, c));
		System.out.println(alg.smc(a, b));
		System.out.println(alg.Jaccard(a, b));
		System.out.println(alg.Pearson(a, c)); 
		System.out.println(alg.Cosine(a, b)); 
	}
}
