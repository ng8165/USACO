#include <fstream>

using namespace std;

struct CowCount {
    int h, g, j;

    CowCount() {
        h = g = j = 0;
    }
};

int main() {
    ifstream fin("bcount.in");
    ofstream fout("bcount.out");

    int n, q;
    fin >> n >> q;

    CowCount prefix[n+5];
    prefix[0] = CowCount();

    for (int i=1; i<=n; i++) {
        int breed;
        fin >> breed;

        prefix[i] = prefix[i-1];

        if (breed == 1) {
            prefix[i].h++;
        } else if (breed == 2) {
            prefix[i].g++;
        } else {
            prefix[i].j++;
        }
    }

    for (int i=0; i<q; i++) {
        int l, r;
        fin >> l >> r;

        int h = prefix[r].h-prefix[l-1].h;
        int g = prefix[r].g-prefix[l-1].g;
        int j = prefix[r].j-prefix[l-1].j;

        fout << h << " " << g << " " << j << endl;
    }

    return 0;
}