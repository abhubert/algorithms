#include <iostream>
#include "FisherYates.cpp"
#include <array>
void fisherYatesTest(){
    int m = 10;
    int arr[m];
    for (int i = 0; i < m; i++){
        arr[i] = i;
    }
    for (int i = m - 1; i >= 0; i--)
    std::cout << arr[i];
    fisherYates(arr, m);
    for (int i = m - 1; i >= 0; i--)
    std::cout << arr[i];
}
