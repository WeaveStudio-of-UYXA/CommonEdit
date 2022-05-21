#pragma once
//CEOBJECT
#include "CEMacro.h"
#include "CEEnum.h"
#include <string>
#include <iostream>
#define regIns(CEObjectInstance) CEObjectInstance.InstanceName.set(#CEObjectInstance)
#define CEVarB(Type) CE::CEFieldBase<Type>
#define CEVar(Type) CE::CEField<Type>
namespace CE {
	template<typename T> class CEFieldBase
	{
		Public T value;
		Public CEFieldBase();
		Public CEFieldBase(T v) { value = v; }
		Public void set(T v) { value = v; }
		Public T get(void) { return value; }
		Public T c_get(void) const { return value; }
		Public operator T(void) { return value; }
	};
	class CEObject 
	{
		Private std::string ObjectType;
		Public CEObject();
		Public ~CEObject();
		
		Public CEVarB(std::string) ObjectName;
		Public CEVarB(std::string) InstanceName;
	};
	template<typename T> class CEField :public CEFieldBase<T> ,public CEObject
	{
		Public CEField(T value) {
			this->value = value;
		}
	};
	class CEInt :public CEField<int>
	{
		Public CEInt(int value) :CEField(value) {}
		Public CEInt operator+(CEInt v) {
			return CEInt(this->get() + v.get());
		}
		Public CEInt operator+(int v) {
			return CEInt(this->get() + v);
		}
	};
}
//std::ostream& operator<<(std::ostream& os, const CE::CEInt& obj);