package com.ok.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OwnerDto {
    private int ownerId;
    private String ownerName;
    private List<DogDto> dogList;
}
