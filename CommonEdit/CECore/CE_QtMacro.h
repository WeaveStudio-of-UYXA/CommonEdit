#pragma once
//The macros in this file are used to enhance the Qt development experience. 
//If there is no Qt environment, they will not be activated
#ifdef QT_VERSION_MAJOR
	#if (QT_VERSION_MAJOR==5)
		#ifndef CE_QT_5
		#define CE_QT_5
	#endif
#elif (QT_VERSION_MAJOR==6)
	#ifndef CE_QT_6
		#define CE_QT_6
		#endif
	#endif
#endif

#ifdef QT_VERSION_MAJOR
	#define CE_QT_KEYWORDS
//We hope that the following four macros can make a sense segmentation for too many public functions.
//For example, you can put your own "setTopText()" function under "public sets:".
//This operation does not affect the function to become a QT slot function
		#define events Q_SLOTS
		#define funcs Q_SLOTS
		#define sets Q_SLOTS
		#define returns Q_SLOTS
#endif

#ifdef QT_VERSION_MAJOR
	#define connectPushButton(QPushButton_Object,Target_Object,Slot) connect(QPushButton_Object,SIGNAL(clicked()),Target_Object,SLOT(Slot))
	#define connectAction(QAction_Object,Target_Object,Slot) connect(QAction_Object,SIGNAL(triggered()),Target_Object,SLOT(Slot))
#endif

#ifndef Q_NULLPTR
	#define Q_NULLPTR nullptr
#endif