import java.util.Arrays;
import java.util.Hashtable;

public class features {
	Hashtable<Integer, String[]> data;
	Hashtable<Integer, String[]> ndata;
	String[] coll;
	Hashtable<String, Integer> alphabet = new Hashtable<String, Integer>();
	int size;

	public features(Hashtable<Integer, String[]> d, String[] c) {
		ini_alphabet();
		data = d;
		ndata = d;
		size = 2;
		coll = c;
		letter_count(); // 2
		doubles(); // 3
		first_length(); // 4
		last_length(); // 5
		first_last_ratio(); // 6
		vowel_count(); // 7
		first_vowel_count(); // 8
		last_vowel_count(); // 9
		first_cons(); // 10
		last_cons(); // 11
		total_cons(); // 12
		first_fl_distance(); // 13
		last_fl_distance(); // 14
		alphabetical_distance(); // 15
		
		secondposition_vowel(); 

	}

	private void secondposition_vowel() {
		newcol();
		coll[size - 1] = "determines if the second positon in the name is a vowel or not ";
		for (int i = 0; i < data.size(); i++) {
			char[] p = ndata.get(i)[0].toCharArray();
			char sec = p[1];
			if (sec == 'a' || sec == 'e' || sec == 'i' || sec == 'o'
					|| sec == 'u' || sec == 'A' || sec == 'E' || sec == 'I'
					|| sec == 'O' || sec == 'U') {
				ndata.get(i)[size - 1] = String.valueOf(true);
			} else
				ndata.get(i)[size - 1] = String.valueOf(false);
		}
	}

	private void alphabetical_distance() {
		newcol();
		coll[size - 1] = "the of the numerical aphabetical values ";
		for (int i = 0; i < ndata.size(); i++) {
			char[] parts = ndata.get(i)[0].toCharArray();
			int sum = 0;
			for (int k = 0; k < parts.length; k++) {
				if (parts[k] != '.' && parts[k] != ' ' && parts[k] != '-'
						&& parts[k] != '\'') {
					sum = sum + alphabet.get(String.valueOf(parts[k]));
				}

			}
			ndata.get(i)[size - 1] = String.valueOf(sum);

		}
	}

	private void first_fl_distance() {
		newcol();
		coll[size - 1] = "on the alphabet how far awway are the first and last characters of the first name ";
		for (int i = 0; i < ndata.size(); i++) {
			String[] parts = ndata.get(i)[0].split(" ");
			char[] first = parts[0].toCharArray();
			int f = alphabet.get(String.valueOf(first[0]));
			int l = 0;
			if (first[first.length - 1] == '.') {
				l = alphabet.get(String.valueOf(first[first.length - 2]));
			} else
				l = alphabet.get(String.valueOf(first[first.length - 1]));
			ndata.get(i)[size - 1] = String.valueOf(f - l);
		}

	}

	private void last_fl_distance() {
		newcol();
		coll[size - 1] = "on the alphabet how far awway are the first and last characters of the last name ";
		for (int i = 0; i < ndata.size(); i++) {
			String[] parts = ndata.get(i)[0].split(" ");
			char[] first = parts[parts.length - 1].toCharArray();
			int f = alphabet.get(String.valueOf(first[0]));
			int l = 0;
			if (first[first.length - 1] == '.') {
				l = alphabet.get(String.valueOf(first[first.length - 2]));
			} else
				l = alphabet.get(String.valueOf(first[first.length - 1]));
			ndata.get(i)[size - 1] = String.valueOf(f - l);
		}

	}

	private void total_cons() {
		newcol();
		coll[size - 1] = "total consonants in the name";
		for (int i = 0; i < ndata.size(); i++) {
			int first = Integer.parseInt(ndata.get(i)[10]);
			int last = Integer.parseInt(ndata.get(i)[11]);
			int tot = first + last;
			ndata.get(i)[size - 1] = String.valueOf(tot);
		}
	}

	private void first_cons() {
		newcol();
		coll[size - 1] = " consonants in the first name";
		for (int i = 0; i < ndata.size(); i++) {
			int length = Integer.parseInt(ndata.get(i)[4]);
			int vowels = Integer.parseInt(ndata.get(i)[8]);
			int con = length - vowels;
			ndata.get(i)[size - 1] = String.valueOf(con);
		}
	}

