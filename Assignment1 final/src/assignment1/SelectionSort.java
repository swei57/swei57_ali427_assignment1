/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author wtfmi
 */
public class SelectionSort implements SortingStrategy{
   
    @Override //selection sort code
    public void sortingMethod(int[] array){
       ThreadLocalRandom.current();
        for (int i = 0; i < array.length - 1; i++){
            int index = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[index]) 
                    index = j;
      
            int smallerNumber = array[index];  
            array[index] = array[i];
            array[i] = smallerNumber;
        }
    }
}
