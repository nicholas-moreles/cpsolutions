#include <cstdio>
#include <cstring>
#include <string>

#define MAX_NUM 50000
#define MAX_SQUARE 224

using namespace std;

/*
 * Author: Nicholas Moreles
 *
 * Details: http://nicholasmoreles.com/cpsolutions/three-square-uva-11342/
 */

void fill_cache(int cache[][4]) {
  for (int i = 0; i <= MAX_SQUARE; i++) {
    for (int j = i; j <= MAX_SQUARE; j++) {
      for (int k = j; k <= MAX_SQUARE; k++) {
        const int total = i*i + j*j + k*k;
        if (total > MAX_NUM) {
          break;
        }
        if (cache[total][0] == 0) {
          ++cache[total][0];
          cache[total][1] = i;
          cache[total][2] = j;
          cache[total][3] = k;
        }
      }
    }
  }
}

int main() {
  int cache[MAX_NUM + 1][4];
  memset(cache, 0, sizeof(int) * (MAX_NUM + 1) * 4);
  fill_cache(cache);

  int num;
  scanf("%d\n", &num); // unneeded
  while (scanf("%d\n", &num) == 1) {
    if (cache[num][0] == 0) {
      printf("-1\n");
    } else {
      printf("%d %d %d\n", cache[num][1], cache[num][2], cache[num][3]);
    }
  }

  return 0;
}
