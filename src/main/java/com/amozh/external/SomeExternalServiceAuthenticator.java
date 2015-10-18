package com.amozh.external;

import com.amozh.auth.DomainUser;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * Created by Andrii on 19.10.2015.
 */
public class SomeExternalServiceAuthenticator implements ExternalServiceAuthenticator {

    @Override
    public AuthenticatedExternalWebService authenticate(String username, String password) {
        ExternalWebServiceStub externalWebService = new ExternalWebServiceStub();

        // Do all authentication mechanisms required by external web service protocol and validated response.
        // Throw descendant of Spring AuthenticationException in case of unsucessful authentication.
        // For example BadCredentialsException

        // ...
        // ...

        // If authentication to external service succeeded then create authenticated wrapper
        // with proper Principal and GrantedAuthorities.
        // GrantedAuthorities may come from external service authentication or be hardcoded at our layer
        // as they are here with ROLE_DOMAIN_USER
        AuthenticatedExternalWebService authenticatedExternalWebService =
                new AuthenticatedExternalWebService(
                        new DomainUser(username),
                        null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_DOMAIN_USER"));
        authenticatedExternalWebService.setExternalWebService(externalWebService);

        return authenticatedExternalWebService;
    }
}