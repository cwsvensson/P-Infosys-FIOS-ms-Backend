package com.verizon.internetservice.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode @ToString
public class InternetSubscription {
    @Id
    private int id;

    private String name;
}
