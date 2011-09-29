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
		 letter_count();	//2
		 doubles();			//3
		 first_length(); 	//4
		 last_length(); 	//5
		 first_last_ratio(); //6
		 vowel_count(); 	// 7
		 first_vowel_count(); // 8
		 last_vowel_count(); // 9 
		 
	 }
	 
	 private void first_vowel_count(){
		 newcol() ;
		 coll[size-1] = "vowels in the first name" ;
		 for(int i=0; i<ndata.size(); i++){
			 int vowel_count = 0 ;
			 String[] parts = ndata.get(i)[0].trim().split(" ");
			 char[] a = parts[0].toCharArray();
			 for(int k=0; k < a.length; k++){
				 if(a[k] == 'a' || a[k] == 'e' || a[k] == 'i' ||  a[k] == 'o' || a[k] == 'u' || a[k] == 'A' || a[k] == 'E' || a[k] == 'I' || a[k] == 'O' || a[k] == 'U'){
					 vowel_count++; 
				 }
			 }
			 ndata.get(i)[size-1] = String.valueOf(vowel_count); 
		 }
	 }
	 private void last_vowel_count(){
		 newcol() ;
		 coll[size-1] = "vowels in the first name" ;
		 for(int i=0; i<ndata.size(); i++){
			 int vowel_count = 0 ;
			 String[] parts = ndata.get(i)[0].trim().split(" ");
			 char[] a = parts[parts.length-1].toCharArray();
			 for(int k=0; k < a.length; k++){
				 if(a[k] == 'a' || a[k] == 'e' || a[k] == 'i' ||  a[k] == 'o' || a[k] == 'u' || a[k] == 'A' || a[k] == 'E' || a[k] == 'I' || a[k] == 'O' || a[k] == 'U'){
					 vowel_count++; 
				 }
			 }
			 ndata.get(i)[size-1] = String.valueOf(vowel_count); 
		 }
	 }
	  
	 private void vowel_count(){
		 newcol() ;
		 coll[size-1] = "vowels in the name" ;
		 for(int i=0; i<ndata.size(); i++){
			 int vowel_count = 0 ; 
			 char[] a = ndata.get(i)[0].toCharArray();
			 for(int k=0; k < a.length; k++){
				 if(a[k] == 'a' || a[k] == 'e' || a[k] == 'i' ||  a[k] == 'o' || a[k] == 'u' || a[k] == 'A' || a[k] == 'E' || a[k] == 'I' || a[k] == 'O' || a[k] == 'U'){
					 vowel_count++; 
				 }
			 }
			 ndata.get(i)[size-1] = String.valueOf(vowel_count); 
		 }
	 }
	 
	 
	 private void first_last_ratio(){
		 newcol(); 
		 coll[size-1] = "ratio of first name to last"; 
		 for(int i=0; i<ndata.size(); i++){
			 double first = Double.parseDouble(ndata.get(i)[size-3]); 
			 double last = Double.parseDouble(ndata.get(i)[size-2]); 
			 double result = first/last; 
			 ndata.get(i)[size-1] = String.valueOf(result); 
		 }
	 }
	 
	 private void first_length(){
		 newcol();
		 coll[size-1] = "lenght of first name"; 
		 for(int i=0; i<ndata.size(); i++){
			 String[] split = ndata.get(i)[0].trim().split(" ");
			 ndata.get(i)[size-1] = String.valueOf(split[0].length());
		 }
		 
	 }
	 
	 private void last_length(){
		 newcol();
		 coll[size-1] = "lenght of last name"; 
		 for(int i=0; i<ndata.size(); i++){
			 String[] split = ndata.get(i)[0].trim().split(" ");
			 ndata.get(i)[size-1] = String.valueOf(split[split.length-1].length());
		 }
		 
	 }
	 
	 private void doubles(){
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
