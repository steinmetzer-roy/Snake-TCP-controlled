# Snake-TCP-controlled

This is a very simplified snake game with 2 main classes:
  - Controller
  - Game

The Controller will send packets through TCP on port 5005 to the game. 

Within these Packets are commands contained which control the direction of the snake and also allow it to close the game.

To start the game the Game class has to be executed before the Controller class.
