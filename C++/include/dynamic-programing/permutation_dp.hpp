#pragma once
#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>
#include <utility>
#include <vector>

namespace dp {
// O(n!) -> O(n2^n)
class PermutationDp {
 public:
  // [2,3,4,5,9]
  PermutationDp()
      : n_(5), limit_(12), history_(""), d_(1 << 5), choice_(1 << 5) {
    weight_.push_back(2);
    weight_.push_back(3);
    weight_.push_back(4);
    weight_.push_back(5);
    weight_.push_back(9);

    std::ostringstream oss;
    oss << "무게: [2,3,4,5,9], 수용무게: 12\n";
    addHistory(oss.str());
  };

  void solve() {
    d_[0] = std::make_pair(1, 0);
    std::ostringstream oss;

    for (int i = 1; i < (1 << n_); i++) {
      d_[i] = std::make_pair(n_ + 1, 0);
      for (int j = 0; j < n_; j++) {
        if (i & (1 << j)) {
          auto option = d_[i ^ (1 << j)];
          if (weight_[j] + option.second <= limit_) {
            option.second += weight_[j];
          } else {
            option.first++;
            option.second = weight_[j];
          }
          if (d_[i] > option) {
            d_[i] = option;
            choice_[i] = j;
          }
        }
      }
    }

    std::vector<std::vector<int>> trips;
    std::vector<int> curTrip;
    int mask = (1 << n_) - 1;
    while (mask != 0) {
      int j = choice_[mask];
      int preMask = mask ^ (1 << j);
      curTrip.push_back(weight_[j]);
      if (d_[mask].first != d_[preMask].first) {
        trips.push_back(curTrip);
        curTrip.clear();
      }

      mask = preMask;
    }

    if (!curTrip.empty()) {
      trips.push_back(curTrip);
    }

    std::reverse(trips.begin(), trips.end());
    oss << "집합: \n";
    for (auto trip : trips) {
      oss << "{ ";
      for (auto j : trip) {
        oss << j << " ";
      }
      oss << "}\n";
    }

    oss << "최소 운행 횟수: " << d_[(1 << n_) - 1].first << "\n";
    addHistory(oss.str());
  }

  void printHistory() { std::cout << "=== 결과 ===\n" << history_; };

 private:
  int n_;
  int limit_;
  std::string history_;
  std::vector<std::pair<int, int>> d_;
  std::vector<int> weight_;
  std::vector<int> choice_;
  void addHistory(const std::string& record) { history_ += record + "\n"; };
};

inline void testPermutationDP() {
  PermutationDp permutationDp;
  permutationDp.solve();
  permutationDp.printHistory();
};
}  // namespace dp