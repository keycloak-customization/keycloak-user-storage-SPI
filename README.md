### About :
A keycloak user storage SPI to import users to the keycloak users storage, with this storage you can import users from any source such as SQL-server, DynamoDB, or another remote server.

**N.B :** You can also do this with your custom keycloak rest api SPI, contact the [maintainer](mailto:mbaillahi@pca.ma) for more details.

### How it works :
- Generate your .jar file with :
```shell
gradlew build
```

- Copy the generated file and paste it in **<keycloak_folder>/standalone/deployments**
- Go to your keycloak admin console in User Federation page, choose **Add provider** and select your provider by the name you gave to the method **getId()** in your factory class, then click save, and it's done!

