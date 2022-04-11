// Subset Equality
// USACO Silver US Open 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1231

#include <bits/stdc++.h>

using namespace std;

const int letters = 'r'-'a'+1;

string s;
string t;
int Q;

bool isEqual[letters][letters];

bool solve() {
    string query; cin >> query;
    const int N = query.size();

    if (N == 1)
        return isEqual[query[0]-'a'][query[0]-'a'];

    // if all character pairs in the query are equal, then this query is equal
    for (int i=0; i<N; i++) {
        for (int j=i+1; j<N; j++) {
            if (!isEqual[query[i]-'a'][query[j]-'a'])
                return false;
        }
    }

    return true;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> s;
    cin >> t;

    // for all pairs of characters, determine equality in two strings
    for (int i=0; i<letters; i++) {
        for (int j=i; j<letters; j++) {
            string ns, nt;

            for (char& c: s) {
                if (c == i+'a' || c == j+'a')
                    ns += c;
            }

            for (char& c: t) {
                if (c == i+'a' || c == j+'a')
                    nt += c;
            }

            isEqual[i][j] = (ns == nt);
        }
    }

    cin >> Q;

    for (int i=0; i<Q; i++)
        cout << (solve() ? 'Y' : 'N');
    
    cout << '\n';
}