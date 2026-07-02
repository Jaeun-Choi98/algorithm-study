#pragma once
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace dp {
class Knapsack {
 public:
  Knapsack() : history_(""), possible_(5, std::vector<bool>(13, false)) {
    weights_.push_back(0);
    weights_.push_back(1);
    weights_.push_back(3);
    weights_.push_back(3);
    weights_.push_back(5);

    std::ostringstream oss;
    oss << "배낭에 있는 무게들: 1, 3, 3, 5\n";
    addHistory(oss.str());
  };

  void solve() {
    int w_size = weights_.size();
    possible_[0][0] = true;
    for (int i = 1; i < w_size; i++) {
      for (int w = 0; w <= 12; w++) {
        if (w - weights_[i] >= 0) {
          possible_[i][w] =
              possible_[i - 1][w - weights_[i]] || possible_[i][w];
        } else {
          possible_[i][w] = possible_[i - 1][w] || possible_[i][w];
        }
      }
    }
    std::ostringstream oss;
    oss << "만들 수 있는 무게: ";
    for (int w = 0; w <= 12; w++) {
      if (possible_[w_size - 1][w]) {
        oss << w << " ";
      }
    }
    oss << "\n";
    addHistory(oss.str());
  };

  void printHistory() { std::cout << "=== 결과 ===\n" << history_; }

 private:
  std::string history_;
  std::vector<int> weights_;
  std::vector<std::vector<bool>> possible_;
  void addHistory(const std::string& record) { history_ += record + "\n"; }
};

inline void testKnapsack() {
  Knapsack knapsack;
  knapsack.solve();
  knapsack.printHistory();
};
}  // namespace dp