#include <bits/stdc++.h>

using namespace std;

int cows[3];

int main() {
    ifstream fin("herding.in");
    ofstream fout("herding.out");

    fin >> cows[0] >> cows[1] >> cows[2];

    sort(cows, cows+3);

    // special case: already sorted
    if (cows[2]-cows[1] == 1 && cows[1]-cows[0] == 1) {
        fout << 0 << endl << 0 << endl;
        return 0;
    }

    if (cows[1]-cows[0] == 2 || cows[2]-cows[1] == 2) {
        // one move is sufficient
        fout << 1 << endl;
    } else {
        // can always be done in at least 2 moves
        fout << 2 << endl;
    }

    // max will consist of moving the most, move into biggest gap
    fout << max(cows[2]-cows[1], cows[1]-cows[0])-1 << endl;
}