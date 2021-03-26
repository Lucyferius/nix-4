package nix.alevel.util.knightmove;

public class KnightMoveCheck {
   public static boolean checkMovePossible(KnightPosition currentPosition, KnightPosition moveToPosition) {

        if (Math.abs(Math.abs(currentPosition.getRow()) - Math.abs(moveToPosition.getRow())) == 2
                && Math.abs(Math.abs(currentPosition.getColumn()) - Math.abs(moveToPosition.getColumn())) == 1
                || Math.abs(Math.abs(currentPosition.getRow()) - Math.abs(moveToPosition.getRow())) == 1
                && Math.abs(Math.abs(currentPosition.getColumn())  - Math.abs(moveToPosition.getColumn())) == 2 ) {
            return true;
        }
        return false;
    }


}
