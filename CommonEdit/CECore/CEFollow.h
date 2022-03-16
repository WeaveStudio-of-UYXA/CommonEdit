#pragma once
#include <string>
#include <map>
#define CETo bool;
namespace CE{
	class CEThenSystem
	{
	public:
		friend class CEThen;
		std::map<CEThen*, bool(*)()> FromToPair;
		CEThenSystem() {}
		void from(CEThen* From) {
			
		}
	};
	CEThenSystem CETHENSYSTEM;
	class CEThen
	{
	public:
		CEThen(...) {
			
		}
		bool then(bool(*FuncName)()) {
			CETHENSYSTEM.FromToPair[this] = FuncName;
		}
		bool next()
		{
			CETHENSYSTEM.from(this);
		}
	};
	class Test
	{
		CEThen signal;
		Test()
		{
			signal.next();
			signal.then(&fun1);
		}
		bool fun1(void) {
			
		}
	};
}