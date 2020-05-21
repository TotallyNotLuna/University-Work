
#Extended Euclidean Algorithm

import math

startLarge = 3373173703468584728999065980339992781006346742496369077930826947841348985090302550149421077377734508912005371590030093819999810288613548901222608296089578494563874807828486514870556035600292455467467109896792098065671846896181713928254906078154942647385027932581418962537100702721767973469229451341245778940484167761735597977769838688336125466941182558530861180855721395461768669312636029598922294022
startMin = 250556952327646214427246777488032351712139094643988394726193347352092526616305469220133287929222242315761834129196430398011844978805263868522770723615504744438638381670321613949280530254014602887707960375752016807510602846590492724216092721283154099469988532068424757856392563537802339735359978831013

ri_min_2 = startLarge
ri_min_1 = startMin
ri = -1

qi_min_1 = 1

si_min_2 = 1
ti_min_2 = 0

si_min_1 = 0
ti_min_1 = 1

si = 0
ti = 0

i = 1

print("---Starting iteration---\n")
while( ri != 0):

    i = i + 1
    ri = ri_min_2%ri_min_1
    qi_min_1 = ri_min_2//ri_min_1
    si = si_min_2 - (qi_min_1*si_min_1)
    ti = ti_min_2 - (qi_min_1*ti_min_1)

    #shift calcs for next iter
    ri_min_2 = ri_min_1
    ri_min_1 = ri

    si_min_2 = si_min_1
    si_min_1 = si

    ti_min_2 = ti_min_1
    ti_min_1 = ti

    #print stuff
    print("i: "+str(i)+" ri: "+str(ri)+" qi-1: "+str(qi_min_1)+" si: "+str(si)+" ti: "+str(ti))

    if(ri == 0):

        print("\n---End iteration---\n")
        check = (si_min_2*startLarge) + (ti_min_2*startMin)
        print("check result(should equal ri-1): "+str(check))

        if(check == 1):

            print("Inverse of "+str(startLarge)+" mod "+str(startMin)+": "+str(ti_min_2))