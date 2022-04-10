// Subset Equality
// USACO Silver US Open 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1231

#include <bits/stdc++.h>

using namespace std;

const int letters = 'r'-'a'+1;

string s;
string t;
int Q;

vector<int> idxS[letters];
vector<int> idxT[letters];
vector<bitset<letters>> no;
set<int> forbidden;

void add(int i, int j) {
    bitset<letters> b;
    b.set(i);
    b.set(j);
    no.push_back(b);
}

bitset<letters> make(string str) {
    bitset<letters> b;
    for (char& c: str)
        b.set(c-'a');
        
    return b;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> s;
    cin >> t;

    // create list of all positions that each character is in
    for (int i=0; i<s.size(); i++)
        idxS[s[i]-'a'].push_back(i);
    for (int i=0; i<t.size(); i++)
        idxT[t[i]-'a'].push_back(i);

    for (int i=0; i<letters; i++) {
        for (int j=i; j<letters; j++) {
            // if same letter, compare how many times they appear in each
            if (i == j) {
                if (idxS[i].size() != idxT[j].size())
                    forbidden.insert(i);
                
                continue;
            }

            auto &si = idxS[i], &sj = idxS[j];
            auto &ti = idxT[i], &tj = idxT[j];

            // must have same number of letter 1 and letter 2
            if (si.size() != ti.size() || sj.size() != tj.size()) {
                add(i, j);
                continue;
            }

            // for all letter 2, try to insert into letter 1 in both words
            // if insertion position same for both letters, then ok
            for (int k=0; k<sj.size(); k++) {
                int lbi = lower_bound(si.begin(), si.end(), sj[k]) - si.begin();
                int lbj = lower_bound(ti.begin(), ti.end(), tj[k]) - ti.begin();

                if (lbi != lbj) {
                    add(i, j);
                    break;
                }
            }
        }
    }

    cin >> Q;

    for (int i=0; i<Q; i++) {
        string str; cin >> str;

        if (str.size() == 1) {
            cout << (forbidden.count(str[0]-'a') ? "N" : "Y");
            continue;
        }

        bitset<letters> b = make(str);

        bool ok = true;

        for (int j=0; j<letters; j++) {
            if (b[j] == 1) {
                if (forbidden.count(j)) {
                    cout << "N";
                    ok = false;
                    break;
                }
            }
        }

        if (!ok) continue;
        
        for (auto& n: no) {
            if ((b & n) == n) {
                cout << "N";
                ok = false;
                break;
            }
        }

        if (ok) cout << "Y";
    }

    cout << "\n";
}