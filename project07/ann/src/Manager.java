import java.util.ArrayList;
import java.util.Random;


public class Manager {
	int in_nodes, mid_nodes, out_nodes; 
	ArrayList<Double[]> weights = new ArrayList<Double[]>();
	double[] in; 
	double[] mid;  
	double[] out; 
	
	public Manager(int input, int middle, int output){
		
	in_nodes  = input; 
	mid_nodes = middle; 
	out_nodes = output; 
	
	in = new double[in_nodes];
	mid = new double[mid_nodes];
	out = new double[out_nodes];
	
	create_connections();
	}
	private void create_connections(){
		Random rand = new Random();
		for(int i=0; i < in_nodes; i++){
			for(int j=0; j < mid_nodes; j++){
				double n = rand.nextDouble(); 
				Double[] n_con = {(double) i,(double) in_nodes+  j, n};
				weights.add(n_con);
				System.out.println(i + "   "  + n_con[1] + "    " + n );
			}
		}
		for(int i=0; i < mid_nodes; i++){
			for(int j=0; j < out_nodes; j++){
				double n = rand.nextDouble(); 
				Double[] n_con = {(double) in_nodes + i,(double) mid_nodes + in_nodes+  j, n};
				weights.add(n_con);
				System.out.println(n_con[0] + "   "  + n_con[1] + "    " + n );
			}
		}
	}

}
