package com.doranco.multitiers.resources;

import java.net.URI;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import com.doranco.multitiers.context.RemoteContext;
import com.doranco.multitiers.ejb.interfaces.IViewing;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.mappers.ViewingMapper;
import com.doranco.multitiers.vuesmodeles.ViewingVM;

@Path("viewings")
@Produces(MediaType.APPLICATION_JSON)
public class ViewingResource {
	
	IViewing iviewing;

	Logger logger = Logger.getLogger(ViewingResource.class);

	ViewingMapper mapper = ViewingMapper.INSTANCE;
	
	
	
	public ViewingResource() throws LibraryException {
		super();

		

		try {

			iviewing = (IViewing) RemoteContext.getInstance().getInitialContext()
					.lookup("java:global/library-ear/library-ejb/ViewingBean"); // C'est via cette ligne specifique
																				// qu'on demande au moteur ejb
																				// d'instancier ViewingBean
			// C'EST D'AILLEURS UNE SIMPLE UTILISATION DE JNDI
		} catch (Exception e) {
			logger.error("Impossible acceder à la ressource ViewingResource", e);
			throw new LibraryException();
		}

	}

	
	//Tester sous POSTMAN en format json (header content-type application/json) en donnant l'idbook , la duration
	@POST
	public Response create(ViewingVM vm) throws LibraryException {
		
		vm = mapper.entityToVM(iviewing.create(mapper.vmToEntity(vm)));
		
		URI uri = UriBuilder.fromResource(ViewingResource.class).build();
		
		return Response.created(uri).entity(vm).header("Server message", "Demande de consultation enregistrée").build();
	}

}
