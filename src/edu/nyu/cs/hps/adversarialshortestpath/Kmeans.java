package edu.nyu.cs.hps.adversarialshortestpath;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Kmeans {
    int n;
    int[][] map;
    Set<Integer> startSet;
    Set<Integer> endSet;

    public Kmeans(int[][] map){
        n = map.length;
        this.map = map.clone();
        startSet = new HashSet<>();
        endSet = new HashSet<>();
    }

    public int findNewCentroid(Set<Integer> cluster){
        int sum = Integer.MAX_VALUE;
        int target = 0;
        for(Integer i : cluster){
            int tempSum = 0;
            for(Integer j : cluster){
                if(!j.equals(i)){
                    tempSum += map[i][j];
                }
            }
            if(tempSum < sum){
                sum = tempSum;
                target = i;
            }
        }
        return target;
    }

    public void clustering(int start, int end){
        int rawStart = start;
        int rawEnd = end;
        for(int iteration = 1; iteration < 100; iteration++) {
            startSet.clear();
            endSet.clear();
            startSet.add(rawStart);
            endSet.add(rawEnd);
            for (int i = 0; i < n; i++) {
                if(i != rawEnd && i != rawStart){
                    if (map[i][start] != 0) {
                        if (map[i][end] != 0) {
                            if (map[i][start] < map[i][end]) {
                                startSet.add(i);
                            } else {
                                endSet.add(i);
                            }
                        }
                        else{
                            startSet.add(i);
                        }
                    }
                    else if (map[i][end] != 0) {
                        endSet.add(i);
                    }
                }

            }
            int newStart,newEnd;
            newStart = findNewCentroid(startSet);
            newEnd = findNewCentroid(endSet);
            if(newStart == start && newEnd == end){
                break;
            }
            else{
                start = newStart;
                end = newEnd;
            }
        }
    }
}