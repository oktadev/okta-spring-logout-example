# OpenID Connect Logout Options with Spring Boot
  
This repository contains an example Spring Boot application that is used to demonstrate the various logout options with Spring Security and OIDC. 

Please read [OpenID Connect Logout Options with Spring Boot][blog-post] to see how this app was created.

**Prerequisites:** 
* [Java 17+](https://adoptium.net/)

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage, and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Spring Boot Example

To install this example, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-spring-logout-example.git
cd okta-spring-logout-example
```

### Create a Web Application in Okta

Log in to your Okta Developer account (or [sign up](https://developer.okta.com/signup/) if you don't have an account).

1. From the **Applications** page, choose **Add Application**.
2. On the Create New Application page, select **Web**.
3. Give your app a memorable name, add `http://localhost:8080/login/oauth2/code/okta` as a Login redirect URI, select **Refresh Token** (in addition to **Authorization Code**), and click **Done**.

Copy the issuer (found under **API** > **Authorization Servers**), client ID, and client secret into the `application.properties` of the `api-gateway` and `car-service` projects.

```properties
spring.security.oauth2.client.provider.okta.issuer-uri=https://{yourOktaDomain}/oauth2/default
spring.security.oauth2.client.registration.okta.client-id=$clientId
spring.security.oauth2.client.registration.okta.client-secret=$clientSecret
```

Then, run all the projects with `./mvnw` in separate terminal windows. You should be able to navigate to `http://localhost:8080` to see the application.

## Links

These examples uses the following open source libraries:
 
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [OpenJDK](https://openjdk.java.net/)

## Help

Please post any questions as comments on the example's [blog post][blog-post], or on the [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).

[blog-post]: https://developer.okta.com/blog/2020/03/27/spring-oidc-logout-options
