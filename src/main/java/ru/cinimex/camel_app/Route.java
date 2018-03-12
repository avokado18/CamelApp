package ru.cinimex.camel_app;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("{{route.from}}")
				.process(new StringToSoapProcessor())
				.to("{{route.to}}");

	}

	private class StringToSoapProcessor implements Processor {
		public void process(Exchange exchange) throws Exception
		{
			System.out.println(exchange.getIn().getBody(String.class));
			String xmlRequest = "<GetWeather xmlns=\"http://www.webserviceX.NET\"><CityName>Voronezh</CityName><CountryName>Russia</CountryName></GetWeather>";
//			String soapHeader = "<h:Header xmlns:h=\"http://www.webserviceX.NET/\"><h:MessageID>1234567890</h:MessageID><h:Nested><h:NestedID>1111</h:NestedID></h:Nested></h:Header>";
//			exchange.getOut().copyFrom(exchange.getIn());
			exchange.getOut().setBody(xmlRequest);
			exchange.getOut().setHeader(Exchange.CONTENT_TYPE, constant("text/xml; charset=utf-8"));
			exchange.getOut().setHeader(Exchange.HTTP_PROTOCOL_VERSION, constant(1.1));
			exchange.getOut().setHeader(Exchange.HTTP_METHOD, constant("POST"));
			exchange.getOut().setHeader(Exchange.CONTENT_LENGTH, constant(100));
//			exchange.setPattern(null);
		}
	}
}


