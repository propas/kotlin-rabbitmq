package cz.propas.rabbitmq.constants

const val HUMAN_RESOURCES_EXCHANGE = "x.hr"
const val PICTURE_DIRECT_EXCHANGE = "x.picture.direct"
const val PICTURE_TOPIC_EXCHANGE = "x.picture.topic"
const val PROMOTION_EXCHANGE = "x.promotion"
const val MY_PICTURE_FANOUT_EXCHANGE = "x.mypicture"
const val MY_PICTURE_DLX_FANOUT_EXCHANGE = "x.mypicture.dlx"
const val DELAYED_MESSAGE_EXCHANGE = "x.delayed"
const val SPRING_RETRY_DIRECT_EXCHANGE = "x.spring.work"
const val SPRING_RETRY_DLX_DIRECT_EXCHANGE = "x.spring.dead"
const val SPRING_RETRY_FANOUT_EXCHANGE = "x.spring2.work"
const val SPRING_RETRY_FANOUT_DLX_DIRECT_EXCHANGE = "x.spring2.dead"

const val DELAYED_ROUTING_KEY = "delayed_routing_key"