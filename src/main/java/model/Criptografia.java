package model;

public class Criptografia {
	public String cripto(String msg, int shift) {
		String s = "";
		int len = msg.length();
		for (int x = 0; x < len; x++) {
			char c = (char) (msg.charAt(x) + shift);
			if (Character.isLetter(msg.charAt(x))) {
				if (c > 'z')
					s += (char) (msg.charAt(x) - (26 - shift));
				else if ((c < 'a') && (shift > 0))
					s += (char) (msg.charAt(x) - shift);
				else if ((c < 'a') && (shift < 0))
					s += (char) (msg.charAt(x) + (26 + shift));
				else
					s += (char) (msg.charAt(x) + shift);
			} else {
				if (c > '9')
					s += (char) (msg.charAt(x) - (10 - shift));
				else if ((c < '0') && (shift > 0))
					s += (char) (msg.charAt(x) - shift);
				else if ((c < '0') && (shift < 0))
					s += (char) (msg.charAt(x) + (10 + shift));
				else
					s += (char) (msg.charAt(x) + shift);
			}
		}
		return s;
	}

	/*public static void main(String[] args) {
		System.out.println(cripto("abcdef", 3));
		System.out.println(cripto("defghi", -3));
		System.out.println(cripto("uvwxyz", 3));
		System.out.println(cripto("xyzabc", -3));
		System.out.println(cripto("abc0123", 3));
		System.out.println(cripto("def3456", -3));
		System.out.println(cripto("xyz7890", 3));
		System.out.println(cripto("abc0123", -3));
	}*/
}
