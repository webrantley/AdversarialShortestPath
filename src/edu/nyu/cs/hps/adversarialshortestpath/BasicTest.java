package edu.nyu.cs.hps.adversarialshortestpath;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BasicTest {

    public static void main(String args[]){
        ASPFileReader reader;
        int[][] readerMap = new int[1][1];
        int N = 1;
        int start = 0;
        int end = 0;
        try {
            reader = new ASPFileReader("advshort.txt");
            readerMap = reader.getAdjacencyMatrix();
            N = reader.getLargestVertex() + 1;
            start = reader.getStartingVertex();
            end = reader.getEndingVertex();
        } catch (java.io.FileNotFoundException e){
            System.out.println("File not found" + e.getMessage());
        } catch (java.io.IOException f){
            System.out.println("IO exception" + f.getMessage());
        }

        System.out.println(N);
        int[][] map = new int[N][N];


        //Print out the map
        for(int i = 0 ;i < N; i++) {
            for(int j = 0; j < N; j++){
                map[i][j] = readerMap[i][j];
            }
        }

        int[][] d = ShortestPathAlgorithm.floyd(map);
        // Print out the floyd result
        int count = 0;
        for(int i = 0 ;i < N; i++) {
            if(d[start][i] != 0) {
                count++;
            }
        }
        System.out.printf("count %d \n",count);


        Kmeans myKmeans = new Kmeans(d);
        myKmeans.clustering(start, end);
        System.out.printf("start:%d end:%d\n", start, end);
        ArrayList<HashSet<Integer>> prevs = ShortestPathAlgorithm.dijkstra(map, start);
        System.out.printf("num of shortest paths left: %d\n", Helper.countPathNum(prevs, end));
        int max = Helper.countPathNum(prevs, end);

        for(Integer i : myKmeans.startSet){
            for(Integer j : myKmeans.endSet){
                if(readerMap[i][j] != 0){
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
                    }
                    map[j][i] /= 2;
                    map[i][j] /= 2;
                }
            }
        }
    }

}
