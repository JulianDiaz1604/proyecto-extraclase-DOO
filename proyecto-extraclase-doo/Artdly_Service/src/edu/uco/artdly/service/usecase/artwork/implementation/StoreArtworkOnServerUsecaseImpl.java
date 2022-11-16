package edu.uco.artdly.service.usecase.artwork.implementation;

import java.io.File;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.StoreArtworkOnServerUsecase;

public class StoreArtworkOnServerUsecaseImpl implements StoreArtworkOnServerUsecase {

    @Override
    public void execute(ArtworkDTO artwork, String file) {

        BasicAWSCredentials creds = new BasicAWSCredentials("AKIA4OZTGCCGFLUIPPEN",
                                     "O5Ipx4S8ctvxMlm/nZjNinQMVMi2Vvi7+o/fvnz8");
        
        String bucketName = "artdly";
        String key = artwork.getTittle();
        String filePath = file;
        
        AmazonS3 amazonS3 = AmazonS3Client.builder()
        .withRegion("sa-east-1")
        .withCredentials(new AWSStaticCredentialsProvider(creds))
        .build();

        amazonS3.putObject(bucketName, key, new File(filePath));
    }
}
