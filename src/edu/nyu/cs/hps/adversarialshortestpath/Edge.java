package edu.nyu.cs.hps.adversarialshortestpath;

public class Edge {
    int start;
    int end;
    int distance;
    public Edge(int start,int end,int distance){
        this.start = start;
        this.end = end;
        this.distance = distance;
    }
}
