// Repetitions
// CSES Introductory Problems: https://cses.fi/problemset/task/1069

#include <bits/stdc++.h>

using namespace std;

int n;
string str;

int main() {
    cin >> str;
    n = str.length();

    int i = 0;
    int result = 0;

    while (i < n) {
        char match = str[i];
        int repeat = 0;

        while (i < n && str[i] == match) {
            i++;
            repeat++;
        }

        result = max(result, repeat);
    }

    cout << result << endl;
}