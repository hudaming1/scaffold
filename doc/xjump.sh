#!/usr/bin/env bash

# 用户配置
user='${user}'
passwd='${password}'
secret_key='${secret_key}'
# 默认配置
host='${target_host}'
remote_port=${target_port}

function get_ga(){
        ga_code=`echo $secret_key | python -c 'import hmac, base64, struct, hashlib, time;secretKey = raw_input();input = int(time.time())//30;lens = len(secretKey);lenx = 8 - (lens % 4 if lens % 4 else 4);secretKey += lenx * "=";key = base64.b32decode(secretKey);msg = struct.pack(">Q", input);googleCode = hmac.new(key, msg, hashlib.sha1).digest();o = ord(googleCode[19]) & 15;googleCode = str((struct.unpack(">I", googleCode[o:o+4])[0] & 0x7fffffff) % 1000000);googleCode = ("0" + googleCode if (len(googleCode) == 5) else googleCode);print googleCode;'`
        echo $ga_code
}

# start front
function start_front(){
        ga_code=`get_ga`
        expect -c "
        set timeout -1
        spawn ssh $user@$host -p$remote_port
        expect {
                *yes/no* { send yes\r; exp_continue; }
            *password* { send ${passwd}${ga_code}\r; exp_continue; }
            *Opt* {}
        }
        interact"
}

cmd=${1:-"front"}
case "$cmd" in
        -f|front) start_front ;;
        -c|code) get_ga ;;
        *) cat <<EOF
usage -c|code) get auth code
    -f|front start
EOF
        exit 0 ;;
esac
