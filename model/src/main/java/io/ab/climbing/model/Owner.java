package io.ab.climbing.model;

import java.util.List;


public class Owner {

	private Integer id;

	private String email;

	private String firstName;

	private String lastName;

	private String phoneNumber;
	
	private String password;

	private List<Topo> topos;
	
	private boolean topoAvailable;

	public Owner() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Topo> getTopos() {
		return this.topos;
	}

	public void setTopos(List<Topo> topos) {
		this.topos = topos;
	}

	public void addTopo(Topo topo) {
		getTopos().add(topo);
	}

	public void removeTopo(Topo topo) {
		getTopos().remove(topo);
	}

	public boolean isTopoAvailable() {
		return topoAvailable;
	}

	public void setTopoAvailable(boolean isTopoAvailable) {
		this.topoAvailable = isTopoAvailable;
	}
	
	public String getFullName() {
		return String.format("%1s %2s", this.firstName, this.lastName);
	}

}