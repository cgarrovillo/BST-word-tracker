package application;

import exceptions.TreeException;
import problemDomain.Word;
import referenceBasedTreeImplementation.BSTreeNode;
import referenceBasedTreeImplementation.BSTreeReferenceBased;
import utility.Iterator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * ******************************************** **
 * WordTracker - application.WordTracker
 * Implementation of a Word Tracker that data about words in a text file
 * @author Christian Garrovillo
 * Information and Communications Technologies
 * Software Development
 * * ********************************************* **
 */
public class WordTracker {

    static String path = null;
    static BSTreeReferenceBased<Word> tree;
    static File file = null;
    static String outputFileName = null;
    static String inputFileName = null;

    public static void main(String[] args) {
        try {
            if (checkRepoExists())
                loadSerialized();
            else
                tree = new BSTreeReferenceBased<>();

            //last minute bug workaround
            if (Arrays.stream(args).anyMatch("-f"::equals)) {
                outputFileName = args[args.length-1];
            }

            for (String arg : args) {
                if (arg.contains(":\\")) {
                    path = arg;
                    file = new File(path);
                    inputFileName = file.getName();
                    loadTree(inputFileName);
                }


                if (arg.charAt(0) == '-') {
                    if (arg.contains("-f")) {
//                        outputFileName = args[args.length-1];
                    }
                    else if (arg.contains("pf")) {
                        output(false, false);
                    }
                    else if (arg.contains("pl")){
                        output(true, false);
                    }
                    else if (arg.contains("po")){
                        output(true, true);
                    }
                    else {
                        System.out.println("Invalid parameter " + arg);
                        System.out.println("Parameter usage: " +
                                "\n\t\t-pf display which files the word is found in" +
                                "\n\t\t-pl display line numbers in file" +
                                "\n\t\t-po display frequency of occurrences" +
                                "\n\t\t-f <filename> to output to a file");
                    }
                }


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs the word tree that contains all the words into a system standard output
     * @param lines when true, output displays the lines a word occurs in
     * @param occurrences when true, output displays the amount of occurrences of a certain word
     */
    private static void output(boolean lines, boolean occurrences) {
        Iterator<Word> iterator = tree.inorderIterator();
        ArrayList<Word> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }

        try {
            PrintStream ps;
            if (outputFileName!=null){
                ps = new PrintStream(outputFileName);
                System.setOut(ps);
            }

            for (Word w : list) {
                System.out.println(w.getWord());
                System.out.println("\t" + inputFileName);

                if (lines){
                    System.out.println("\tFound in lines:");
                    System.out.print("\t\t");
                    for (Integer i : w.getLineNumber())
                        System.out.print( + i + " ");
                }
                System.out.println("");
                if (occurrences){
                    System.out.println("\tOccurred: " + w.getOccurrences() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a Binary Search Tree from a text file that contains the words
     * @param fileName the name of the file
     */
    private static void loadTree(String fileName) { //bp
        try {
            int currentLine = 0;
            Scanner in = new Scanner(file);
            while (in.hasNextLine()) {
                ++currentLine;
                String line = in.nextLine();
                if ("".equals(line)) {
                    line = in.nextLine();
                    ++currentLine;
                }

                String regex = "[^a-zA-Z\\s]";
                line = line.replaceAll(regex, "");
                line = line.trim().replaceAll("\\s{2,}", " ");
                String[] wordsInLine = line.split("\\s");
                for (String word : wordsInLine) {
                    Word wordObj = new Word(word);
                    if (tree.contains(wordObj)) {
                        BSTreeNode<Word> node = tree.search(wordObj);
                        wordObj = node.getElement();
                        wordObj.addLineNumber(currentLine);
                        wordObj.addOccurrence();
                        tree.search(wordObj).setElement(wordObj);
                    }
                    else {
                        wordObj.addLineNumber(currentLine);
                        tree.add(wordObj);
                    }
                }
            }

            save();
        } catch (FileNotFoundException | TreeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether a binary file already exists
     * @return true if the binary file exists
     */
    private static boolean checkRepoExists(){
        File f = new File ("repository.ser");
        return f.exists();
    }

    /**
     * loads a binary file into the tree
     */
    private static void loadSerialized() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("repository.ser"));
            WordTracker.tree = (BSTreeReferenceBased<Word>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {ois.close();} catch (IOException e) { e.printStackTrace(); }
        }
    }

    /**
     * Saves the tree to a binary file
     */
    private static void save() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("repository.ser"));
            oos.writeObject(WordTracker.tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {oos.close();} catch (IOException e) { e.printStackTrace(); }
        }
    }
}
