package test;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class TestApp {

	/*
	 * http://htmlunit.sourceforge.net/gettingStarted.html
	 * 
	 * INTRODUCTION:
	 * 
	 * The dependencies page lists all the jars that you will need to have in
	 * your classpath.
	 * 
	 * The class com.gargoylesoftware.htmlunit.WebClient is the main starting
	 * point. This simulates a web browser and will be used to execute all of
	 * the tests.
	 * 
	 * Most unit testing will be done within a framework like JUnit so all the
	 * examples here will assume that we are using that.
	 * 
	 * In the first sample, we create the web client and have it load the
	 * homepage from the HtmlUnit website. We then verify that this page has the
	 * correct title. Note that getPage() can return different types of pages
	 * based on the content type of the returned data. In this case we are
	 * expecting a content type of text/html so we cast the result to an
	 * com.gargoylesoftware.htmlunit.html.HtmlPage.
	 */
	
	/*
	@Test
	public void homePage() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
			Assert.assertEquals("HtmlUnit " + '\u2013' + " Welcome to HtmlUnit", page.getTitleText());

			final String pageAsXml = page.asXml();
			Assert.assertTrue(pageAsXml.contains("<body class=\"topBarDisabled\">"));

			final String pageAsText = page.asText();
			Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
		}
	}
	*/
	
	/*
	 * IMITATING A SPECIFIC BROWSER
	 * 
	 * Often you will want to simulate a specific browser. This is done by
	 * passing a com.gargoylesoftware.htmlunit.BrowserVersion into the WebClient
	 * constructor. Constants have been provided for some common browsers but
	 * you can create your own specific version by instantiating a
	 * BrowserVersion.
	 */
	/*
	@Test
	public void homePage_Firefox() throws Exception {
		try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52)) {
			final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
			Assert.assertEquals("HtmlUnit " + '\u2013' + " Welcome to HtmlUnit", page.getTitleText());
		}
	}
	*/
	/*
	 * FINDING A SPECIFIC ELEMENT
	 * 
	 * Once you have a reference to an HtmlPage, you can search for a specific
	 * HtmlElement by one of 'get' methods, or by using XPath.
	 * 
	 * Below is an example of finding a 'div' by an ID, and getting an anchor by
	 * name:
	 */
	
	/*
	@Test
	public void getElements() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://some_url");
			final HtmlDivision div = page.getHtmlElementById("some_div_id");
			final HtmlAnchor anchor = page.getAnchorByName("anchor_name");
		}
	}
	*/
	
	// A simple way for finding elements might be to find all elements of a
	// specific type.
	/*
	 * @Test public void getElements2() throws Exception { try (final WebClient
	 * webClient = new WebClient()) { final HtmlPage page =
	 * webClient.getPage("http://some_url"); NodeList inputs =
	 * page.getElementsByTagName("input"); final Iterator<E> nodesIterator =
	 * nodes.iterator(); // now iterate } }
	 */
	// XPath is the suggested way for more complex searches, a brief tutorial
	// can be found in W3Schools
	/*
	@Test
	public void xpath() throws Exception {
		try (final WebClient webClient = new WebClient()) {
			final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

			// get list of all divs
			final List<?> divs = page.getByXPath("//div");

			// get div which has a 'name' attribute of 'John'
			final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@name='John']").get(0);
		}
	}
	*/
	
	/*
	 * USING A PROXY SERVER
	 * 
	 * The last WebClient constructor allows you to specify proxy server
	 * information in those cases where you need to connect through one.
	 * 
	 * Specifying this BrowserVersion will change the user agent header that is
	 * sent up to the server and will change the behavior of some of the
	 * JavaScript.
	 * 
	 * NOTE: CHANGE ARGS PROXY AND PORT FROM WEBCLIENT ARGS
	 */
	/*
	@Test
	public void homePage_proxy() throws Exception {
		try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_52, "195.red-83-45-10.dynamicip.rima-tde.net",8080)) {

			// set proxy username and password
			final DefaultCredentialsProvider credentialsProvider = (DefaultCredentialsProvider) webClient
					.getCredentialsProvider();
			credentialsProvider.addCredentials("username", "password");

			final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
			Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
		}
	}
	*/
	
	/*
	 * SUBMITTING A FORM
	 * 
	 * Frequently we want to change values in a form and submit the form back to
	 * the server. The following example shows how you might do this.
	 */

	@Test
	public void submittingForm() throws Exception {
		try (final WebClient webClient = new WebClient()) {

			// Get the first page
			final HtmlPage page1 = webClient.getPage("https://accounts.google.com/SignUp?dsh=-2780850149554206483&continue=https%3A%2F%2Faccounts.google.com%2FManageAccount&hl=es-ES#FirstName=&LastName=");

			// Get the form that we are dealing with and within that form,
			// find the submit button and the field that we want to change.
			final HtmlForm form = page1.getFormByName("createaccount");

//			final HtmlSubmitInput button = form.getInputByName("submitbutton");
			final HtmlTextInput textField = form.getInputByName("userid");

			// Change the value of the text field
			textField.setValueAttribute("root");

			// Now submit the form by clicking the button and get back the
			// second page.
			
//			final HtmlPage page2 = button.click();
			
		}
	}
}
