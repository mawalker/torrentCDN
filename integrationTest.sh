#!/bin/sh

#fab tracker trackerStartBG
#fab seeder seederStartBG
#fab leech leechStartBG

touch file-n/testfile1.txt
#cp file-o/testfile1.txt file-n/testfile1.txt
diff file-n/testfile1.txt file-o/testfile1.txt 

OUT=$?
if [ $OUT -eq 0 ];then
   echo "Created File is same as original. Integration Test finished with Proper Completion."
else
   echo "Created File is NOT same as original. Integration Test finished with different file created."
fi
