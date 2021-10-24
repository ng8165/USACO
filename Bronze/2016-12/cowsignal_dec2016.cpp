#include <bits/stdc++.h>

using namespace std;

int m, n, k;
string row;

int main() {
    ifstream fin("cowsignal.in");
    ofstream fout("cowsignal.out");

    fin >> m >> n >> k;

    for (int a=0; a<m; a++) { // for each row
        fin >> row;

        for (int b=0; b<k; b++) { // print row k times
            for (int c=0; c<n; c++) { // for each character
                for (int d=0; d<k; d++) { // print character k times
                    fout << row[c];
                }
            }
            
            fout << endl;
        }
    }
}