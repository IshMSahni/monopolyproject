import java.util.List;
/*
*   @author Gang Han
*   Process the rent payment
*/
public class PayRent {

    private Players tenant;
    private Players landLord;
    private List<Players> players;
    private Property property;
    private Board board;

    public PayRent(Players tenant, List<Players> players,Board board){
        this.tenant = tenant;
        this.board = board;
        this.players = players;
        this.property = board.propertyholder.get(tenant.getPosition());

        //If the property is owned
        if(property.isOwned() == true){
            //Loop through player list to find the owner
            for(int i = 0; i < 4; i++){
                //Once the owner is found
                if(property.getOwner() == players.get(i).getName()){
                    //Assign landLord
                    landLord = players.get(i);
                    paymentProgress();
                }
            }
            //When the property is not owned by anyone
        }else{
            System.out.println("This property is not owned by anyone... yet");
        }
    }

    //Go through the payment
    public void paymentProgress(){
        //Create a local integer of rent
        int rent = this.property.getRent(this.property.getCost());
        //Do payment for tenant
        tenant.setMoney(tenant.getMoney() - rent);
        //Landlord receive the payment
        landLord.setMoney(landLord.getMoney() + rent);
    }


}
