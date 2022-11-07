package com.dm.library.user.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import com.dm.library.user.model.dto.UserCreateCommand;
import com.dm.library.user.model.dto.UserDto;

/**
 * A delegate to be called by the {@link UsersApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-11-07T19:33:36.554507200+01:00[Europe/Berlin]")
public interface UsersApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /users/
     *
     * @param limit  (optional)
     * @param markerUser Email of the last entry on the current page (optional)
     * @return List of users sucesfully retrived (status code 200)
     *         or Bad request (status code 400)
     * @see UsersApi#usersGet
     */
    default ResponseEntity<List<UserDto>> usersGet(Integer limit,
        String markerUser) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"Dominik\", \"lastName\" : \"Wa\", \"personId\" : \"123e4567-e89b-12d3-a456-426614174000\", \"email\" : \"example@test.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /users/{personId} : Deletes the User
     *
     * @param personId  (required)
     * @return ok (status code 200)
     * @see UsersApi#usersPersonIdDelete
     */
    default ResponseEntity<UserDto> usersPersonIdDelete(String personId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"Dominik\", \"lastName\" : \"Wa\", \"personId\" : \"123e4567-e89b-12d3-a456-426614174000\", \"email\" : \"example@test.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /users/{personId} : Reads User to passed personId
     *
     * @param personId  (required)
     * @return ok (status code 200)
     * @see UsersApi#usersPersonIdGet
     */
    default ResponseEntity<UserDto> usersPersonIdGet(String personId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"Dominik\", \"lastName\" : \"Wa\", \"personId\" : \"123e4567-e89b-12d3-a456-426614174000\", \"email\" : \"example@test.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * PUT /users/{personId} : Modifies the User
     *
     * @param personId  (required)
     * @param userDto  (optional)
     * @return ok (status code 200)
     * @see UsersApi#usersPersonIdPut
     */
    default ResponseEntity<UserDto> usersPersonIdPut(String personId,
        UserDto userDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"Dominik\", \"lastName\" : \"Wa\", \"personId\" : \"123e4567-e89b-12d3-a456-426614174000\", \"email\" : \"example@test.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * POST /users/ : Creates a new user
     *
     * @param userCreateCommand  (optional)
     * @return User sucessfully created (status code 201)
     * @see UsersApi#usersPost
     */
    default ResponseEntity<UserDto> usersPost(UserCreateCommand userCreateCommand) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"Dominik\", \"lastName\" : \"Wa\", \"personId\" : \"123e4567-e89b-12d3-a456-426614174000\", \"email\" : \"example@test.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
