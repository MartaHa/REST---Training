package pl.Marta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


public class MotorcycleResources {

    MotorcycleRepository motorcycleRepository = new MotorcycleRepository();


    //create

    @POST
    @Path("motorcycle")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Motorcycle createMotorcycle(Motorcycle moto) {
        System.out.println(moto);
        motorcycleRepository.create(moto);
        return moto;
    }


   //update

    @GET
    @Path("motorcycle/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Motorcycle getMotorcycle(@PathParam("id") int id) {
        return motorcycleRepository.getMotorcycle(id);
    }

    @PUT
    @Path("alien")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Motorcycle updateMotorcycle(Motorcycle moto) {
        System.out.println(moto);
        if (motorcycleRepository.getMotorcycle(moto.getId()).getId() == 0) {
            motorcycleRepository.create(moto);
        } else {

        }
        return moto;
    }




    @DELETE
    @Path("motorcycle/{id}")
    public Motorcycle deleteMotorcycle(@PathParam("id") int id){
        Motorcycle moto = motorcycleRepository.getMotorcycle(id);
        if (moto.getId()!=0) {
            motorcycleRepository.deleteMotorcyle(id);
        }
        return moto;

    }

}
