import java.util.*;

public class BuyCommand {
    private Players players;
    private Property property;

    public BuyCommand(Players players){
        this.players = players;

    }

    private void checkOwned(){

        if(property.isOwned())
            System.out.println("Sorry, this property is already owned");

      //  else if (players.)
    }

}
