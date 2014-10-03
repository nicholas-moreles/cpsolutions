#include <iostream>
#include <vector>

using std::cin;
using std::cout;
using std::vector;

/**
 * Author: Nicholas Moreles
 */
int main() {
  const int NUM_COINS = 3;
  int number;
  int coins[NUM_COINS] = {0};

  cin >> number;
  for (int i = 0; i < NUM_COINS; ++i) {
    cin >> coins[i];
  }

  vector<int> max_coins;
  max_coins.push_back(0);
  for (int i = 1; i <= number; ++i) {
    max_coins.push_back(-1);
    for (int j = 0; j < NUM_COINS; ++j) {
      const int coin = coins[j];
      const int prev = i - coin;
      if (prev >= 0 && max_coins[prev] >= max_coins[i]
            && max_coins[prev] != -1) {
        max_coins[i] = max_coins[prev] + 1;
      }  
    }
  }

  cout << max_coins[number];

  return 0;
}
