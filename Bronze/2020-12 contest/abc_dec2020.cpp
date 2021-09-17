#include <bits/stdc++.h>

using namespace std;

int nums[7];

int main() {
    for (int& num: nums) cin >> num;

    sort(nums, nums+7);

    cout << nums[0] << " " << nums[1] << " " << nums[6]-nums[0]-nums[1] << endl;
}