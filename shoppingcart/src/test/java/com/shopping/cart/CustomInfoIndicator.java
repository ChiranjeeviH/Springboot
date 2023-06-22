package com.shopping.cart;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoIndicator implements InfoContributor{

	@Override
	public void contribute(Builder builder) {
		
		builder.withDetail("Info added", "This is custom info indicator");
		
	}

	
}
