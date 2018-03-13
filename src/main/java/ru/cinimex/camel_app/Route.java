package ru.cinimex.camel_app;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.spring.ws.SpringWebserviceConstants;
import org.springframework.stereotype.Component;

import javax.xml.soap.SOAPConstants;


@Component
public class Route extends RouteBuilder {
	@Override
	public void configure() throws Exception {
		from("{{route.from}}")
				.process(new StringToSoapProcessor())
				.to("{{route.to}}");

		from("{{route.to}}")
				.process(new MyProcessor());

	}

	private class StringToSoapProcessor implements Processor {
		public void process(Exchange exchange) throws Exception
		{
			String body = exchange.getIn().getBody(String.class);
			String xmlRequest = "<GetCitiesByCountry xmlns=\"http://www.webserviceX.NET\"> <CountryName>" + body + "</CountryName> </GetCitiesByCountry>";

			exchange.getOut().setBody(xmlRequest);

			exchange.getOut().setHeader(SpringWebserviceConstants.SPRING_WS_SOAP_ACTION, "http://www.webserviceX.NET/GetCitiesByCountry");
			exchange.getOut().setHeader(Exchange.CONTENT_TYPE, constant("text/xml; charset=utf-8"));
			exchange.getOut().setHeader(Exchange.HTTP_PROTOCOL_VERSION, constant(1.1));
			exchange.getOut().setHeader(Exchange.HTTP_METHOD, constant("POST"));
			exchange.getOut().setHeader(Exchange.CONTENT_LENGTH, constant(4000));
		}
	}

	private class MyProcessor implements Processor{
		public void process(Exchange exchange) throws Exception
		{
			System.out.println(exchange.getIn().getBody(String.class));
//			String xmlRequest = "<GetCitiesByCountry xmlns=\"http://www.webserviceX.NET\"> <CountryName>Russia</CountryName> </GetCitiesByCountry>";
//
//			exchange.getOut().setBody(xmlRequest);
//
//			exchange.getOut().setHeader("SOAPAction", constant("http://www.webserviceX.NET/GetCitiesByCountry"));
//			exchange.getOut().setHeader(Exchange.CONTENT_TYPE, constant("application/soap+xml; charset=utf-8"));
//			exchange.getOut().setHeader(Exchange.HTTP_PROTOCOL_VERSION, constant(1.1));
//			exchange.getOut().setHeader(Exchange.HTTP_METHOD, constant("POST"));
//			exchange.getOut().setHeader(Exchange.CONTENT_LENGTH, constant(4000));
		}
	}
}


