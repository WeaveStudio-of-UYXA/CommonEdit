#pragma once

#ifndef CEL
#define CEL
#define CE_VERSION_MAJOR 1
#define CE_VERSION_MINOR 0
#endif

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

#ifndef CEL_KEYWORD
	#define CEL_KEYWORD
	#define tick unsigned int
	#define l_tick unsigned long long
	#define CE_NULLPTR nullptr
	#define publicOperator public 

	#define CEL_PYTHON_TO_CPP
	#define self this
	#define elif else if
	#define def void
	#define True true
	#define False false
	#define match switch
#endif

#ifndef FALSE
	#define FALSE false
	#define TRUE true
#endif

#ifndef NULL
	#define NULL 0
#endif

#ifndef Q_NULLPTR
	#define Q_NULLPTR nullptr
#endif
