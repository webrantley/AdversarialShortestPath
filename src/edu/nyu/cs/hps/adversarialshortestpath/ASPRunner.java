package edu.nyu.cs.hps.adversarialshortestpath;

import java.io.IOException;

public class ASPRunner {
  ///When we call our file from the shell script, we can just call it as
  // ASPRunner Adversary/Player PortNumber
  
  public static void main(String[] args) throws IOException {
    if ("Player".equals(args[0])) {
      new GameController(args[0]);
      //Create player AI
    } else if ("Adversary".equals(args[0])) {
      //Create Adversary AI
      new GameController(args[0]);
    } else {
      System.out.println("Unknown command line argument. Specify Player or Adversary");
    }
  }

}
