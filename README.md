# Milestone 3

## User Manual:

Navigate to the 'MonopolyGUI' class. Then from the IntelliJ toolbar select 
Run -> Run 'MonopolyGUI'. If you face errors simply opening the project try the following,
To load the game:
1) Extract jar contents to a folder of your choice.
2) In IntelliJ, File -> New -> Project From Existing Sources
3) Choose the folder you had extracted the Jar contents into -> OK
4) Next -> Next -> Yes -> Next -> Next -> Next -> Next -> Finish

Upon filling out the prompts for number of players, and players names; you will be able to play the game. 

## Roadmap:

In this Milestone we implemented additional features based on our version of the Monopoly game designed in milestone 2. This iteration has the following features:
- An AI that will play the game autonomously
- Added special property functionality, and updated the board 
- Added ability to purchase houses and hotels 

## GUI Explained: 

The top right of the panel contains buttons allowing the players to roll dice, skip their turn, or surrender. 

The text box below displays information of the events transpiring whilst playing.

Below the board is information pertaining to the players; specifically their name, money, and current position.

The players are represented by different coloured orbs on the board.
Each property is coloured based on their colour set, and once purchased a banner at the top of each property will have a colour corresponding to its owners colour.

## Design Decisions:

The RollActionEvent handles the brunt of the processing. This is because the addition of special properties directly influences the outcome of the RollActionEvent, (if a player lands on a railroad for example, they should be able to buy it). These special properties were grouped into 2 categories; ones that are purchasable, and those with static events such as tax or jail. RollActionEvent also handles the purchasing of houses and hotels as they are to be triggered upon a dice roll resulting in  the player landing o their own property.

The AI was designed to automatically roll the dice and purchase the property it lands on should it have the funds. The AI turn is intentionally ended via the user clicking the 'End Round' button. This was done so that should the game consist of only AI players, the user would still be able to follow along. The use of delays were tested, however it was found that they would not be able to delay the moves enough without making the game feel as if it were lagging. If the AI is in jail, it will pay the exit fee if it has the funds; otherwise it will attempt to roll a double.




