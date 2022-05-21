#pragma once
#include "CEMacro.h"
#include <list>
#include <string>
typedef std::list<std::string> strlist;
namespace CE {
	/*enum class CEType
	{
		Int,
		Float,
		Char,
		Bool,
		String,
		List,
		Dictionary,
		Hash,
		Null,
	};*/
	class CEType
	{
		Private static const strlist CEInsiderType;
		Public static strlist CEUserType;
		Public static strlist getInsiderType() { return CEInsiderType; }
		Public static strlist getUserType() {return CEUserType; }
		Public static void regUserType(std::string typeName) { CEUserType.push_back(typeName); }
	};
}