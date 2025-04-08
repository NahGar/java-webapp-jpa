package org.ngarcia.webapp.repositories;

import jakarta.inject.Qualifier;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.*;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({CONSTRUCTOR, METHOD, TYPE, PARAMETER, FIELD})
public @interface RepositoryJpa {
}
