#include <bits/stdc++.h>

using namespace std;

int capacity[3], amount[3];

int main() {
    ifstream fin("mixmilk.in");
    ofstream fout("mixmilk.out");

    for (int i=0; i<3; i++) fin >> capacity[i] >> amount[i];

    int from = 0, to = 1;
    for (int i=0; i<100; i++) {
        int pour = min(amount[from], capacity[to]-amount[to]);
        amount[from] -= pour;
        amount[to] += pour;

        from = (from+1)%3;
        to = (to+1)%3;
    }

    fout << amount[0] << "\n" << amount[1] <<"\n" << amount[2] << endl;
}