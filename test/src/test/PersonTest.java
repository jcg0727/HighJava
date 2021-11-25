package test;

import java.io.File;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class PersonTest {

	public static void main(String[] args) {
		
		File file = new File("./src/test/People.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
			SAXParser parser = factory.newSAXParser();
			PeopleSAXHandler handler = new PeopleSAXHandler();
			parser.parse(file, handler);
			
			List<Person> list = handler.getPersonList();
			
			for (Person person : list) {
				System.out.println(person);
			}
			
		} catch (Exception e) {
			
		}
	}
}
