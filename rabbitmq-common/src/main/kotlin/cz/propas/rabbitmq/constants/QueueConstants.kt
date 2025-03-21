package cz.propas.rabbitmq.constants

const val HUMAN_RESOURCES_ACCOUNTING_QUEUE = "q.hr.accounting"
const val HUMAN_RESOURCES_MARKETING_QUEUE = "q.hr.marketing"
const val PICTURE_IMAGE_QUEUE = "q.picture.image"
const val PICTURE_FILTER_QUEUE = "q.picture.filter"
const val PICTURE_LOG_QUEUE = "q.picture.log"
const val PICTURE_VECTOR_QUEUE = "q.picture.vector"
const val MY_PICTURE_IMAGE_QUEUE = "q.mypicture.image"
const val MY_PICTURE_IMAGE_TTL_QUEUE = "q.mypicture.image-ttl"
const val MY_PICTURE_DLX_QUEUE = "q.mypicture.dlx"
const val PROMOTION_DISCOUNT_QUEUE = "q.promotion.discount"
const val PROMOTION_FREE_DELIVERY_QUEUE = "q.promotion.free-delivery"
const val DELAYED_MESSAGE_QUEUE = "q.delayed"

const val DEAD_LETTER_EXCHANGE_QUEUE_FEATURE = "x-dead-letter-exchange"
const val MESSAGE_TTL_QUEUE_FEATURE = "x-message-ttl"

