package com.ok.owner.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Owner {
    private int ownerId;
    private String ownerName;
}
