// Berry Picking
// USACO Silver January 2020: http://www.usaco.org/index.php?page=viewproblem2&cpid=990

#include <bits/stdc++.h>

using namespace std;

int N, K;
int B[1000];

int check(int maxBucket) {
    priority_queue<int> trees;
    for (int i=0; i<N; i++)
        trees.push(B[i]);

    // elsie's buckets
    for (int i=0; i<K/2; i++) {
        int tree = trees.top(); trees.pop();
        trees.push(max(tree-maxBucket, 0));
    }

    // bessie's buckets
    int result = 0;

    for (int i=0; i<K/2; i++) {
        int tree = trees.top(); trees.pop();
        result += min(maxBucket, tree);
        trees.push(max(tree-maxBucket, 0));
    }

    return result;
}

int main() {
    ifstream fin("berries.in");
    ofstream fout("berries.out");

    int maxTree = 0;
    fin >> N >> K;
    for (int i=0; i<N; i++) {
        fin >> B[i];
        maxTree = max(maxTree, B[i]);
    }

    int result = 0;

    for (int maxBucket=1; maxBucket<=maxTree; maxBucket++)
        result = max(result, check(maxBucket));

    fout << result << "\n";
}