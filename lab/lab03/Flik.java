public class Flik {


	static boolean isSameNumber (Integer a, Integer b) {
		return a == b;
	}

	public static void main (String [] args) {
		int i = 0;
		for (int j = 0; i < 500; ++i, ++j) {
			if (!isSameNumber(i, j)) {
          break;
			}
		}
		System.out.println("i is " + i);
	}

}
