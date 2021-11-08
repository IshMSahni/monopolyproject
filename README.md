#Milestone 2

##User Manual:

Navigate to the 'MonopolyGUI' class. Then from the IntelliJ toolbar select 
Run -> Run 'MonopolyGUI'.
Upon filling ou the prompts for number of players, and player names; you will be able to play the game. 

##Objective:

In this Milestone we implemented a GUI-based version of the Monopoly game designed in milestone 1. This iteration made use of JFrame as well as ActionListeners to allow for the game to be played via mouse input. 

##GUI Explained: 

The top right of the panel contains buttons allowing the player to roll dice, skip their turn, or surrender. 

The text box below displays information of the events transpiring whilst playing.

Below the board is information pertaining to the players; specifically their name, money, and current position.

The players are represented by different coloured orbs on the board.
Each property is coloured based on their colour set, and once purchased a banner at the top of each property will have a colour corresponding to its owners colour.

##Design Decisions:

The three actions: Roll Dice, End Round, and Surrender are seperated into their own classes. These classes implement ActionListeners so that their functions can be triggered when the button is pressed. RollActionEvent utilises Threads to allow for its functions to be delayed so that the dice rolling 'animation' can be played.

The design and layout of the board is declared in a seperate class called 'GameBoardDesign' as to allow for easy modifications for future milestones. Simalarily the 'panels' folder conatins classes for bothe the board panel itself, and the dice pannel. 

MonopolyGUI is the class in which the game is setup and run form. It calls 'GameBoardDesign' properties to create the board as well as prompting the user for the relevant information. It is also responsible for updating the GUI as the game progresses.


