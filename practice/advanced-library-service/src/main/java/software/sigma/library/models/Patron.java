package software.sigma.library.models;

import lombok.Getter;

@Getter
public class Patron {

	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String ph;
	private Address addr;

	public Patron(String string1, String string2, String string3,
			String string4, Address address, String string5) {
    //string1 is id
    //string2 is first name
    //string3 is middle initial
    //string4 is last name
    //string5 is  phone
		this.id = string1;
		this.firstName = string2;
		this.middleName = string3;
		this.lastName = string4;
		this.ph = string5;
		this.addr = address;
	}

	public Patron(String id) {
		this(id, "", "", "", new Address("", "", "", "", ""), "");
	}

	public boolean hasId(String expectedId) {
		return id.equals(expectedId);
	}
}
