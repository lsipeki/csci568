import java.util.Arrays;
import java.util.Hashtable;


public class features {
	 Hashtable<Integer, String[]> data; 
	 Hashtable<Integer, String[]> ndata; 
	 String[] coll; 
	 int size; 
	 
	 public features( Hashtable<Integer, String[]> d, String[] c){
		 data = d; 
		 ndata= d; 
		 size = 2; 
		 coll = c; 
		 letter_count();
		 doubles();
		 first_length(); 
		 last_length(); 
		 
	 }
	 public void first_length(){
		 newcol();
		 coll[size-1] = "lenght of first name"; 
		 for(int i=0; i<ndata.size(); i++){
			 String[] split = ndata.get(i)[0].trim().split(" ");
			 ndata.get(i)[size-1] = String.valueOf(split[0].length());
		 }
		 
	 }
	 
	 public void last_length(){
		 newcol();
		 coll[size-1] = "lenght of last name"; 
		 for(int i=0; i<ndata.size(); i++){
			 String[] split = ndata.get(i)[0].trim().split(" ");
			 ndata.get(i)[size-1] = String.valueOf(split[split.length-1].length());
		 }
		 
	 }
	 
	 public void doubles(){
		 newcol(); 
		 coll[size-1] = "# or repeat charachters" ; 
		 for(int i=0; i<ndata.size(); i++){
			 char[] a = ndata.get(i)[0].toCharArray();
			 Arrays.sort(a);
			 int doub =0; 
			 for(int k=1; k < a.length; k++){
				 if(a[k] == a[k-1]){
					 if (k > 1 ){
						 if (a[k] != a[k-2]){
							doub++;  
						 }
						 
					 }
					 else
						 doub++; 
				 }
			 }
			 ndata.get(i)[size-1] = String.valueOf(doub); 
		 }
		 
		 
	 }
	 private void letter_count(){
		 newcol(); 
		 coll[size-1] = "# of characters"; 
		 for(int i =0; i < ndata.size(); i++){
			 ndata.get(i)[size-1] = String.valueOf(ndata.get(i)[0].length());
		 }
	 }
	 
	 private void newcol(){
		 for(int i=0; i < ndata.size(); i++){
			 String[] n = new String[ndata.get(i).length + 1]; 
			 for(int k =0; k < ndata.get(i).length; k++ ){
				 n[k] = ndata.get(i)[k]; 
			 }
			 ndata.put(i, n);
		 }
		 String[] ncoll = new String[coll.length +1]; 
		 for(int i =0; i<coll.length; i++){
			 ncoll[i] = coll[i];
		 }
		 coll = ncoll; 
		 size++ ;
	 }
	 
	 
	 public String[] getcol(){
		  return coll ;
	 }
	 public  Hashtable<Integer, String[]> getNewData(){
		 return ndata; 
	 }
}
