package com.ultimatum.innovata;

import com.ultimatum.model.buyer;
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/buyers")
public class buyerService {

	buyer buyerObj = new buyer(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return buyerObj.readItems(); 
	 }
	
	//Insert Details
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("name") String name1, 
	 @FormParam("descri") String descri1,@FormParam("address") String address1,@FormParam("email") String email1,@FormParam("pwd") String pwd1,@FormParam("tp") String tp1,@FormParam("gender") String gender1) 
	{ 
		 String output = buyerObj.insertItem(name1, descri1,address1,email1,pwd1,tp1,gender1); 
		 return output; 
	}
	
	//updating
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String buyerData) 
	{ 
		//Convert the input string to a JSON object 
		 JsonObject buyerObject = new JsonParser().parse(buyerData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String bid = buyerObject.get("bid").getAsString(); 
		 String name = buyerObject.get("name").getAsString(); 
		 String descri = buyerObject.get("descri").getAsString(); 
		 String address = buyerObject.get("address").getAsString(); 
		 String email = buyerObject.get("email").getAsString(); 
		 String pwd = buyerObject.get("pwd").getAsString();
		 String tp = buyerObject.get("tp").getAsString();
		 String gender = buyerObject.get("gender").getAsString();
		 String output = buyerObj.updateItem(bid, name, descri, address, email, pwd, tp, gender);
		 return output; 
	}
	
	//deleting
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String buyerData) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(buyerData, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String bid = doc.select("bid").text(); 
		 String output = buyerObj.deleteItem(bid); 
		 return output; 
	}
	
		//services
		//get one person Details
		@POST
		@Path("/viewBuyerProfile")
		@Produces(MediaType.TEXT_HTML)
		public String  ViewBuyerDetails(@FormParam("bid") int buyerId) 
		{
			return buyerObj.viewBuyerProfile(buyerId);
		}
		
		
		
		//services
		//purchaseOne
		@POST
		@Path("/purchaseProducts")
		@Produces(MediaType.TEXT_HTML)
		public String  purchasePro(@FormParam("bid") int buyerId) 
		{
			return buyerObj.purchaseProducts(buyerId);
		}
	
		//services
		//get sold Details
		@POST
		@Path("/viewBuyiedProductReport")
		@Produces(MediaType.TEXT_HTML)
		public String  ViewBuyied(@FormParam("status") String status1) 
		{
			return buyerObj.viewBuyiedProductReport(status1);
		}
		
}
