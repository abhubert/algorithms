/**
* The SolveSC program minimizes the weight of a 
* subset cover on a set of points.
*
* @author  Aleksander Brynjulf Hübert
* @since   2020-02-18 
*/

package algorithms.approximation.greedy;

import java.util.ArrayList;
import java.util.Comparator;

class SolveSC{

    /**
     * This method minimizes the weight of a set cover using 
     * the greedy 2 approach outlined in Williams & Shmoys
     * @param sets This is an ArrayList of the subsets
     * @param numpoints This is the number of points in the ground set
     * @return ArrayList  The indicies of the set cover.
     */
    private static ArrayList solver(ArrayList sets, int numpoints){
        ArrayList cover = new ArrayList();
        ArrayList indicies = new ArrayList();
        ArrayList<SubSet> setshat = sets; 

        setshat.sort(Comparator.comparing(SubSet::getPrice));
        while(cover.size() != numpoints){
            SubSet temp = setshat.remove(0);
            if (temp.getPrice() == -1){
                continue;
            }
            //adds points to the cover after removing duplicates
            cover.removeAll(temp.getPoints());
            cover.addAll(temp.getPoints());
            indicies.add(temp.getIndex());
        }
        return indicies; 
    }

    /**
     * This method reads the user input and creates subset
     * objects to store the values for use in the minimazation. 
     * @param args This is user arguments specifying the subsets
     * @return ArrayList Returns an ArrayList of the subset objects.
     */
    private static ArrayList readArgs(String[] args){
        ArrayList sets = new ArrayList<>();
        int index = 0;
        for(int i = 1; i < args.length; i++){
            double tempweight = Double.parseDouble(args[i]);
            i++;
            //adds points belonging to the subset
            ArrayList temppoints = new ArrayList<>();
            while(Integer.parseInt(args[i]) != 0){
                temppoints.add(Integer.parseInt(args[i]));
                i++;
            }
            //calculates the price value of each subset
            double price;
            if(temppoints.size() != 0){
                price = tempweight/temppoints.size();
            }else{
                price = -1;
            }
            sets.add(new SubSet(tempweight, temppoints, index, price));
            index++;
        }
        return sets; 
    }

    /**
     * This method runs the readArgs method and the
     * solver method and prints the size of the cover
     * as well as the indicies of subsets in the cover
     * @param args This is arguments specifying the subsets
     * @return ArrayList Returns the list of indicies.
     */
    public static ArrayList run(String[] args){
        int numpoints = Integer.parseInt(args[0]);
        if (numpoints != 0){
            ArrayList sets = readArgs(args);
            ArrayList cover = solver(sets, numpoints);

            System.out.println("Size of Cover: " + cover.size());
            System.out.println("Subset Indicies: ");
            cover.forEach(System.out::println);
            return cover;
        }else{
            System.out.println("No Points to Cover!");
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        run(args);
    }
}

class SubSet{
    private double weight; 
    private ArrayList points;
    private double price;
    private int index;
    //Constructor
    SubSet(double weight, ArrayList points, int index, double price){
        this.weight = weight;
        this.points = points;
        this.index = index;
        this.price = price;
    }
    //Getters
    public double getWeight(){
        return this.weight;
    }
    public ArrayList getPoints(){
        return this.points;
    }
    public double getPrice(){
        return this.price;
    }
    public int getIndex(){
        return this.index;
    }
    //Setters
    public void setWeight(double weight){
        this.weight = weight;
    }
    public void setPoints(ArrayList points){
        this.points = points;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setIndex(int index){
        this.index = index;
    }
}
