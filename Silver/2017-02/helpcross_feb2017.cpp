#include <bits/stdc++.h>

using namespace std;

struct Cow {
    int start, end;

    Cow(int s, int e) {
        start = s;
        end = e;
    }

    bool operator<(const Cow& other) {
        if (end == other.end) return start < other.start;
        return end < other.end;
    }
};

int C, N;
vector<int> chickenTime;
vector<Cow> cowTime;

int main() {
    ifstream fin("helpcross.in");
    ofstream fout("helpcross.out");

    fin >> C >> N;

    for (int i=1; i<=C; i++) {
        int time; fin >> time;
        chickenTime.push_back(time);
    }

    for (int i=1; i<=N; i++) {
        int A, B; fin >> A >> B;
        cowTime.push_back(Cow(A, B));
    }

    sort(chickenTime.begin(), chickenTime.end());
    sort(cowTime.begin(), cowTime.end()); // sort by ending time

    int result = 0;

    for (int& chickTime: chickenTime) {
        // find cow who has ending time closest to current chicken
        auto it = lower_bound(cowTime.begin(), cowTime.end(), Cow(0, chickTime));

        // ensure that cow's start time <= chicken time
        while (it->start > chickTime && it != cowTime.end()) it++;
        
        if (it != cowTime.end()) {
            cowTime.erase(it); // remove cow
            result++;
        }
    }

    fout << result << endl;
}