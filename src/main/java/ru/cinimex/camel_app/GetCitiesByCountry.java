package ru.cinimex.camel_app;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"countryName"
})
@XmlRootElement(name = "GetCitiesByCountry")
public class GetCitiesByCountry {

	@XmlElement(name = "CountryName")
	protected String countryName;

	/**
	 * Gets the value of the countryName property.
	 *
	 * @return
	 *     possible object is
	 *     {@link String }
	 *
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the value of the countryName property.
	 *
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *
	 */
	public void setCountryName(String value) {
		this.countryName = value;
	}

}
