package edu.nyu.cs.hps.adversarialshortestpath;
import java.util.Random;

public class BasicTest {

    public static void main(String args[]){
        int N = 10;
        int[][] map = new int[N][N];
        Random random = new Random();
        for(int i = 0 ;i < N; i++){
            for(int j = i; j < N; j++){
                if(i != j) {
                    map[i][j] = random.nextInt(8) + 1;
                    map[j][i] = map[i][j];
                }
                else {
                    map[i][j] = 0;
                }
            }
        }
        for(int i = 0 ;i < N; i++) {
            for(int j = 0; j < N; j++){
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        int[][] d = ShortestPathAlgorithm.floyd(map);

        for(int i = 0 ;i < N; i++) {
            for(int j = 0; j < N; j++){
                System.out.printf("%2d ", d[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        Kmeans myKmeans = new Kmeans(d);
        myKmeans.clustering(0, N-1);

        for(Integer i : myKmeans.startSet){
            System.out.printf("%d ",i);
        }
        System.out.println();
        System.out.println("-------------------------------------------------------------");

        for(Integer i : myKmeans.endSet){
            System.out.printf("%d ",i);
        }

    }

}
