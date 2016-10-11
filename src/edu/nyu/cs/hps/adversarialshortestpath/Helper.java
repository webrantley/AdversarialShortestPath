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

    public static int countBridges(long[][] map, Set<Integer> startSet, Set<Integer> endSet){
        int count = 0;
        for(Integer i : startSet){
            for(Integer j : endSet){
                if(map[i][j] != 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static int countPathNum(ArrayList<HashSet<Integer>> prevs, int current){
        if(prevs.get(current).isEmpty()){
            return 1;
        }
        int count = 0;
        for(Integer preve : prevs.get(current)){
            count += countPathNum(prevs, preve);
        }
        return count;
    }
}
