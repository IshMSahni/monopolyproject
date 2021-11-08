package monopoly;

import java.util.List;
/**
*   @author Gang Han
*   Process the rent payment
*/
public class PayRent {

    private Players tenant;
    private Players landLord;
    private List<Players> players;
    private Property property;
    private Board board;
    private Integer j;


    /**
     * Constructor for monopoly.PayRent, also has function to check if the property is owned
     *
     * @param tenant
     * @param players
     * @param board
     * @param j
     */
    public PayRent(Players tenant, List<Players> players,Board board, Integer j){
        this.tenant = tenant;
        this.board = board;
        this.players = players;
        this.property = board.propertyholder.get(tenant.getPosition());
        this.j = j;

        System.out.println("This property is owned by" + property.getOwner());
        System.out.println(players.get(j).getName()+" just paid $" + property.getRent(
                property.getCost()) + " to " + property.getOwner());
            //If the property is owned
            //Loop through player list to find the owner
            for(int i = 0; i < 4; i++){
                //Once the owner is found
                if(property.getOwner() == players.get(i).getName()){
                    //Assign landLord
                    landLord = players.get(i);
                    paymentProcess();
                }
            }
            //When the property is not owned by anyone
    }

    /**
     * Process the payment
     */
    public void paymentProcess(){
        //Create a local integer of rent
        int rent = this.property.getRent(this.property.getCost());
        //Do payment for tenant
        tenant.setMoney(tenant.getMoney() - rent);
        //Landlord receive the payment
        landLord.setMoney(landLord.getMoney() + rent);
    }


}
