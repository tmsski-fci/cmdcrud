# cmdcrud

Aplicação CRUD com interface via linha de comando.

Esta aplicação foi desenvolvida utilizando SPRING BOOT.

O arquivo `src/main/resources/application.properties` está configurado supondo que:

* O banco de dados que se quer acessar é o MySQL.

* Já há uma base de dados criada com o nome `sistema_bancario`.

* Já há uma tabela criada pelo seguinte script:

```
    CREATE TABLE titulares (
        nro_titular BIGINT NOT NULL,
        nome VARCHAR(255) NOT NULL,
        rg VARCHAR(32) NOT NULL,
        cpf VARCHAR(32) NOT NULL,
        PRIMARY KEY (nro_titular)
    );
```

* O acesso à base de dados é realizado utilizando o usuário `root` com senha em branco (esta configuração é usada somente no ambiente de desenvolvimento; nunca utilize seu ambiente de produção com esta configuração).
