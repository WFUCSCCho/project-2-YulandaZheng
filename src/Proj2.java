/****************************************************************************
 @file: Proj2.java
 @description: This program is Proj2 program with the main
 @author: Yulanda Zheng
 @date: October 24, 2024
 ****************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj2 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);

        ArrayList<Cat> arr = new ArrayList<>();
        ArrayList<Cat> og;
        Cat kitty;

        //For calculating time
        long start;
        long end;
        long temp;

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        //putting lines in an array list
        for(int i = 0; i < numLines; i++) {
            String tempLine = inputFileNameScanner.nextLine();
            String[] line = tempLine.split(",");
            kitty = new Cat(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]),line[3],line[4]);
            arr.add(kitty);
        }

        og = arr; //keep original
        Collections.sort(arr); // sort the array

        //Insert into BST and print out time
        BST<Cat> sortedBST = new BST<>();

        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            sortedBST.insert(arr.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("BST Sorted Insert       -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");

        //Insert into AVL and print out runtime
        AvlTree<Cat> sortedAVL = new AvlTree<>();

        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            sortedAVL.insert(arr.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("AVLTree Sorted Insert   -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        Collections.shuffle(arr); //shuffle the array
        //Insert shuffled array into BST and print out runtime
        BST<Cat> shuffleBST = new BST<>();

        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {

            shuffleBST.insert(arr.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("BST Shuffled Insert     -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        //Insert shuffled array into AVLTree
        AvlTree<Cat> shuffleAVL = new AvlTree<>();

        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {

            shuffleAVL.insert(arr.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("AVLTree Shuffled Insert -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        //Search sorted BST against original array
        start = System.nanoTime();
        for (int i = 0; i < numLines; i++) {
            sortedBST.search(og.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("BST Sorted Search       -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        //Search sorted AVLTree against original array
        start = System.nanoTime();
        for (int i = 0; i < numLines; i++) {

            sortedAVL.contains(og.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("AVLTree Sorted Search   -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        //Search shuffled BST against original array
        start = System.nanoTime();
        for (int i = 0; i < numLines; i++) {
            shuffleBST.search(og.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("BST Shuffled Search     -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        //Search sorted AVLTree against original array
        start = System.nanoTime();
        for (int i = 0; i < numLines; i++) {
            shuffleAVL.contains(og.get(i));
        }
        end = System.nanoTime();
        temp = end - start;
        writeToFile("AVLTree Shuffled Search -- Time: " + temp + " Number of lines: " + numLines, "./output.txt");


        //closing input streams
        inputFileNameScanner.close();
        inputFileNameStream.close();
    }


    public static void writeToFile(String content, String filePath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath,true  ))){

            pw.println(content);

        } catch (IOException e) {
            System.out.println("Error writing to file");

        }
    }
}
