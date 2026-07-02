#include "binary-search/binary_search.hpp"
#include "calc/calculator.hpp"
#include "dynamic-programing/knapsack.hpp"
#include "dynamic-programing/minimum_count_coin.hpp"
#include "n-queen/n_queen.hpp"
#include "permutation/permutation.hpp"
#include "sort/sort.hpp"
#include "subset/subset.hpp"

int main() {
  // testCalc();
  // recursion::testSubset();
  // recursion::testPermutation();
  // recursion::testNQueen();
  // sort::testSort();
  // search::testBinarySearch();
  // dp::testMinimumCountCoin();
  dp::testKnapsack();
  return 0;
}
