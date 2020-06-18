package com.jbnu.cocofarm.service.payment;

import java.net.URI;
import java.net.URISyntaxException;

import com.jbnu.cocofarm.domain.kakaopay.KakaoPayApprovalVO;
import com.jbnu.cocofarm.domain.kakaopay.KakaoPayReadyVO;
import com.jbnu.cocofarm.domain.order.dto.OrderTotalDto.OrderTotalRegisterDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady(OrderTotalRegisterDto totalRegisterDto) {

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
        params.add("partner_user_id", totalRegisterDto.getCustomerName()); // 가맹점에서 사용자를 구분할 수 있는 id
        params.add("item_name", totalRegisterDto.getCustomerName());
        params.add("quantity", "10");
        params.add("total_amount", String.valueOf(totalRegisterDto.getTotalPrice()));
        params.add("tax_free_amount", "100");
        params.add("approval_url", "http://localhost:8080/payment/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8080/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8080/kakaoPaySuccessFail");

        // 헤더와 바디를 붙임
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            // RestTemplate을 이용해 카카오페이에 데이터를 보내는 방법, KakaoPayReadyVO.classs는 응답을 받는 객체를 설정한
            // 것
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body,
                    KakaoPayReadyVO.class);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";

    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, OrderTotalRegisterDto totalRegisterDto) {

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
        params.add("partner_user_id", totalRegisterDto.getCustomerName());
        params.add("pg_token", pg_token);
        params.add("total_amount", String.valueOf(totalRegisterDto.getTotalPrice()));

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
                    KakaoPayApprovalVO.class);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

}