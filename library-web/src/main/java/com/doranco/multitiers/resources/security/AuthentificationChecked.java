package com.doranco.multitiers.resources.security;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface AuthentificationChecked { //Definition d'une annotation personnalisée de sécurité utilisée dans UserResource.java

}
