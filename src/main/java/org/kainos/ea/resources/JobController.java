package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.JobService;
import org.kainos.ea.client.FailedToGetJobSpecException;
import org.kainos.ea.client.JobSpecDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.kainos.ea.client.FailedToGetAllJobsException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Api("Job Role API")
@Path("/api")
public class JobController {
    private JobService jobService = new JobService();

    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecById(@PathParam("id") int id) {
        try {
            return Response.ok(jobService.getJobSpecById(id)).build();

        } catch (FailedToGetJobSpecException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();

        } catch (JobSpecDoesNotExistException e) {
            System.err.println(e.getMessage());

            return Response.status(Response.Status.BAD_REQUEST).build();
          
    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllJobs() {
        try {
            return Response.ok(jobService.getAllJobs()).build();
        } catch (FailedToGetAllJobsException e) {
            System.err.println(e.getMessage());

            return Response.serverError().build();
        }
    }
}