	private void last_cons() {
		newcol();
		coll[size - 1] = " consonants in the first name";
		for (int i = 0; i < ndata.size(); i++) {
			int length = Integer.parseInt(ndata.get(i)[5]);
			int vowels = Integer.parseInt(ndata.get(i)[9]);
			int con = length - vowels;
			ndata.get(i)[size - 1] = String.valueOf(con);
		}
	}

	private void first_vowel_count() {
		newcol();
		coll[size - 1] = "vowels in the first name";
		for (int i = 0; i < ndata.size(); i++) {
			int vowel_count = 0;
			String[] parts = ndata.get(i)[0].trim().split(" ");
			char[] a = parts[0].toCharArray();
			for (int k = 0; k < a.length; k++) {
				if (a[k] == 'a' || a[k] == 'e' || a[k] == 'i' || a[k] == 'o'
						|| a[k] == 'u' || a[k] == 'A' || a[k] == 'E'
						|| a[k] == 'I' || a[k] == 'O' || a[k] == 'U') {
					vowel_count++;
				}
			}
			ndata.get(i)[size - 1] = String.valueOf(vowel_count);
		}
	}

	private void last_vowel_count() {
		newcol();
		coll[size - 1] = "vowels in the first name";
		for (int i = 0; i < ndata.size(); i++) {
			int vowel_count = 0;
			String[] parts = ndata.get(i)[0].trim().split(" ");
			char[] a = parts[parts.length - 1].toCharArray();
			for (int k = 0; k < a.length; k++) {
				if (a[k] == 'a' || a[k] == 'e' || a[k] == 'i' || a[k] == 'o'
						|| a[k] == 'u' || a[k] == 'A' || a[k] == 'E'
						|| a[k] == 'I' || a[k] == 'O' || a[k] == 'U') {
					vowel_count++;
				}
			}
			ndata.get(i)[size - 1] = String.valueOf(vowel_count);
		}
	}

	private void vowel_count() {
		newcol();
		coll[size - 1] = "vowels in the name";
		for (int i = 0; i < ndata.size(); i++) {
			int vowel_count = 0;
			char[] a = ndata.get(i)[0].toCharArray();
			for (int k = 0; k < a.length; k++) {
				if (a[k] == 'a' || a[k] == 'e' || a[k] == 'i' || a[k] == 'o'
						|| a[k] == 'u' || a[k] == 'A' || a[k] == 'E'
						|| a[k] == 'I' || a[k] == 'O' || a[k] == 'U') {
					vowel_count++;
				}
			}
			ndata.get(i)[size - 1] = String.valueOf(vowel_count);
		}
	}

	private void first_last_ratio() {
		newcol();
		coll[size - 1] = "ratio of first name to last";
		for (int i = 0; i < ndata.size(); i++) {
			double first = Double.parseDouble(ndata.get(i)[size - 3]);
			double last = Double.parseDouble(ndata.get(i)[size - 2]);
			double result = first / last;
			ndata.get(i)[size - 1] = String.valueOf(result);
		}
	}

	private void first_length() {
		newcol();
		coll[size - 1] = "lenght of first name";
		for (int i = 0; i < ndata.size(); i++) {
			String[] split = ndata.get(i)[0].trim().split(" ");
			ndata.get(i)[size - 1] = String.valueOf(split[0].length());
		}

	}

	private void last_length() {
		newcol();
		coll[size - 1] = "lenght of last name";
		for (int i = 0; i < ndata.size(); i++) {
			String[] split = ndata.get(i)[0].trim().split(" ");
			ndata.get(i)[size - 1] = String.valueOf(split[split.length - 1]
					.length());
		}

	}

