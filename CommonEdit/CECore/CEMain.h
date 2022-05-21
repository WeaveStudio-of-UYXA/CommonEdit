#pragma once
#include <QtCore>
#include <QApplication>
#include "../CEFunction/CELanguage.h"
#include "../CEFunction/CESettings.h"

#define def_CEMain class Main : public CE::CEMain{public:Main(int argc, char* argv[]):CEMain(argc, argv) {};
#define do_CEMain int main(int argc, char* argv[]){Main app(argc, argv); app.act(); return 0;}
#define def_END }; do_CEMain
#define CEMainPara int argc, char* argv[]
namespace CE {
	class CEMain
	{
	public:
		QMap<QString, QString> SetDict;
		int gargc;
		char** gargv;
		CEMain(int argc, char* argv[]) {
			gargc = argc;
			gargv = argv;
		}
		void act() {
			doPreSet();
			main(gargc, gargv);
		}
		virtual void doPreSet() = 0;
		virtual int main(CEMainPara) = 0;
	};
}

