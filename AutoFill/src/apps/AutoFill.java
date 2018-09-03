package apps;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.time.DateUtils;

import entities.Country;
import entities.User;

public class AutoFill {
	
	public static void main(String[] args) throws IOException, ParseException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AutoFill");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

//		insertTestEntry(em);
		insertUserGen(em, 100, "Spain");
		
		em.close();
		System.out.println("DONE");
		System.exit(0);
		;
	}
	
	public static User userGen (Country country) throws ParseException{
		Boolean isFemale = new Random().nextBoolean();
		String gender = isFemale ? "female" : "male";
		String femaleName = country.getFemaleNames().get((new Random()).nextInt(country.getFemaleNames().size()));
		String maleName = country.getMaleNames().get((new Random()).nextInt(country.getMaleNames().size()));
		String name = isFemale ? femaleName : maleName;
		String surname = country.getSurNames().get((new Random()).nextInt(country.getSurNames().size()));
		surname = surname + " " +country.getSurNames().get((new Random()).nextInt(country.getSurNames().size()));
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date birthdate = formatter.parse(formatter.format(randomDate()));
		String location = country.getCountry();

		
		return new User(name, surname, gender, DateUtils.toCalendar(birthdate), location);
	}
	
	public static void insertUserGen(EntityManager em, Integer num, String countryName) throws IOException, ParseException{
		Map<String,Country> countries = readNames();
		Country country = countries.get(countryName);
		
		for (Integer i=1; i<=num; i++){
			insertNewEntry(userGen(country), em);
		}
		em.getTransaction().commit();
		//TO CHECK WHAT IS IN THE DB
		Query selectAllQuery = em.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> users = selectAllQuery.getResultList();
		for (User u : users)System.out.println(u);
	}
	
	//ReadNames:
	public static Map<String, Country> readNames () throws IOException{

		CSVParser nameParser = CSVParser.parse(new File("name.csv"),
				StandardCharsets.ISO_8859_1, CSVFormat.EXCEL.withHeader().withDelimiter(';'));
		List<CSVRecord> lines = nameParser.getRecords();
		Map<String, Country> countries = new HashMap<String, Country>();
		for (CSVRecord line : lines){
			if (countries.containsKey(line.get("Country"))){
				countries.get(line.get("Country")).addByType(line.get("Type"), line.get("Name"));
			}else{
				Country country = new Country(line.get("Country"));
				country.addByType(line.get("Type"), line.get("Name"));
				countries.put(line.get("Country"), country);
			}
		}
//		for(String country : countries.keySet())System.out.println(countries.get(country));
		return countries;
	}
	
	//InsertNewEntry
	public static void insertNewEntry (User user, EntityManager em) throws ParseException{
		//Get Last Id +1
		Query q = em.createQuery("SELECT MAX(u.id)+1 FROM User u");
		Integer id=(Integer) q.getSingleResult();//FIX;
		user.setId(id);
		try {
			em.persist(user);
		} catch (Exception e) {
			System.out.println("No commit possible: "+e);
			em.getTransaction().rollback();
		} finally {
			
		}
		
	}
	public static Date randomDate (){
		GregorianCalendar gc = new GregorianCalendar();
        Integer year = randomBetween(2017-40, 2017-18);
        gc.set(Calendar.YEAR, year);
        Integer dayOfYear = randomBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);
		return gc.getTime();
	}
    public static int randomBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
    
	//InsertNewEntry
	public static void insertTestEntry (EntityManager em) throws ParseException{
		//Get Last Id +1
		Query q = em.createQuery("SELECT MAX(u.id)+1 FROM User u");
		Integer id=(Integer) q.getSingleResult();//FIX;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		User user = new User(id, "Antonio","Garcia","male",DateUtils.toCalendar(new Date()),"Spain");
		
		try {
			em.persist(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("No commit possible: "+e);
			em.getTransaction().rollback();
		} finally {
			
		}
		
		//TO CHECK WHAT IS IN THE DB
		Query selectAllQuery = em.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> users = selectAllQuery.getResultList();
		for (User u : users)System.out.println(u);
		
	}
}
