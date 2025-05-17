package syntax;

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
        static String castle = "(O\\-O|O\\-O\\-O)";
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

     public static void ProveMatch(String match) {
         Matcher matchTurns = Pattern.compile(gameTurn).matcher(match);
         
         

         while (matchTurns.find() && accpt) {
            String turn = matchTurns.group(1);
            String white = matchTurns.group(2);
            String black = matchTurns.group(3); // can be null
            
            ProveMove(white, turn, "white");
            if(black != null){
                ProveMove(black, turn, "black");
            }
         }
         if(accpt){
             System.out.println("Valid match");
         }

     }

     private static boolean ProveMove(String turnMove, String turnNum, String color){
        if(turnMove.matches(move)){
           return true;
        } else {

            String[][] errors ={
                    {".*[^a-h1-8KQRBNx=\\+#O\\-].*", " contain unknown characters"},
                    {".*[O\\-]+.*", " invalid castle"},
                    {".*[a-h1-8]+.*",  " has invalid square"},
                    {".*[O]+.*",  " invalid castle"},
                    {".*=[^QRBN]+.*",  " has invalid promotion"},
                    {".*[^KQRBN]+.*",  " has invalid piece"},
                    {".*", " is invalid"}
            };

            for(String[] error : errors){
                if(turnNum.matches(error[0])){
                    System.out.println("In turn "+turnNum+", for "+color+" pieces, move "+turnMove + error[1]);
                }
            }

           accpt = false;
           return false;
        }
     }

     public static void main(String[] args) {
         String match = "1. e4 e5 2. N";

         ProveMatch(match);
     }

}


