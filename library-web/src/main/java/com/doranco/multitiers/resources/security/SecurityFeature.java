package com.doranco.multitiers.resources.security;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

@Provider 
public class SecurityFeature implements DynamicFeature {

	@Override // verification si une annotation est presente pour une ressource donnée
	public void configure(ResourceInfo ri, FeatureContext ctx) {
		if (ri.getResourceMethod().isAnnotationPresent(AuthentificationChecked.class)) {
			// enregistrement du filtre réél
			ctx.register(AuthentificationCheckedInterceptor.class);
		}
		
		if (ri.getResourceMethod().isAnnotationPresent(AdminAuthentificationChecked.class)) {
			// enregistrement du filtre réél
			ctx.register(AdminAuthentificationCheckedInterceptor.class);
		}
		
		if (ri.getResourceMethod().isAnnotationPresent(SuperAdminAuthChecked.class)) {
			// enregistrement du filtre réél
			ctx.register(SuperAdminAuthCheckedInterceptor.class);
		}
	}

}
