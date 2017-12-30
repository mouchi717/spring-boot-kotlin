#!/bin/bash

echo "*********************************"
echo "bootstrap.sh [START]"
echo "*********************************"
cd

# time zone
timedatectl set-timezone Asia/Tokyo

yum -y install yum-utils
yum-config-manager --enable epel/x86_64
yum -y install net-tools telnet

# OSのメモリ調整
if ! grep "vm.overcommit_memory" /etc/sysctl.conf >/dev/null 2>&1; then
    echo "" >> /etc/sysctl.conf
    echo "vm.overcommit_memory = 1" >> /etc/sysctl.conf
fi
if ! grep "vm.overcommit_ratio" /etc/sysctl.conf >/dev/null 2>&1; then
    echo "" >> /etc/sysctl.conf
    echo "vm.overcommit_ratio = 99" >> /etc/sysctl.conf
fi

/sbin/sysctl -p

#### selinux, firewall, etc

yum -y install epel-release

# selinux
setenforce 0
sed -i "s/\(^SELINUX=\).*/\1disabled/" /etc/selinux/config

# firewall
systemctl stop firewalld
systemctl disable firewalld

#### docker
yum -y install yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
yum makecache fast
# yum -y install docker-ce-17.06.0.ce-1.el7.centos
yum -y install docker-ce-17.12.0.ce-1.el7.centos
systemctl enable docker.service
systemctl restart docker

#### docker-compose
curl -Ls https://github.com/docker/compose/releases/download/1.15.0/docker-compose-`uname -s`-`uname -m` \
    > /usr/local/bin/docker-compose
chown root:root /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose

echo "*********************************"
echo "bootstrap.sh [END]"
echo "*********************************"
exit 0
