package edu.nyu.cs.hps.adversarialshortestpath;

import java.util.*;

public class Helper {
    public static ArrayList<LinkedList<Edge>> converter(int[][] map){
        int n = map.length;
        ArrayList<LinkedList<Edge>> adjacentTable = new ArrayList<>();
        for(int i = 0; i < n; i++){
            LinkedList<Edge> e = new LinkedList<>();
            for(int j = 0; j < n; j++){
                if(map[i][j] != 0){
                    e.add(new Edge(i,j,map[i][j]));
                }
            }
            adjacentTable.add(e);
        }
        return adjacentTable;
    }
}
