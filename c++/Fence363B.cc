#include <iostream>
#include <limits>
#include <vector>

using std::cin;
using std::cout;
using std::numeric_limits;
using std::vector;

int main() {
  int num_planks, piano_width;
  cin >> num_planks >> piano_width;

  vector<int> planks;
  planks.push_back(0); // dummy value
  for (int i = 1; i <= num_planks; ++i) {
    int temp;
    cin >> temp;
    planks.push_back(temp);
  }

  long last_width_planks = 0;
  long best_planks = numeric_limits<long>::max();
  int best_index = -1;
  for (int i = 1; i <= num_planks; ++i) {
    last_width_planks += (i > piano_width) ? planks[i] - planks[i - piano_width] : planks[i];
    if (i >= piano_width && last_width_planks < best_planks) {
      best_planks = last_width_planks;
      best_index = i - (piano_width - 1);
    }
  }
  cout << best_index;

  return 0;
}
