#include "DebugTest.h"

int __stdcall add( int a, int b )
{
#if 0
	printf("access error.\r\n");
	char *p = NULL;
	*p = 'a';
#endif
	
#if 1
	printf("out of memory error.\r\n");
	for (int i=0; i<1000*1000*10; i++)
	{
		printf("%dM\r\n", i*10);
		char *p = new char[1024*1024*10]();
	}
#endif
	return a + b;
}



