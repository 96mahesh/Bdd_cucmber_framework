package com.testperform.web.bdd.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.testperform.web.bdd.enums.AuthorType;
import com.testperform.web.bdd.enums.CategoryType;

//This is an Custom Annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameworkAnnotation {

	// This is not a method
	public AuthorType[] author();

	// public String[] category();
	public CategoryType[] category();

}
