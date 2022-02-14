// Do You Know Your ABCs?
// USACO Bronze December 2020: http://usaco.org/index.php?page=viewproblem2&cpid=1059

#include <bits/stdc++.h>

using namespace std;

int nums[7];

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);

    for (int i=0; i<7; i++)
        cin >> nums[i];

    sort(nums, nums+7);

    int A = nums[0], B = nums[1], ABC = nums[6];

    cout << A << " " << B << " " << ABC-A-B << "\n";
}