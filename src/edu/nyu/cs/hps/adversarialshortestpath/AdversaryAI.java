package edu.nyu.cs.hps.adversarialshortestpath;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

public class AdversaryAI implements ASPAI{
    GameController gameController;
    long[][] map;
    int N;
    int start,end;

    public String makeMove(String gameUpdate){
        start = Integer.valueOf(gameUpdate);
        System.out.println(start);
        long[][] d = ShortestPathAlgorithm.floyd(map);
        Kmeans myKmeans = new Kmeans(d);
        System.out.printf("start:%d end:%d\n", start, end);
        myKmeans.clustering(start, end);
        ArrayList<HashSet<Integer>> prevs = ShortestPathAlgorithm.dijkstra(map, start);
        System.out.printf("num of shortest paths left: %d\n", Helper.countPathNum(prevs, end));
        int max = Helper.countPathNum(prevs, end);
        int bridgeStart = 1;
        int bridgeEnd = 1;
        for(Integer i : myKmeans.startSet){
            for(Integer j : myKmeans.endSet){
                if(map[i][j] != 0){
                    map[i][j] *= 2;
                    map[j][i] *= 2;
                    d = ShortestPathAlgorithm.floyd(map);
                    Kmeans tempKmeans = new Kmeans(d);
                    tempKmeans.clustering(start, end);
                    prevs = ShortestPathAlgorithm.dijkstra(map, start);
                    int num = Helper.countPathNum(prevs, end);
                    System.out.printf("Try double bridge: %d %d result: %d\n", i, j, Helper.countBridges(map,tempKmeans.startSet,tempKmeans.endSet));
                    System.out.printf("num of shortest paths left: %d\n",num);
                    System.out.println("---------------------------------------------");
                    if(num < max) {
                        max = num;
                        bridgeStart = i;
                        bridgeEnd = j;
                    }
                    map[i][j] /= 2;
                    map[j][i] /= 2;
                }
            }
        }
        System.out.println("sent");
        return Integer.toString(bridgeStart) + " " + Integer.toString(bridgeEnd);
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
    }



}
