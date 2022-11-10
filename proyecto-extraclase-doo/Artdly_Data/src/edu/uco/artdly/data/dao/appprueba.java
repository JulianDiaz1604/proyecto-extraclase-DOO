package edu.uco.artdly.data.dao;

import java.io.File;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class appprueba {
    public static void main(String[] args) {
        
        BasicAWSCredentials creds = new BasicAWSCredentials("AKIA4OZTGCCGFLUIPPEN",
                                     "O5Ipx4S8ctvxMlm/nZjNinQMVMi2Vvi7+o/fvnz8");
        
        String bucket_name = "artdly";
        String key = "Fondo.png";
        String file_path = "C:/artdly/Fondo.png";
        
        AmazonS3 amazonS3 = AmazonS3Client.builder()
        .withRegion("sa-east-1")
        .withCredentials(new AWSStaticCredentialsProvider(creds))
        .build();

        amazonS3.putObject(bucket_name, key, new File(file_path));


        
        /*
        S3Client c = S3Client.builder()..build();
        String bucket_name = "artdly";
        String key = "Fondo.png";
        String file_path = "C:/artdly/Fondo.png";
        PutObjectRequest request = PutObjectRequest.builder().bucket(bucket_name).key(key).build();
        c.putObject(request, RequestBody.fromFile(new File(file_path)));
        */

    }
}
