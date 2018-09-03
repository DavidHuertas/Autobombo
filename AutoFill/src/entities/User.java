package entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.StringUtils;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String name;
	private String surname;
	private String gender;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar birthdate;
	private String location;
	private String email;
	private String pwd;
	private String conn;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastConn;
	private String lastProxy;

	public User() {
	}

	public User(Integer id, String name, String surname, String gender) {
		super();
		this.id = id;
		setName(name);
		setSurname(surname);
		this.gender = gender;
	}

	public User(int id, String name, String surname, String gender, Calendar birthdate, String location) {
		super();
		this.id = id;
		setName(name);
		setSurname(surname);
		this.gender = gender;
		this.birthdate = birthdate;
		this.location = location;
	}

	public User(String name, String surname, String gender, Calendar birthdate, String location) {
		super();
		setName(name);
		setSurname(surname);
		this.gender = gender;
		this.birthdate = birthdate;
		this.location = location;
	}

	public User(int id, String name, String surname, String gender, Calendar birthdate, String location, String email,
			String conn) {
		super();
		this.id = id;
		setName(name);
		setSurname(surname);
		this.gender = gender;
		this.birthdate = birthdate;
		this.location = location;
		this.email = email;
		this.conn = conn;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public String getConn() {
		return this.conn;
	}

	public void setConn(String conn) {
		this.conn = conn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name="";
		String[] names = name.split(" ");
		for (String n : names){
			this.name += " " + StringUtils.capitalize((n.toLowerCase()));
		}
		this.name=this.name.trim();
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname="";
		String[] surnames = surname.split(" ");
		for (String s : surnames){
			this.surname += " " + StringUtils.capitalize((s.toLowerCase()));
		}
		this.surname=this.surname.trim();
	}
	@SuppressWarnings("static-access")
	public String getBirthDay(){
		return this.birthdate.DAY_OF_MONTH+"";
	}
	@SuppressWarnings("static-access")
	public String getBirthMonth(){
		return StringUtils.leftPad((this.birthdate.MONTH)+"", 2, '0');
	}
	@SuppressWarnings("static-access")
	public String getBirthYear(){
		return this.birthdate.YEAR+1900+"";
	}
	public Date getLastConn() {
		return this.lastConn;
	}

	public void setLastConn(Date lastConn) {
		this.lastConn = lastConn;
	}
	public String getLastProxy() {
		return lastProxy;
	}

	public void setLastProxy(String lastProxy) {
		this.lastProxy = lastProxy;
	}

	@Override
	public String toString() {
		return "User->\tid: " + id + ", \tname: " + name + ", \tsurname: " + surname + ", \tgender: " + gender
				+ ", \tbirthdate: " + birthdate + ", \tlocation: " + location + ", \temail: " + email + ", \tconn: "
				+ conn;
	}

	public String getGenderGmForm() {
		return this.gender;
	}
	
}