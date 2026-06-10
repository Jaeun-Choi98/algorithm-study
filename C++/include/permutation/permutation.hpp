#pragma once
#include <string>

class Permutation
{
public:
  Permutation();

private:
  std::string history_;
  void addHistory(const std::string &record);
};