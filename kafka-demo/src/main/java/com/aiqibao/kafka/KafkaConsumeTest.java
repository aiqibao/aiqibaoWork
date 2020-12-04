package com.aiqibao.kafka;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

/**
 * @Author:aiqibao
 * @Date:2020/12/2 16:48
 * Best wish!
 */
public class KafkaConsumeTest {
    private final ConsumerConnector consumerConnector ;
    private KafkaConsumeTest(){
        Properties properties = new Properties() ;
        //zookeeper配置
        properties.put("zookeeper.connect","175.24.124.91:2181/kafka") ;
        /**
         * group代表一个消费组，加入组里面，消息只能被该组的一个消费者消费
         * 如果所有消费者在一个组内，就是传统的队列模式，排队拿消息
         * 如果所有的消费者都不在同一个组内，就是发布-订阅模式，消息广播给所有组
         * 如果介于两者之间，那么广播的消息在组内也是要排队的
         */
        properties.put("group.id","jd-group") ;

        //zk连接超时
        //ZooKeeper的最大超时时间，就是心跳的间隔，若是没有反映，那么认为已经死了，不易过大
        //properties.put("zookeeper.session.timeout.ms", "4000");
        //zk follower落后于zk leader的最长时间
        properties.put("zookeeper.sync.time.ms", "200");
        //往zookeeper上写offset的频率
        properties.put("auto.commit.interval.ms", "1000");
        /*
         * 此配置参数表示当此groupId下的消费者,在ZK中没有offset值时(比如新的groupId,或者是zk数据被清空),consumer应该从哪个offset开始消费.
         * largest表示接受接收最大的offset(即最新消息),smallest表示最小offset,即从topic的开始位置消费所有消息.
         * */
        //消费最老消息,最新为largest
        properties.put("auto.offset.reset", "smallest");
        //序列化类
        properties.put("serializer.class", "kafka.serializer.StringEncoder");

        ConsumerConfig config = new ConsumerConfig(properties);

        consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(config);

    }

    void consume() {
        // 描述读取哪个topic，需要几个线程读
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("canal", new Integer(1));


        /* 默认消费时的数据是byte[]形式的,可以传入String编码器*/
        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap =
                consumerConnector.createMessageStreams(topicCountMap, keyDecoder, valueDecoder);

        //消费数据时每个Topic有多个线程在读,所以取List第一个流
        KafkaStream<String, String> stream = consumerMap.get("canal").get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().topic() + ":" + it.next().partition() + ":" + it.next().offset() + ":" + it.next().key() + ":" + it.next().message());
        }
    }

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "175.24.124.91:9092");
        props.put("group.id", "jd-group");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("max.poll.records", 1000);
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList("canal"));
        ConsumerRecords<String, String> msgList=consumer.poll(1000);
        for (ConsumerRecord record:msgList){
            System.out.println(record.value());
        }
    }
}
