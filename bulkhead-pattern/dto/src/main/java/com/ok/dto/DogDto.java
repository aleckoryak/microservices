package com.ok.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DogDto {
    private int dogId;
    private int ownerId;
    private String dogName;
    private String trim;
    private int age;
}
