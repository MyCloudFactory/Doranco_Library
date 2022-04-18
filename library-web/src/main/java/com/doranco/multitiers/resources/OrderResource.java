package com.doranco.multitiers.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import com.doranco.multitiers.LibraryConstants;
import com.doranco.multitiers.context.RemoteContext;
import com.doranco.multitiers.ejb.interfaces.IOrder;
import com.doranco.multitiers.exceptions.LibraryException;
import com.doranco.multitiers.mappers.OrderMapper;
import com.doranco.multitiers.vuesmodeles.OrderVM;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

	@Context
	UriInfo uriInfo;
	
	OrderMapper orderMapper = OrderMapper.INSTANCE; // Ecriture propre à MAPSTRUCT , car en fait on instancie une
	// interface(interdit en java)
	
	Logger logger = Logger.getLogger(OrderResource.class);
	
	IOrder iorder;
	
	public OrderResource() throws LibraryException {
		super();
		// TODO Auto-generated constructor stub
		
		try {

			iorder = (IOrder) RemoteContext.getInstance().getInitialContext()
					.lookup("java:global/library-ear/library-ejb/OrderBean"); //C'est via cette ligne specifique qu'on demande au moteur ejb d'instancier OrderBean
				//C'EST D'AILLEURS UNE SIMPLE UTILISATION DE JNDI
		} catch (Exception e) {
			logger.error("Impossible acceder à la ressource OrderResource", e);
			throw new LibraryException(e);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createOrder(OrderVM orderVM) throws LibraryException {
		
		orderVM = orderMapper.entityToVm(iorder.create(orderMapper.vmToEntity(orderVM)));
		
		URI uri = UriBuilder.fromResource(OrderResource.class).build();
		
		return Response.created(uri).entity(orderVM).header(LibraryConstants.RESPONSE_HEADER_MESSAGE,"Commande enregistrée avec sucés").build();
	}
	
}
