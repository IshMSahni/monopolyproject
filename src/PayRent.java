import java.util.List;

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

        if(property.isOwned() == true){
            for(int i = 0; i < 4; i++){
                if(property.getOwner() == players.get(i).getName()){
                    landLord = players.get(i);
                    paymentProgress();
                }
            }
        }else{
            System.out.println("This property is not owned by anyone... yet");
        }
    }


    public void paymentProgress(){
        int rent = this.property.getRent(this.property.getCost());
        tenant.setMoney(tenant.getMoney() - rent);
        landLord.setMoney(landLord.getMoney() + rent);
    }


}
