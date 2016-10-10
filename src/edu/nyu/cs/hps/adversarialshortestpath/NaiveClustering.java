package edu.nyu.cs.hps.adversarialshortestpath;

import java.util.HashSet;
import java.util.Set;

class NaiveClustering {
  static class Pair {
    private final Integer v1;
    private final Integer v2;
    
    Pair(Integer v1, Integer v2) {
      this.v1 = v1;
      this.v2 = v2;
    }
    
    Integer getVertex1() {
      return v1;
    }
    
    Integer getVertex2() {
      return v2;
    }
    
    @Override
    public int hashCode() {
      if (v1 < v2) {
        return 1000 * v1 + v2;
      } else {
        return 1000 * v2 + v1;
      }
    }
    
    @Override
    public String toString() {
      return v1.toString() + " " + v2.toString();
    }
  }
  
  static Set<Pair> findVulnerableEdges(ASPFileReader game) {
    int[][] adjMatrix = game.getAdjacencyMatrix();
    Set<Integer> vertices = new HashSet<Integer>();
    Set<Integer> foundVertices = new HashSet<Integer>();
    vertices.add(game.getEndingVertex());
    foundVertices.add(game.getEndingVertex());
    
    Set<Pair> leastPairs = new HashSet<Pair>();
    
    for (int depth = 1; depth <= 5; depth++) {
      Set<Pair> outgoingEdges = new HashSet<Pair>();
      Set<Integer> newVertices = new HashSet<Integer>();
      //Search for neighbors of each element in the vertex set
      for(Integer vertex : vertices) {
        //Find other vertices it shares edges with
        for (int i = 0; i < game.getLargestVertex(); i++) {
          //Only consider an edge if it hasn't been traversed before
          if (adjMatrix[vertex][i] != 0 && !foundVertices.contains(i)) {
              outgoingEdges.add(new Pair(vertex, i));
              newVertices.add(i);
          }
        }
      }
      System.out.println("Edges at depth " + depth + ": ");
      for (Pair edgePair : outgoingEdges) {
        System.out.println(edgePair);
      }
      System.out.println();
      // Replace vertices to search with newly found vertices
      vertices = newVertices;
      // 
      if (leastPairs.size() == 0 || outgoingEdges.size() < leastPairs.size()) {
        leastPairs = outgoingEdges;
      }
    }
    return leastPairs;
  }
}