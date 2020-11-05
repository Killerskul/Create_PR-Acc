package org.example;

import java.io.IOException;

public class App {

    public static void main ( String[] args ) throws IOException, InterruptedException {

        // Realizando a criação dos Account Templates
        AccountTemplate accountTemplate = new AccountTemplate();
        accountTemplate.createAcc();

        // Realizando a criação das Provisioning Roles
        ProvisioningRole provisioningRole = new ProvisioningRole();
        provisioningRole.createPR();
    }
}
