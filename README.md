### About ❗ 💡:
A keycloak user storage SPI to import users to the keycloak users storage, with this storage you can import users from any source such as SQL-server, DynamoDB, or another remote server.

**N.B :** You can also do this with your custom keycloak rest api SPI, contact the [maintainer](mailto:baillahiamine1@gmail.com) for more details.

### How it works 🤔 :
- Generate your .jar file with :
```shell
gradlew build
```

- Copy the generated file and paste it in **<keycloak_folder>/standalone/deployments**
- Go to your keycloak admin console in User Federation page, choose **Add provider** and select your provider by the name you gave to the method **getId()** in your factory class, then click save, and it's done!
 
Besides, there are some quite important methods used in this code that you really need to know about : 

- **isConfiguredFor() :** called by the runtime to determine if a specific credential type is configured for the user.
- **supportsCredentialType() :** method returns whether validation is supported for a specific credential type
- **isValid() :** is responsible for validating passwords.

Some extra implemented interfaces : 

- **CredentialInputValidator :** We've implemented this interface to be able to control and validate different types of credentials such as passwords.
- **UserLookupProvider :** implemented to log in with users from different sources.

### How to undeploy your SPI 🤔  : 
to undeploy your SPI just remove the pasted .jar file from the **deployments** keycloak folder.

### Want to contribute ?
To contribute to this project, just clone it with :

````shell
git clone https://github.com/keycloak-customization/keycloak-user-storage-SPI
````

then create your custom PR as a new branch, all contributions are most welcome 😉.