#include <iostream>
#include "RandNumber.hpp"

int randomizer(int n){
    int limit;
    int r;

    limit = RAND_MAX - (RAND_MAX % n);

    while((r = rand()) >= limit);

    return r % n;
}
