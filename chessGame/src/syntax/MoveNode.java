package syntax;
public class MoveNode {
    public String label;
    public MoveNode left;   // white
    public MoveNode right;  // black

    public MoveNode(String label) {
        this.label = label;
    }
}
