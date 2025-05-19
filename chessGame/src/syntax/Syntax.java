package syntax;

import javax.swing.*;
import java.util.regex.*;

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

     public static void proveMatch(String match) {

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
                     JOptionPane.WARNING_MESSAGE
             );
         }else if(!foundAny){
             System.out.println("Please enter a valid match");
             JOptionPane.showMessageDialog(
                     null,
                     "Please enter a valid match.",
                     "match",
                     JOptionPane.WARNING_MESSAGE
             );

         }

     }

     public static boolean proveMove(String turnMove, String turnNum, String color){
        if(turnMove.matches(move)){
            foundAny = true;
           return true;
        } else {
            String[][] errors ={
                    {".*[^a-h1-8KQRBNx=\\+#O\\-].*", " contain unknown characters"},
                    {".*[O][^\\-O].*", " invalid castle"},
                    {".*[i-z]|[9-9]{1,}.*",  " has invalid square"},
                    {".*=[^QRBN]+.*",  " has invalid promotion"},
                    {".*x.*",  " invalid capture"},
                    {".*[^KQRBN].?(" + letter + "|" + number + "|" + square + ")?x?\" + square.*",  " has invalid piece"},
                    {".*", " is invalid"}
            };

            for(String[] error : errors){
                if(turnNum.matches(error[0])){
                    System.out.println("In turn "+turnNum+", for "+color+" pieces, move "+turnMove + error[1]);
                    String mistake = ("In turn "+turnNum+", for "+color+" pieces, move "+turnMove + error[1]);
                    JOptionPane.showMessageDialog(
                            null,
                            mistake,
                            "error",
                            JOptionPane.WARNING_MESSAGE
                    );
                    accpt = false;
                    return false;
                }
            }

           accpt = false;
           return false;
        }
     }


}


