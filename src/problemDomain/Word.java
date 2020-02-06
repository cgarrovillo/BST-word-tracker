package problemDomain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ******************************************** **
 * Word - problemDomain.Word
 * Implementation of a word object that holds the metadata of a String to be used in a WordTracker
 * @author Christian Garrovillo
 * Information and Communications Technologies
 * Software Development
 * * ********************************************* **
 */
public class Word implements Comparable<Word>, Serializable {

    private static final long serialVersionUID = 3279020108896077017L;
    private String word;
    private ArrayList<Integer> lineNumber = new ArrayList<>();      //defensive programming not applicable
//    private int[] lineNumber = {};
    private int occurrences;

    /**
     * Constuctor of this Word
     * @param word the word to be stored in this Word object
     */
    public Word(String word) {
        setWord(word);
//        addLineNumber(lineNumber);
        addOccurrence();
    }

    /**
     * Returns the word that this Word object holds
     * @return the word that this Word object holds
     */
    public String getWord() {
        return word;
    }

    /**
     * Sets the word that this Word object holds
     * @param word the word that this Word object will hold
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Returns the line number that this Word object holds
     * @return the line number that this Word object holds
     */
    public ArrayList<Integer> getLineNumber() {
        return this.lineNumber;
    }

//    public int[] getLineNumber() {
//        return this.lineNumber;
//    }

    /**
     * Sets the line number that this Word object holds
     * @param lineNumber the word that this Word object will hold
     */
    public void addLineNumber(int lineNumber) {
        this.lineNumber.add(lineNumber);
//        if (this.lineNumber == null)
//            this.lineNumber[0] = lineNumber;
//        else
//            this.lineNumber[this.lineNumber.length] = lineNumber;
    }

    /**
     * Returns the amount of occurrences for this specific word
     * @return the amount of occurrences for this specific word
     */
    public int getOccurrences() {
        return occurrences;
    }

    /**
     * Adds 1 to the amount of occurrences for this specific word
     */
    public void addOccurrence() {
        this.occurrences++;
    }

    @Override
    public int compareTo(Word o) {
        return this.word.compareTo(o.getWord());
    }
}