	private void doubles() {
		newcol();
		coll[size - 1] = "# or repeat charachters";
		for (int i = 0; i < ndata.size(); i++) {
			char[] a = ndata.get(i)[0].toCharArray();
			Arrays.sort(a);
			int doub = 0;
			for (int k = 1; k < a.length; k++) {
				if (a[k] == a[k - 1]) {
					if (k > 1) {
						if (a[k] != a[k - 2]) {
							doub++;
						}

					} else
						doub++;
				}
			}
			ndata.get(i)[size - 1] = String.valueOf(doub);
		}

	}

	private void letter_count() {
		newcol();
		coll[size - 1] = "# of characters";
		for (int i = 0; i < ndata.size(); i++) {
			ndata.get(i)[size - 1] = String.valueOf(ndata.get(i)[0].length());
		}
	}

	private void newcol() {
		for (int i = 0; i < ndata.size(); i++) {
			String[] n = new String[ndata.get(i).length + 1];
			for (int k = 0; k < ndata.get(i).length; k++) {
				n[k] = ndata.get(i)[k];
			}
			ndata.put(i, n);
		}
		String[] ncoll = new String[coll.length + 1];
		for (int i = 0; i < coll.length; i++) {
			ncoll[i] = coll[i];
		}
		coll = ncoll;
		size++;
	}

	private void ini_alphabet() {
		alphabet.put(new String("a"), new Integer(1));
		alphabet.put(new String("b"), new Integer(2));
		alphabet.put(new String("c"), new Integer(3));
		alphabet.put(new String("d"), new Integer(4));
		alphabet.put(new String("e"), new Integer(5));
		alphabet.put(new String("f"), new Integer(6));
		alphabet.put(new String("g"), new Integer(7));
		alphabet.put(new String("h"), new Integer(8));
		alphabet.put(new String("i"), new Integer(9));
		alphabet.put(new String("j"), new Integer(10));
		alphabet.put(new String("k"), new Integer(11));
		alphabet.put(new String("l"), new Integer(12));
		alphabet.put(new String("m"), new Integer(13));
		alphabet.put(new String("n"), new Integer(14));
		alphabet.put(new String("o"), new Integer(15));
		alphabet.put(new String("p"), new Integer(16));
		alphabet.put(new String("q"), new Integer(17));
		alphabet.put(new String("r"), new Integer(18));
		alphabet.put(new String("s"), new Integer(19));
		alphabet.put(new String("t"), new Integer(20));
		alphabet.put(new String("u"), new Integer(21));
		alphabet.put(new String("v"), new Integer(22));
		alphabet.put(new String("w"), new Integer(23));
		alphabet.put(new String("x"), new Integer(23));
		alphabet.put(new String("y"), new Integer(24));
		alphabet.put(new String("z"), new Integer(25));
		alphabet.put(new String("A"), new Integer(1));
		alphabet.put(new String("B"), new Integer(2));
		alphabet.put(new String("C"), new Integer(3));
		alphabet.put(new String("D"), new Integer(4));
		alphabet.put(new String("E"), new Integer(5));
		alphabet.put(new String("F"), new Integer(6));
		alphabet.put(new String("G"), new Integer(7));
		alphabet.put(new String("H"), new Integer(8));
		alphabet.put(new String("I"), new Integer(9));
		alphabet.put(new String("J"), new Integer(10));
		alphabet.put(new String("K"), new Integer(11));
		alphabet.put(new String("L"), new Integer(12));
		alphabet.put(new String("M"), new Integer(13));
		alphabet.put(new String("N"), new Integer(14));
		alphabet.put(new String("O"), new Integer(15));
		alphabet.put(new String("P"), new Integer(16));
		alphabet.put(new String("Q"), new Integer(17));
		alphabet.put(new String("R"), new Integer(18));
		alphabet.put(new String("S"), new Integer(19));
		alphabet.put(new String("T"), new Integer(20));
		alphabet.put(new String("U"), new Integer(21));
		alphabet.put(new String("V"), new Integer(22));
		alphabet.put(new String("W"), new Integer(23));
		alphabet.put(new String("X"), new Integer(24));
		alphabet.put(new String("Z"), new Integer(25));
		alphabet.put(new String("Y"), new Integer(26));
	}

	public String[] getcol() {
		return coll;
	}

	public Hashtable<Integer, String[]> getNewData() {
		return ndata;
	}
}
