package apps;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProxyReader {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		//ReadNames:
//		public static Map<String, Proxy> readNames () throws IOException{
//
//			CSVParser nameParser = CSVParser.parse(new File("name.csv"),
//					StandardCharsets.ISO_8859_1, CSVFormat.EXCEL.withHeader().withDelimiter(';'));
//			List<CSVRecord> lines = nameParser.getRecords();
//			Map<String, Country> countries = new HashMap<String, Country>();
//			for (CSVRecord line : lines){
//				if (countries.containsKey(line.get("Country"))){
//					countries.get(line.get("Country")).addByType(line.get("Type"), line.get("Name"));
//				}else{
//					Country country = new Country(line.get("Country"));
//					country.addByType(line.get("Type"), line.get("Name"));
//					countries.put(line.get("Country"), country);
//				}
//			}
////			for(String country : countries.keySet())System.out.println(countries.get(country));
//			return countries;
//		}

	}

}
