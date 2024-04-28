package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Path("/mobile")
public class GreetingResource {
//    public Response GreetingResource {

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String hello() {
//
//        return "Hello from Quarkus RES";
//    }

    List<String> mobileList= new ArrayList<>();
//    mobileList.add("iphone 20");


    @GET
    @Produces(MediaType.TEXT_PLAIN)
   // public List<String> getMobileList(){
        public Response getMobileList(){
            return Response.ok(mobileList).build();
        }

        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces(MediaType.TEXT_PLAIN)
//        public void addNewMobile(String mobileName){
        public Response addNewMobile(String mobileName){
         return Response.ok(mobileName).build();
        }


        //relam9?newmobilename=relame 9
    @PUT
    @Path("/{oldmobilename}?{newmobilename}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
//        public void addNewMobile(String mobileName){
    public Response updateMobile(@PathParam ("oldmobilename") String oldmobileName,@QueryParam("newmobilename") String newMobilename){
//        return Response.ok(mobileName).build();
        mobileList=mobileList.stream().map(mobile->{
            if(mobile.equals(oldmobileName)){
                return newMobilename;
            }else{
                return mobile;
            }

        }).collect(Collectors.toList());
        return Response.ok(mobileList).build();

    }
//    need to correct
    @DELETE
    @Path("{mobileTodDelete}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMobile(@PathParam("mobileToDelete") String mobileName){
        boolean isRemoved=mobileList.remove(mobileName);
        if (isRemoved) {
        return Response.ok(mobileList).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
   }

}
