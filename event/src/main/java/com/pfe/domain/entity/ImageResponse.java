package com.pfe.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private String signature;
    private String format;
    private String resource_type;
    private String secure_url;
    private LocalDateTime created_at;
    private String asset_id;
    private String version_id;
    private String type;
    private int version;
    private String url;
    private String public_id;
    private String[] tags;
    private String folder;
    private String original_filename;
    private String api_key;
    private int bytes;
    private boolean overwritten;
    private int width;
    private String etag;
    private boolean placeholder;
    private int height;

}
