package br.mackenzie.ps2.cmdcrud;

import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static br.mackenzie.ps2.utils.ES.*;

@SpringBootApplication
public class Main 
  implements CommandLineRunner {

    @Autowired
    private TitularRepo titularRepo;
    
    public static void main(String[] args) {
            SpringApplication.run(Main.class, args);
    }

    private void criarTitular() {
        print("\n## CRIAÇÃO DE UM NOVO TITULAR");
        long id = inputLong("\n## Id do novo titular: ");
        String nome = inputString("## Nome do novo titular: ");
        String rg = inputString("## RG do novo titular: ");
        String cpf = inputString("## CPF do novo titular: ");
        Titular novoTitular = new Titular(id, nome, rg, cpf);
        titularRepo.save(novoTitular);
        print("\n## Novo titular criado com sucesso!");
    }

    private void lerTodosTitulares() {
        print("\n## LISTA DE TODOS OS TITULARES:");
        Iterable<Titular> titulares = titularRepo.findAll();
        for (Titular t: titulares) {
            print(String.format("- %s: %s; RG: %s; CPF: %s", 
              t.getNroTitular(), t.getNome(), t.getRg(), t.getCpf()));
        }
    }

    private void lerTitularPeloId() {
        long id = inputLong("\n## Id do titular: ");
        Optional<Titular> opt = titularRepo.findById(id);
        if (opt.isPresent()) {
            Titular t = opt.get();
            print(String.format("- %s: %s; RG: %s; CPF: %s", 
              t.getNroTitular(), t.getNome(), t.getRg(), t.getCpf()));
        }
        else {
            print("\n## Não há um titular com o id " + id);
        }
    }

    private void alterarTitular() {
        print("\nALTERAÇÃO DE UM TITULAR");
        long id = inputLong("\n## ID do titular a ser alterado: ");
        Optional<Titular> opt = titularRepo.findById(id);
        if (opt.isPresent()) {
            Titular t = opt.get();
            String novoNome = inputString(
                String.format("\n## Novo nome [%s]: ", t.getNome()));
            String novoRg = inputString(
                    String.format("\n## Novo RG [%s]: ", t.getRg()));
            String novoCpf = inputString(
                    String.format("\n## Novo CPF [%s]: ", t.getCpf()));
            t.setNome(novoNome);
            t.setRg(novoRg);
            t.setCpf(novoCpf);
            titularRepo.save(t);
            print("\n## Titular alterado com sucesso!");
        }
        else {
            print("\n## Não existe um titular com ID " + id);
        }
    }

    private void apagarTitular() {
        print("\n## REMOÇÃO DE UM TITULAR");
        long id = inputLong("\n## ID do titular a ser apagado: ");
        Optional<Titular> opt = titularRepo.findById(id);
        if (opt.isPresent()) {
            Titular t = opt.get();
            titularRepo.delete(t);
            print("\n## Titular apagado com sucesso!");
        }
        else {
            print("\n## Não existe um titular com ID " + id);
        }
    }

    @Override
    public void run(String... args) {
        print("\n## GERENCIADOR DE TITULARES ##");

        boolean sair = false;
        while(!sair) {
            print("\n## MENU:");
            print("(1) Criar titular");
            print("(2) Ler todos os titulares");
            print("(3) Ler titular pelo ID");
            print("(4) Alterar um titular");
            print("(5) Apagar um titular");
            print("(6) Sair");
            int op = inputInt("\nEscolha uma opção: ");
            switch(op) {
            case 1: criarTitular(); break;
            case 2: lerTodosTitulares(); break;
            case 3: lerTitularPeloId(); break;
            case 4: alterarTitular(); break;
            case 5: apagarTitular(); break;
            case 6: sair = true; break;
            default: print("\n## Opção inválida!");
            }

        }
        print("\n## FIM DO PROGRAMA! ##");
    }
    
}
