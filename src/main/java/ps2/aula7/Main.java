package ps2.aula7;

import java.util.Optional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import static ps2.utils.ES.*;

@SpringBootApplication
public class Main 
  implements CommandLineRunner {

    @Autowired
    private TitularRepo titularRepo;
    
    public static void main(String[] args) {
            SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        print("\n## GERENCIADOR DE TITULARES ##");

        boolean sair = false;
        
        while(!sair) {
            print("\n## MENU PRINCIPAL ##");
            print("(1) Criar titular");
            print("(2) Ler todos os titulares");
            print("(3) Ler um titular");
            print("(4) Atualizar um titular");
            print("(5) Apagar um titular");
            print("(0) Sair");
            String opcao = inputString("\n## Escolha uma opção: ");
            switch(opcao) {
                case "1": criar(); break;
                case "2": lerTodos(); break;
                case "3": lerUm(); break;
                case "4": atualizar(); break;
                case "5": apagar(); break;
                case "0": sair = true; break;
                default:
                    print("\n## Opção inválida!");
            }
        }
        
        print("\n## FIM DO PROGRAMA! ##");
    }
    
    private void criar() {
        print("\n## CRIAÇÃO DE UM TITULAR");
        long nro = inputLong("\n# Número do titular: ");
        String nome = inputString("\n# Nome do titular: ");
        String rg = inputString("\n# RG do titular: ");
        String cpf = inputString("\n# CPF do titular: ");
        Titular t = new Titular(nro, nome, rg, cpf);
        titularRepo.save(t);
        print("\n## Titular criado!");
    }
    private void lerTodos() {
        print("\n## LEITURA DE TODOS OS TITULARES");
        Iterable<Titular> titulares = titularRepo.findAll();
        for (Titular t: titulares) {
            print(String.format("- Nro: %d, Nome: %s, RG: %s, CPF: %s",
                 t.getNroTitular(), t.getNome(), t.getRg(), t.getCpf()));
        }
    }
    private void lerUm() {
        print("\n## LEITURA DE UM TITULAR");
        long nro = inputLong("\n# Número do titular: ");
        Optional<Titular> opt = titularRepo.findById(nro);
        if (opt.isPresent()) {
            Titular t = opt.get();
            print(String.format("- Nro: %d, Nome: %s, RG: %s, CPF: %s",
                 t.getNroTitular(), t.getNome(), t.getRg(), t.getCpf()));
        }
        else {
            print("\n## Não achei titular com este número!");
        }
    }
    private void atualizar() {
        print("\n## ATUALIZAÇÃO DE UM TITULAR");
    }
    private void apagar() {
        print("\n## REMOÇÃO DE UM TITULAR");
    }

}
