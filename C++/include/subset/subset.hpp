#pragma once

#include <string>
#include <sstream>
#include <iostream>
#include <vector>

namespace recursion
{
  class Subset
  {
  public:
    Subset() : history_("") {};

    void run(int cur)
    {
      if (cur == 0)
      {
        std::ostringstream oss;
        int size = tmp_.size();
        oss << "{";
        for (int i = 0; i < size; i++)
        {
          oss << tmp_.at(i);
          if (i != size - 1)
          {
            oss << ",";
          }
        }
        oss << "}";
        addHistory(oss.str());
        return;
      }

      tmp_.push_back(cur);
      run(cur - 1);
      tmp_.pop_back();
      run(cur - 1);
    };

    void printHistory() const
    {
      std::cout << "=== 결과 === \n"
                << history_;
    }

  private:
    std::string history_;
    std::vector<int> tmp_;
    void addHistory(const std::string &record) { history_ += record + "\n"; };
  };

  void inline testSubset()
  {
    Subset subset;
    try
    {
      subset.run(3);
    }
    catch (const std::invalid_argument &e)
    {
      std::cerr << "에러: " << e.what() << "\n";
    };

    subset.printHistory();
  }
}