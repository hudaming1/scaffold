#### 科学上网
> ssh -g -D 1080 -p 996 root@47.75.102.227  

#### 本地SSH隧道(将46服务器的80端口上流量转发到我本地8091端口)
> 先打开46服务器ssh的ip转发配置  
>> vim /etc/ssh/sshd_config 
>> 新增一行配置 GatewayPorts clientspecified  
> 然后本地执行：ssh -R 0.0.0.0:80:localhost:8091 root@39.96.83.46
