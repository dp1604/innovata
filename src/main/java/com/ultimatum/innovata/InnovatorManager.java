package com.ultimatum.innovata;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ultimatum.model.Innovator;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/innovators")
public class InnovatorManager {
    Innovator innovator = new Innovator();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray read() {
        return innovator.read();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insert(String data)
    {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String pass = obj.get("pass").getAsString();
        String tel = obj.get("tel").getAsString();
        String gender = obj.get("gender").getAsString();

        innovator.insert(name, address, email, pass, tel, gender);
        return null;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(String data)
    {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

        String iid = obj.get("iid").getAsString();
        String name = obj.get("name").getAsString();
        String address = obj.get("address").getAsString();
        String email = obj.get("email").getAsString();
        String pass = obj.get("pass").getAsString();
        String tel = obj.get("tel").getAsString();
        String gender = obj.get("gender").getAsString();

        innovator.update(iid, name, address, email, pass, tel, gender);
        return null;
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(String data)
    {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
        String iid = obj.get("iid").getAsString();
        innovator.delete(iid);
        return null;
    }

    @POST
    @Path("/view")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray viewInnovator(String data) {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
        int iid = Integer.parseInt(obj.get("iid").getAsString());
        return innovator.getInnovator(iid);
    }
}
