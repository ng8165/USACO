// Rental Service
// USACO Silver January 2018: http://www.usaco.org/index.php?page=viewproblem2&cpid=787

#include <bits/stdc++.h>

using namespace std;

typedef long long ll;

struct Store {
    int qty;
    int price;

    bool operator<(const Store& other) {
        if (price == other.price) return qty > other.qty;
        return price > other.price;
    }
};

int n, m, r;
int cows[100001];
Store milk[100001];
int rental[100001];

int milkPrice[100001];

int main() {
    ifstream fin("rental.in");
    ofstream fout("rental.out");

    fin >> n >> m >> r;

    for (int i=1; i<=n; i++) fin >> cows[i];
    sort(cows+1, cows+n+1, greater<int>());

    for (int i=1; i<=m; i++) fin >> milk[i].qty >> milk[i].price;
    sort(milk+1, milk+m+1);

    for (int i=1; i<=r; i++) fin >> rental[i];
    sort(rental+1, rental+r+1, greater<int>());

    // calculate price for each cow's milk
    // milk of the cow with most milk is sold for highest price
    ll profit = 0;
    int storeIdx = 1;

    for (int i=1; i<=n; i++) {
        int amt = cows[i];

        while (amt > 0 && storeIdx <= m) {
            auto& [qty, price] = milk[storeIdx];

            int sellAmt = min(amt, qty);

            milkPrice[i] += sellAmt * price;
            qty -= sellAmt;
            amt -= sellAmt;

            if (qty == 0) storeIdx++;
        }

        profit += milkPrice[i];
        if (storeIdx > m) break;
    }

    ll result = profit;

    // rent cows with lowest milk for highest price
    // subtract their milk selling price and add rental price
    int cowIdx = n;
    int rentIdx = 1;

    while (rentIdx<=r && cowIdx>=1) {
        if (milkPrice[cowIdx] >= rental[rentIdx]) break;

        profit -= milkPrice[cowIdx--];
        profit += rental[rentIdx++];

        result = max(result, profit);
    }

    fout << result << endl;
}