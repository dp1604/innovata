package com.ultimatum.innovata;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ultimatum.model.Innovator;
import com.ultimatum.model.Payment;
import org.json.JSONArray;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/payments")
public class PaymentManager {
    Payment payment = new Payment();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray read() {
        return payment.read();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insert(String data)
    {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

        String pname = obj.get("pname").getAsString();
        String paddress = obj.get("paddress").getAsString();
        String pemail = obj.get("pemail").getAsString();
        String ptel = obj.get("ptel").getAsString();
        String pamount = obj.get("pamount").getAsString();

        payment.insert(pname, paddress, pemail, ptel,pamount);
        return null;
    }

    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String update(String data)
    {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();

        String pid = obj.get("pid").getAsString();
        String pname = obj.get("pname").getAsString();
        String paddress = obj.get("paddress").getAsString();
        String pemail = obj.get("pemail").getAsString();
        String ptel = obj.get("ptel").getAsString();
        String pamount = obj.get("pamount").getAsString();


        payment.update(pid, pname, paddress, pemail,ptel,pamount );
        return null;
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String delete(String data)
    {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
        String pid = obj.get("pid").getAsString();
        payment.delete(pid);
        return null;
    }

    @POST
    @Path("/view")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONArray viewInnovator(String data) {
        JsonObject obj = new JsonParser().parse(data).getAsJsonObject();
        int pid = Integer.parseInt(obj.get("pid").getAsString());
        return payment.getPayment(pid);
    }

}
