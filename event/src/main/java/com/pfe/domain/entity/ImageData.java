package com.pfe.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageData {

    public InputStream imageData;
    public String filename;
}
