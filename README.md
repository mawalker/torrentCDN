# torrentCDN — BitTorrent CDN written in Java
###
[torrentCDN](/) is a CDN (Content Delievery Network) tool written in Java that leverages the BitTorrent protocol to transfer files between each target node. 

___

For Vanderbilt University CS 278:

This project makes has the following properties.


Core Requirements
--------------------
1. Spec laid out in user stories on Pivotal Tracker https://www.pivotaltracker.com/s/projects/954698
2. Has unit tests (ran by maven during build system)
3. Has integration tests ()
4. Is well-designed and implemented
5. Involves network communication


Auxiliary Requirements
-----------------------
1. Uses a continuous integration server
2. Uses multi-host Vagrant
3. Manages nodes with Fabric
4. Uses custom non-http network built on top of a framework (Uses BitTorrent.)

___
There are several packages required to build and run this project

*  Java-JDK 7.0
*  maven 3.0
*  vagrant
*  puppet
*  python
*  fabric

There are 2 projects created:

*   client: acts as both the 'seeder' and 'leech' applications, depending on file placement
*   tracker: The BitTorrent Tracker that allows all the clients to connect.

___

Due to the limitations of the library I'm using for BitTorrent capabilities, manual installation of the library to your local maven repositoy is needed (because they do not have a public jar listed for maven repo access.)
by executing:
```bash
. ./requiredToRun/installReqLibraryToMaven.sh
```

___


To build the client software 
```bash
cd client/
mvn clean install
```

To build the client tracker
```bash
cd tracker/
mvn clean install
```

___

To Test the client and tracker run:
```bash
vagrant up    (NOTE:this might take a while to execute)
fab tracker trackerStartBG
fab seeder seederStartBG
fab leech leechStartBG
```

The torrent file located at 'torrents/testfile1.txt.torrent'

It is used by all 3 VMs to configure what file to seed (file-o/testfile1.txt) and what to copy to the leech instance(s)
(file-n/testfile1.txt)
