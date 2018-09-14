#ifndef _DEBUG_TEST_
#define _DEBUG_TEST_

#include <iostream>

#if DEBUGTEST_EXPORTS
#define DECLDIR __declspec(dllexport)
#else
#define DECLDIR __declspec(dllimport)
#endif

extern "C"
{
	DECLDIR int __stdcall add(int a, int b);
}

#endif