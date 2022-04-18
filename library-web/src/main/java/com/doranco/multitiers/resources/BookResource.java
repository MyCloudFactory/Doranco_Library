package com.doranco.multitiers.resources;

import java.io.IOException;
import java.io.InputStream;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.doranco.multitiers.context.RemoteContext;
import com.doranco.multitiers.ejb.interfaces.IBook;
import com.doranco.multitiers.entity.Book;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.mappers.BookMapper;
import com.doranco.multitiers.resources.security.AdminAuthentificationChecked;
import com.doranco.multitiers.resources.security.AuthentificationChecked;
import com.doranco.multitiers.utils.Page;
import com.doranco.multitiers.vuesmodeles.BookVM;
import com.sun.mail.imap.protocol.Status;

@Path("books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

	
	IBook ibook = null;

	BookMapper bookMapper = BookMapper.INSTANCE; // Ecriture propre à MAPSTRUCT , car en fait on instancie une
	// interface(interdit en java)
	
	Logger logger = Logger.getLogger(UserResource.class);
	
		
	public BookResource() throws LibraryException {
		super();
		
		try {

			ibook = (IBook) RemoteContext.getInstance().getInitialContext()
					.lookup("java:global/library-ear/library-ejb/BookBean"); //C'est via cette ligne specifique qu'on demande au moteur ejb d'instancier BookBean
				//C'EST D'AILLEURS UNE SIMPLE UTILISATION DE JNDI
		} catch (Exception e) {
			logger.error("Impossible acceder à la ressource BookResource", e);
			throw new LibraryException();
		}
	}
		
	//Parametres de la requete POSTMAN: requete POST, dans header mettre le Key/value Content-type/multipart/form-data
	//Dans le body mettre les Key/Value suivantes: file/selectionner un fichier bidon   ET   bookMetaData/{"title":"les bidochons", "ISBN": 452887}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@AdminAuthentificationChecked
	public Response create(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetails,
	//"file" apparait au niveau du body dans POSTMAN en tant que key dans le tableau Key/Value; la value est à fixer
			@FormDataParam("bookMetaData") FormDataBodyPart jsonPart)
	//"bookMetaData" apparait au niveau du body dans POSTMAN en tant que key dans le tableau Key/Value; la value est à fixer au format json
			throws LibraryException, IOException {
		
		jsonPart.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		BookVM bookVM = jsonPart.getValueAs(BookVM.class);
		
		logger.info("file details  >>>>>>>>> " + fileDetails.toString());
		
		byte[] fileContent = IOUtils.toByteArray(fileInputStream);
		
		logger.info("file content length  >>>>>>>>> " + fileContent.length);
		
		bookVM.setContent(fileContent);
		
		bookVM = bookMapper.entityToVM(ibook.createBook(bookMapper.VMToEntity(bookVM)));
		
		URI uri = UriBuilder.fromResource(BookResource.class).build();
		
		return Response.created(uri).entity(bookVM).header("Server Message", "Livre crée avec succés").build();
		
	}
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@AdminAuthentificationChecked
	public Response CreateBook(BookVM bookVM) throws LibraryException {

		bookVM = bookMapper.entityToVM(ibook.createBook(bookMapper.VMToEntity(bookVM)));
		
		URI uri = UriBuilder.fromResource(BookResource.class).build();
		
		return Response.created(uri).entity(bookVM).header("Server Message", "Livre crée avec succés").build();
									
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@AdminAuthentificationChecked
	public Response UpdateBook(BookVM bookVM) throws LibraryException {

		bookVM = bookMapper.entityToVM(ibook.updateBook(bookMapper.VMToEntity(bookVM)));
		return Response.ok().entity(bookVM).build();
		
		/*try {
			logger.debug("passing by update Book ...");
			ibook.updateBook(bookMapper.VMToEntity(book));
		} catch (Exception e) {
			logger.error("Impossible de modifier un livre", e);
			throw new LibraryException();
		}
		return book;*/

	}
		
	@DELETE
	@Path("{id}") 
	@Consumes(MediaType.APPLICATION_JSON)
	@AdminAuthentificationChecked
	public Response DeleteBook(@PathParam("id") long id) throws LibraryException {
		
		ibook.deleteBookById(id);
		return Response.ok().header("Server Message", "Livre supprimé avec succés").build();	
	}

	
	@Path("{id}") 
	@GET
	@AuthentificationChecked
	public Response getBookByid(@PathParam("id") long id) throws LibraryException {

		BookVM bookVM = null;
		logger.debug("Trying to get a book from its id ==" + id);

		bookVM = bookMapper.entityToVM(ibook.findById(id));

		return Response.ok().entity(bookVM).build(); // on incorpore dans la response un objet userVM qui pourra etre
														// recupere par la suite
	}
	

	@Path("title/{match}") 
	@GET
	@AuthentificationChecked
	public Response getBookByTitle(@PathParam("match") String str, @PathParam("pageSize") int pageSize, @PathParam("pageNumber") int pageNumber) throws LibraryException {

		Page<BookVM> booksVM = null;
		logger.debug("Trying to get a book from its title ==" + str);

		booksVM = bookMapper.entitiesPageToVMsPage(ibook.findBookByTitle(str, pageSize, pageNumber));

		//Ligne suivante plus utile car on a changé List<BookVM> en Page<BookVM> (Conservé car instructif)
		//GenericEntity<List<BookVM>> entity = new GenericEntity<List<BookVM>>(booksVM) {};
		//During runtime, type erasure removes generic type information. For example an object of type List<Order> appears to contain a raw List<?> at runtime.
		//In JAX-RS, wrapping a response object (like List<Order>) in Response, removes the generic type information. Because of that, some MessageBodyWriter
		//implementations cannot be selected without the generic information. Wrapping the entity in GenericEntity preserves the generic type.
		
		return Response.ok().entity(booksVM).build(); // on incorpore dans la response un objet userVM qui pourra etre
														// recupere par la suite
	}
	
	
	//A tester dans POSTMAN avec key/value pour idUser et idBook
	@GET
	@Path("viewing")
	public Response getViewingBook(@QueryParam("idUser") long idUser, @QueryParam("idBook") long idBook)
	throws LibraryException {
		
		Book book = ibook.findById(idBook, idUser);
		BookVM bookvm = bookMapper.entityToVM(book);
		return Response.status(javax.ws.rs.core.Response.Status.FOUND).entity(bookvm).build();
		
	}
	
}
