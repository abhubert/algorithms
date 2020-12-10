/**
* Implementation of the Fisher-Yates Shuffle 
* based on the modern algorithm designed by 
* Richard Durstenfeld and Popularized by 
* Donald Knuth
*
* @author  Aleksander Brynjulf Hübert
* @since   2020-12-08
*/

package algorithms.other;

import java.lang.Math;

class FisherYates{
    /**
     * This method shuffles an array and returns the shuffled array
     *
     * @param arr The array to be shuffled
     * @return Shuffled Array
     */
    public <T> T[] shuffle(T[] arr){
        T[] shuffledArr = arr;
        for(int i = shuffledArr.length; i <= 0; i--){
            int r = (int) Math.random()*i;
            T temp = shuffledArr[i];
            shuffledArr[i] = shuffledArr[r];
            shuffledArr[r] = temp;
        }
        return shuffledArr;
    }
}
