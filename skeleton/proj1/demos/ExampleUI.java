/*  If you were to include ExampleUI in a package, you'd need to do the following 
    import statement:

    import edu.princeton.cs.introcs.StdIn;

    The reason is that you actually have StdIn in two locations:
       stdlib.jar (all code is in the default anonymous package)
       stdlib-package.jar (all code is in the edu.princeton.cs.introcs package)

    See lecture 17 for more (3/2) details. But basically if you don't have 
    this import, and things aren't working, you should add this import.
*/

/** An example of a UI.
 *  @author Josh Hug
 */
public class ExampleUI {

    public static void main(String[] args) {
        while (true) {
            System.out.print("> ");
            String line = StdIn.readLine();
            String[] rawTokens = line.split(" ");
            String command = rawTokens[0];
            String[] tokens = new String[rawTokens.length - 1];
            System.arraycopy(rawTokens, 1, tokens, 0, rawTokens.length - 1);
            switch (command) {
                case "quit": 
                    return;
                case "help":
                    In in = new In("help.txt");
                    String helpStr = in.readAll();
                    System.out.println(helpStr);
                    break;  
                case "range": 
                    int startDate = Integer.parseInt(tokens[0]); 
                    int endDate = Integer.parseInt(tokens[1]);
                    System.out.println("Start date: " + startDate);
                    System.out.println("End date: " + endDate);
                    break;
                default:
                    System.out.println("Invalid command.");  
                    break;
            }
        }
    }
} 
