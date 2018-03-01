/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.util.Random;

public class Model {

    private int[] intArray;
    private int arraySize;

    public Model(int size) {
        arraySize = size;
        intArray = new int[arraySize];
    }

    public void reset(int size) {
        arraySize = size;
        intArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            intArray[i] = i+1;
        }
    }

    public int[] getUnsortedList() {
        Random random = new Random();
        for (int i = 0; i <intArray.length - 1; i++) {
            int index = random.nextInt(i+1);
            int num = intArray[index];
            intArray[index] = intArray[i];
            intArray[i] = num;
        }
        return intArray;
    }
    
    public int getArraySize() {
        return arraySize;
    }

    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }
}
