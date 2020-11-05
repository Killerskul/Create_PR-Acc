# (CA IAM) Criação de Provisioning Roles e Account Templates
Script criado no intuito de realizar criações de **Provisioning Roles e Accounts Templates em massa**.
Os dados utilizados para criar os mesmos são extraidos de um arquivo, no projeto segue um arquivo(.csv) para teste em seu ambiente.

A criação é realizada via Webservice, talvez você precise habilitar a utilização das Admins Tasks via webservice no seu IDM.
O XML enviado pode e deve ser modificado.

**Atenção**

O Script vem com os seguintes campos em branco, onde é necessário o preenchimento dos dados.

- <'wsdl:admin_id> Credenciais do Usuário </wsdl:admin_id>


- <'wsdl:admin_password> Credenciais do Usuário </wsdl:admin_password> 

- String IDMUrl = "http://Host_IDM:8080/iam/im/TEWS6/identityEnv";

![Teste](https://user-images.githubusercontent.com/69530029/98127057-04894f00-1e95-11eb-8d2c-f93126526463.gif)
