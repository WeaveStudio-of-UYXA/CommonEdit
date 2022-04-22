#This File contains the CELanguage System translated from C++ Version
#It can also be said to be the improved SPLanguage System，
from enum import *
import os

class CELanguageName(Enum):
    unknown = "unknown"
    zh_CN = "zh_CN"
    en_US = "en_US"
    zh_TW = "zh_TW"

class CELanguage:
    __LangName__ = CELanguageName.unknown
    __DirPath__ = ""
    __TransDict__ = {}
    def __init__(this,LanguageName:CELanguageName = CELanguageName.unknown)->None:
        this.__LangName__ = LanguageName

    def setName(this, LanguageName:CELanguageName)->None:
        if ((LanguageName in CELanguageName) and (not LanguageName==CELanguageName.unknown)):
            this.__LangName__ = LanguageName        
        else:
            this.__LangName__ = CELanguageName.zh_CN
            print("Error:CESettings:The specified target '"+ LanguageName.value +"' is not in the CELanguageName enumeration class. The language is now set to zh_ CN")

    @staticmethod
    def fromStr(LanguageName:str)->CELanguageName:
        if (LanguageName == "zh_CN"):
            return CELanguageName.zh_CN
        elif (LanguageName == "zh_TW"):
            return CELanguageName.zh_TW
        elif (LanguageName == "en_US"):
            return CELanguageName.en_US
        else:
            return CELanguageName.unknown

    def setPath(this, DirPath:str)->None:
        this.__DirPath__ = DirPath

    def load(this)->bool:
        if (this.__LangName__ == CELanguageName.unknown) :
            raise Exception("Exception:CELanguage:The file could not be loaded because no language type was specified")
        TargetFileName = this.__DirPath__ + "\\" + this.__LangName__.value +".celang"
        if (os.path.exists(TargetFileName)) :
            if (os.access(TargetFileName,os.R_OK)):
                TargetFile = open(TargetFileName,"r")
                for Line in TargetFile.readlines():
                    if (Line[0]!="#" or Line[0]!="\n"):
                        if (Line[-1]!="\n"): Line += "\n"
                        if ":" not in Line : 
                            print("Warning:CELanguage:The separator ':' was not checked on line ' " + Line + " ' in the file:" + TargetFileName)
                            continue
                        this.__TransDict__[Line.split(":")[0]]=Line.split(":")[1]
                TargetFile.close()
                return True
            else:
                print("Error:CELanguage:The specified file '"+ TargetFileName +"' is unreadable")
                return False
        else:
            print("Error:CELanguage:The specified file '"+ TargetFileName +"' could not be found")
            return False

    def returnValueOf(this,Key:str)->str:
        try:
            return this.__TransDict__[Key]
        except Exception:
            print("Error:CELanguage:The translated text corresponding to the key'" + Key + "' could not be found")
            return Key

#Provide a global variable named CELangSys and a global function pointer named msg to quickly access the celanguage system.
#With these two variables, the SPLanguage System can also be quickly migrated to the CELanguage System
CELangSys = CELanguage()
msg = CELangSys.returnValueOf
