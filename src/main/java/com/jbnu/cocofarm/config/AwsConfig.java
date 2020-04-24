// package com.jbnu.cocofarm.config;

// import com.amazonaws.auth.AWSStaticCredentialsProvider;
// import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.regions.Regions;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.AmazonS3ClientBuilder;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @EnableWebMvc
// public class AwsConfig implements WebMvcConfigurer {

// @Value("${AWS.ACCESSKEY}")
// private String accessKey;

// @Value("${AWS.SECRETKEY}")
// private String secretKey;

// @Bean
// public BasicAWSCredentials awsCredentials() {
// BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,
// secretKey);
// return awsCredentials;
// }

// @Bean
// public AmazonS3 amazonS3() {
// AmazonS3 amazonS3Builder =
// AmazonS3ClientBuilder.standard().withRegion(Regions.AP_NORTHEAST_2)
// .withCredentials(new
// AWSStaticCredentialsProvider(this.awsCredentials())).build();
// return amazonS3Builder;
// }
// }