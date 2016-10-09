package edu.nyu.cs.hps.adversarialshortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/*** Class that connects to a socket (5000 if the socket number is not defined
 * by the user), and reads the entire graph from the socket. You can get the
 * elements of the game from this class via getter methods. You can write your 
 * move to the server and the server will call the AI with the newest data
 * from the server.
 * 
 * @author William Brantley
 *
 */
class GameController {
  private final Integer portNumber;
  private final Integer startingVertex;
  private final Integer endingVertex;
  private Integer largestVertex = 0;
  private final int[][] adjacencyMatrix;
  
  private Socket gameSocket;
  private PrintWriter outputStream;
  private BufferedReader inputStream;
  
  GameController(Integer portNumber, String playType) throws IOException {
    this.portNumber = portNumber;
    connectToSocket();
    startingVertex = Integer.parseInt(inputStream.readLine().split(": ")[1]);
    endingVertex = Integer.parseInt(inputStream.readLine().split(": ")[1]);
    inputStream.readLine(); // Skip the Edges: line
    adjacencyMatrix = parseGraphData();
  }
  
  GameController(String playType) throws IOException {
    portNumber = 5000;
    connectToSocket();
    startingVertex = Integer.parseInt(inputStream.readLine().split(": ")[1]);
    endingVertex = Integer.parseInt(inputStream.readLine().split(": ")[1]);
    inputStream.readLine(); // Skip Edges: line
    adjacencyMatrix = parseGraphData();
  }
  
  private void connectToSocket() {
    try {
      gameSocket = new Socket(InetAddress.getLocalHost(), this.portNumber);
      outputStream = new PrintWriter(gameSocket.getOutputStream(), true);
      inputStream = new BufferedReader(
          new InputStreamReader(gameSocket.getInputStream()));
    } catch (Exception notHandled) {
      notHandled.printStackTrace();
    }
  }
  
  private int[][] parseGraphData() throws IOException {
    int[][] adjacencyMatrix = new int[1000][1000];
    
    String edgePair;
    while (!"#".equals((edgePair = inputStream.readLine()))) {
      String[] vertices = edgePair.split(" ");
      Integer firstVertex = Integer.parseInt(vertices[0]);
      Integer secondVertex = Integer.parseInt(vertices[1]);
      
      largestVertex = (firstVertex > largestVertex) ? firstVertex : largestVertex;
      largestVertex = (secondVertex > largestVertex) ? secondVertex : largestVertex;
      
      adjacencyMatrix[firstVertex][secondVertex] = 1;
      adjacencyMatrix[secondVertex][firstVertex] = 1;
    }
    
    return adjacencyMatrix;
  }
  
  void writeToSocket(String moveToMake) {
    outputStream.write(moveToMake);
    outputStream.flush();
  }
  
  void endGame() throws IOException {
    outputStream.close();
    inputStream.close();
    gameSocket.close();
  }
  
  /***
   * Get the starting vertex defined by the input file
   * @return Starting vertex of the problem
   */
  Integer getStartingVertex() {
    return startingVertex;
  }
  
  /***
   * Get the ending/goal vertex of the problem
   * @return End vertex of the problem
   */
  Integer getEndingVertex() {
    return endingVertex;
  }
  
  /***
   * Get the ID of the largest vertex in the graph. May be helpful
   * so we don't have to repeatedly view the entire adjacency matrix.
   * @return Largest vertex ID in the graph
   */
  Integer getLargestVertex() {
    return largestVertex;
  }
  
  /***
   * Get the adjacency matrix for the problem
   * @return Adjacency matrix of the graph
   */
  int[][] getAdjacencyMatrix() {
    return adjacencyMatrix;
  }
}
