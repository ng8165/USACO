#include <bits/stdc++.h>

using namespace std;

int n, k;

int main() {
    ifstream fin("word.in");
    ofstream fout("word.out");

    fin >> n >> k;

    string word;
    int remainChar;

    // handle first word separately
    fin >> word;
    fout << word;
    remainChar = k-word.length();

    for (int i=2; i<=n; i++) {
        fin >> word;

        if (word.length() <= remainChar) {
            fout << " " << word;
            remainChar -= word.length();
        } else {
            fout << endl << word;
            remainChar = k - word.length();
        }
    }
}