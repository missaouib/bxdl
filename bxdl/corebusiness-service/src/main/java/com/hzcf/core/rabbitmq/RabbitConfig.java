package com.hzcf.core.rabbitmq;

/*import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
*/
/**
 * <dl>
 * <dt>类名：com.hzcf.basic.rabbitmq</dt>
 * <dd>描述: rabbitmq配置</dd>
 * <dd>创建时间：上午 11:31 2018/8/16 0016 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
/*@Configuration
public class RabbitConfig {
    @Bean(name="hello1")
    public Queue helloQueue1() {
        return new Queue("hello1");
    }

    @Bean(name="hello2")
    public Queue helloQueue2() {
        return new Queue("hello2");
    }

    //实现一对一、一对多功能
     @Bean
     public TopicExchange exchange(){
     return new TopicExchange("exchange");
     }

     @Bean
     Binding bindingExchangeMessage(@Qualifier("hello1") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.hello1");
     }

     @Bean
     Binding bindingExchangeMessages(@Qualifier("hello2") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
     }
}*/
