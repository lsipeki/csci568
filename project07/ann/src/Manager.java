import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Manager {
	int in_nodes, mid_nodes, out_nodes;

	double[][] in_weights; 
	double[][] out_weights; 
	double[] in;
	double[] mid;
	double[] out;

	public Manager(int input, int middle, int output) {

		in_nodes = input;
		mid_nodes = middle;
		out_nodes = output;

		in = new double[in_nodes];
		mid = new double[mid_nodes];
		out = new double[out_nodes];
		in_weights = new double[in_nodes][mid_nodes];
		out_weights = new double[mid_nodes][out_nodes]; 
		

		create_connections();
		double[] t = { 1.0, 0.25, -.5 };
		double[] test = run(t);

		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
		train();

	}
	
	private void train(){
		double[] input = { 1.0, 0.25, -.5 };
		double[] target = {1.0, -1.0, 0.0};
		double[] act = run(input);
		while(euclidean(target, act) < .99){
			double[] o = o_errors(act, target);
			double[] m = m_errors(o);
			update_out(o);
			update_mid(m);
			
			act = run(input);
			System.out.println("----------------------");
			for (int i = 0; i < act.length; i++) {
				System.out.println(act[i]);
			}
		}
		System.out.println();
		System.out.println("Target values");
		System.out.println(1.0);
		System.out.println(-1.0);
		System.out.println(0);
	}
	
	private double[] m_errors(double[] o_deltas){
		double[] h_deltas = new double[mid.length];
		double error = 0; 
		for(int i=0; i<h_deltas.length; i++){
			error = 0; 
			for(int k=0; k<o_deltas.length; k++){
				error = error + o_deltas[k]*out_weights[i][k];
			}
			h_deltas[i] = dtanh(mid[i]) * error; 
		}
		
		return h_deltas;
	}
	
	private void update_out(double[] o_delta){
		for(int i=0; i<mid.length; i++){
			for(int k=0; k<out.length; k++){
				double change = o_delta[k] * mid[i];
				out_weights[i][k] = out_weights[i][k] + change/2; 
			}
		}
	}
	
	private void update_mid(double[] m_delta){
		for(int i=0; i<in.length; i++){
			for(int k=0; k<mid.length; k++){
				double change = m_delta[k] * in[i];
				in_weights[i][k] = in_weights[i][k] + change/2; 
			}
		}
	}
	private double dtanh(double y){
		return 1.0 - (y * y); 
	}
	
	private double[] o_errors(double[] r, double[] t){
		double[] o_deltas = new double[r.length];
		for(int i=0; i<r.length; i++){
			double error = t[i] - r[i];
			o_deltas[i] = dtanh(out[i]) * error;
		}
		return o_deltas;
	}

	private void create_connections() {
		Random rand = new Random();
		for(int i=0; i<in_weights.length; i++){
			for(int j=0; j<in_weights[i].length; j++){
				in_weights[i][j] = rand.nextDouble();
			}
		}
		
		for(int i=0; i<out_weights.length; i++){
			for(int j=0; j<out_weights[i].length; j++){
				out_weights[i][j] = rand.nextDouble();
			}
		}
	}

	public double[] run(double[] input) {
		in = input;
		for(int j=0; j<mid.length; j++){
			double value = 0; 
			for(int i=0; i<in.length; i++){	
				value = value + in[i] * in_weights[i][j];
			}
			mid[j] = value;
		}
		for(int j=0; j<out.length; j++){
			double value=0; 
			for(int i=0; i<mid.length; i++){
				value = value + mid[i] * out_weights[i][j];
			}
			out[j] = value;
		}
		return out; 
	}
	
	public double euclidean(double[] a, double[] b){    // euclidean distnace 
		double result =0; 
		double sum=0; 
		for(int i=0; i<b.length; i++){
			double sub = a[i] - b[i];   // getting the difference between the two coensiding attributes 
			sum = sum + Math.pow(sub, 2); 					// raising it to a power of 2 and adding it to the other sums 
		}
		result  = Math.sqrt(sum);						// taking the square root of the sums 
		return 1/(1 + result);						// normalizing the number 
		
	}

}
