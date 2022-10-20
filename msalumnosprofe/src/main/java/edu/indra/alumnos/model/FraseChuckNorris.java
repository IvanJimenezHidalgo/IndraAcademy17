package edu.indra.alumnos.model;

/**
 * 
 * {
    "categories": [],
    "created_at": "2020-01-05 13:42:27.496799",
    "icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
    "id": "BlAsaKYDStOwnNvghMz1lw",
    "updated_at": "2020-01-05 13:42:27.496799",
    "url": "https://api.chucknorris.io/jokes/BlAsaKYDStOwnNvghMz1lw", --HAL/HATEOAS
    "value": "Chuck Norris can give a black guy a fat lip"
}
 *
 */

public class FraseChuckNorris {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FraseChuckNorris(String value) {
		super();
		this.value = value;
	}
	
	public FraseChuckNorris() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FraseChuckNorris [value=" + value + "]";
	}

	
}
