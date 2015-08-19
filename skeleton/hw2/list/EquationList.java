package list;

public class EquationList {
    public EquationList next;
    public String equation;
    public int result;

    /**
     * Example of how EquationLists work: this list has two stored equations. 
     * +-------------------+    +-------------------+
     * | EquationList      |    | EquationList      |
     * +-------------------+    +-------------------+
     * | equation: "1 + 2" |    | equation: "3 * 4" |
     * | result:   3       |    | result:   12      |
     * | next:     --------+--->| next:     null    |
     * +-------------------+    +-------------------+
    **/

    public EquationList(String equation, int result, EquationList next) {
        this.equation = equation;
        this.result = result;
        this.next = next;
    }
}
