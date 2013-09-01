package org.crabapple.binding.serialize;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

import com.hp.wwops.ecommerce.service.entity.wstax3.AuthenticationType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Calling_ApplicationType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Currency_CodesType;
import com.hp.wwops.ecommerce.service.entity.wstax3.CustomerHeaderType;
import com.hp.wwops.ecommerce.service.entity.wstax3.CustomerLineType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Document_DatesType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Document_HeaderType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Document_LineType;
import com.hp.wwops.ecommerce.service.entity.wstax3.EventType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Logistic_InfoType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Net_AmountType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Product_Material_PartsType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Ship_FromAnonType;
import com.hp.wwops.ecommerce.service.entity.wstax3.Ship_ToType;
import com.hp.wwops.ecommerce.service.entity.wstax3.TaxRequest;
import com.hp.wwops.ecommerce.service.entity.wstax3.TransactionAnonType;
import com.hp.wwops.ecommerce.service.entity.wstax3.TransactionType;
import com.hp.wwops.ecommerce.service.entity.wstax3.VendorHeaderType;
import com.hp.wwops.ecommerce.service.entity.wstax3.VendorLineType;

public class TaxRequestUtils {
	
	/**
	 * Initialise the TaxRequest
	 * @return
	 */
	public static TaxRequest initializeTaxRequest(){
		TaxRequest taxRequest = new TaxRequest();
		// Authentication
		taxRequest.setAuthentication(new AuthenticationType());
		taxRequest.getAuthentication().setAppl_Id("pdapi");
		taxRequest.getAuthentication().setAppl_Pw("pdapi");
		
		// Calling_Application
		taxRequest.setCalling_Application(new Calling_ApplicationType());
		taxRequest.getCalling_Application().setAppl_Name("WSTAX_TEST");
		taxRequest.getCalling_Application().setAppl_Type("");
		taxRequest.getCalling_Application().setAppl_Version("3.0");
		taxRequest.getCalling_Application().setClient_Ident("wstax");
		// Event type
		taxRequest.setEvent(new EventType());
		taxRequest.getEvent().setEvent_Type("PDAPI_order");
		
		// Document Header
		taxRequest.setDocument_Header(new Document_HeaderType());
		taxRequest.getDocument_Header().setCurrency_Codes(new Currency_CodesType());
		taxRequest.getDocument_Header().getCurrency_Codes().setDoc_Currency("CAD");
		taxRequest.getDocument_Header().setCustomer(new CustomerHeaderType());
		taxRequest.getDocument_Header().getCustomer().setCustomer_Number("1234234");
//		taxRequest.getDocument_Header().getCustomer().setMycomp_If_Cust("");
		taxRequest.getDocument_Header().setVendor(new VendorHeaderType());
		taxRequest.getDocument_Header().getVendor().setMycomp_If_Vend("CA00");
		taxRequest.getDocument_Header().setDocument_Dates(new Document_DatesType());
		taxRequest.getDocument_Header().getDocument_Dates().setDoc_Create_Date(Calendar.getInstance());
		taxRequest.getDocument_Header().setTransaction(new TransactionType());
		taxRequest.getDocument_Header().getTransaction().setTrans_Indicator("o");
		
		// Document Line
		Document_LineType line = new Document_LineType();
		line.setDoc_Line_No(BigInteger.valueOf(1L));
		line.setDoc_Line_Quan(BigDecimal.valueOf(1));
		line.setCustomer(new CustomerLineType());
		line.getCustomer().setShip_To(new Ship_ToType());
		line.getCustomer().getShip_To().setShip_To_Country("CA");
		line.getCustomer().getShip_To().setShip_To_Statec("ON");
		line.setVendor(new VendorLineType());
		line.getVendor().setShip_From(new Ship_FromAnonType());
		line.getVendor().getShip_From().setShip_Frm_Country("CA");
		line.getVendor().getShip_From().setShip_Frm_Statec("AB");
		line.setNet_Amount(new Net_AmountType());
		line.getNet_Amount().setNet_Amount_Doc(BigDecimal.valueOf(100.0));
		line.setTransaction(new TransactionAnonType());
		line.getTransaction().setTrans_Type("GS");
		line.setLogistic_Info(new Logistic_InfoType());
		line.getLogistic_Info().setDelivery_Terms("DDP");
		line.setProduct_Material_Parts(new Product_Material_PartsType());
		line.getProduct_Material_Parts().setProduct_Cat("44000000");
		taxRequest.setLineItemTaxs(new Document_LineType[]{line});
		return taxRequest;
	}


}
