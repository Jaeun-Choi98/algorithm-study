#pragma once
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace recursion {
class Permutation {
 public:
  Permutation(int n) : n_(n), history_(""), check_(n) {};

  void run(int cur) {
    if (cur == n_) {
      std::ostringstream oss;
      int size = tmp_.size();
      oss << "{";
      for (int i = 0; i < size; i++) {
        oss << tmp_.at(i);
        if (i != size - 1) {
          oss << ",";
        }
      }
      oss << "}";
      addHistory(oss.str());
      return;
    }

    for (int i = 0; i < n_; i++) {
      if (check_.at(i)) {
        continue;
      }
      check_.at(i) = true;
      tmp_.push_back(i + 1);
      run(cur + 1);
      check_.at(i) = false;
      tmp_.pop_back();
    }
  };

  void printHistory() { std::cout << "=== 결과 === \n" << history_; }

 private:
  int n_;
  std::string history_;
  std::vector<int> tmp_;
  std::vector<bool> check_;
  void addHistory(const std::string& record) { history_ += record + "\n"; }
};

inline void testPermutation() {
  int n = 5;
  Permutation permutation(n);
  permutation.run(0);
  permutation.printHistory();
}
}  // namespace recursion