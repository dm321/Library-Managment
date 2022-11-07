package com.dm.library.user.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-07T19:33:36.554507200+01:00[Europe/Berlin]")
@Controller
@RequestMapping("${openapi.libraryManagment.base-path:/library-managment/1.0.0}")
public class UsersApiController implements UsersApi {

	@Autowired
    private final UsersApiDelegate delegate;

    public UsersApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) UsersApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new UsersApiDelegate() {});
    }

    @Override
    public UsersApiDelegate getDelegate() {
        return delegate;
    }

}
