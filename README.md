# kotlin-rabbitmq

Project is using Spring profiles for running producers and consumers. 
You can download and run RabbitMQ as docker container: https://hub.docker.com/_/rabbitmq

## rabbitmq-plugins
Some examples require downloading and enabling RabbitMQ plugins.<br/>
Enable plugins via command: ***rabbitmq-plugins enable [plugin-name]***

To copy the plugins to docker use this command:  
***docker cp [path-to-ez-file] [container-name]:[container-path]***<br/>
example: docker cp .\rabbitmq_delayed_message_exchange-v4.0.7.ez rabbitmq:/opt/rabbitmq/plugins

__Plugins:__<br>
Delayed message exchange - https://github.com/rabbitmq/rabbitmq-delayed-message-exchange/releases

## rabbitmq-consumer
Module contains consumers

## rabbitmq-producer
Module contains producers and configs for exchanges, queues and bindings

## rabbitmq-common
Module contains shared configuration and entity for producers and consumers.
Profiles are configured in application-common.yaml

Example:
```yaml
spring:  
  profiles:
    active: headers-exchange

```
### Profiles
Copy the profile name and paste it into __application-common.yaml__
* __fixed-rate:__
  * __Producers:__ FixedRateProducer.kt
  * __Consumers:__ FixedRateConsumer.kt
  * __Description:__ Used scheduler for sending message each 500 ms and concurrency for more consumers.
* __employee:__
    * __Producers:__ EmployeeProducer.kt
    * __Consumers:__ EmployeeConsumer.kt
    * __Description:__ Convert object to json.
* __human-resources:__
    * __Producers:__ HumanResourceFanoutProducer.kt
    * __Consumers:__ AccountingConsumer.kt, MarketingConsumer.kt
    * __Description:__ Used fanout exchange for sending message to multiple consumers (Broadcast).
* __picture-direct-exchange:__
    * __Producers:__ PictureDirectProducer.kt
    * __Consumers:__ PictureImageConsumer.kt, PictureVectorConsumer.kt
    * __Description:__ Used direct exchange for sending message to selective queue(s) based on routing keys.
* __picture-topic-exchange:__
    * __Producers:__ PictureTopicProducer.kt
    * __Consumers:__ PictureTopicConsumer.kt
    * __Description:__ Used topic exchange for sending message with multiple routing criteria.
* __furniture-promotion:__
    * __Producers:__ FurnitureHeadersProducer.kt
    * __Consumers:__ 
    * __Description:__ Used headers exchange for sending message with multiple criteria at custom message header.
* __my-picture-dlx:__
    * __Producers:__ MyPictureDlxProducer.kt
    * __Consumers:__ MyPictureImageAutomaticRejectConsumer.kt, MyPictureImageManualRejectConsumer.kt
    * __Description:__ Return message from consumers automatically or manually.
* __delayed-exchange:__
    * __Producers:__ ReportRequestProducer.kt
    * __Consumers:__ ReportRequestConsumer.kt
    * __Description:__ Delayed message in RabbitMQ via new type of exchange "x-delayed-message". For new type of exchange install rabbitmq_delayed_message_exchange plugin to RabbitMQ.
* __spring-retry-direct-exchange__
    * __Producers:__ SpringRetryDirectProducer.kt
    * __Consumers:__ SpringRetryDirectPictureConsumer.kt
    * __Description:__ Enabled retry mechanism for direct exchange via Spring Framework
* __spring-retry-fanout-exchange__
  * __Producers:__ SpringRetryFanoutProducer.kt
  * __Consumers:__ SpringRetryFanoutEmployeeConsumer.kt
  * __Description:__ Enabled retry mechanism for fanout exchange via Spring Framework