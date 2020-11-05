package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

// Classe de criação dos Account Template, os processos que ocorrem aqui são basicamente identicos ao da criação de PR
public class AccountTemplate {

    public void createAcc () throws IOException, InterruptedException {
        // URL da IDM
        String IDM_Url = "";

        // Realizando leitura da carga .csv
        Scanner leituraCarga = new Scanner(new File("src/main/java/org/example/Carga.csv"));

        while (leituraCarga.hasNextLine()) {

            // Leitura da linha completa da carga
            String linhaCompleta = leituraCarga.nextLine();
            // Divisão da linha em um array
            String[] linhaArray = linhaCompleta.split(",");
            // String formatada com o nome do Account Template
            String nomeAcc = linhaArray[0] + linhaArray[1];

            // XML de criação das Account Template
            String XML = String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdl=\"http://tews6/wsdl\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <wsdl:TaskContext>\n" +
                    "         <wsdl:admin_id>~</wsdl:admin_id>\n" +
                    "         <wsdl:admin_password>~</wsdl:admin_password>\n" +
                    "      </wsdl:TaskContext>\n" +
                    "      <wsdl:CreateActiveDirectoryAccountTemplate>\n" +
                    "         <wsdl:CreateActiveDirectoryAccountTemplateSearch>\n" +
                    "            <wsdl:CreateNew>true</wsdl:CreateNew>\n" +
                    "            <wsdl:SelectedEndPointType>ActiveDirectory</wsdl:SelectedEndPointType>\n" +
                    "         </wsdl:CreateActiveDirectoryAccountTemplateSearch>\n" +
                    "         <wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateAccountTemplateTab>\n" +
                    "            <wsdl:_PCT_ACCOUNT_TEMPLATE_NAME_PCT_>%s</wsdl:_PCT_ACCOUNT_TEMPLATE_NAME_PCT_>\n" +
                    "            <wsdl:description>Teste</wsdl:description>\n" +
                    "            <wsdl:comments>Testing</wsdl:comments>\n" +
                    "            <wsdl:usingStrongSynchronization>?</wsdl:usingStrongSynchronization>\n" +
                    "         </wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateAccountTemplateTab>\n" +
                    "         <wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateAccountTab>\n" +
                    "            <wsdl:accountID>%%AC%%</wsdl:accountID>\n" +
                    "            <wsdl:ntAccountId>%%AC%%</wsdl:ntAccountId>\n" +
                    "            <wsdl:accountName>%%UN%%</wsdl:accountName>\n" +
                    "            <wsdl:_PCT_PASSWORD_PCT_>%%P%%</wsdl:_PCT_PASSWORD_PCT_>\n" +
                    "            <wsdl:pwdLastSet>false</wsdl:pwdLastSet>\n" +
                    "            <wsdl:optionsWord2>false</wsdl:optionsWord2>\n" +
                    "            <wsdl:pwdNeverExpireBit>false</wsdl:pwdNeverExpireBit>\n" +
                    "            <wsdl:ADSObjectClass>user</wsdl:ADSObjectClass>\n" +
                    "         </wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateAccountTab>\n" +
                    "         <wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateUserTab>\n" +
                    "            <wsdl:displayName>%%UN%%</wsdl:displayName>\n" +
                    "            <wsdl:firstName>%%UF%%</wsdl:firstName>\n" +
                    "            <wsdl:middleInitials>%%UMI%%</wsdl:middleInitials>\n" +
                    "            <wsdl:lastName>%%UL%%</wsdl:lastName>\n" +
                    "            <wsdl:ADSdescription>%%UD%%</wsdl:ADSdescription>\n" +
                    "            <wsdl:title>%%UT%%</wsdl:title>\n" +
                    "            <wsdl:office>%%UO%%</wsdl:office>\n" +
                    "            <wsdl:department>%%UDEPT%%</wsdl:department>\n" +
                    "            <wsdl:company>%%UCOMP%%</wsdl:company>\n" +
                    "         </wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateUserTab>\n" +
                    "         <wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateContactTab>\n" +
                    "            <wsdl:street>%%USA%%</wsdl:street>\n" +
                    "            <wsdl:city>%%UC%%</wsdl:city>\n" +
                    "            <wsdl:state>%%US%%</wsdl:state>\n" +
                    "            <wsdl:zip>%%UPC%%</wsdl:zip>\n" +
                    "            <wsdl:country>%%UCOUNTRY%%</wsdl:country>\n" +
                    "            <wsdl:email>%%UE%%</wsdl:email>\n" +
                    "            <wsdl:webPage>%%UHP%%</wsdl:webPage>\n" +
                    "            <wsdl:telephone>%%UP%%</wsdl:telephone>\n" +
                    "            <wsdl:pagerPhone>%%UPAGE%%</wsdl:pagerPhone>\n" +
                    "            <wsdl:mobilePhone>%%UMP%%</wsdl:mobilePhone>\n" +
                    "            <wsdl:faxPhone>%%UFAX%%</wsdl:faxPhone>\n" +
                    "         </wsdl:CreateActiveDirectoryAccountTemplateActiveDirectoryAccountTemplateContactTab>\n" +
                    "      </wsdl:CreateActiveDirectoryAccountTemplate>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>\n", nomeAcc);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(IDM_Url))
                    .header("Content-Type", "text/xml; charset=UTF-8")
                    .header("SOAPAction", "CreateActiveDirectoryAccountTemplateSoap")
                    .POST(HttpRequest.BodyPublishers.ofString(XML))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        }
    }
}
