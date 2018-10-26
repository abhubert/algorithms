#include <iostream>
#include <PriorityQueue.hpp>

class PriorityQueue{
public:
    int size();
    void add(T key);
    bool isEmpty(){
        return size() == 0;
    }
};
