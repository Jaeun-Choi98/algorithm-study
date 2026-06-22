#pragma once
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>

#define INF int(1e9)

namespace dp
{
  class MinimumCountCoin
  {
  public:
    MinimumCountCoin(int money) : history_(""), d_(money + 1, -1), money_(money)
    {
      coins_.push_back(1);
      coins_.push_back(2);
      coins_.push_back(4);
    };

    void run()
    {
      std::ostringstream oss;
      oss << "Needed Coin: " << money_ << "\n";
      oss << "Coins: [ ";
      for (auto c : coins_)
      {
        oss << c << " ";
      }
      oss << "]\n";

      auto rst = solve(money_);

      oss << "Memozation: [ ";
      for (auto m : d_)
      {
        oss << m << " ";
      }
      oss << "]\n";

      oss << "최소의 수: " << rst << "\n";
      addHistory(oss.str());
    }

    void printHistory()
    {
      std::cout << "=== 결과 ===\n"
                << history_;
    };

  private:
    std::string history_;
    std::vector<int> coins_;
    std::vector<int> d_;
    int money_;
    void addHistory(const std::string record) { history_ += record + "\n"; };
    int solve(int cur)
    {
      if (cur == 0)
        return 0;
      if (cur < 0)
        return INF;
      if (d_[cur] != -1)
        return d_[cur];

      int best = INF;
      for (auto coin : coins_)
      {
        best = std::min(solve(cur - coin) + 1, best);
      }
      d_[cur] = best;
      return d_[cur];
    };
  };

  inline void testMinimumCountCoin()
  {
    MinimumCountCoin mcc(6);
    mcc.run();
    mcc.printHistory();
  };
};