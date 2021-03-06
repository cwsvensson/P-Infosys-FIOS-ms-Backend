package com.verizon.models;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class Subscriptions 
{
	private int id;
	private String name;
	private boolean isCableSubscribed;
	private boolean isInternetSubscribed;
	private boolean isPhoneSubscribed;
	
	
}