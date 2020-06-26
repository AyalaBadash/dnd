package Controller;

import java.util.Scanner;

public class InputReciever {

    boolean isStart = true;

    public char RecieveUserInput(){
        Scanner scanner = new Scanner ( System.in );
        String input = scanner.nextLine ();
        boolean isValid = CheckValidity ( input.charAt ( 0 ) );
        while (!isValid)
        {
            input = scanner.nextLine ();
            isValid = CheckValidity ( input.charAt ( 0 ) );
        }
        isStart = false;
        return  input.charAt ( 0 );
    }


    //check validity of input
    private boolean CheckValidity(char toCheck){
        if(isStart == true)
        {
            String numbers = "1234567";
            if(numbers.indexOf ( toCheck ) == -1)
                return false;
            return true;
        }
        String moves = "wsadeq";
        if(moves.indexOf ( toCheck ) == -1)
            return  false;
        return true;
    }

}
