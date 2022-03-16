#pragma once
#include <QtCore>
#define USE_CELANGUAGE

#ifndef USE_CELANGUAGE
#define msg tr
#else
#define msg CE::CELanguage::Msg
#endif

namespace CE{
    static QMap<QString, QString> msglist;
    class CELanguage :public QObject {
        Q_OBJECT
    public:
        static bool openFile(QString FileDir) {
            QFile spLanguageFile;
            spLanguageFile.setFileName(FileDir);
            spLanguageFile.open(QIODevice::ReadOnly | QIODevice::Text);
            if (!spLanguageFile.isOpen()) {
                return false;
            }
            else {
                QTextStream spLanguageText(&spLanguageFile);
                spLanguageText.setCodec("UTF-8");
                QString spSingleLine;
                for (;;) {
                    spSingleLine = spLanguageText.readLine();
                    if (spSingleLine[0] == '#' || spSingleLine[0] == '\n') { continue; }
                    msglist[spSingleLine.section(":", 0, 0)] = spSingleLine.section(":", 1, -1);
                    if (spLanguageText.atEnd()) { break; }
                }
                return true;
            }
        }
        static QString Msg(QString Usript) {
            try {
                if (msglist[Usript] == "") {
                    throw "FAQ";
                }
                else {
                    return msglist[Usript];
                }
            }
            catch (...) {
                return Usript;
            }
        }
    };
}