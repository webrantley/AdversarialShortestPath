package edu.nyu.cs.hps.adversarialshortestpath;

import java.io.IOException;

public class ASPRunner {
  ///When we call our file from the shell script, we can just call it as
  // ASPRunner Adversary/Player PortNumber
  
  public static void main(String[] args) throws IOException {
    GameController gameController = null;
    if ("Player".equals(args[1])) {
      gameController = new GameController(Integer.valueOf(args[0]),args[1]);
      //Create player AI
    } else if ("Adversary".equals(args[1])) {
      //Create Adversary AI
      gameController = new GameController(Integer.valueOf(args[0]),args[1]);
    } else {
      System.out.println("Unknown command line argument. Specify Player or Adversary");
    }
  }

}
