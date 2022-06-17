#include <bits/stdc++.h>

using namespace std;

int n;
int swaps[105][2];
int guesses[105];

bool shell[5];

void swap(int a, int b) {
    bool temp = shell[a];
    shell[a] = shell[b];
    shell[b] = temp;
}

int main() {
    ifstream fin("shell.in");
    ofstream fout("shell.out");

    fin >> n;

    for (int i=1; i<=n; i++) {
        fin >> swaps[i][0] >> swaps[i][1] >> guesses[i];
    }
    
    // simulate each game
    int result = 0;

    for (int i=1; i<=3; i++) {
        shell[1] = shell[2] = shell[3] = false;
        shell[i] = true;

        //cout << endl << "initial " << shell[1] << " " << shell[2] << " " << shell[3] << endl;
        int correct = 0;

        for (int j=1; j<=n; j++) {
            swap(swaps[j][0], swaps[j][1]);
            //cout << shell[1] << " " << shell[2] << " " << shell[3] << endl;

            if (shell[guesses[j]]) correct++;
        }

        result = max(result, correct);
    }

    fout << result << endl;
}