# Lab

## 1.8 Remarkable trees of Paris

### 1.8.1 Districts containing trees

I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar districts_with_trees /user/getendard/trees.csv trees
20/11/12 15:06:28 INFO client.AHSProx Created token for getendard: HDFS_DELEGATION_TOKEN owner=getendard@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1605189988490, maxDate=1605794788490, sequenceNumber=7311, masterKeyId=47 on ha-hdfs:efrei
20/11/12 15:06:28 INFO security.TokenCache: Got dt for hdfs://efrei; Kind: HDFS_DELEGATION_TOKEN, Service: ha-hdfs:efrei, Ident: (token for getendard: HDFS_DELEGATION_TOKEN owner=getendard@EFREI.ONLINE, renewer=yarn, realUser=, issueDate=1605189988490, maxDate=1605794788490, sequenceNumber=7311, masterKeyId=47)
20/11/12 15:06:28 INFO mapreduce.JobRey: Connecting to Application History server at hadoop-master03.efrei.online/163.172.100.24:10200
                                      20/11/12 15:06:28 INFO hdfs.DFSClient:sourceUploader: Disabling Erasure Coding for path: /user/getendard/.staging/job_1603290159664_4573
20/11/12 15:06:29 INFO input.FileInputFormat
...
20/11/12 15:06:59 INFO mapreduce.Job: Job job_1603290159664_4573 completed successfully
...
```
And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees/part-r-00000
11
12
13
14
15
16
17
18
19
20
3
4
5
6
7
8
9
```

### 1.8.2 Show all existing species

I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar existing_species  /user/getendard/trees.csv trees_species
...
20/11/12 20:59:29 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 20:59:29 INFO mapreduce.Job: Job job_1603290159664_4969 completed successfully
...
```
And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees_species/part-r-00000
araucana
atlantica
australis
baccata
bignonioides
biloba
bungeana
cappadocicum
carpinifolia
colurna
coulteri
decurrens
dioicus
distichum
excelsior
fraxinifolia
giganteum
giraldii
glutinosa
grandiflora
hippocastanum
ilex
involucrata
japonicum
kaki
libanii
monspessulanum
nigra
nigra laricio
opalus
orientalis
papyrifera
petraea
pomifera
pseudoacacia
sempervirens
serrata
stenoptera
suber
sylvatica
tomentosa
tulipifera
ulmoides
virginiana
x acerifolia
```

### 1.8.3 Number of trees by species
For this, I create a Mapper with ...
I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar number_by_species /user/getendard/trees.csv trees_species_number
...
20/11/12 22:03:24 INFO mapreduce.Job:  map 100% reduce 0%
20/11/12 22:03:34 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 22:03:34 INFO mapreduce.Job: Job job_1603290159664_5005 completed successfully
...
```
And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees_species_number/part-r-00000
araucana        1
atlantica       2
australis       1
baccata 2
bignonioides    1
biloba  5
bungeana        1
cappadocicum    1
carpinifolia    4
colurna 3
coulteri        1
decurrens       1
dioicus 1
distichum       3
excelsior       1
fraxinifolia    2
giganteum       5
giraldii        1
glutinosa       1
grandiflora     1
hippocastanum   3
ilex    1
involucrata     1
japonicum       1
kaki    2
libanii 2
monspessulanum  1
nigra   3
nigra laricio   1
opalus  1
orientalis      8
papyrifera      1
petraea 2
pomifera        1
pseudoacacia    1
sempervirens    1
serrata 1
stenoptera      1
suber   1
sylvatica       8
tomentosa       2
tulipifera      2
ulmoides        1
virginiana      2
x acerifolia    11
```


