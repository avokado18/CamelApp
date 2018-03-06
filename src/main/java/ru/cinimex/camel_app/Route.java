package ru.cinimex.camel_app;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.springframework.stereotype.Component;

@Component
public class Route extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("{{route.from}}")
				.process(new StringToSoapProcessor())
				.to("{{route.to}}").end();

	}

	private class StringToSoapProcessor implements Processor {
		public void process(Exchange exchange) throws Exception
		{
			String xmlRequestForGoogleStockQuote = "<GetWeather xmlns=\"http://www.webserviceX.NET\"><CityName>Voronezh</CityName><CountryName>Russia</CountryName></GetWeather>";
			String soapHeader = "<h:Header xmlns:h=\"http://www.webserviceX.NET/\"><h:MessageID>1234567890</h:MessageID><h:Nested><h:NestedID>1111</h:NestedID></h:Nested></h:Header>";
			exchange.getOut().copyFrom(exchange.getIn());
			exchange.getOut().setBody(xmlRequestForGoogleStockQuote);
			exchange.getOut().setHeader(SpringWebserviceConstants.SPRING_WS_SOAP_HEADER, soapHeader);
		}
	}
}


