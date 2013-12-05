#!/bin/sh

echo "This Integration Test assumes that 'vagrant up' has been called and allowed to start all 3 VMs"

fab tracker startTrackerBG
sleep 3
fab seeder startSeederBG
sleep 3
fab leech startLeechBG
sleep 3

touch file-n/testfile1.txt
#cp file-o/testfile1.txt file-n/testfile1.txt #to make output file same as input for testing
OUT=$(diff file-n/testfile1.txt file-o/testfile1.txt)
if [ ${#OUT} -ne 0 ];then
   echo "Created File is same as original. Integration Test finished with Proper Completion."
else
   echo "Created File is NOT same as original. Integration Test finished with different file created."
fi

rm -f file-n/testfile1.txt
rm -f file-n/testfile1.txt.part