### 1.8.4 Maximum height per specie of tree
For this, I create a Mapper with ...
I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar max_height_per_specie /user/getendard/trees.csv trees_max_height
...
20/11/12 22:05:47 INFO mapreduce.Job:  map 100% reduce 0%
20/11/12 22:05:57 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 22:05:57 INFO mapreduce.Job: Job job_1603290159664_5010 completed successfully
...
```
And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees_max_height/part-r-00000
araucana        9.0
atlantica       25.0
australis       16.0
baccata 13.0
bignonioides    15.0
biloba  33.0
bungeana        10.0
cappadocicum    16.0
carpinifolia    30.0
colurna 20.0
coulteri        14.0
decurrens       20.0
dioicus 10.0
distichum       35.0
excelsior       30.0
fraxinifolia    27.0
giganteum       35.0
giraldii        35.0
glutinosa       16.0
grandiflora     12.0
hippocastanum   30.0
ilex    15.0
involucrata     12.0
japonicum       10.0
kaki    14.0
libanii 30.0
monspessulanum  12.0
nigra   30.0
nigra laricio   30.0
opalus  15.0
orientalis      34.0
papyrifera      12.0
petraea 31.0
pomifera        13.0
pseudoacacia    11.0
sempervirens    30.0
serrata 18.0
stenoptera      30.0
suber   10.0
sylvatica       30.0
tomentosa       20.0
tulipifera      35.0
ulmoides        12.0
virginiana      14.0
x acerifolia    45.0
```

### 1.8.5 Sort the trees height from smallest to largest
In the mapper, I extract the height as key and geopoint as value to sort the trees by height
And I put the geopoint as key in the reducer and the height as value for the result

I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar height_sorted /user/getendard/trees.csv trees_height
...
20/11/12 22:08:33 INFO mapreduce.Job:  map 100% reduce 0%
20/11/12 22:08:42 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 22:08:42 INFO mapreduce.Job: Job job_1603290159664_5015 completed successfully
...
```

And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees_height/part-r-00000
(48.8453050031, 2.35307565328)  2.0
(48.8320684332, 2.41182825531)  5.0
(48.867221687, 2.27027693483)   6.0
(48.8716117578, 2.24933653506)  9.0
(48.8814628758, 2.38367383179)  10.0
(48.8471789821, 2.25293802515)  10.0
(48.8213214388, 2.45537251962)  10.0
(48.8691485018, 2.27224125103)  10.0
(48.8615768444, 2.25902702441)  10.0
(48.8520958913, 2.34740754195)  11.0
(48.8314334738, 2.4115101993)   12.0
(48.8588189763, 2.25832952119)  12.0
(48.8215800145, 2.45494779675)  12.0
(48.8578717375, 2.29706549763)  12.0
(48.8619346483, 2.39870061217)  12.0
(48.8232165418, 2.46016871078)  12.0
(48.8450859307, 2.26948936839)  12.0
(48.8732545226, 2.24886543775)  12.0
(48.8708601366, 2.24769299518)  13.0
(48.857140829, 2.29533455314)   13.0
(48.8647824278, 2.25120424857)  14.0
(48.8210086122, 2.45551492936)  14.0
(48.8400754064, 2.43381509843)  14.0
(48.8302532096, 2.41400587444)  15.0
(48.856902513, 2.33666989768)   15.0
(48.8731203887, 2.24884917245)  15.0
(48.8557581795, 2.35399582218)  15.0
(48.8183933679, 2.43791766754)  15.0
(48.8428118006, 2.2972574926)   16.0
(48.8400020891, 2.46422657197)  16.0
(48.86260617, 2.23782412563)    16.0
(48.8373323894, 2.40776275516)  16.0
(48.8622517606, 2.26098883991)  18.0
(48.8347628794, 2.42029690936)  18.0
(48.8330496955, 2.35078878768)  18.0
(48.8640166469, 2.26774597209)  18.0
(48.861574817, 2.2910717819)    20.0
(48.85646631, 2.39469777758)    20.0
(48.8830346813, 2.37007425143)  20.0
(48.8618464812, 2.37910176561)  20.0
(48.8685686134, 2.31331809304)  20.0
(48.8648376291, 2.36062929978)  20.0
(48.8577766649, 2.29329076205)  20.0
(48.8399672948, 2.43375148978)  20.0
(48.8619170093, 2.2924448277)   20.0
(48.8669690843, 2.31951408752)  20.0
(48.8324049718, 2.41169855654)  20.0
(48.8652536076, 2.31333976248)  20.0
(48.8792159582, 2.30640768208)  22.0
(48.8597396934, 2.39997847741)  22.0
(48.833545551, 2.41033694606)   22.0
(48.8768191638, 2.33210374339)  22.0
(48.8319232533, 2.41202933239)  22.0
(48.8420426954, 2.43848438671)  23.0
(48.827737189, 2.3592096955)    25.0
(48.8476260928, 2.25278179131)  25.0
(48.8292071873, 2.41301158121)  25.0
(48.8661956075, 2.26238964912)  25.0
(48.8204495642, 2.44579219199)  25.0
(48.8336849895, 2.42111102704)  25.0
(48.8704017043, 2.24852577475)  26.0
(48.8633555664, 2.26174532022)  27.0
(48.8845880534, 2.34391859224)  27.0
(48.8333849101, 2.41261756721)  28.0
(48.8630909172, 2.24159242682)  30.0
(48.8341842636, 2.46130493573)  30.0
(48.8693939056, 2.24776773334)  30.0
(48.8471653404, 2.25199572129)  30.0
(48.8723867386, 2.27912885453)  30.0
(48.8220238534, 2.33628540112)  30.0
(48.8224956954, 2.3366608746)   30.0
(48.8880577555, 2.31595908796)  30.0
(48.8632834941, 2.24066981564)  30.0
(48.8727584235, 2.2873548507)   30.0
(48.865022534, 2.2538285063)    30.0
(48.8646850734, 2.25360607406)  30.0
(48.8764503804, 2.25765244347)  30.0
(48.8820015094, 2.39836942721)  30.0
(48.8634848878, 2.2403752961)   30.0
(48.8201249835, 2.44524393613)  30.0
(48.8717782491, 2.27973325759)  30.0
(48.8433252639, 2.4497117757)   31.0
(48.8785456147, 2.30757576047)  31.0
(48.8395160905, 2.43350820893)  32.0
(48.8802898189, 2.38157469859)  33.0
(48.8803986732, 2.38129958306)  34.0
(48.8606198209, 2.2599223737)   35.0
(48.8394165343, 2.43360205128)  35.0
(48.8353848188, 2.38157245444)  35.0
(48.846044762, 2.2529555706)    35.0
(48.879759998, 2.38064802989)   35.0
(48.8226749117, 2.33869560229)  40.0
(48.867043584, 2.25320074406)   40.0
(48.8691433358, 2.24587597613)  40.0
(48.8323806372, 2.41052012477)  42.0
(48.8325900983, 2.41116455985)  45.0
```

