#pragma once
#include <iostream>
#include <sstream>
#include <string>
#include <utility>
#include <vector>

namespace str {
class KMP {
 public:
  KMP(const std::string& text, const std::string& pattern)
      : text_(text), pattern_(pattern), f_(pattern.size()) {};
  void printHistory() { std::cout << "===결과===\n" << history_; }

  void solve() {
    makeFailFunc();
    std::ostringstream oss;
    oss << "text: " << text_ << "\npattern: " << pattern_ << "\n";
    oss << "pattern이 나타나는 시작 위치: ";
    int textLen = text_.size();
    int patternLen = pattern_.size();
    int j = 0;
    for (int i = 0; i < textLen; i++) {
      while (j != 0 && text_[i] != pattern_[j]) {
        j = f_[j - 1];
      }

      if (text_[i] == pattern_[j]) {
        j++;
      }

      if (j == patternLen) {
        oss << i - j + 1 << " ";
        j = 0;
      }
    }

    oss << "\n";
    addHistory(oss.str());
  }

 private:
  std::string history_;
  const std::string& text_;
  const std::string& pattern_;
  std::vector<int> f_;
  void addHistory(const std::string& record) { history_ += record + "\n"; }
  void makeFailFunc() {
    int patternLen = pattern_.size();
    int failIdx = 0;
    for (int i = 1; i < patternLen; i++) {
      failIdx = f_[i - 1];
      while (failIdx != 0 && pattern_[failIdx] != pattern_[i]) {
        failIdx = f_[failIdx - 1];
      }

      if (pattern_[failIdx] == pattern_[i]) {
        f_[i] = failIdx + 1;
      }
    }
  }
};

inline void testKMP() {
  KMP kmp("bbabcabaeeedabcabassss", "abcaba");
  kmp.solve();
  kmp.printHistory();
}
};  // namespace str