// Counting Haybales
// USACO Silver December 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=666

#include <bits/stdc++.h>

using namespace std;

int n, q;
vector<int> nums;

int main() {
    ifstream fin("haybales.in");
    ofstream fout("haybales.out");

    fin >> n >> q;
    for (int i=0; i<n; i++) {
        int num; fin >> num;
        nums.push_back(num);
    }
    
    sort(nums.begin(), nums.end());

    for (int i=0; i<q; i++) {
        int a, b; fin >> a >> b;

        auto itlow = lower_bound(nums.begin(), nums.end(), a);
        auto itup = upper_bound(itlow, nums.end(), b);

        fout << itup - itlow << endl;
    }
}