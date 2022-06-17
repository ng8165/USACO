#include <iostream>

using namespace std;

int main() {
    int n;
    cin >> n;

    int cows[n];
    int odd = 0, even = 0;

    for (int i=0; i<n; i++) {
        cin >> cows[i];

        if (cows[i]%2 == 0) even++;
        else odd++;
    }

    while (odd > even) {
        // form two odd into one even
        even++;
        odd -= 2;
    }

    if (even > odd+1) cout << 2*odd+1 << endl; // if too many even, you can combine evens to get even
    else cout << odd + even << endl; // already processed if more odd, so just output
}