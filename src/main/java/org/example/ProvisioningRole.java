package org.example;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ProvisioningRole {

    public void createPR () throws IOException, InterruptedException {
        // Definindo uma string para a URL
        String IDMUrl = "";

        //Lendo os dados da Carga.csv
        Scanner leituraCarga = new Scanner(new File("src/main/java/org/example/Carga.csv"));

        // While-loop para fazer diversos request a URL
        while (leituraCarga.hasNextLine()) {

            // Pegando linha por linha da carga
            String linhaCompleta = leituraCarga.nextLine();

            // Transformando cada linha em um array
            String[] linhaArray = linhaCompleta.split(",");

            // Variaveis volateis que recebem os dados da carga
            String Nome = linhaArray[0];
            String Desc = linhaArray[1];
            String Comments = linhaArray[2];
            String Depart = linhaArray[3];
            String CF01 = linhaArray[4];
            String CF02 = linhaArray[5];
            String CF03 = linhaArray[6];
            String CF04 = linhaArray[7];
            String CF05 = linhaArray[8];
            String nomeAcc = linhaArray[0] + linhaArray[1];

            // Corpo da ação que vamos realizar no IDM
            String XML = String.format("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsdl=\"http://tews6/wsdl\">\n" +
                    "        <soapenv:Header/>\n" +
                    "        <soapenv:Body>\n" +
                    "            <wsdl:TaskContext>\n" +
                    "            <wsdl:admin_id>~/wsdl:admin_id>\n" +
                    "            <wsdl:admin_password>~</wsdl:admin_password>\n" +
                    "            </wsdl:TaskContext>\n" +
                    "        <wsdl:CreateProvisioningRole>\n" +
                    "            <wsdl:CreateProvisioningRoleProfileTab>\n" +
                    "                <wsdl:Name>%s</wsdl:Name>\n" +
                    "                <wsdl:Description>%s</wsdl:Description>\n" +
                    "                <wsdl:Comments>%s</wsdl:Comments>\n" +
                    "                <wsdl:Department>%s</wsdl:Department>\n" +
                    "                <wsdl:customField01>%s</wsdl:customField01>\n" +
                    "                <wsdl:customField02>%s</wsdl:customField02>\n" +
                    "                <wsdl:customField03>%s</wsdl:customField03>\n" +
                    "                <wsdl:customField04>%s</wsdl:customField04>\n" +
                    "                <wsdl:customField05>%s</wsdl:customField05>\n" +
                    "            </wsdl:CreateProvisioningRoleProfileTab>\n" +
                    "            <wsdl:CreateProvisioningRoleAccountTemplatesTab>\n" +
                    "                <wsdl:Policies>\n" +
                    "                    <wsdl:add index=\"?\">\n" +
                    "                        <wsdl:Name>%s</wsdl:Name>\n" +
                    "                        <wsdl:EndpointType>ActiveDirectory</wsdl:EndpointType>\n" +
                    "                    </wsdl:add>\n" +
                    "                </wsdl:Policies>\n" +
                    "            </wsdl:CreateProvisioningRoleAccountTemplatesTab>\n" +
                    "            <wsdl:CreateProvisioningRoleOwnersTab>\n" +
                    "                <wsdl:Policy>\n" +
                    "                <wsdl:add index=\"?\">\n" +
                    "                    <wsdl:Owner><![CDATA[<MemberRule><RoleMember><AdminRole name=\"System Manager\"/></RoleMember></MemberRule>]]></wsdl:Owner>\n" +
                    "                </wsdl:add>\n" +
                    "                </wsdl:Policy>\n" +
                    "            </wsdl:CreateProvisioningRoleOwnersTab>\n" +
                    "        </wsdl:CreateProvisioningRole>\n" +
                    "        </soapenv:Body>\n" +
                    "    </soapenv:Envelope>",Nome, Desc, Comments, Depart, CF01, CF02, CF03, CF04, CF05, nomeAcc);

            // Client, Request e Send da biblioteca
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(IDMUrl))
                    .header("Content-Type", "text/xml; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(XML))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // "Feedback"
            System.out.println(String.format(response.body()));
        }
    }
}
