import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
/**
 * Fleury's Algorithm implementation
 * 
 * @author Filipe Castanheira 
 * @version 11/10/2018
 */
public class Fleury
{
    int[][] tempGraph;
    int size = 0;
    static int edge;
    int startVect = 0;
    Scanner keyboard = new Scanner(System.in);

    public Fleury() {
        System.out.println("Enter size:");
        size = Integer.parseInt(keyboard.nextLine());
        tempGraph = new int[size][size];
        System.out.println("Enter the elements of the " + size + " by " + size + " matrix");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.println("Element [" + i + "][" + j + "]");
                tempGraph[i][j] = Integer.parseInt(keyboard.nextLine());
            }
        }
        keyboard.close();
    }

    public int findStartVert() {
        for(int i = 0; i < size; i++) {
            int deg = 0;
            for(int j = 0; j < size; j++) {
                if(tempGraph[i][j] == 1) {
                    deg++;
                }
            }

            if(deg % 2 != 0) {     //when degree of vertices are odd
                return i;          //i is node with odd degree
            }
        }
        return 0;  //when all vertices have even degree, start from 0
    }
    
    public void isCircuit() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if((tempGraph[i][j] == 1 && (this.findStartVert() == j))) {
                    System.out.println(i + "--" + j + " ");
                    System.out.println("This Adjacency Matrix has an Eulerian Circuit");
                }
            }
        }
    }

    public boolean isBridge(int u, int v) {
        int deg = 0;
        for(int i = 0; i < size; i++) {
            if(tempGraph[v][i] == 1) {
                deg++;
            }
        }
        if(deg > 1) {
            return false;  //the edge is not forming bridge
        }
        return true;  //edge forming a bridge
    }

    public int edgeCount() {
        int count = 0;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(tempGraph[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;    //count nunber of edges in the graph
    }

    public void calculate(int start) {
        edge = edgeCount();
        for(int i = 0; i < size; i++) {
            if(tempGraph[start][i] == 1) {
                if(edge <= 1 || !isBridge(start, i)) {
                    System.out.println(start + "--" + i + " ");
                    tempGraph[start][i] = 0;
                    tempGraph[i][start] = 0;
                    calculate(i);
                }
            }
        }
    }
    
    public void matrixToString() {
        System.out.println("");
        System.out.println("Given Adjacency Matrix:");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                System.out.print(tempGraph[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Fleury a = new Fleury();
        a.matrixToString();

        System.out.println("Euler Path or Circuit: ");
        a.calculate(a.findStartVert());
        a.isCircuit();
    }
}