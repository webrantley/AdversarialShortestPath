package edu.nyu.cs.hps.adversarialshortestpath;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ASPFileReaderTest {
  ASPFileReader testReader;
  
  @Before
  public void setup() throws IOException {
    testReader = new ASPFileReader("advshort");
  }
  
  @Test
  public void testASPFileReader() {
    assertEquals(testReader.getLargestVertex(), new Integer(200));
    assertEquals(testReader.getStartingVertex(), new Integer(165));
    assertEquals(testReader.getEndingVertex(), new Integer(157));
    
    System.out.println(testReader.getLargestVertex());
    
    int[][] adjMatrix = testReader.getAdjacencyMatrix();
    
    System.out.println(adjMatrix[41][18]);
    assertEquals(adjMatrix[18][41], 1);
    assertEquals(adjMatrix[41][18], 1);
    
    assertEquals(adjMatrix[106][170], 1);
    assertEquals(adjMatrix[170][106], 1);
    
    assertEquals(adjMatrix[10][100], 0);
    assertEquals(adjMatrix[100][10], 0);
    
    assertEquals(adjMatrix[10][10], 0);
    assertEquals(adjMatrix[10][10], 0);
  }
}
