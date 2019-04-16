package kr.co.ymtech.provider;

import java.util.Map;

public interface CustomProvider {

	Map<String, Object> execute(String query);
	
}
