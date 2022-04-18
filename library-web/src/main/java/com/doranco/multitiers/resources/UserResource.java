//Classe d'accés au webservice
package com.doranco.multitiers.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.doranco.multitiers.LibraryConstants;
import com.doranco.multitiers.context.RemoteContext;
import com.doranco.multitiers.ejb.interfaces.IUser;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.exceptions.UserNotFoundException;
import com.doranco.multitiers.mappers.UserMapper;
import com.doranco.multitiers.resources.security.AuthentificationChecked;
import com.doranco.multitiers.resources.security.SuperAdminAuthChecked;
import com.doranco.multitiers.utils.Page;
import com.doranco.multitiers.vuesmodeles.CredentialVM;
import com.doranco.multitiers.vuesmodeles.UserVM;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

	IUser iuser = null;

	@Context
	UriInfo uriInfo;

	UserMapper userMapper = UserMapper.INSTANCE; // Ecriture propre à MAPSTRUCT , car en fait on instancie une
													// interface(interdit en java)
	
	

	Logger logger = Logger.getLogger(UserResource.class);

	public UserResource() throws LibraryException {
		super();

		try {

			iuser = (IUser) RemoteContext.getInstance().getInitialContext()
					.lookup("java:global/library-ear/library-ejb/UserBean"); //C'est via cette ligne specifique qu'on demande au moteur ejb d'instancier UserBean
			//C'EST D'AILLEURS UNE SIMPLE UTILISATION DE JNDI
		} catch (Exception e) {
			logger.error("Impossible acceder à la ressource UserResource", e);
			throw new LibraryException();
		}

	}

	/*
	 * @POST public User suscribe(User user) { try { this.iuser.suscribe(user); }
	 * catch (LibraryException e) {
	 * logger.error("Impossible acceder à la ressource UserResource", e); // TODO
	 * Send a custom message to the user }
	 * 
	 * return user; }
	 */

	@GET // EN REQUETANT L'ADRESSE(via POSTMAN, methode GET)
			// http://localhost:8080/library-web/webapi/users ON OBTIENT LA LISTE DES
			// UTILISATEURS
	public Response getAll(@QueryParam("pageSize") int pageSize, @QueryParam("pageNumber") int pageNumber)
			throws Exception {
		Page<UserVM> userVMs = null;

		userVMs = userMapper.entitiesPageToVMsPage(this.iuser.find(pageSize, pageNumber));

		return Response.ok().entity(userVMs).build();// on incorpore dans la response un objet userVM qui pourra etre
													// recupere par la suite
	}

	@Path("{id}") // ici cette partie d'url rajoutée constitue en plus une variable qu'on
					// recupere; ex: http://localhost:8080/library-web/webapi/users/2
	@GET
	@AuthentificationChecked
	public Response getUser(@PathParam("id") long id) throws LibraryException {

		UserVM userVM = null;
		logger.debug("Trying to get a user form its id ==" + id);

		userVM = userMapper.entityToVM(iuser.getUser(id));

		return Response.ok().entity(userVM).build(); // on incorpore dans la response un objet userVM qui pourra etre
														// recupere par la suite
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public UserVM CreateUser(UserVM user) {

		try {
			logger.debug("passing by create User ...");
			iuser.suscribe(userMapper.VMToEntity(user));
		} catch (LibraryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}
	
	
	@GET
	@Path("setadmin/{id}")
	@SuperAdminAuthChecked
	public Response setAsAdmin(@PathParam("id") long id) throws LibraryException {
		
		iuser.setAsAdmin(id);
		
		return Response.ok().header(LibraryConstants.RESPONSE_HEADER_MESSAGE, "L'utilisateur crée avec succés").build();
	}
	

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response authenticate(CredentialVM cred) throws LibraryException {
		try {
			String token = iuser.connect(cred.getUsername(), cred.getPwd(), uriInfo.getAbsolutePath().toString());
			// en fait on recupere un responsebuilder(fabrique de reponse) en faisant un
			// response.ok()
			// qui permet de personnaliser la reponse grace à ses methodes intégrées
			return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();

		} catch (UserNotFoundException e) {
			logger.error("probleme survenu lors de l'authentification", e);
			return Response.status(Status.UNAUTHORIZED).build();
		}

	}
	
	
}
