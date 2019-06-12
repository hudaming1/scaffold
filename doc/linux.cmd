#### 科学上网
> ssh -g -D 1080 -p 996 root@47.75.102.227  

#### 本地SSH隧道(将46服务器的80端口上流量转发到我本地8091端口)
> 先打开46服务器ssh的ip转发配置  
>> vim /etc/ssh/sshd_config 
>> 新增一行配置 GatewayPorts clientspecified  
> 然后本地执行：ssh -R 0.0.0.0:80:localhost:8091 root@39.96.83.46

#### 踢出用户
pkill -kill -t pts/0

#### 免密登录
> 1. ssh-keygen -t rsa
> 2. scp /root/.ssh/id_rsa.pub root@192.168.0.2:/root/.ssh/
> 3. ssh root@192.168.0.2
> 4. cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys

#### 统计调用次数
grep "validateToKen.req" grampus-ccs.log.info | grep "2019-06-12 13" | grep "CcsTokenServiceImpl" | awk -F 'req=' '{print $2}' | awk -F ',' '{print $1}' | sort -n | uniq -c