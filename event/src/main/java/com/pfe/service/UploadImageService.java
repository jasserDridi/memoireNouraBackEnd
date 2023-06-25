package com.pfe.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.http.entity.InputStreamEntity;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UploadImageService {
    @Inject
    @ConfigProperty(name="cloudName")
    String cloudName ;
    @ConfigProperty( name = "apiKey")
    String apiKey;
    @ConfigProperty(name="apiSecret")
    String apiSecret;


    public Map uploadPhoto(byte[] image) throws IOException {
    Map config = new HashMap();
config.put("cloud_name",cloudName);
config.put("api_key", apiKey);
config.put("api_secret", apiSecret);
config.put("permanent", true);
System.out.println(config.toString());
    Cloudinary cloudinary = new Cloudinary(config);


        return cloudinary.uploader().upload(image, ObjectUtils.asMap("public_id", "olympic_flag"));
    }
}