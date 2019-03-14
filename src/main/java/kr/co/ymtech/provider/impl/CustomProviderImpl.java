package kr.co.ymtech.provider.impl;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.coxautodev.graphql.tools.SchemaParser;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import kr.co.ymtech.provider.CustomProvider;

@Component
public class CustomProviderImpl implements CustomProvider {

	@Autowired
	private ApplicationContext context;
	
	@Value("classpath:schema.graphqls")
	private Resource resource;
	
	private GraphQL graphQL;

	@PostConstruct
	private void init() throws IOException {
		String[] beanNames = context.getBeanNamesForType(GraphQLResolver.class);
		final GraphQLResolver<?>[] resolvers = new GraphQLResolver<?>[beanNames.length];
		for (int i = 0; i < beanNames.length; i++) {
			resolvers[i] = (GraphQLResolver<?>) context.getBean(beanNames[i]);
		}

		GraphQLSchema schema = SchemaParser.newParser()
				.file(resource.getFilename())
				.resolvers(resolvers)
				.build()
				.makeExecutableSchema();
		graphQL = GraphQL.newGraphQL(schema).build();
	}
	
	@Override
	public ExecutionResult execute(String query) {
		return graphQL.execute(query);
	}

}
