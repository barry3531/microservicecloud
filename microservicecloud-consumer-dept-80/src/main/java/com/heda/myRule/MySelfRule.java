package com.heda.myRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MySelfRule {

	@Bean
	public IRule myRule() {
		//return new RandomRule();//默认是轮询，我们自定义为随机
		return new MyRandomRule();
	}
}
