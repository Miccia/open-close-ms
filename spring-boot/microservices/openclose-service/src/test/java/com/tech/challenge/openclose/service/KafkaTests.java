//package com.tech.challenge.openclose.service;
//
//@RunWith(SpringRunner.class)
//@Import(com.baeldung.kafka.testcontainers.KafkaTestContainersLiveTest.KafkaTestContainersConfiguration.class)
//@SpringBootTest(classes = KafkaProducerConsumerApplication.class)
//@DirtiesContext
//public class KafkaTests {
//
//    @ClassRule
//    public static KafkaContainer kafka =
//            new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:5.4.3"));
//
//    @Autowired
//    private KafkaConsumer consumer;
//
//    @Autowired
//    private KafkaProducer producer;
//
//    @Value("${test.topic}")
//    private String topic;
//
//    @Test
//    public void givenKafkaDockerContainer_whenSendingtoSimpleProducer_thenMessageReceived()
//            throws Exception {
//        producer.send(topic, "Sending with own controller");
//        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//        assertThat(consumer.getLatch().getCount(), equalTo(0L));
//        assertThat(consumer.getPayload(), containsString("embedded-test-topic"));
//    }
//}
//}