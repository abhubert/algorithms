#include <iostream>
#include <PriorityQueue.hpp>
#include <list>

class LazyPriorityQueue : public PriorityQueue{
    private list keys<T>;
    
    @override
    public int size(){
        return keys.size()
    }
    
    public void add(T key){
        keys.add(key);
    }
    
    public void remove(){
        if keys.size()==0{
            throw NoSuchElementException
        }
        
    }
};
