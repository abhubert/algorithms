/**
* Solves min-cut using Krager's Algorithm
*
* The program first reads the graph from args constructing
* a list of edges and verticies using ArrayList storing
* the nodes as strings using a graph class to keep track.
* I leave out self loops since they will never be in the cut.
*
* Since ArrayList has order the edges must be checked
* for both source and destination in some cases.
*
* The program then implements Krager's Algorithm by randomly
* generating an index and picking the edge at that index. 
* it then concatonates the strings to make the new node and 
* updates the edges and removes self loops before chosing the
* next random number until 2 verticies are left.
*
* The number of runs was calculated by solving 
* (1-2/n^2)^runtimes = 0.01 because we want the probability
* of every run to give the wrong answer to be 0.01.
*
* @author  Aleksander Brynjulf Hübert
* @since   2020-04
*/

package algorithms.approximation.randomized;

import java.util.ArrayList;
import java.lang.Math;
import algorithms.datastructures.Graph;

class RMinCut{
    private static Graph readArgs(String[] args){
        //creates the graph
        Graph graph = new Graph();
        int n;
        //checks if the input is even and ignores the last input if odd
        if(args.length % 2 == 0){
            n = args.length;
        }else{
            n = args.length - 1;
        }
        //loops through the array by two adding an edge at a time
        for(int i = 1; i < n; i = i+2){
            //ignoring self loops since they will never be in the cut.
            if (!args[i-1].equals(args[i]) ){
                //adds vertex if not already present
                if(!graph.getVerticies().contains(args[i])){
                    graph.addVertex(args[i]);
                }
                if(!graph.getVerticies().contains(args[i-1])){
                    graph.addVertex(args[i-1]);
                }
                //adds edge if not self loop
                graph.addEdge(args[i-1], args[i]);
            }
        }
        return graph;
    }
    
    private static ArrayList<ArrayList<String>> kragersAlgorithm(Graph graph){
        while(graph.getSize() > 2){
            //n is a random index of the edge list
            int n = (int)(Math.random()*graph.getEdges().size());
            //clones the edge at index n 
            //(This gives an unavoidable unchecked type cast due to the inbuilt clone function in ArrayList returning a generic)
            ArrayList<String> randEdge = (ArrayList<String>)graph.getEdges().get(n).clone();
            //concatonates the edge using string concatination
            String newVertex = randEdge.get(0) + ", " + randEdge.get(1);
            //deletes the original edge from the graph
            graph.deleteEdge(randEdge);
            //for loop to update the edges containing one of the 
            //verticies from the random edge picked
            for(int i = 0; i < graph.getEdges().size(); i++){
                ArrayList<String> edge = graph.getSingleEdge(i);
                if(edge.contains(randEdge.get(0))){
                    //sets the previous node to new concatonated node in first position
                    graph.getSingleEdge(i).set(edge.indexOf(randEdge.get(0)), newVertex);
                }
                if(edge.contains(randEdge.get(1))){
                    //sets the previous node to new concatonated node in second position
                    graph.getSingleEdge(i).set(edge.indexOf(randEdge.get(1)), newVertex);
                }
            }
            //adds the new concatonated vertex to the graph
            graph.addVertex(newVertex);
            //deletes any self loops since the algorithm will never
            //cut them and gives the wrong answer
            graph.deleteSelfLoop(newVertex);
            //deletes the verticies from the graph from the random edge
            graph.deleteVertex(randEdge.get(0));
            graph.deleteVertex(randEdge.get(1));
            //System.out.println(graph.getEdges()); //Debugging the edges as they are concatonated
        }
        return graph.getEdges();
    }
    
    public static void main(String[] args) {
        Graph graph = readArgs(args);

        //Squares the number of vertecies first to avoid catastrophic cancellation
        double graphsqr = (graph.getSize()*graph.getSize());
        //given a error probability of 0.01 I solved for the number of run times
        //by setting runtimes = ln(0.01)/ln(1-(2/n^2))
        double runtimes = (Math.log(0.01))/(Math.log(1-(2/graphsqr)));

        //runs the first itteration of the algorithm and finds the cutsize
        ArrayList<ArrayList<String>> cut = kragersAlgorithm(graph);
        int cutsize = cut.size(); 
        //runs the algorithm based on the number of times calculated
        for(int i = 1; i < runtimes; i++ ){
            // Cloning the object does not give a deep clone, So the array lists nested inside will not be
            // detached from the single memory location so recreating the graph from the args is the simlpest solution
            graph = readArgs(args);
            //reruns the algorithm
            ArrayList<ArrayList<String>> temp = kragersAlgorithm(graph);
            //checks if the cut is smaller 
            if(temp.size() < cutsize){
                cut = temp;
                cutsize = temp.size();
            }
        }
        //gets the first "edge" which will contain the sets cut
        ArrayList<String> sets = cut.get(0);
        System.out.println("====================");
        System.out.println("Size of Cut: " + cut.size());
        System.out.println("====================");
        System.out.println("Set 1: " + "("+ sets.get(0) + ")");
        System.out.println("Set 2: " + "("+ sets.get(1) + ")"); 
    }
}
