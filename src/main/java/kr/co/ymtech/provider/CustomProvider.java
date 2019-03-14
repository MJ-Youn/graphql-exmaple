package kr.co.ymtech.provider;

import graphql.ExecutionResult;

public interface CustomProvider {

	ExecutionResult execute(String query);
	
}
