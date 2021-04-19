package com.verizon.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Subscriptions 
{
	private int id;
	private String name;
	private boolean isCableSubscribed;
	private boolean isInternetSubscribed;
	private boolean isPhoneSubscribed;
	
	
}
