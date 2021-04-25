package com.ultimatum.innovata;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/fundbodies")
public class fundService {

    fundBody fundBodyObj = new fundBody();
    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readItems()
    {
        return fundBodyObj.readItems();
    }

    //Insert Details
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertItem(@FormParam("fname") String fundBodyName,
                             @FormParam("femail") String email,
                             @FormParam("iname") String innovatorName,
                             @FormParam("pname") String projectName,
                             @FormParam("famount") String fundAmount,@FormParam("fplan") String fundPlan)
    {
        String output = fundBodyObj.insertItem(fundBodyName, email, innovatorName, projectName, fundAmount, fundPlan);
        return output;
    }

    //updating
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateItem(String fundBodyData)
    {
        //Convert the input string to a JSON object
        JsonObject fundBodyObject = new JsonParser().parse(fundBodyData).getAsJsonObject();
        //Read the values from the JSON object
        String bid = fundBodyObject.get("fid").getAsString();
        String name = fundBodyObject.get("name").getAsString();
        String email = fundBodyObject.get("email").getAsString();
        String fundPlan = fundBodyObject.get("fundPlan").getAsString();
        String output = fundBodyObj.updateItem(bid, name, email, fundPlan);
        return output;
    }

    //deleting
    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteItem(String fundBodyData)
    {
        //Convert the input string to an XML document
        Document doc = Jsoup.parse(fundBodyData, "", Parser.xmlParser());

        //Read the value from the element <ID>
        String fid = doc.select("fid").text();
        String output = fundBodyObj.deleteItem(fid);
        return output;
    }

    //services
    //get one person Details
    @POST
    @Path("/viewFundBodyProfile")
    @Produces(MediaType.TEXT_HTML)
    public String  viewFundBodyDetails(@FormParam("fid") int fundBodyId) {
        return fundBodyObj.viewFundBodyProfile(fundBodyId);
    }


}
