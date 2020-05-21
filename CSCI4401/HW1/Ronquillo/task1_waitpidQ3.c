//Question 3
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

//waitpid
#include <sys/types.h>
#include <sys/wait.h>

void appendfile(){

	FILE *fp;

	fp = fopen("procdata.txt","a");

	fprintf(fp, "%d|", getpid());
	fprintf(fp, "%d|\n", getppid());

	fclose(fp);
}

void forkproc(int ammount) 
{ 

	
} 

int main(int argc, char *argv[])
{
    
	if(argc <= 1){

		printf("no amount provided, can't fork, exiting...");
		return 1;
	}
	else{

		int status;
		pid_t childPid, exitPid;

		printf("I am process %d, top of the tree.\n", getpid());

	    for(int i = 1; i <= atoi(argv[1]); i++){

	    	childPid = fork();

	    	if( childPid != 0){

	    		exitPid = waitpid(childPid, &status, 0);
	    		break;
	    	}

	    	printf("I am process %d, child of process %d\n", getpid(), getppid());
	        appendfile();
	    }
	}
    return 0;
}