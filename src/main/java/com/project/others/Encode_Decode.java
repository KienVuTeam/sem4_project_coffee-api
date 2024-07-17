package com.project.others;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.poi.ss.formula.functions.T;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.Supplier;

public class Encode_Decode {
	
	static Encode_Decode instance = null;
	public static Encode_Decode getInstance() {
		if(instance == null) {
			instance = new Encode_Decode();
		}
		return instance;
	}
	private Encode_Decode() {}
	
	public String encodeString(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR encodeString");
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public String decodeString(String value) {
		try {
			return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR decodeString");
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public String objToJsonString(Object ob) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(ob);
		} catch (JsonProcessingException e) {
			System.out.println("ERROR objToJsonString");
			System.out.println(e.getMessage());
			return "";
		}
	}
	
	public Object jsonToObject(String json, Class<Supplier> ob) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(json, ob);
		} catch (JsonProcessingException e) {
			System.out.println("ERROR jsonToObject");
			System.out.println(e.getMessage());
			return "";
		}
	}
}
