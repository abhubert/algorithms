package algorithms.datastructures;

import java.util.ArrayList;

public class Graph{
    private ArrayList<String> verticies;
    private ArrayList<ArrayList<String>> edges;
    private int n;
    public Graph(){
        this.verticies = new ArrayList<String>();
        this.edges = new ArrayList<ArrayList<String>>();
        this.n = 0;
    }
    //GETTING METHODS
    public ArrayList<String> getVerticies(){
        return verticies;
    }

    public ArrayList<ArrayList<String>> getEdges(){
        return edges;
    }

    public ArrayList<String> getSingleEdge(int i){
        return edges.get(i);
    }

    public int getSize(){
        return n;
    }
    //ADDING METHODS
    public void addVertex(String v){
        this.verticies.add(v);
        n++;
    }

    public void addEdge(String v, String u){
        //stores the edge as an arraylist of strings
        ArrayList<String> edge = new ArrayList<String>();
        edge.add(v);
        edge.add(u);
        //adds the arraylist to the edges list
        this.edges.add(edge);
    }

    //DELETING METHODS
    public void deleteVertex(String v){
        this.verticies.remove(v);
        //forloop for removing edges containing the vertex
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).contains(v)){
                edges.remove(i);
            }
        }
        n--; //decreases vertex count
    }

    public void deleteEdge(ArrayList<String> e){
        this.edges.remove(e);
    }

    public void deleteSelfLoop(String v){
        //creates a temporary self loop given a particular node
        //so the temp edge can be used in the delete edge method
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(v);
        temp.add(v);
        deleteEdge(temp);
    }
}