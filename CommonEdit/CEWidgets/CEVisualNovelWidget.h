#pragma once
#include <QtCore>
#include <QtGui>
#include <QtWidgets>

#ifdef QT_VERSION_MAJOR
#ifndef CE_QT_KEYWORDS
//We hope that the following four macros can make a sense segmentation for too many public functions.
//For example, you can put your own "setTopText()" function under "public sets:".
//This operation does not affect the function to become a QT slot function
#define CE_QT_KEYWORDS
#define events Q_SLOTS
#define funcs Q_SLOTS
#define sets Q_SLOTS
#define returns Q_SLOTS
#endif
#endif


namespace CEVisualNovelPrivateClass{
	class uTitlePage :public QFrame 
	{
		Q_OBJECT
	private:
		QLabel* MainTitle;
		QLabel* SubTitle;
		QLabel* LogoLabel;
		QLabel* BackgroundLabel;
		QLabel* CoverBackgroundLabel;
		QGraphicsBlurEffect* BackgroundBlur;
		QGraphicsOpacityEffect* BackgroundOpacity;
	public:
		uTitlePage(QWidget* parent = Q_NULLPTR) {
			this->setParent(parent);
			this->setObjectName("uTitlePage");
			this->setStyleSheet("QFrame#uTitlePage{background-color:#000000;}");

			BackgroundLabel = new QLabel(this);
			BackgroundBlur = new QGraphicsBlurEffect(this);
			BackgroundOpacity = new QGraphicsOpacityEffect(this);
			CoverBackgroundLabel = new QLabel(this);
			LogoLabel = new QLabel(this);
			MainTitle = new QLabel(this);
			SubTitle = new QLabel(this);

			MainTitle->setObjectName("MainTitle");
			SubTitle->setObjectName("SubTitle");
			LogoLabel->setObjectName("LogoLabel");
			BackgroundLabel->setObjectName("BackgroundLabel");
			CoverBackgroundLabel->setObjectName("CoverBackgroundLabel");

			BackgroundBlur->setBlurRadius(0);
			BackgroundLabel->setGraphicsEffect(BackgroundBlur);
			BackgroundOpacity->setOpacity(0);
			CoverBackgroundLabel->setGraphicsEffect(BackgroundOpacity);
			CoverBackgroundLabel->setStyleSheet("QLabel#CoverBackgroundLabel{background-color:#000000;}");
		}
	public sets:
		void setMainTitle(QString MainTitleText) {
			MainTitle->setText(MainTitleText);
		}
		void setMainTitleStyleSheet(QString MainTitleStyleSheet) {
			MainTitle->setStyleSheet(MainTitleStyleSheet);
		}
		void setSubTitle(QString SubTitleText) {
			SubTitle->setText(SubTitleText);
		}
		void setSubTitleStyleSheet(QString SubTitleStyleSheet) {
			SubTitle->setStyleSheet(SubTitleStyleSheet);
		}
		void setLogoFileName(QString LogoFileName) {
			LogoLabel->setStyleSheet("\
					QLabel#LogoLabel{\
						border-image:url('"+LogoFileName+"');\
				}");
		}
		void clearLogo() {
			LogoLabel->setStyleSheet("\
					QLabel#LogoLabel{\
						background-color:#00FFFFFF;\
				}");
		}
		void setBGFileName(QString BGFileName) {
			BackgroundLabel->setStyleSheet("\
					QLabel#BackgroundLabel{\
						border-image:url('" + BGFileName + "');\
				}");
		}
		void setBGBlur(int Blur) {
			BackgroundBlur->setBlurRadius(Blur);
		}
		void setBGOpacity(float Opacity) {
			BackgroundOpacity->setOpacity(1.0 - Opacity);
		}
		void test(QStringList TestString) {
		}
	public slots:
		void dynamicFunc(QStringList FuncName, QStringList Parameters) {
			try {
				if (FuncName[0] == "setMainTitle") {
					if (Parameters.length() != 1) { 
						this->setMainTitle(Parameters[0]); 
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setSubTitle") {
					if (Parameters.length() != 1) {
						this->setSubTitle(Parameters[0]);
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setMainTitleStyleSheet") {
					if (Parameters.length() != 1) {
						this->setMainTitleStyleSheet(Parameters[0]);
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setSubTitleStyleSheet") {
					if (Parameters.length() != 1) {
						this->setSubTitleStyleSheet(Parameters[0]);
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setLogoFileName") {
					if (Parameters.length() != 1) {
						this->setLogoFileName(Parameters[0]);
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "clearLogo") {
					if (Parameters.length() != 1 && Parameters[0]!="") {
						this->clearLogo();
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setBGFileName") {
					if (Parameters.length() != 1) {
						this->setBGFileName(Parameters[0]);
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setBGBlur") {
					if (Parameters.length() != 1) {
						this->setBGBlur(Parameters[0].toInt());
					}
					else { throw "The number of parameters does not match"; }
				}
				else if (FuncName[0] == "setBGOpacity") {
					if (Parameters.length() != 1) {
						this->setBGOpacity(Parameters[0].toFloat());
					}
					else { throw "The number of parameters does not match"; }
				}
				else {
					throw "Invalid object or function";
				}
			}
			catch (QString Excep) {
				throw Excep;
			}
		}
	};
}

namespace CE {
	class CEVisualNovelWidget :public QFrame 
	{
		Q_OBJECT
	private:
		CEVisualNovelPrivateClass::uTitlePage* TitlePage;
	public:
		CEVisualNovelWidget(QWidget* parent = Q_NULLPTR) {
			this->setObjectName("Player");
			TitlePage = new CEVisualNovelPrivateClass::uTitlePage(this);
		}
	public slots:
		void dynamicFunc(QStringList FuncPath, QStringList Parameters) {
			try {
				if (FuncPath[0] == "TitlePage") {
					TitlePage->dynamicFunc(FuncPath.mid(1, -1), Parameters);
				}
				else {
					throw "Invalid object or function";
				}
			}
			catch (QString Excep) {
				qDebug() << FuncPath.join(".");
				qDebug() << Parameters;
				qDebug() << Excep;
			}
		}
	};
}