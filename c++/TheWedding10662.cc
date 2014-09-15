#include <cstdio>
#include <limits>
#include <string>
#include <vector>
using namespace std;
typedef vector< vector<int> > MATRIX;

/*
 * Author: Nicholas Moreles
 *
 * Details: http://nicholasmoreles.com/cpsolutions/the-wedding-uva-10662/
 */

void populate(MATRIX& matrix, int rows, int cols) {
  int val;
  for (int i = 0; i < rows; i++) {
    matrix.push_back(vector<int> ());
    for (int j = 0; j < cols; j++) {
      scanf("%d", &val);
      matrix[i].push_back(val);  
    }
  }
}

void printLowestCost(MATRIX& travel_agencies, MATRIX& restaurants, MATRIX& hotels) {
  int lowest_cost = numeric_limits<int>::max();
  int best_travel_agency, best_restaurant, best_hotel;

  for (int i = 0; i < travel_agencies.size(); i++) {
    for (int j = 0; j < restaurants.size(); j++) {
      for (int k = 0; k < hotels.size(); k++) {
        if (travel_agencies[i][j+1] == 0 && restaurants[j][k+1] == 0 && hotels[k][i+1] == 0) {
          const int cost = travel_agencies[i][0] + restaurants[j][0] + hotels[k][0];
          if (cost < lowest_cost) {
            lowest_cost = cost;
            best_travel_agency = i;
            best_restaurant = j;
            best_hotel = k;
          }
        }
      }
    }
  }

  if (lowest_cost == numeric_limits<int>::max()) {
    printf("Don't get married!\n");
  } else {
    printf("%d %d %d:%d\n", best_travel_agency, best_restaurant, best_hotel, lowest_cost);
  }
}

int main() {
  int num_travel_agencies, num_restaurants, num_hotels;
  while (scanf("%d %d %d\n", &num_travel_agencies, &num_restaurants, &num_hotels) == 3) {
    MATRIX travel_agencies, hotels, restaurants;
    populate(travel_agencies, num_travel_agencies, num_restaurants + 1);
    populate(restaurants, num_restaurants, num_hotels + 1);
    populate(hotels, num_hotels, num_travel_agencies + 1);
    
    printLowestCost(travel_agencies, restaurants, hotels);
  } 
  return 0;
}
