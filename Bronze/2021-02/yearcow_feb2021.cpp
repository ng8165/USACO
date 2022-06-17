// Year of the Cow
// USACO Bronze February 2021: http://www.usaco.org/index.php?page=viewproblem2&cpid=1107

#include <bits/stdc++.h>

using namespace std;

const string cycle[12] = {"Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse",
                          "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat"};

int N;
string cow1, dir, zodiac, cow2;

map<string, int> year;

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    cin >> N;

    year["Bessie"] = 1200;

    for (int i=0; i<N; i++) {
        cin >> cow1 >> dir >> dir >> dir >> zodiac >> cow2 >> cow2 >> cow2;

        int inc = (dir == "next") ? 1 : -1;
        int y = year[cow2];
        
        do {
            y += inc;
        } while (cycle[y%12] != zodiac);

        year[cow1] = y;
    }

    cout << abs(year["Bessie"] - year["Elsie"]) << "\n";
}