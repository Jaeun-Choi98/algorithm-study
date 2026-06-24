#pragma once
#include <algorithm>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace search {
class BinarySearch {
 public:
  BinarySearch(int size) : history_("") {
    int r = 0;
    for (int i = 0; i < size; i++) {
      r = std::rand() % 4;
      array_.push_back(r);
      // oss << r << ",";
    }

    std::sort(array_.begin(), array_.end());
    std::ostringstream oss;
    oss << "배열: [ ";
    for (auto v : array_) {
      oss << v << " ";
    }
    oss << "]\n";
    addHistory(oss.str());
  };

  void bSearch(int target) {
    int s_idx = 0;
    int e_idx = array_.size() - 1;
    while (s_idx <= e_idx) {
      auto m = (s_idx + e_idx) / 2;
      if (array_[m] == target) {
        s_idx = m;
        break;
      }

      if (array_[m] > target) {
        e_idx = m - 1;
      } else {
        s_idx = m + 1;
      }
    }
    std::ostringstream oss;
    oss << "bSearch 결과: index=" << s_idx << " value=" << array_[s_idx]
        << "\n";
    addHistory(oss.str());
  }

  void upperBoundBSearch(int target) {
    int s_idx = 0;
    int e_idx = array_.size() - 1;
    while (s_idx < e_idx) {
      auto m = (s_idx + e_idx) / 2;

      if (array_[m] > target) {
        e_idx = m;
      } else {
        s_idx = m + 1;
      }
    }
    std::ostringstream oss;
    oss << "upperBoundBSearch 결과: index=" << s_idx - 1
        << " value=" << array_[s_idx - 1] << "\n";
    addHistory(oss.str());
  }

  void lowerBoundBSearch(int target) {
    int s_idx = 0;
    int e_idx = array_.size() - 1;
    while (s_idx < e_idx) {
      auto m = (s_idx + e_idx) / 2;

      if (array_[m] >= target) {
        e_idx = m;
      } else {
        s_idx = m + 1;
      }
    }
    std::ostringstream oss;
    oss << "lowerBoundBSearch 결과: index=" << s_idx
        << " value=" << array_[s_idx] << "\n";
    addHistory(oss.str());
  }

  void printHistory() { std::cout << "=== 결과 ===\n" << history_; }

 private:
  std::string history_;
  std::vector<int> array_;
  void addHistory(const std::string& record) { history_ += record + "\n"; };
};

inline void testBinarySearch() {
  BinarySearch binarySearch(20);
  binarySearch.bSearch(2);
  binarySearch.lowerBoundBSearch(2);
  binarySearch.upperBoundBSearch(2);
  binarySearch.printHistory();
}

}  // namespace search