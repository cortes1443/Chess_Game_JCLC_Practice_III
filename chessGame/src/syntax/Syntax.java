package syntax;

import java.util.regex.*;

 class Syntax {

     private static String BuildSyntax() {
        //Board
        String letter = "[a-h]";
        String number = "[1-9]";
        String square = "(" + number + letter + ")";

        //           ------GAME-------
        //Pieces
        String piece = "[KQRBN]";

        //Special moves
        String castle = "(O-O|O-O-O)";
        String check = "(\\+|#)?";
        String promote = "(=[QRBN])?";

        //Pieces moves
        String advPawn = square + promote + check;
        String pawnTakes = letter + "x" + square + promote + check;
        String pawnMove = "(" + advPawn + "|" + pawnTakes + ")";

        String pieceMove = "(" + piece + "(" + letter + "|" + number + "|" + square + ")?x?" + square + promote + check + ")";

        String move = "(" + castle + "|" + pawnMove + "|" + pieceMove + ")";

         return "(\\d)+\\.\\s" + move + "\\s" + move + "?"; //turno (por si acaso)
    }

     /*private static Boolean ProveMatch(String match) {
         Matcher matchTurns = Pattern.compile(BuildSyntax()).matcher(match);


     }*/
}
