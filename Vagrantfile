# -*- mode: ruby -*-
# vi: set ft=ruby :


boxes = [
    # Tracker
    { 
        :name => :tracker, 
        :ip => "192.168.42.100",
        :pupp => "tracker.pp",
        :ssh_port => "2080"
        #:jar => 'java -jar Torrent-tracker.jar - [FINISH ME]
    },
    # Client to Seed
    { 
        :name => :seeder,
        :ip => "192.168.42.80",
        :pupp => "seeder.pp",
        :ssh_port => "2081"
    },
    # Client to Download
    { 
        :name => :leech,
        :ip => "192.168.42.40",
        :pupp => "leech.pp",
        :ssh_port => "2082"
    }
]


Vagrant.configure("2") do |config|
    boxes.each do |opts|
        config.vm.define opts[:name] do |config|
            # Box basics
            config.vm.box = "precise"
            config.vm.box_url = "http://files.vagrantup.com/precise32.box"
            config.vm.network :private_network, ip: opts[:ip]
            #config.ssh.port = opts[:ssh_port]       
        end     
		config.vm.provision :puppet do |puppet|
			puppet.manifests_path = "manifests"
			puppet.manifest_file = opts[:pupp]
		end
    end
 
end  

