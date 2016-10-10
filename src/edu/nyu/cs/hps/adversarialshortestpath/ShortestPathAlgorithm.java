package edu.nyu.cs.hps.adversarialshortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShortestPathAlgorithm {

    public static int[][] floyd(int[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = adjacencyMatrix[i][j];
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][k]!= 0 && d[k][j] != 0) {
                        if(d[i][j] == 0 || d[i][j] > d[i][k] + d[k][j]) {
                            d[i][j] = d[i][k] + d[k][j];
                        }
                    }
                }
            }
        }
        return d;
    }

    public static ArrayList<HashSet<Integer>> dijkstra(int[][] map, int s){
        int n = map.length;
        int[] edges = Arrays.copyOf(map[s],n);
        ArrayList<HashSet<Integer>> prevs = new ArrayList<>();
        for(int i = 0; i < n; i++){
            HashSet<Integer> prev = new HashSet<>();
            prevs.add(prev);
        }
        Set<Integer> finishedSet = new HashSet<>();
        Set<Integer> unfinishedSet = new HashSet<>();
        for(int i = 0; i < n; i++){
            unfinishedSet.add(i);
        }
        int target = s;

        while(!unfinishedSet.isEmpty()){
            finishedSet.add(target);
            unfinishedSet.remove(target);
            for(Integer i : unfinishedSet){
                if(map[target][i] != 0){
                    if(edges[target] + map[target][i] < edges[i] || edges[i] == 0){
                        edges[i] = edges[target] + map[target][i];
                        prevs.get(i).clear();
                        prevs.get(i).add(target);
                    }
                    else if(edges[target] + map[target][i] == edges[i]){
                        prevs.get(i).add(target);
                    }
                }
            }

            int minDis = Integer.MAX_VALUE;
            boolean breakFlag = true;
            for(Integer i : unfinishedSet){
                if(edges[i] < minDis && edges[i] != 0){
                    breakFlag = false;
                    minDis = edges[i];
                    target = i;
                }
            }
            if(breakFlag){
                break;
            }
        }
        return prevs;
    }
}
