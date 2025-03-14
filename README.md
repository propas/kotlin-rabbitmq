# kotlin-rabbitmq

Project is using Spring profiles for running producers and consumers.

## rabbit-common
Module contains shared configuration and entity for producers and consumers.
Profiles are configured in application-common.yaml

Example:
```yaml
spring:  
  profiles:
    active: headers-exchange

```

| Profiles                | Producers                      | Consumers                                                                       | Description                                                                               | 
|-------------------------|--------------------------------|---------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| fixed-rate              | FixedRateProducer.kt           | FixedRateConsumer.kt                                                            | Used scheduler for sending message each 500 ms and concurency for more consumers.         |
| employee                | EmployeeProducer.kt            | EmployeeConsumer.kt                                                             | Convert object to json.                                                                   |
| human-resources         | HumanResourceFanoutProducer.kt | AccountingConsumer.kt, MarketingConsumer.kt                                     | Used fanout exchange for sending message to multiple consumers (Broadcast)                |
| picture-direct-exchange | PictureDirectProducer.kt       | PictureImageConsumer.kt, PictureVectorConsumer.kt                               | Used direct exchange for sending message to selective queue(s) based on routing keys      |
| picture-topic-exchange  | PictureTopicProducer.kt        | PictureTopicConsumer.kt                                                         | Used topic exchange for sending message with multiple routing criteria                    |
| furniture-promotion     | FurnitureHeadersProducer.kt    |                                                                                 | Used headers exchange for sending message with multiple criteria at custom message header |
| my-picture-dlx          | MyPictureDlxProducer.kt        | MyPictureImageAutomaticRejectConsumer.kt, MyPictureImageManualRejectConsumer.kt | Return message from consumers automatically or manually                                   |
|                         |                                |                                                                                 |                                                                                           |

## rabbit-consumer
Module contains consumers

## rabbit-producer
Module contains producers