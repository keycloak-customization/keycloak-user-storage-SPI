package com.lemine.keycloak_userstorageprovider_spi.providers;

import com.lemine.keycloak_userstorageprovider_spi.dto.User;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

import java.util.HashMap;
import java.util.Map;

public class CustomUserStorageProvider implements UserStorageProvider, CredentialInputValidator, UserLookupProvider {
    private KeycloakSession session;
    private ComponentModel model;
    protected Map<String, UserModel> loadedUsers = new HashMap<>();
    private HashMap<String, User> users=new HashMap<>();
    public CustomUserStorageProvider(KeycloakSession session, ComponentModel model) {
        this.session=session;
        this.model=model;

        users.put("soufiane",new User("soufiane","123"));
        users.put("ammar",new User("ammar","102938"));
        users.put("aziz",new User("aziz","11002299"));
    }

    @Override
    public void close() {

    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return credentialType.equals(PasswordCredentialModel.TYPE);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        try {
            String password = users.get(user.getUsername()).getPassword();
            return credentialType.equals(PasswordCredentialModel.TYPE) && password != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        System.out.println("given credentials : \nusername : "+user.getUsername()+", password : "+credentialInput.getChallengeResponse());
        if (!supportsCredentialType(credentialInput.getType())) return false;
        try {
            // here we can add a database treatment instead of a static hashmap
            String password = users.get(user.getUsername()).getPassword();
            if (password == null) return false;
            return password.equals(credentialInput.getChallengeResponse());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        StorageId storageId = new StorageId(id);
        String username = storageId.getExternalId();
        return getUserByUsername(username, realm);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        UserModel adapter = loadedUsers.get(username);
        if (adapter == null) {
            User user = users.get(username);
            if (user != null) {
                adapter = createAdapter(realm, username);
                loadedUsers.put(username, adapter);
            }
        }
        return adapter;
    }
    private UserModel createAdapter(RealmModel realm, String username) {
        return new AbstractUserAdapter(this.session, realm, this.model) {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        return null;
    }
}
