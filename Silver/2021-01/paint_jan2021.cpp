// No Time to Paint
// USACO Silver January 2021: http://usaco.org/index.php?page=viewproblem2&cpid=1087

#include <bits/stdc++.h>

using namespace std;

int N, Q;
string s;

int psum[100000]; // psum[i] = min strokes in range from index 0 to i
int ssum[100000]; // ssum[i] = min strokes in range from index i to N-1

int main() {
    cin.tie(0) -> sync_with_stdio(0);

    cin >> N >> Q;
    cin >> s;

    // find prefix sums
    psum[0] = 1;
    for (int i=1; i<N; i++) {
        if (s[i] > s[i-1]) {
            // if this char is greater than the prev char
            // then this psum is 1 + prev psum
            psum[i] = psum[i-1] + 1;
        } else if (s[i] == s[i-1]) {
            // if this char is same as the prev char
            // then this psum is same as prev psum
            psum[i] = psum[i-1];
        } else {
            // if this char is less than the prev char
            // the goal is to look backwards and see if you can connect this char
            // to a char before it that is the same letter
            // must be sure that all characters between them are >= this char
            
            int j=i-1;
            while (j >= 0) {
                if (s[j] < s[i]) {
                    // cannot connect, found a character that is less than this char
                    // this psum is 1 + prev psum
                    psum[i] = psum[i-1] + 1;
                    break;
                } else if (s[j] == s[i]) {
                    // able to connect
                    // this psum is same as prev psum
                    psum[i] = psum[i-1];
                    break;
                }

                j--;
            }
            
            if (j < 0) {
                // unable to find character before this char
                // this psum is 1 + prev psum
                psum[i] = psum[i-1] + 1;
            }
        }
    }

    // find suffix sums (very similar to prefix sums)
    ssum[N-1] = 1;
    for (int i=N-2; i>=0; i--) {
        if (s[i] > s[i+1])
            ssum[i] = ssum[i+1] + 1;
        else if (s[i] == s[i+1])
            ssum[i] = ssum[i+1];
        else {
            int j=i+1;
            while (j < N) {
                if (s[j] < s[i]) {
                    ssum[i] = ssum[i+1] + 1;
                    break;
                } else if (s[j] == s[i]) {
                    ssum[i] = ssum[i+1];
                    break;
                }

                j++;
            }
            
            if (j >= N)
                ssum[i] = ssum[i+1] + 1;
        }
    }

    // process queries
    for (int i=0; i<Q; i++) {
        int l, r; cin >> l >> r;
        l--; r--; // turn 1-indexed to 0-indexed
        int sum = 0;

        if (r < N-1) // if missing range doesn't end at last char
            sum += ssum[r+1];
        
        if (l > 0) // if missing range doesn't start at first char
            sum += psum[l-1];
        
        cout << sum << "\n";
    }

    cout << flush;
}