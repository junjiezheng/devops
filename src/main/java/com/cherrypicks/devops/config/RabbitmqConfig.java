package com.cherrypicks.devops.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
	//Create first queue
	@Bean("customQueue")
	public Queue getCustomQueue() {
		return new Queue("custom-queue");
	}
	
	
	@Bean("customExchange")
	public TopicExchange getCustomExchange() {
		return new TopicExchange("custom-exchange");
	}
	
	//将以 customQueue 绑定到 customExchange 交换机上
	@Bean
	public Binding bindingCustomqueueToCustomQueue() {
		return BindingBuilder.bind(getCustomQueue()).to(getCustomExchange()).with("topic.custom.#");
	}
	
}
