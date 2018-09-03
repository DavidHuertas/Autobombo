package entities;

import java.util.ArrayList;
import java.util.List;

public class Country {

	private String country;
	private List<String> femaleNames = new ArrayList<String>();
	private List<String> maleNames = new ArrayList<String>();
	private List<String> surNames = new ArrayList<String>();
	private List<String> ports = new ArrayList<String>();
	public Country(String country) {
		super();
		this.country = country;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public List<String> getFemaleNames() {
		return femaleNames;
	}
	public void setFemaleNames(List<String> femaleNames) {
		this.femaleNames = femaleNames;
	}
	public List<String> getMaleNames() {
		return maleNames;
	}
	public void setMaleNames(List<String> maleNames) {
		this.maleNames = maleNames;
	}
	public List<String> getSurNames() {
		return surNames;
	}
	public void setSurNames(List<String> surNames) {
		this.surNames = surNames;
	}
	public List<String> getPorts() {
		return ports;
	}
	public void setPorts(List<String> ports) {
		this.ports = ports;
	}
	
	public void addFemaleName (String femaleName){
		this.femaleNames.add(femaleName);
	}
	public void addMaleName (String maleName){
		this.maleNames.add(maleName);
	}
	public void addSurName (String surName){
		this.surNames.add(surName);
	}
	public void addPort (String port){
		this.femaleNames.add(port);
	}
	public void addByType (String type, String name){
		if (type.equals("femaleName")){
			this.addFemaleName(name);
		}else if (type.equals("maleName")){
			this.addMaleName(name);
		}else if (type.equals("surName")){
			this.addSurName(name);
		}
	}
	@Override
	public String toString() {
		return "Country->\tcountry: " + country + ", \tfemaleNames: " + femaleNames + ", \tmaleNames: " + maleNames
				+ ", \tsurNames: " + surNames + ", \tports: " + ports;
	}
	
}
