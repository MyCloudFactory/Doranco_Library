package com.doranco.multitiers.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.doranco.multitiers.LibraryConstants;
import com.doranco.multitiers.context.RemoteContext;
import com.doranco.multitiers.ejb.interfaces.INote;
import com.doranco.multitiers.entity.Note;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.mappers.NoteMapper;
import com.doranco.multitiers.resources.security.AuthentificationChecked;
import com.doranco.multitiers.vuesmodeles.NoteVM;

@Path("notes")
@Produces(MediaType.APPLICATION_JSON)
public class NoteRessource {

	INote inote = null;

	@Context
	UriInfo uriInfo;
	
	NoteMapper noteMapper = NoteMapper.INSTANCE; // Ecriture propre à MAPSTRUCT , car en fait on instancie une
	// interface(interdit en java)
	
	Logger logger = Logger.getLogger(NoteRessource.class);
	
	
	
	public NoteRessource() throws LibraryException {
		super();

		try {

			inote = (INote) RemoteContext.getInstance().getInitialContext()
					.lookup("java:global/library-ear/library-ejb/NoteBean"); //C'est via cette ligne specifique qu'on demande au moteur ejb d'instancier BookBean
				//C'EST D'AILLEURS UNE SIMPLE UTILISATION DE JNDI
		} catch (Exception e) {
			logger.error("Impossible acceder à la ressource NoteResource", e);
			throw new LibraryException(e);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//@SecurityChecked
	public Response CreateNote(NoteVM noteVM) throws LibraryException {
		
		Note note = noteMapper.VMToEntity(noteVM);
		
		noteVM = noteMapper.entityToVM(inote.createNote(note));

		URI uri = UriBuilder.fromResource(NoteRessource.class).build();
		
		return Response.created(uri).entity(noteVM).header(LibraryConstants.RESPONSE_HEADER_MESSAGE, "Note crée avec succés").build();
		
	}
	
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@AuthentificationChecked
	public Response DeleteNote(@QueryParam("userId") int userId, @QueryParam("bookId") long bookId)throws LibraryException {

		inote.deleteNote(userId, bookId);

		return Response.ok().header(LibraryConstants.RESPONSE_HEADER_MESSAGE, "Suppression Note avec succés").build();
		
	}
	
	
		
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@AuthentificationChecked
	public Response UpdateNote(NoteVM noteVM) throws LibraryException {

		inote.updateNote(noteMapper.VMToEntity(noteVM));
		
		return Response.ok().build();

	}
	
	
	
	@Path("{id}") 
	@GET
	@AuthentificationChecked
	public Response NotebyBookId(@PathParam("id") long id) throws LibraryException {

		NoteVM noteVM = null;
		logger.debug("Trying to get a note from book id ==" + id);
		
		noteVM = noteMapper.entityToVM(inote.NotebyBookId(id));

		System.out.println("note = "+ inote.NotebyBookId(id).toString());
		

		return Response.ok().entity(noteVM).build(); // on incorpore dans la response un objet userVM qui pourra etre
		

	}
	
	
}
