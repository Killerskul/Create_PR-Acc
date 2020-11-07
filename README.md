# Criação de Provisioning Roles e Account Templates (CA IAM)
Script criado no intuito de realizar criações de **Provisioning Roles e Accounts Templates em massa**.
Os dados utilizados para criar os mesmos são extraidos de um arquivo, no projeto segue um arquivo (.csv) para teste em seu ambiente.

A criação é realizada via Web service, talvez você precise habilitar a utilização das Admins Tasks via Web service no seu IDM.
Isso é feito de forma simples, apenas marcando o checkbox "*Enable Web Services*" como mostra o GIF abaixo.

![Gif](https://user-images.githubusercontent.com/69530029/98449726-aec2da00-2114-11eb-947d-60f535c77a8e.gif)

*O projeto faz a criação de Accounts Templates ActiveDirectory, caso você necessite de Accounts Templates de outro tipo de Endpoint é necessário buscar o XML no link "http://host_idm:8080/iam/im/TEWS6/identityEnv/?wsdl". Sugiro a utilização do SOAP UI para buscar o XML.*

_Admin Tasks usadas no projeto (default): **Create Provisioning Role || Create Active Directory Account Template**_

**Atenção**

O Script vem com os seguintes campos dos XML's em branco, onde é necessário o preenchimento dos dados em ambas as classes de criação do projeto.

- <'wsdl:admin_id> **Credenciais do Usuário** </wsdl:admin_id>


- <'wsdl:admin_password> **Credenciais do Usuário** </wsdl:admin_password> 

- String IDMUrl = **"http://Host_IDM:8080/iam/im/TEWS6/identityEnv"**;


Enjoy it!

![giphy (1)](https://user-images.githubusercontent.com/69530029/98310055-d5650180-1faa-11eb-8c2e-b77f907dbc10.gif)
