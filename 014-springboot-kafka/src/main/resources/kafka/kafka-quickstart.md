## docker kafka

```text
$ docker exec -it kafka bash
$ cd opt/kafka
>>>
bin/xxx.sh(s)
```

[apache kafka quickstart](https://kafka.apache.org/quickstart)

```text
$ bin/kafka-topics.sh --create --topic quickstart-events --bootstrap-server localhost:9092
$ bin/kafka-topics.sh --describe --topic quickstart-events --bootstrap-server localhost:9092
$ bin/kafka-console-producer.sh --topic quickstart-events --bootstrap-server localhost:9092
$ bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
```
