package ru.cinimex.camel_app;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("{{route.from}}").end()
				.to("{{route.to}}");
	}
}
