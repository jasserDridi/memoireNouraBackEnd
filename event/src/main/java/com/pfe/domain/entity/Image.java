package com.pfe.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

import java.io.InputStream;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
        public String filename;
        public byte[] data;

    }

