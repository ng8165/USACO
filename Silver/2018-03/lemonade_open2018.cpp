#include <bits/stdc++.h>

using namespace std;

int n;
int w[100005];

int main() {
    ifstream fin("lemonade.in");
    ofstream fout("lemonade.out");

    fin >> n;
    for (int i=1; i<=n; i++) fin >> w[i];
    
    sort(w+1, w+n+1, greater<int>());

    int cowsInLine = 0;

    for (int i=1; i<=n; i++) {
        if (cowsInLine <= w[i]) {
            cowsInLine++;
        }
    }

    fout << cowsInLine << endl;
}