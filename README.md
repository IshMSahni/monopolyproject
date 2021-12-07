# Milestone 4 

## User Manual:

Navigate to the 'MonopolyGUI' class. Then from the IntelliJ toolbar select 
Run -> Run 'MonopolyGUI'. If you face errors simply opening the project try the following,
To load the game:
1) Extract jar contents to a folder of your choice.
2) In IntelliJ, File -> New -> Project From Existing Sources
3) Choose the folder you had extracted the Jar contents into -> OK
4) Next -> Next -> Yes -> Next -> Next -> Next -> Next -> Finish

Upon launching the game you will bne prompted to load a board. You will then be prompted to choose a currency.


## Roadmap:

In this Milestone we implemented additional features based on our version of the Monopoly game designed in milestone 3. This iteration has the following features:
- A save/load feature
- Added different currencies 
- Added different maps and ability to load a custom map

## GUI Explained: 

The top right of the panel contains buttons allowing the players to roll dice, skip their turn, or surrender. 

The text box below displays information of the events transpiring whilst playing.

Below the board is information pertaining to the players; specifically their name, money, and current position.

The players are represented by different coloured orbs on the board.
Each property is coloured based on their colour set, and once purchased a banner at the top of each property will have a colour corresponding to its owners colour.

## Design Decisions:

Serialization was used to read and write when using the save and load functions. GameControl and ConfigureUI classes were created to allow for easier manipulation of the game.
The ability to load a board as opposed to assigning a board based on currency was done to allow users to load a custom board, as well as allow for ease of testing. 
The Surrender button was fixed as to address the issues mentioned in our previous milestones. 

## Known Issues:

The end round button appears to be hidden sometimes when launching the game, resulting in the AI completing its turns rapidly. This is not a game breaking bug as the it does not affect the game itself but is rather a quality of life issue.

The load function works correctly however saves from a different computer may interfere. If an error is encountered, clearing the save folder fixes it.
