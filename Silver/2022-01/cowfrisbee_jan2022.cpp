// Cow Frisbee
// USACO Silver January 2022: http://www.usaco.org/index.php?page=viewproblem2&cpid=1183

#include <bits/stdc++.h>

using namespace std;

int N;
int cows[300001];

int idx[300001];
long long result = 0;
stack<int> s; // monotonic stack (all elements are decreasing)

void countFrisbee(int i) {
    int& cow = cows[i];

    // pop until the top is greater than cow
    // all cows that were popped can throw a frisbee with cow
    // because all cows in between are guaranteed to be smaller
    while (!s.empty() && s.top() < cow) {
        result += (abs(idx[s.top()] - i) + 1);
        s.pop();
    }

    s.push(cow);
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;
    for (int i=1; i<=N; i++) {
        cin >> cows[i];
        idx[cows[i]] = i;
    }

    // count all frisbee cases where left cow is smaller than right cow
    for (int i=1; i<=N; i++)
        countFrisbee(i);
    
    while (!s.empty()) s.pop(); // clear the stack

    // count all frisbee cases where left cow is bigger than right cow
    for (int i=N; i>=1; i--)
        countFrisbee(i);

    cout << result << "\n";
}