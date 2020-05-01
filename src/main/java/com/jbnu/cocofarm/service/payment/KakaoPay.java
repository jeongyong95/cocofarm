package com.jbnu.cocofarm.service.payment;

import java.net.URI;
import java.net.URISyntaxException;

import com.jbnu.cocofarm.domain.kakaopay.KakaoPayApprovalVO;
import com.jbnu.cocofarm.domain.kakaopay.KakaoPayReadyVO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.java.Log;

@Service
@Log
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady() {

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "447b3e953116a1d2308f0633b4d17563");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME"); // cid = 가맹점 코드 , Test 코드는 TC0ONETIME
        params.add("partner_order_id", "1001"); // 결제건에 대한 가맹점의 주문번호
        params.add("partner_user_id", "주정용"); // 가맹점에서 사용자를 구분할 수 있는 id
        params.add("item_name", "딸기");
        params.add("quantity", "10");
        params.add("total_amount", "5000");
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        // 헤더와 바디를 붙임
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            // RestTemplate을 이용해 카카오페이에 데이터를 보내는 방법, KakaoPayReadyVO.classs는 응답을 받는 객체를 설정한
            // 것
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body,
                    KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/pay";

    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {

        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "447b3e953116a1d2308f0633b4d17563");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayReadyVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "주정용");
        params.add("pg_token", pg_token);
        params.add("total_amount", "5000");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
                    KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}