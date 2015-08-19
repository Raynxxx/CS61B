import java.util.ArrayList;

public class Hopper {
	
	public static void main (String[] args) {
		int val = 3;
		val++;
		StringList rock = new StringList();
		rock.add(1);
		rock.add(2);
		for (int i = 3; i < 20; i++) {
			rock.add(i);
			String s = "This line is not very interesting...";
		}
		String x = "This line is probably more interesting";
		x  = "This is the home stretch!";
		System.out.println("You've ran the last line!");
	}
	
	

}
