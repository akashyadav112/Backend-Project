package com.Ecom.AuthService.Security.Oauth2;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
@Configuration
@EnableWebSecurity
public class OathConfig {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public OathConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        // for enabling Open id connect
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());
        // if anything goes wrong, authentication is un successful, re-direct to this page
        http.exceptionHandling((exceptions)->
                exceptions.defaultAuthenticationEntryPointFor(
                    new LoginUrlAuthenticationEntryPoint("/login"),
                    new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                )
        )
                // if no issue, then just give the jwt token
                .oauth2ResourceServer((resourceServer)->
                        resourceServer.jwt(Customizer.withDefaults()));

        return http.build();
    }

    /**
     * This is for the basic authentication, suppose you do not want to do Oauth then simply
     * just do normal credentials based authentication
     * how we can do normal -> http:localhost:9091/auth/login
     * for Oauth -> http:localhost:9091/login -> /login is fundamental endpont porvided by oath,
     * now since we are using bean here for simple cred based authentication, bean in SecurityConfig
     * should be commeneted out, 2 beans are not required at a time when we are using Ouath(base + oauth)
     * but if we are using only custom api we should not comment SecurityConfig
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityChain(HttpSecurity http) throws Exception {
        http
                // every request needs to be authenticated..
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
        /**
         *  Form login handles the redirect to the login page
         *  from the authorization server filter chain
         */
            .formLogin(Customizer.withDefaults());
        return http.build();
    }

    /**
     * in order to make an Oauth server, and verify through it, we need to register the clients who will use it
     * e.g Lex is going login via google then it needs to register itself in google oAuth server
     * here productService will register itself as a client.
     * while registering the client we need to give client id, client secret so that client authentication can happen
     * i.e whether the call is comming from lex or not in Outh2 server.
     * the problem with this bean is that we have to do manually for every client and do it in-memory db, so
     * we will set up its db part so that as many client can register and we can store them in db
     * for that we will comment this out, Client model(how client will register), Authorization db(how authroixation will happen),
     * AuthorizationConcent( do you accept to allow contacts to lex)
     **/
//    @Bean
//    public RegisteredClientRepository registeredClientRepository() {
//        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                /*how client will authenticate that we need to set so client id, client secret and name is there*/
//                .clientId("productservice")
//                .clientSecret(bCryptPasswordEncoder.encode("passwordofproductserviceclient")) // this password can be anything, its basically how the client will identify itself
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                /*how to authneticate and authorize the user who are comming via client, and google should respond back with these three things for authenticate/authorize user*/
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // auth token
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/oidc-client")
//                .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                // how we are doing authentication and authorization and fundamental logic of it using open id and user profile base
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//                /*currently we are storing in the server memory but as soon as the data grows we need to add db for it*/
//        return new InMemoryRegisteredClientRepository(oidcClient);
//    }
    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }
}
