package edu.nyu.cs.hps.adversarialshortestpath;

public interface ASPAI {
  /***
   * The AI will receive a move from the server, and must return a string
   * in response to the move made by the opponent. This string must be formatted
   * as described by the architecture's code. The adversary must send two vertices
   * describing the edge to double and the player must send the index of the vertex
   * to move to. Make sure you update the state of the game given the gameUpdate
   * argument, including the case where the strings may be the sign of an error
   * on part of the opponenet (i.e. -1 -1 -1 if the adversary makes a bad move).
   * 
   * @param gameUpdate Updated from the server describing the latest move of the
   *    opponent.
   * @return Formatted string representing the move to be made.
   */
  String makeMove(String gameUpdate);
  
  /***
   * The AI will need to register a GameController to receive the initial
   * data of the game via getter methods. I.e. getStartingVertex(), etc.
   * Make a private GameController variable inside the class. Make sure to
   * copy the state of the game in this function.
   * 
   * @param gameController Controller you're registering to your object.
   */
  void setGameController(GameController gameController);
}
