package edu.nyu.cs.hps.adversarialshortestpath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/***
 * Reads input file and creates an object containing all relevant data for the
 * Adversarial Shortest Path problem. I.e., the starting vertex, ending
 * vertex, and the adjacency matrix of the graph. 
 * @author William Brantley
 *
 */
class ASPFileReader {
  private final Integer startingVertex;
  private final Integer endingVertex;
  private final Integer numberOfVertices;
  private final int[][] adjacencyMatrix;
  
  ASPFileReader(String path) throws FileNotFoundException, IOException {
    FileReader firstFileReader = new FileReader(path);
    BufferedReader firstPass = new BufferedReader(firstFileReader);
    
    startingVertex = Integer.parseInt(firstPass.readLine().split(": ")[1]);
    endingVertex = Integer.parseInt(firstPass.readLine().split(": ")[1]);
    firstPass.readLine(); //Ignore Edges: line
    numberOfVertices = findLargestVertex(firstPass) + 1;
    
    firstPass.close();
    firstFileReader.close();
    
    FileReader secondFileReader = new FileReader(path);
    BufferedReader secondPass = new BufferedReader(secondFileReader);
    adjacencyMatrix = readEdgePairs(secondPass, numberOfVertices);
    
    secondPass.close();
    secondFileReader.close();
  }
  
  /***
   * Reads the edge pairs given in the input file and creates an adjacency
   * matrix from the input file. Will contain a 0 at [m][n] if there is no
   * edge between vertex m and vertex n, and will contain a 1 if there is
   * an edge.
   * @param reader BufferedReader pointed at the top of the input file
   * @param numberOfVertices the number of vertices in the graph 
   * @return Adjacency matrix of the graph as described in this doc
   */
  private int[][] readEdgePairs(BufferedReader reader, int numberOfVertices)
      throws IOException{

    int[][] adjacencyMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];
    
    reader.readLine(); // Ignore starting vertex line
    reader.readLine(); // Ignore ending vertex line
    reader.readLine(); // Ignore Edges: line
    
    String edgePair;
    while ((edgePair = reader.readLine()) != null) {
      String[] vertices = edgePair.split(" ");
      Integer firstVertex = Integer.parseInt(vertices[0]);
      Integer secondVertex = Integer.parseInt(vertices[1]);
      
      adjacencyMatrix[firstVertex][secondVertex] = 1;
      adjacencyMatrix[secondVertex][firstVertex] = 1;
    }
    
    return adjacencyMatrix;
  }
  
  /***
   * Parse through a Buffered reader containing only pairs of vertices delimited
   * by a pair of single space. Returns the largest vertex in the graph. I.e., 
   * the number of vertices in the graph.
   * 
   * In this problem, you must push the reader down beyond the Edges: line to get proper behavior.
   * @param reader Buffered Reader pointed to nothing but vertex pairs
   * @return Largest vertex id
   * @throws IOException
   */
  private int findLargestVertex(BufferedReader reader) throws IOException {
    int largestVertex = -1;
    
    String edgePair;
    while ((edgePair = reader.readLine()) != null) {
      String[] vertices = edgePair.split(" ");
      Integer firstVertex = Integer.parseInt(vertices[0]);
      Integer secondVertex = Integer.parseInt(vertices[1]);
      
      if (firstVertex > largestVertex) {
        largestVertex = firstVertex;
      } if (secondVertex > largestVertex) {
        largestVertex = secondVertex;
      }
    }
    
    return largestVertex;
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
   * Get the number of vertices in the graph
   * @return Number of vertices in the graph
   */
  Integer getNumberOfVertices() {
    return numberOfVertices;
  }
  
  /***
   * Get the adjacency matrix for the problem
   * @return Adjacency matrix of the graph
   */
  int[][] getAdjacencyMatrix() {
    return adjacencyMatrix;
  }
}
