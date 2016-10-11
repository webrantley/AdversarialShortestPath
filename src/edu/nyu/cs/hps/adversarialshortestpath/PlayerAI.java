package edu.nyu.cs.hps.adversarialshortestpath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Stack;

public class PlayerAI implements ASPAI{
    GameController gameController;
    long[][] map;
    int N;
    int start,end;
    ArrayList<HashSet<Integer>> prevs;
    Stack<Integer> myPath;
    public String makeMove(String gameUpdate){
        String[] update = gameUpdate.split(" ");
        System.out.println(gameUpdate);
        if(!"-1".equals(update[0])){
            map[Integer.valueOf(update[0])][Integer.valueOf(update[1])] = Long.valueOf(update[2]);
            map[Integer.valueOf(update[1])][Integer.valueOf(update[0])] = Long.valueOf(update[2]);
        }

        System.out.printf("start:%d end:%d\n", start, end);

        System.out.println(String.valueOf(myPath.peek()));
        start = myPath.peek();
        return String.valueOf(Integer.valueOf(myPath.pop()));
    }

    public void setGameController(GameController gameController){
        this.gameController = gameController;
        int[][] readerMap = gameController.getAdjacencyMatrix();
        N = gameController.getLargestVertex() + 1;
        map = new long[N][N];
        //Generate small map;
        for(int i = 0 ;i < N; i++) {
            for(int j = 0; j < N; j++){
                map[i][j] = readerMap[i][j];
            }
        }
        start = gameController.getStartingVertex();
        end = gameController.getEndingVertex();
        prevs = ShortestPathAlgorithm.dijkstra(map, start);
        int current = end;
        Random random = new Random();
        myPath = new Stack<Integer>();
        while(!prevs.get(current).isEmpty()){
            myPath.push(current);
            Integer[] arr = prevs.get(current).toArray(new Integer[prevs.get(current).size()]);
            current = arr[random.nextInt(arr.length)];
        }
    }



}
