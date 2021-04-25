package com.ultimatum.innovata;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ultimatum.model.fundBody;
import org.json.JSONArray;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/fund")
public class fundService {

    fundBody fundBodyObj = new fundBody();
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray read() { return fundBodyObj.read();
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insert(String fundBodyData)
    {
        JsonObject obj = new JsonParser().parse(fundBodyData).getAsJsonObject();

        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String projectname = obj.get("projectname").getAsString();
        String innovatorname = obj.get("innovatorname").getAsString();
        String fundaamount = obj.get("fundamount").getAsString();
        String fundplan = obj.get("fundplan").getAsString();

        fundBodyObj.insert(name, address, email, projectname, innovatorname, fundaamount, fundplan);
        return null;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(String fundBodyData)
    {
        JsonObject obj = new JsonParser().parse(fundBodyData).getAsJsonObject();

        String fid = obj.get("fid").getAsString();
        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String projectname = obj.get("projectname").getAsString();
        String innovatorname = obj.get("innovatorname").getAsString();
        String fundaamount = obj.get("fundamount").getAsString();
        String fundplan = obj.get("fundplan").getAsString();

        fundBodyObj.update(fid, name, address, email, projectname, innovatorname, fundaamount, fundplan);
        return null;
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(String fundBodyData)
    {
        JsonObject obj = new JsonParser().parse(fundBodyData).getAsJsonObject();
        String fid = obj.get("fid").getAsString();
        fundBodyObj.delete(fid);
        return null;
    }

    @POST
    @Path("/view")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray viewInnovator(String fundBodyData) {
        JsonObject obj = new JsonParser().parse(fundBodyData).getAsJsonObject();
        int fid = Integer.parseInt(obj.get("fid").getAsString());
        return fundBodyObj.getInnovator(fid);
    }


}