### 1.8.6 District containing the oldest tree
For this one, the mapper extract the age and district of each tree.
I created a new writable, AgeDistrictWritable, with the tree's district and age.

I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar district_oldest_tree /user/getendard/trees.csv trees_oldest
...
20/11/12 22:11:53 INFO mapreduce.Job:  map 100% reduce 0%
20/11/12 22:12:04 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 22:12:04 INFO mapreduce.Job: Job job_1603290159664_5017 completed successfully
...
```

And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees_oldest/part-r-00000
5
```


### 1.8.7 District containing the most trees
For this one, I created two jobs with 2 mappers and 2 reducers
The first job get all trees by district, and the second job just keep the district which have the most trees.
I created a new writable, DistrictTreesNumber, with the district and the number of trees associated.

I ran the job with the command :
```
-sh-4.2$ yarn jar hadoop-examples-mapreduce-1.0-SNAPSHOT-jar-with-dependencies.jar district_most_trees /user/getendard/trees.csv trees_most_district
...
20/11/12 22:16:06 INFO mapreduce.Job:  map 100% reduce 0%
20/11/12 22:16:15 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 22:16:16 INFO mapreduce.Job: Job job_1603290159664_5019 completed successfully
...
20/11/12 22:16:46 INFO mapreduce.Job:  map 100% reduce 100%
20/11/12 22:16:46 INFO mapreduce.Job: Job job_1603290159664_5020 completed successfully
...
```

And this is the result : 
```
-sh-4.2$ hdfs dfs -cat trees_most_district/part-r-00000
16
```