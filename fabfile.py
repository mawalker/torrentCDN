from fabric.api import env, local, run
 
#setup ssh keys for each vagrant server
def tracker():
    # change from the default user to 'vagrant'
    env.user = 'vagrant'
    # connect to the port-forwarded ssh
    env.hosts = ['127.0.0.1:2222']
 
    # use vagrant ssh key
    result = local('vagrant ssh-config tracker | grep IdentityFile', capture=True)
    env.key_filename = result.split()[1]
    

def seeder():
    # change from the default user to 'vagrant'
    env.user = 'vagrant'
    # connect to the port-forwarded ssh
    env.hosts = ['127.0.0.1:2200']
 
    # use vagrant ssh key
    result = local('vagrant ssh-config seeder | grep IdentityFile', capture=True)
    env.key_filename = result.split()[1]
    
def leech():
    # change from the default user to 'vagrant'
    env.user = 'vagrant'
    # connect to the port-forwarded ssh
    env.hosts = ['127.0.0.1:2201']
 
    # use vagrant ssh key
    result = local('vagrant ssh-config leech | grep IdentityFile', capture=True)
    env.key_filename = result.split()[1]
 
#Sample command 
def uname():
    run('uname -a')

#Commands to run the 3 different program configurations on each VM
def startTracker(time='300', torrents='/vagrant/torrents/', port='8080'):
	run("java -jar /vagrant/tracker/target/TorrentCDN-Tracker-1.0-SNAPSHOT.jar -d %s -p %s -t %s" % (torrents, port, time))
	
def startSeeder(time='300', torrent='/vagrant/torrents/testfile1.txt.torrent', directory='/vagrant/file-o/'):
	run("java -jar /vagrant/client/target/TorrentCDN-Client-1.0-SNAPSHOT.jar -d %s -p %s -t %s " % (directory, torrent, time))
	
def startLeech(time='300', torrent='/vagrant/torrents/testfile1.txt.torrent', directory='/vagrant/file-n/'):
	run("java -jar /vagrant/client/target/TorrentCDN-Client-1.0-SNAPSHOT.jar -d %s -p %s -t %s" % (directory, torrent, time))

#Background versions of the above commands
def startTrackerBG(time='300', torrents='/vagrant/torrents/', port='8080'):
	run("nohup java -jar /vagrant/tracker/target/TorrentCDN-Tracker-1.0-SNAPSHOT.jar -d %s -p %s -t %s >& /dev/null < /dev/null &" % (torrents, port, time))
	
def startSeederBG(time='300', torrent='/vagrant/torrents/testfile1.txt.torrent', directory='/vagrant/file-o/'):
	run("nohup java -jar /vagrant/client/target/TorrentCDN-Client-1.0-SNAPSHOT.jar -d %s -p %s -t %s >& /dev/null < /dev/null &" % (directory, torrent, time))
	
def startLeechBG(time='300', torrent='/vagrant/torrents/testfile1.txt.torrent', directory='/vagrant/file-n/'):
	run("nohup java -jar /vagrant/client/target/TorrentCDN-Client-1.0-SNAPSHOT.jar -d %s -p %s -t %s >& /dev/null < /dev/null &" % (directory, torrent, time))
