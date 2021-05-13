package com.verizon.controllers;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.verizon.models.PhoneSubscription;
import com.verizon.models.Subscription;
import com.verizon.models.Subscriptions;
import com.verizon.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.verizon.models.CableSubscription;
import com.verizon.models.Customer;
import com.verizon.models.Html;
import com.verizon.models.InternetSubscription;
import com.verizon.services.CableService;
import com.verizon.services.CustomerService;
import com.verizon.services.InternetService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/subscribe")
public class SubscriptionController 
{
	@Autowired
	CableService cableService;
	
	@Autowired
	InternetService internetService;
	
	@Autowired
	PhoneService phoneService;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Html> getById(@PathVariable("id") int id) throws Exception 
	{
		ResponseEntity<CableSubscription> cableResponse = cableService.findById(id);
		ResponseEntity<InternetSubscription> internetResponse = internetService.findById(id);
		ResponseEntity<PhoneSubscription> phoneResponse = phoneService.findById(id);
		
		Subscriptions result = createSubscription(id, cableResponse, internetResponse, phoneResponse);

		String asXml = toXMLString(result);
		String asHTML = xmlStringToHTMLString(asXml);
		
		return ResponseEntity.ok(new Html(asHTML));
	}
	
	@GetMapping("customer/{id}")
	public ResponseEntity<Subscription[]> getCustomerSubscriptions(@PathVariable("id") int id) 
	{
		Subscription[] result = new Subscription[3];
		
		ResponseEntity<CableSubscription> cableResponse = cableService.findById(id);
		ResponseEntity<InternetSubscription> internetResponse = internetService.findById(id);
		ResponseEntity<PhoneSubscription> phoneResponse = phoneService.findById(id);
		
		if (cableResponse.getStatusCode().equals(HttpStatus.OK))
			result[0] = new Subscription(cableResponse.getBody().getId(), "Cable", cableResponse.getBody().getName());
		
		if (internetResponse.getStatusCode().equals(HttpStatus.OK))
			result[1] = new Subscription(internetResponse.getBody().getId(), "Internet", internetResponse.getBody().getName());
		
		if (phoneResponse.getStatusCode().equals(HttpStatus.OK))
			result[2] = new Subscription(phoneResponse.getBody().getId(), "Phone", phoneResponse.getBody().getName());
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("customer/usernames/{username}")
	public ResponseEntity<Subscription[]> getById(@PathVariable("username") String username) 
	{
		ResponseEntity<Customer> customerResponse = customerService.getCustomerByUsername(username);
		
		if (customerResponse.getStatusCode().equals(HttpStatus.OK))
		{
			Customer customer = customerResponse.getBody();
			if (customer != null)
			{
				return getCustomerSubscriptions(customer.getId()); 
			}
			else
			{
				return ResponseEntity.noContent().build();
			}
		}
		else
		{
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<List<CableSubscription>> getCable() 
	{
		return cableService.findAll();
	}
	
	@PostMapping("/cable")
	public ResponseEntity<CableSubscription> subscribeCable(@RequestBody CableSubscription subscription) 
	{
		return cableService.Subscribe(subscription);
	}
	@PostMapping("/internet")
	public ResponseEntity<InternetSubscription> subscribeInternet(@RequestBody InternetSubscription subscription) 
	{
		return internetService.Subscribe(subscription);
	}
	@PostMapping("/phone")
	public ResponseEntity<PhoneSubscription> subscribePhone(@RequestBody PhoneSubscription subscription) 
	{
		return phoneService.Subscribe(subscription);
	}
	
	@DeleteMapping("/cable/{id}")
	public ResponseEntity<CableSubscription> deleteCable(@PathVariable("id") int id) 
	{
		return cableService.delete(id);
	}
	@DeleteMapping("/internet/{id}")
	public ResponseEntity<InternetSubscription> deleteInternet(@PathVariable("id") int id) 
	{
		return internetService.delete(id);
	}
	@DeleteMapping("/phone/{id}")
	public ResponseEntity<PhoneSubscription> deletePhone(@PathVariable("id") int id) 
	{
		return phoneService.delete(id);
	}
	
	private Subscriptions createSubscription(int id, ResponseEntity<CableSubscription> cableResponse,
			ResponseEntity<InternetSubscription> internetResponse,
			ResponseEntity<PhoneSubscription> phoneResponse)
	{
		Subscriptions result = new Subscriptions();
		result.setId(id);
		result.setName("Not a valid customer ID:");
		
		if (cableResponse.getStatusCode().equals(HttpStatus.OK))
		{
			result.setCableSubscribed(true);
			result.setName(cableResponse.getBody().getName());
		}
		
		if (internetResponse.getStatusCode().equals(HttpStatus.OK))
		{
			result.setInternetSubscribed(true);
			result.setName(internetResponse.getBody().getName());
		}
		
		if (phoneResponse.getStatusCode().equals(HttpStatus.OK))
		{
			result.setPhoneSubscribed(true);
			result.setName(phoneResponse.getBody().getName());
		}
		return result;
	}
	
	
	private static String toXMLString(Subscriptions subscriptions) throws JAXBException 
	{         
		//Test test = new Test(3, false, true, false);         
		JAXBContext jaxbContext = JAXBContext.newInstance(Subscriptions.class);         
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller(); // output pretty printed         
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);         
		StringWriter sw = new StringWriter();         
		jaxbMarshaller.marshal(subscriptions, sw);         
		return sw.toString();     
	}
	private static String xmlStringToHTMLString(String xmlString) throws Exception     
	{         
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();         
		//File xml = new File("C://Users//alexander.swain//Desktop//persons.xml");         
		File xsl = new File("src\\main\\resources\\newDisplay.xsl");       
		//DocumentBuilder builder = factory.newDocumentBuilder();         
		//document = builder.parse(xml);         
		Document document = convertStringToXMLDocument(xmlString);         
		TransformerFactory transformerFactory = TransformerFactory.newInstance();         
		StreamSource style = new StreamSource(xsl);        
		Transformer transformer = transformerFactory.newTransformer(style);         
		StringWriter writer = new StringWriter();         
		DOMSource source = new DOMSource(document);         
		//StreamResult result = new StreamResult(new File("C://Users//alexander.swain//Desktop//persons.html"));         
		StreamResult result = new StreamResult(writer);         
		transformer.transform(source, result);         
		return writer.toString();    
	}     
	private static Document convertStringToXMLDocument(String xmlString)     
	{        
		//Parser that produces DOM object trees from XML content         
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();         
		//API to obtain DOM Document instance         
		DocumentBuilder builder = null;         
		try         
		{             
			//Create DocumentBuilder with default configuration             
			builder = factory.newDocumentBuilder();             
			//Parse the content to Document object             
			Document doc = builder.parse(new InputSource(new StringReader(xmlString)));             
			return doc;         
		}         
		catch (Exception e)         
		{             
			e.printStackTrace();         
		}         
		return null;     
	} 
}
