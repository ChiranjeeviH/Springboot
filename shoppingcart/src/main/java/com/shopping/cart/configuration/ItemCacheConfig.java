package com.shopping.cart.configuration;

import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class ItemCacheConfig {
	
	public Config cacheConfig() {
		return new Config().setInstanceName("hazel-instance")
				.addMapConfig(new MapConfig().setName("item-cache").setTimeToLiveSeconds(3000));
	}

}
