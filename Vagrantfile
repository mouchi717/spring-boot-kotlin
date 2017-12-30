Vagrant.configure(2) do |config|
  # Box指定
  config.vm.box = "centos/7"
  # Boxの自動アップデート設定
  config.vbguest.auto_update = false
  config.vm.box_check_update = false
  # プライベートネットワーク接続の設定
  config.vm.network "private_network", ip: "192.168.33.10"
  # virtualbox設定
  config.vm.provider "virtualbox" do |vb|
     vb.gui = false
     vb.cpus = "2"
     vb.memory = "1024"
   end

   config.vm.provision "bootstrap",        type:"shell", :path => "provision/vagrant/bootstrap.sh"
   config.vm.provision "docker-restart",   type:"shell", :path => "provision/vagrant/docker-restart.sh"
end