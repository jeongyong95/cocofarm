// package com.jbnu.cocofarm.service;

// import java.io.File;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import com.amazonaws.AmazonServiceException;
// import com.amazonaws.auth.policy.Resource;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.model.DeleteObjectRequest;
// import com.amazonaws.services.s3.model.GetObjectRequest;
// import com.amazonaws.services.s3.model.ObjectMetadata;
// import com.amazonaws.services.s3.model.PutObjectRequest;
// import com.amazonaws.services.s3.model.S3Object;
// import com.amazonaws.services.s3.model.S3ObjectInputStream;
// import com.amazonaws.util.IOUtils;

// import org.springframework.core.io.ByteArrayResource;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.multipart.MultipartFile;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// /**
// * AwsS3Service
// */
// @Slf4j
// @RequiredArgsConstructor
// @Transactional(rollbackFor = Exception.class)
// @Service
// public class AwsS3Service {

// private final AmazonS3 amazonS3;

// private String bucketName;

// public void uploadObject(MultipartFile multipartFile, String fileName) throws
// IOException {

// SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setContentType(multipartFile.getContentType());
// metadata.setContentLength(multipartFile.getSize());
// metadata.setHeader("filename", multipartFile.getOriginalFilename());

// amazonS3.putObject(new PutObjectRequest(bucketName + "/" + date.format(new
// Date()), fileName,
// multipartFile.getInputStream(), metadata));

// }

// public void deleteObject(String date, String fileName) throws
// AmazonServiceException {
// amazonS3.deleteObject(new DeleteObjectRequest(bucketName + "/" + date,
// fileName));
// }

// public File getObject(String date, String fileName) throws IOException {
// S3Object object = amazonS3.getObject(new GetObjectRequest(bucketName + "/" +
// date, fileName));
// S3ObjectInputStream inputStream = object.getObjectContent();
// byte[] bytes = IOUtils.toByteArray(inputStream);

// ByteArrayResource resource = new ByteArrayResource(bytes);

// return resource.getFile();

// }
// }