package com.dorchsoft.seleniumtest.input;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.dorchsoft.seleniumtest.model.Account;

public class AccountsReader {
	
	public static List<Account> readAccount(){
		List<Account> accounts = new ArrayList<Account>();
		CSVParser parseAccountList;
		try {
			parseAccountList = CSVParser.parse(new File("accounts.csv"),
					StandardCharsets.ISO_8859_1, CSVFormat.EXCEL.withHeader().withDelimiter(';'));
			List<CSVRecord> lines = parseAccountList.getRecords();
			
			for (CSVRecord line : lines){
				Account account = new Account();
				account.setUser(line.get("user"));
				account.setPwd(line.get("pwd"));
				accounts.add(account);
			}
			
			return accounts;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
