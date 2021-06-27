## docker-compose install

> link: https://docs.docker.com/compose/install/

```text
# 由于网络原因curl无法下载

1. 

https://github.com/docker/compose/releases/下载linux版本压缩包，上传到linux服务器

2. 
$ mv docker-compose-Linux-x86_64 /usr/local/bin/docker-compose

3. 
$ sudo chmod +x /usr/local/bin/docker-compose

4.
$ docker-compose --version
```

## docker-compose cmd

```text
$ docker-compose -f docker-compose-single-node.yaml up -d
$ docker-compose -f docker-compose-single-node.yaml ps
$ docker-compose -f docker-compose-single-node.yaml down
```