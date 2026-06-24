#pragma once
#include <iostream>
#include <sstream>
#include <string>
#include <utility>
#include <vector>

namespace recursion {
class NQuuen {
 public:
  NQuuen(int n)
      : n_(n), history_(""), check_ne_(2 * n), check_se_(2 * n), check_col_(n) {
        };
  void run(int cur_row) {
    if (cur_row == n_) {
      int size = tmp_.size();
      std::ostringstream oss;
      if (size == n_) {
        for (int i = 0; i < n_; i++) {
          for (int j = 0; j < n_; j++) {
            bool find = false;
            for (int k = 0; k < size; k++) {
              std::pair<int, int> coordinate = tmp_.at(k);
              if (coordinate.first == i && coordinate.second == j) {
                oss << "* ";
                find = true;
              }
            }

            if (!find) {
              oss << "- ";
            }
          }
          oss << "\n";
        }

        oss << "\n";
        addHistory(oss.str());
      }
      return;
    }

    for (int col = 0; col < n_; col++) {
      if (check_col_.at(col) || check_ne_.at(cur_row - col + n_) ||
          check_se_.at(cur_row + col))
        continue;
      check_col_.at(col) = check_ne_.at(cur_row - col + n_) =
          check_se_.at(cur_row + col) = true;
      tmp_.push_back(std::make_pair(cur_row, col));
      run(cur_row + 1);
      check_col_.at(col) = check_ne_.at(cur_row - col + n_) =
          check_se_.at(cur_row + col) = false;
      tmp_.pop_back();
    }
  };

  void printHistory() { std::cout << "=== 결과 ===\n" << history_; }

 private:
  int n_;
  std::string history_;
  std::vector<std::pair<int, int>> tmp_;
  std::vector<bool> check_ne_, check_se_, check_col_;
  void addHistory(const std::string& record) { history_ += record + "\n"; }
};

inline void testNQueen() {
  NQuuen nqueen(4);
  nqueen.run(0);
  nqueen.printHistory();
};
}  // namespace recursion
