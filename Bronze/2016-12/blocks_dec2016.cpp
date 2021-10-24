#include <bits/stdc++.h>

using namespace std;

int n;
int chars[26];
string s1, s2;

int main() {
    ifstream fin("blocks.in");
    ofstream fout("blocks.out");

    fin >> n;

    for (int i=0; i<n; i++) {
        fin >> s1 >> s2;
        vector<int> c1(26), c2(26);

        for (char c: s1) c1[c-'a']++;
        for (char c: s2) c2[c-'a']++;

        for (int j=0; j<26; j++) chars[j] += max(c1[j], c2[j]);
    }

    for (int i=0; i<26; i++) fout << chars[i] << endl;
}