//Author:Aleksander Brynjulf Hübert
#include <iostream>
#include "RandNumber.cpp"
#include <array>
void fisherYates(int *array, int m){
    int i, t = 0;
    
    while(m > 0){
        i = randomizer(m);
        
        t = array[m];
        
        array[m] = array[i];
        array[i] = t;
        
        m -= 1;
    }
}




