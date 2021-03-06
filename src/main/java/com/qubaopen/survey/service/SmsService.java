package com.qubaopen.survey.service;

import groovy.json.JsonSlurper;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${sms189_url}")
	private String sms189_url;

	@Value("${sms189_app_id}")
	private String sms189_app_id;

	@Value("${sms189_app_secret}")
	private String sms189_app_secret;

	@Value("${sms189_access_token}")
	private String sms189_access_token;

	@Value("${sms189_template_id}")
	private String sms189_template_id;

	@Value("${sms189_template_param}")
	private String sms189_template_param;

	public boolean sendCaptcha(String phone) {
		return sendCaptcha(phone, RandomStringUtils.randomNumeric(6));
	}

	public boolean sendCaptcha(String phone, String captcha) {

		LOGGER.trace("sms189_url := {}", sms189_url);
		LOGGER.trace("sms189_app_id := {}", sms189_app_id);
		LOGGER.trace("sms189_app_secret := {}", sms189_app_secret);
		LOGGER.trace("sms189_access_token := {}", sms189_access_token);
		LOGGER.trace("sms189_template_id := {}", sms189_template_id);
		LOGGER.trace("sms189_template_param := {}", sms189_template_param);

		StringBuilder builder = new StringBuilder();
		builder.append("app_id=").append(sms189_app_id).append('&')
			.append("access_token=").append(sms189_access_token).append('&')
			.append("template_id=").append(sms189_template_id).append('&')
			.append("template_param=").append('{').append("\"validatecode\" : ").append(captcha).append('}').append('&')
			.append("acceptor_tel=").append(phone).append('&')
			.append("timestamp=").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

		HttpEntity<String> request = new HttpEntity<String>(builder.toString());
		String result = restTemplate.postForObject(sms189_url, request, String.class);

		@SuppressWarnings("unchecked")
		Map<String, ?> json = (Map<String, ?>) new JsonSlurper().parseText(result);

		return StringUtils.equals((String) json.get("res_code"), "0");
	}


}
