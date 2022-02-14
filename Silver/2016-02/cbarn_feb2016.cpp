// Circular Barn
// USACO Silver February 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=618
// USACO Gold February 2016: http://www.usaco.org/index.php?page=viewproblem2&cpid=621
// Accepted for both Silver (n <= 1,000) and Gold (n <= 100,000) variants.

#include <bits/stdc++.h>

using namespace std;

int n;

vector<int> v[100000];
int orig[100001];

queue<int> q;

// removes all cows from the room except for the first k ones
// and adds removed cows into the waiting queue
void removeFrom(vector<int>& room, int k) {
    if (k >= room.size()) return;

    for (auto it=room.begin()+k; it!=room.end();) {
        q.push(*it);
        it = room.erase(it);
    }
}

int main() {
    ifstream fin("cbarn.in");
    ofstream fout("cbarn.out");

    fin >> n;

    for (int i=0, id=1; i<n; i++) {
        int c; fin >> c;
        for (int j=0; j<c; j++) {
            orig[id] = i; // save original positions to calculate distance traveled later
            v[i].push_back(id++);
        }
    }

    for (int i=0; i<2; i++) {
        // go through twice so cows at the end (index close to n) will definitely be placed in a room
        
        for (int j=0; j<n; j++) {
            vector<int>& room = v[j];

            if (q.empty()) {
                // no cows waiting for a room
                // leave first cow in current room and move others into the queue
                removeFrom(room, 1);
            } else {
                // there are cows waiting for a room
                // remove all cows in this room and add them to the queue
                // then place cow that travelled the farthest into the room
                removeFrom(room, 0);
                room.push_back(q.front()); q.pop();
            }
        }
    }

    long long result = 0;

    // for each cow, find how far it is away from its original position and add to result
    for (int pos=0; pos<n; pos++) {
        int& origPos = orig[v[pos][0]];
        int dist = (origPos > pos) ? n-origPos+pos : pos-origPos;
        result += dist*dist;
    }

    fout << result << endl;
}