package org.crabapple.binding.serialize;

import java.io.StringWriter;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.hp.wwops.ecommerce.service.entity.wstax3.*;

public class WsTaxModelConvertor {
	private static final String contextPath = "com.hp.wwops.ecommerce.service.entity.wstax3";
	
	public static String convertTaxRequest(TaxRequest request) throws JAXBException{
		StringWriter ws = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(new Class[]{request.getClass()});
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		m.marshal(request, ws);
		return ws.toString();
	}
	
	
	public static void main(String[] args) throws JAXBException{
		TaxRequest taxRequest = TaxRequestUtils.initializeTaxRequest();
		Document_LineType line = taxRequest.getLineItemTaxs()[0];
		line.setDoc_Line_No(BigInteger.valueOf(2));
		line.getCustomer().getShip_To().setShip_To_Statec("PE");
		taxRequest.setLineItemTaxs(new Document_LineType[]{line,taxRequest.getLineItemTaxs()[0]});
		String request = WsTaxModelConvertor.convertTaxRequest(taxRequest);
		System.out.println(request);
	}
}
