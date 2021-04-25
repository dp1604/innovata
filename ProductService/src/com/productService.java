package com;

import model.product; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/products")

public class productService {

	product productObj = new product(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return productObj.readItems(); 
	 }
	
	//Insert Data
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("proCode") String proCode1,@FormParam("rid") String rid1,@FormParam("pname") String pname1,@FormParam("price") String price1,@FormParam("descri") String descri1) 
	{ 
		 String output = productObj.insertItem(proCode1,rid1,pname1,price1,descri1); 
		 return output; 
	}
	
	//update
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String productData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String proCode = productObject.get("proCode").getAsString(); 
		 String rid = productObject.get("rid").getAsString(); 
		 String pname = productObject.get("pname").getAsString(); 
		 String price = productObject.get("price").getAsString(); 
		 String descri = productObject.get("descri").getAsString();
		 String output = productObj.updateItem(proCode, rid,  pname, price, descri);
		 return output; 
	}
	
	//delete
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteItem(String productData) 
		{ 
			//Convert the input string to an XML document
			 Document doc = Jsoup.parse(productData, "", Parser.xmlParser()); 
			 
			//Read the value from the element <itemID>
			 String proCode = doc.select("proCode").text(); 
			 String output = productObj.deleteItem(proCode); 
			 return output; 
		}
	
}
