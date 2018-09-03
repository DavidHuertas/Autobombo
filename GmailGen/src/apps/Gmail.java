package apps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import entities.User;

public class Gmail {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AutoFill");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		newGmailAccount(em);

		em.close();
		System.out.println("DONE");
		System.exit(0);
		;
	}

	private static void newGmailAccount(EntityManager em)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException, InterruptedException {
		
		String query = "SELECT u FROM User u ORDER BY random()";
		Query q = em.createQuery(query);
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		
		User user = users.get(0);//TODO : In the future, try a for each

		// TODO TEST: REQUEST FORM1 AND FILL
		@SuppressWarnings("resource")
		WebClient webClient = new WebClient();
		// Get the first page
		HtmlPage page1 = webClient.getPage(
				"https://accounts.google.com/SignUp?dsh=-2780850149554206483&continue=https%3A%2F%2F"
				+ "accounts.google.com%2FManageAccount&hl=es-ES#FirstName=&LastName=");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the fields that we want to change.
		//FIELDS: garcia.antonio.19851217
		final HtmlForm form = page1.getFormByName("createaccount");
		final HtmlTextInput firstName = form.getInputByName("FirstName");
		firstName.setValueAttribute(user.getName());
		Thread.sleep(randomBetween(1000,3000));
		
		final HtmlTextInput lastName = form.getInputByName("LastName");
		lastName.setValueAttribute(user.getSurname());
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput gmailAddress = form.getInputByName("GmailAddress");
		gmailAddress.setValueAttribute(emailGen(user));
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput passwd = form.getInputByName("Passwd");
		passwd.setValueAttribute(pwdGen(user));
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput passwdAgain = form.getInputByName("PasswdAgain");
		passwdAgain.setValueAttribute(user.getPwd());
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput birthDay = form.getInputByName("BirthDay");
		birthDay.setValueAttribute(user.getBirthDay());
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput birthMonth = form.getInputByName("BirthMonth");
		birthMonth.setValueAttribute(user.getBirthMonth());//LIST (01,02,03,04,05,06,07,08,09,10,11,12)
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput birthYear = form.getInputByName("BirthYear");
		birthYear.setValueAttribute(user.getBirthYear());
		Thread.sleep(randomBetween(1000,3000));

		final HtmlTextInput gender = form.getInputByName("Gender");
		gender.setValueAttribute(user.getGenderGmForm()); //(FEMALE,MALE,OTHER,DECLINE)
		Thread.sleep(randomBetween(1000,3000));

		//TODO: ACCEPT CONDITIONS CHECKBOX!!!!
		
		//SUBMIT BUTTON:
		HtmlSubmitInput nextButton = form.getInputByName("submitbutton");
		HtmlPage page2 = nextButton.click();
		Thread.sleep(randomBetween(1000,3000));
		
		// TODO TEST: REQUEST FORM2 AND FILL
		//BUTTON NAME: iagreebutton
		HtmlForm form2 = page2.getFormByName("agree");
//<input id="iagreebutton" name="iagreebutton" class="tos-button iagree-button" onclick="submitForm()" value="Acepto" type="button">
//		HtmlSubmitInput submitButton = form2.getInputByName("iagreebutton");//OJO, RECICLAR EL FORM
//		HtmlPage page3 = nextButton.click();
		
		// TODO TEST: GO TO YOUT, SUSC
//		HtmlPage youtPage = webClient.getPage("https://www.youtube.com/channel/UCt-9uyPy5zNsWu4XI7TCAkg");
	}

	private static String pwdGen(User user) {
		//TODO: FIX ACCENTS
		user.setPwd((user.getBirthYear())+(user.getBirthMonth())+(user.getBirthDay())+(user.getSurname().replaceAll(" ", "")));
		return user.getPwd();
	}

	private static String emailGen(User user) {
		//TODO: FIX ACCENTS
		return user.getSurname().replaceAll(" ", ".")+user.getName().replaceAll(" ", ".")+user.getId();
	}
	
	public static int randomBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

}
