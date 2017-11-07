package br.com.tcc.rankstudio.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AmazonS3FileUpload {

    private static final String ACCESS_KEY    = "AKIAJU6LSU7MNYOQ2JIQ";
    private static final String SECRET_KEY    = "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";
    private static final String BUCKET_NAME   = "vitormarquesaws-filestorage";
    private static final String STORAGE_PATH  = "rankstudio/";

    public static String uploadFile(MultipartFile file) throws AmazonServiceException, IOException {

        AmazonS3 amazonS3Client = getAmazonS3Client();
        File s3File = multipartToFile(file);

        PutObjectResult retornoS3 = amazonS3Client.putObject(
                new PutObjectRequest(BUCKET_NAME, STORAGE_PATH + file.getOriginalFilename(), s3File)
                        .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return file.getOriginalFilename();

    }

    private static AmazonS3 getAmazonS3Client() {

        BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_WEST_1)
                .build();

    }

    private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {

        File s3File = new File(multipart.getOriginalFilename());
        s3File.createNewFile();
        FileOutputStream fos = new FileOutputStream(s3File);
        fos.write(multipart.getBytes());
        fos.close();
        return s3File;

    }

}
