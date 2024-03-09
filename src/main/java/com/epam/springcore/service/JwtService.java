package com.epam.springcore.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    /**
     * Generates a JWT token for the given username.
     *
     * @param userName The username for which the token will be generated.
     * @return The generated JWT token.
     */
    String generateToken(String userName);

    /**
     * Creates a JWT token with custom claims for the specified username.
     *
     * @param claims   The custom claims to be included in the token.
     * @param username The username associated with the token.
     * @return The created JWT token.
     */
    String createToken(Map<String, Object> claims, String username);

    /**
     * Retrieves the secret key used for signing JWT tokens.
     *
     * @return The secret key used for signing.
     */
    SecretKey getSignKey();

    /**
     * Extracts the username from the provided JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    String extractUsername(String token);

    /**
     * Extracts the expiration date from the provided JWT token.
     *
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date extracted from the token.
     */
    Date extractExpiration(String token);

    /**
     * Extracts a specific claim from the provided JWT token.
     *
     * @param token          The JWT token from which to extract the claim.
     * @param claimsResolver The function to resolve the desired claim from the token's claims.
     * @return The extracted claim.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Extracts all claims from the provided JWT token.
     *
     * @param token The JWT token from which to extract all claims.
     * @return All claims extracted from the token.
     */
    Claims extractAllClaims(String token);

    /**
     * Checks if the provided JWT token has expired.
     *
     * @param token The JWT token to be checked for expiration.
     * @return True if the token is expired, false otherwise.
     */
    Boolean isTokenExpired(String token);

    /**
     * Validates the provided JWT token against the UserDetails.
     *
     * @param token       The JWT token to be validated.
     * @param userDetails The UserDetails object to validate against.
     * @return True if the token is valid for the given UserDetails, false otherwise.
     */
    Boolean validateToken(String token, UserDetails userDetails);

}
