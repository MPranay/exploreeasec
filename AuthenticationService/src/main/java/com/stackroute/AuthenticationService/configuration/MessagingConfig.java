package com.stackroute.AuthenticationService.configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private String exchangeName="user_exchange";
    private String registerQueue="user_queue";

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter()
    {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchangeName);
    }
    @Bean
    public Queue registerQueue()
    {
        return new Queue(registerQueue,true);
    }


    @Bean
    Binding bindingUser(DirectExchange exchange,Queue registerQueue)
    {
        return BindingBuilder.bind(registerQueue()).to(exchange).with("user_routing");
    }




}




