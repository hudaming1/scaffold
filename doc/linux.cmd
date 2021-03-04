#### 科学上网
> ssh -g -D 1080 -p 996 root@47.75.102.227  

#### 本地SSH隧道(将46服务器的80端口上流量转发到我本地8091端口)
> 先打开46服务器ssh的ip转发配置  
>> vim /etc/ssh/sshd_config 
>> 新增一行配置 GatewayPorts clientspecified  
>> 新增超时配置：
ClientAliveCountMax 3600
ClientAliveInterval 60
> 然后本地执行：ssh -o ServerAliveInterval=60 -o ServerAliveCountMax=360 -NfR  0.0.0.0:80:localhost:8080 root@129.28.193.172


ssh -o ServerAliveInterval=60 -o ServerAliveCountMax=360 -NfR  0.0.0.0:52996:10.1.143.145:20886 root@129.28.193.172

ssh -o ServerAliveInterval=60 -o ServerAliveCountMax=360 -NfR  0.0.0.0:52007:idc01-smsshdM-mysql-00.dns.missfresh.cn:3306 root@129.28.193.172

#### 踢出用户
pkill -kill -t pts/0

#### 免密登录
> 1. ssh-keygen -t rsa
> 2. scp /root/.ssh/id_rsa.pub root@192.168.0.2:/root/.ssh/
> 3. ssh root@192.168.0.2
> 4. cat /root/.ssh/id_rsa.pub >> /root/.ssh/authorized_keys

#### 统计调用次数
grep "validateToKen.req" grampus-ccs.log.info | grep "2019-06-12 13" | grep "CcsTokenServiceImpl" | awk -F 'req=' '{print $2}' | awk -F ',' '{print $1}' | sort -n | uniq -c


#### tcpdump
tcpdump tcp port 80 and host 36.112.67.195
tcpdump tcp host 101.201.197.62

#### 查看某一个时段的日志
sed -n '/起始时间/,/结束时间/p' 日志文件   
例如：查看2019-12-17 12:00:00-12:10:10 时间段内的日志内容     
sed -n '/2019-12-17 12:00:00/,/2019-12-17 12:10:10/p'  online.log     


####nc上传下载文件
服务端：nc -4 -l [port] < 123.log
客户端：nc -4 [remote_host] [remote_port] > 123.log

####jrebel
http://jrebel.cicoding.cn/guid  
http://jrebel.cicoding.cn/C2709474-8C0E-B086-C917-D8D6733C9EBC


#### 启动参数
-Xmx1024M -Xms1024M -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+UseStringDeduplication -XX:-OmitStackTraceInFastThrow -XX:+UnlockCommercialFeatures -XX:+FlightRecorder  -server