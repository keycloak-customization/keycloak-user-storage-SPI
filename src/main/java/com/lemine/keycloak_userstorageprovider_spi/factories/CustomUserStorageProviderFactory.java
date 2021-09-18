package com.lemine.keycloak_userstorageprovider_spi.factories;


import com.lemine.keycloak_userstorageprovider_spi.providers.CustomUserStorageProvider;
import org.keycloak.component.ComponentModel;
import org.keycloak.component.ComponentValidationException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.UserStorageProviderFactory;
import org.springframework.beans.factory.annotation.Value;


public class CustomUserStorageProviderFactory implements UserStorageProviderFactory<CustomUserStorageProvider> {
    @Value("${provider_name}")
    private String providerName;

    @Override
    public CustomUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new CustomUserStorageProvider(session,model);
    }

    @Override
    public String getId() {
        return "custom_pca_user_storage_provider";
    }

    @Override
    public String getHelpText() {
        return UserStorageProviderFactory.super.getHelpText();
    }

    @Override
    public void validateConfiguration(KeycloakSession session, RealmModel realm, ComponentModel config) throws ComponentValidationException {
        UserStorageProviderFactory.super.validateConfiguration(session, realm, config);
    }
}
