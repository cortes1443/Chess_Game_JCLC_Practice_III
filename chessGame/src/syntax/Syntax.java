package syntax;

import javax.swing.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class Syntax {

        //Board
        static String letter = "[a-h]";
        static String number = "[1-8]";
        static String square = "(" + letter + number + ")";

        //           ------GAME-------
        //Pieces
        static String piece = "[KQRBN]";

        //Special moves
        static String castle = "(O-O|O-O-O)";
        static String   check = "(\\+|#)?";
        static String promote = "(=[QRBN])?";

        //Pieces moves
        static String advPawn = square + promote + check;
        static String pawnTakes = letter + "x" + square + promote + check;
        static String pawnMove = "(" + advPawn + "|" + pawnTakes + ")";

        static String pieceMove = "(" + piece + "(" + letter + "|" + number + "|" + square + ")?x?" + square + promote + check + ")";

        static String move = "(" + castle + "|" + pawnMove + "|" + pieceMove + ")";
        static String gameTurn = "(\\d+)\\.\\s+([^\\s]+)(?:\\s+(?!\\d+\\.)([^\\s]+))?"; //turno (por si acaso)



    static boolean accpt = true;
    static boolean foundAny = false;

     public static boolean proveMatch(String match) {

         Matcher matchTurns = Pattern.compile(gameTurn).matcher(match);


         while (matchTurns.find() && accpt) {
            String turn = matchTurns.group(1);
            String white = matchTurns.group(2);
            String black = matchTurns.group(3); // can be null
            
            proveMove(white, turn, "white");
            if(black != null){
                proveMove(black, turn, "black");
            }
         }
         if(accpt && foundAny) {
             System.out.println("Valid match");
             JOptionPane.showMessageDialog(
                     null,
                     "Valid match",
                     "Valid match",
                     JOptionPane.INFORMATION_MESSAGE

             );
             return true;
         }else if(!foundAny){
             System.out.println("Please enter a valid match");
             JOptionPane.showMessageDialog(
                     null,
                     "Please enter a valid match.",
                     "match",
                     JOptionPane.ERROR_MESSAGE
             );

         }

         accpt = true;
        foundAny = false;
        return false;
     }

     public static boolean proveMove(String turnMove, String turnNum, String color){
        if(turnMove.matches(move)){
            foundAny = true;
            accpt = true;
           return true;
        } else {
            String[][] errors ={
                    {".*([i-z]|[9-9]{1,}).*",  " has invalid square"},
                    {".*[^KQRBN].?(" + letter + "|" + number + "|" + square + ")?x?" + square+ ".*",  " has invalid piece"},
                    {".*[O][^\\-O].*", " invalid castle"},
                    {".*=[^QRBN]+.*",  " has invalid promotion"},
                    {".*x.*",  " invalid capture"},
                    {".*[^a-h1-8KQRBNx=\\+#O\\-].*", " contain unknown characters"},
                    {".*", " is invalid"}
            };

            for(String[] error : errors){
                if(turnMove.matches(error[0])){
                    System.out.println("In turn "+turnNum+", for "+color+" pieces, move "+turnMove + error[1]);
                    String mistake = ("In turn "+turnNum+", for "+color+" pieces, move "+turnMove + error[1]);
                    JOptionPane.showMessageDialog(
                            null,
                            mistake,
                            "error",
                            JOptionPane.WARNING_MESSAGE
                    );
                    foundAny = true;
                    accpt = false;
                    return false;
                }
            }

           accpt = false;
           return false;
        }
     }

     public static MoveNode buildTreeFromMatch(String match) {
         Matcher matchTurns = Pattern.compile(gameTurn).matcher(match);
         MoveNode root = new MoveNode("Partida");

         while (matchTurns.find()) {
             String turn = matchTurns.group(1);
             String white = matchTurns.group(2);
             String black = matchTurns.group(3); // puede ser null

             MoveNode whiteNode = null;
             MoveNode blackNode = null;

             if (white != null && proveMove(white, turn, "white")) {
                 whiteNode = new MoveNode(white);
             }
             if (black != null && proveMove(black, turn, "black")) {
                 blackNode = new MoveNode(black);
             }

             // Insertar en el árbol binario
             insertIntoTree(root, whiteNode, blackNode);
         }

         return root;
     }

     // Método auxiliar para insertar en el siguiente lugar disponible
     private static void insertIntoTree(MoveNode current, MoveNode whiteNode, MoveNode blackNode) {
         // BFS para encontrar el siguiente par de nodos disponibles
         Queue<MoveNode> queue = new LinkedList<>();
         queue.add(current);

         while (!queue.isEmpty()) {
             MoveNode node = queue.poll();

             if (node.left == null && whiteNode != null) {
                 node.left = whiteNode;
                 if (blackNode != null) node.right = blackNode;
                 return;
             } else if (node.right == null && node.left != null) {
                 if (blackNode != null) {
                     node.right = blackNode;
                     return;
                 }
             }

             if (node.left != null) queue.add(node.left);
             if (node.right != null) queue.add(node.right);
         }
     }




}


