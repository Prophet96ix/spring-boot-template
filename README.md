# spring-boot-template

Template project for a quickstart with Spring Boot.

## What you'll get:

- pre-configured Spring Boot
- a demo Rest-Controller
- CORS-policy configured to allow traffic from everywhere
- pre-configured Spring Security with JWT-Token authorization to secure requests
- very basic user-management
- H2 in-memory database
- Dockerfile with ready-to-use deploy-script

## How to use

Use this repo as a template to speed up your own development of new RESTful APIs with Spring Boot.

You can add and customize everything to your needs.

To start the Application in Docker run the deploy-script:

```sh deploy.sh```

### notes

The easiest way to get this running on a server is to git clone your copy of the repo to your server and run the deploy-script from there.
Otherwise you could also ssh scp your files to the server.

Please keep in mind to change your token-secret
