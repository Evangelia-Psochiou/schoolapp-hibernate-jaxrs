package gr.aueb.cf.schoolapp.rest;


import gr.aueb.cf.schoolapp.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as a RESTful web service for managing teacher information.
 */
@Path("/teachers")
public class TeacherRestController {

    @Inject
    private ITeacherService teacherService; // Injecting the teacher service for handling teacher-related operations.

    /**
     * Retrieves a list of teachers by their last name.
     *
     * @param lastname The last name to search for.
     * @return A JSON response containing a list of TeacherDTO objects.
     */
    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachersByLastname(@QueryParam("lastname") String lastname) {
        List<Teacher> teachers;
        try {
            teachers = teacherService.getTeacherByLastname(lastname);
            List<TeacherDTO> teachersDTO = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teachersDTO.add(new TeacherDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname()));
            }
            return Response.status(Response.Status.OK).entity(teachersDTO).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        }
    }

    /**
     * Retrieves information about a specific teacher by their ID.
     *
     * @param teacherId The ID of the teacher to retrieve.
     * @return A JSON response containing a TeacherDTO object.
     */
    @Path("/{teacherId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacher(@PathParam("teacherId") Long teacherId) {
        Teacher teacher;
        try {
            teacher = teacherService.getTeacherById(teacherId);
            TeacherDTO teacherDto = new TeacherDTO(teacher.getId(), teacher.getFirstname(), teacher.getLastname());
            return Response.status(Response.Status.OK).entity(teacherDto).build();
        }catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        }
    }

    /**
     * Adds a new teacher to the system.
     *
     * @param dto      The TeacherDTO object containing teacher information to be added.
     * @param uriInfo  Information about the request URI.
     * @return A JSON response containing the added TeacherDTO object.
     */
    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeacher(TeacherDTO dto, @Context UriInfo uriInfo) {
        try {
            Teacher teacher = teacherService.insertTeacher(dto);
            TeacherDTO teacherDTO = map(teacher);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(teacherDTO.getId())).build())
                    .entity(teacherDTO).build();
        } catch (EntityAlreadyExistsException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Teacher already exists")
                    .build();
        }
    }

    /**
     * Deletes a teacher from the system by their ID.
     *
     * @param teacherId The ID of the teacher to be deleted.
     * @return A JSON response containing the deleted TeacherDTO object.
     */
    @Path("/{teacherId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTeacher(@PathParam("teacherId") Long teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            teacherService.deleteTeacher(teacherId);
            TeacherDTO teacherDTO = map(teacher);
            return Response.status(Response.Status.OK).entity(teacherDTO).build();
        } catch (EntityNotFoundException e1) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Teacher Not Found")
                    .build();
        }
    }

    /**
     * Updates the information of an existing teacher.
     *
     * @param teacherId The ID of the teacher to be updated.
     * @param dto       The TeacherDTO object containing updated teacher information.
     * @return A JSON response containing the updated TeacherDTO object.
     */
    @Path("/{teacherId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("teacherId") Long teacherId, TeacherDTO dto) {
        try {
            dto.setId(teacherId);
            Teacher teacher = teacherService.updateTeacher(dto);
            TeacherDTO teacherDTO = map(teacher);
            return Response.status(Response.Status.OK).entity(teacherDTO).build();
        } catch (EntityNotFoundException e1) {
            return Response.status(Response.Status.NOT_FOUND).entity("Teacher Not Found").build();
        }
    }

    /**
     * Maps a Teacher object to a TeacherDTO object.
     *
     * @param teacher The Teacher object to be mapped.
     * @return A mapped TeacherDTO object.
     */
    private TeacherDTO map(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstname(teacher.getFirstname());
        teacherDTO.setLastname(teacher.getLastname());
        return teacherDTO;
    }
}
