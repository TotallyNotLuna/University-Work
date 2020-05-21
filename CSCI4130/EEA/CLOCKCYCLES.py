import time
#OUTPUT FOR 5 minutes: Final Iterations: 25,097,151

seconds = 60*60

start = time.perf_counter()
end = time.perf_counter()

iteration = 0

while( end - start < seconds):

    iteration = iteration + 1
    end = time.perf_counter()

    if(iteration%10 == 0):

        print("Current Iterations: "+str(iteration))

print("Final Iterations: "+str(iteration))

