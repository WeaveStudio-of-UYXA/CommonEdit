#pragma once
//CEOBJECT
#include "CEMacro.h"
#include "CEEnum.h"
#include <string>
#include <iostream>
#define regIns(CEObjectInstance) CEObjectInstance.setInstanceName(#CEObjectInstance)
#define regPIns(CEObjectInstance) CEObjectInstance->setInstanceName(#CEObjectInstance)
#define CEVarB(Type) CE::CEFieldObject<Type>
#define CEVar(Type) CE::CEField<Type>
#define CEType(Type) CE::CEField<Type>
#define CETypeB(Type) CE::CEFieldObject<Type>
#define regChild for (auto i = children.begin(); i != children.end(); i++) {(*i)->reg();}
namespace CE {
	template<typename T> class CEFieldObject
	{
		Public T value;
		Public CEFieldObject() {}
		Public CEFieldObject(T v) { value = v; }
		Public void set(T v) { value = v; }
		Public T get(void) { return value; }
		Public T c_get(void) const { return value; }
		Public operator T(void) { return value; }
		Public void operator=(T v) { value = v; }
	};
	class CEObject 
	{
		Protected std::string ObjectType;
		Protected CEObject* parent = CE_NULLPTR;
		Public std::list<CEObject*> children = { };
		Public CEObject() {};
		Public CEVarB(std::string) ObjectName;
		Public CEVarB(std::string) InstanceName;
		Public void setParent(CEObject* p) {
			parent = p;
			if (p != CE_NULLPTR) {
				p->children.push_back(this);
			}
		}
		Public ~CEObject() {
			for (auto i = children.begin(); i != children.end(); i++) {
				delete *i;
			}
		}
		Public void setInstanceName(std::string insName) {
			if (parent == CE_NULLPTR) {
				this->InstanceName.set(insName);
			}else{
				this->InstanceName.set(parent->InstanceName.get() + "::" + insName);
			}
		}
		Public virtual void reg() {};
		/*Public std::string getFullName() {
			std::string f = "";
			if (parent != CE_NULLPTR) {
				f.append(std::string("::")+parent.get()->getFullName());
				return f;
			}
			else {
				return InstanceName.get();
			}
		}*/
	};
	template<typename T> class CEField :public CEFieldObject<T>, public CEObject
	{
		Public CEField(T value) :CEFieldObject<T>(value), CEObject() {}
	};
}

