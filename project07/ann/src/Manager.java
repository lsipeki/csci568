import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Manager {
	// Creates instances for the number of nodes in the input layer, middle layer and the output layer
	private int in_nodes, mid_nodes, out_nodes;

	//Creates a 2d array for the first connections between the input layer and the hidden layer. These will store the values of the weights 
	double[][] in_weights; 
	//Creates a 2d array instance for the second set of connections between the hidden layer and the output layer. These will store the values of the weights
	double[][] out_weights; 
	//The values that will be in the input nodes are stored in this array of doubles
	double[] in;
	//The values that will be in the hidden nodes are stored in this array of doubles
	double[] mid;
	//The values that will be in the output nodes are stored in this array of doubles
	double[] out;

	
	//constructor, the parameters are the number of nodes that each of the layers have 
	public Manager(int input, int middle, int output) {

		
		//lets the input nodes to the private class variable 
		in_nodes = input;
		//sets the number of hidden nodes to the private class variable 
		mid_nodes = middle;
		// sets the number of output nodes to the private class variable 
		out_nodes = output;

		// initializes the double arrays for each layer that sufficiently satisifies the number of nodes in the layer
		in = new double[in_nodes];
		mid = new double[mid_nodes];
		out = new double[out_nodes];
		
		//initializes the 2d array that sufficiently supports the weights between nodes
		in_weights = new double[in_nodes][mid_nodes];
		out_weights = new double[mid_nodes][out_nodes]; 
		
		// calls function create connection that sets up random values for all the weights
		create_connections();
		// initializes the double array to the desired input 
		double[] t = { 1.0, 0.25, -.5 };
		
		// runs the test and places the valus in the test function 
		double[] test = run(t);

		//prints out the first results 
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
		
		//calls the train that will alter the weights of the function depending on their results 
		train();

	}
	
	
	// This function really manages the backprogagation process 
	private void train(){
		// values of given input
		double[] input = { 1.0, 0.25, -.5 };
		// double array of target outputs 
		double[] target = {1.0, -1.0, 0.0};
		// calls run function that puts values into the act(ual) array
		double[] act = run(input);
		// act and target arrays are compared using the Euclidean distance and if values aren't close enought then enters while loop
		while(euclidean(target, act) < .999){
			// function is called to calculate the output_deltas
			double[] o = o_errors(act, target);
			//function is called to calculate the hiddent_deltas with the input parameter of the input deltas
			double[] m = m_errors(o);
			// function is called to update weights connecting hidden nodes to output nodes
			update_out(o);
			// function is called to update weights connecting input nodes to hidden nodes 
			update_mid(m);
			
			// rerunning the new ann 
			act = run(input);
			// New output is printed 
			System.out.println("----------------------");
			for (int i = 0; i < act.length; i++) {
				System.out.println(act[i]);
			}
		}
		
		System.out.println();
		System.out.println("Training Complete Based on ");
		// When the training is done the actaul target values are reprinted for human comparision 
		System.out.println("Target values");
		System.out.println(1.0);
		System.out.println(-1.0);
		System.out.println(0);
	}
	
	
	//this function calcualtes the hidden_deltas 
	private double[] m_errors(double[] o_deltas){
		//creates array apprpriate size for hidden nodes 
		double[] h_deltas = new double[mid.length];
		
		double error = 0; 
		// for all nodes in the hiden layer
		for(int i=0; i<h_deltas.length; i++){
			error = 0; 
			for(int k=0; k<o_deltas.length; k++){
				// calculates the combined total of all of the output_deltas with their respective output_weights
				error = error + o_deltas[k]*out_weights[i][k];
			}
			//the hidden_node values is called with the dtanh function mutiplied with the resulting error
			h_deltas[i] = dtanh(mid[i]) * error; 
		}
		
		return h_deltas;
	}
	
	//this function updates the output_weights between hidden and output layer
	private void update_out(double[] o_delta){
		for(int i=0; i<mid.length; i++){
			for(int k=0; k<out.length; k++){
				// change is caluclated with the output delta multiplied iwth the apropriate hidden node value
				double change = o_delta[k] * mid[i];
				// change is added to the weights affected 
				out_weights[i][k] = out_weights[i][k] + change/2; 
			}
		}
	}
	
	private void update_mid(double[] m_delta){
		// updated the middle 
		for(int i=0; i<in.length; i++){
			for(int k=0; k<mid.length; k++){
				//creates the change that is product of the hidden_deltas and the in_node values 
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
			// find the error which is a difference of the target and the actual 
			double error = t[i] - r[i];
			//product of the dtanh function with the output value and the error 
			o_deltas[i] = dtanh(out[i]) * error;
		}
		return o_deltas;
	}

	
	// This function creates random weights so that the program will be better at training the data
	private void create_connections() {
		// random class instance is defined 
		Random rand = new Random();
		for(int i=0; i<in_weights.length; i++){
			for(int j=0; j<in_weights[i].length; j++){
				//creates a new random number fore each weight in the first set of weighst 
				in_weights[i][j] = rand.nextDouble();
			}
		}
		
		for(int i=0; i<out_weights.length; i++){
			for(int j=0; j<out_weights[i].length; j++){
				// creates a new random double for each set of weights in the second set of weights
				out_weights[i][j] = rand.nextDouble();
			}
		}
	}

	// this function runs values through the ann and returns the result 
	public double[] run(double[] input) {
		
		in = input;
		// this for loop adds up all the weights and apropriate inputs and places them in the array for hidden values 
		for(int j=0; j<mid.length; j++){
			double value = 0; 
			for(int i=0; i<in.length; i++){	
				value = value + in[i] * in_weights[i][j];
			}
			mid[j] = value;
		}
		
		// this for loop takes the weights from the middle and places them in the array for output values 
		for(int j=0; j<out.length; j++){
			double value=0; 
			for(int i=0; i<mid.length; i++){
				value = value + mid[i] * out_weights[i][j];
			}
			out[j] = value;
		}
		return out; 
	}
	
	// euclidean distance from project 4 
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
