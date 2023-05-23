import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.ToIntFunction;

/**
 * @author Diego Andrade
 * 
 */
public class Principal {
    public static final BigDecimal salarioMinimo = new BigDecimal("1212.00");
    
	public static void main(String[] args) { 
      List<Funcionario> funcionarios = new ArrayList<>(); 

//      3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela acima.       
  	  funcionarios.add(new Funcionario("Maria",  LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
	  funcionarios.add(new Funcionario("João",   LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"), "Operador"));
	  funcionarios.add(new Funcionario("Caio",   LocalDate.of(1961,  5, 02), new BigDecimal("9836.14"), "Coordenador"));
	  funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
	  funcionarios.add(new Funcionario("Alice",  LocalDate.of(1995,  1, 05), new BigDecimal("2234.68"), "Recepcionista"));
	  funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
	  funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"), "Contador"));	  
	  funcionarios.add(new Funcionario("Laura",  LocalDate.of(1994,  7, 8), new BigDecimal("3017.45"), "Gerente"));	  
	  funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));	  
	  funcionarios.add(new Funcionario("Helena",  LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));	  
	  
//	  3.2 – Remover o funcionário “João” da lista. 
	  funcionarios.removeIf(Funcionario -> Funcionario.getNome().equals("João"));	  
	  
//	  3.3 – Imprimir todos os funcionários com todas suas informações, sendo que: 
//	  • informação de data deve ser exibido no formato dd/mm/aaaa;	  
	  DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	  • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula.	  
      Locale locale = new Locale("pt", "BR");
      DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(locale);
      simbolos.setDecimalSeparator(',');
      simbolos.setGroupingSeparator('.');
      DecimalFormat formatNum = new DecimalFormat("#,##0.00", simbolos);
	  
	  System.out.println("Funcionários: ");
	  
	  for (Funcionario funcionario : funcionarios) {
   	    String salarioNovo = formatNum.format(funcionario.getSalario());   	    
	    System.out.print(" Nome: " + funcionario.getNome());  
	    System.out.print(" Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    System.out.print(" Salário R$: " +  funcionario.getSalario());
	    System.out.print(" Salário R$: " +  salarioNovo);
	    System.out.println(" Função: " + funcionario.getFuncao());	    
	  }
	  
//	  3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor.
	  for (Funcionario funcionario : funcionarios){
	    BigDecimal aumentoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
	    funcionario.setSalario(aumentoSalario);
	  }
	  
//	  3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
	  Map<String, List<Funcionario>> funcionariosFuncao = new HashMap<>();
	  
	  for (Funcionario funcionario : funcionarios) {
	    String funcao = funcionario.getFuncao();  
	    List<Funcionario> funcionariosNaFuncao = funcionariosFuncao.getOrDefault(funcao, new ArrayList<>());
	    funcionariosNaFuncao.add(funcionario);
	    funcionariosFuncao.put(funcao, funcionariosNaFuncao);
	  }
	  
//	  3.6 – Imprimir os funcionários, agrupados por função.
	  System.out.println();
	  System.out.println("Lista de Funcionários por Função");
	  
	  for (Map.Entry<String, List<Funcionario>> entry : funcionariosFuncao.entrySet()) {		  
		  String funcao = entry.getKey();
		  List<Funcionario> funcionariosNaFuncao = entry.getValue();
		  
		  System.out.println("Função: " + funcao);
		  
		  for (Funcionario funcionario : funcionariosNaFuncao) {
              System.out.println("Nome: " + funcionario.getNome());
              System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatDate));
              System.out.println("Salário: R$" + formatNum.format(funcionario.getSalario()));
              System.out.println();
          }
	  }
	  
//	  3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
	  System.out.println();
	  System.out.println("Lista de Funcionários por Função");
	  System.out.println("Funcionários que fazem aniversário em outubro (10) e dezembro (12):");
	  
      for (Funcionario funcionario : funcionarios) {
          int mesNascimento = funcionario.getDataNascimento().getMonthValue();
          
          if (mesNascimento == 10 || mesNascimento == 12) {
              System.out.println("Nome: " + funcionario.getNome());
              System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatDate));
              System.out.println();
          }
      }
	  
//	  3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
      System.out.println();
	  System.out.println("Lista de Funcionários com maior Idade");
	  
	  Funcionario funcionarioMaiorIdade = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElse(null);

      if (funcionarioMaiorIdade != null) {
          Period diferenca = Period.between(funcionarioMaiorIdade.getDataNascimento(), LocalDate.now()); 
    	  
    	  int idade = diferenca.getYears();         
          
          System.out.println("Nome: " + funcionarioMaiorIdade.getNome());
          System.out.println("Idade: " + idade + " anos");
          System.out.println();
      }
	  
	  
//	  3.10 – Imprimir a lista de funcionários por ordem alfabética.
      System.out.println();	  
	  System.out.println("Lista de funcionários em ordem alfabética:");
      
	  List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
      funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
      
      for (Funcionario funcionario : funcionariosOrdenados) {
          System.out.println("Nome: " + funcionario.getNome());
          System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatDate));
          System.out.println();
      }
	  
	  
//	  3.11 – Imprimir o total dos salários dos funcionários.
      System.out.println();	  
	  BigDecimal totalSalarios = BigDecimal.ZERO;
      for (Funcionario funcionario : funcionarios) {
          totalSalarios = totalSalarios.add(funcionario.getSalario());
      }
      System.out.println("Total dos salários dos funcionários: R$" + formatNum.format(totalSalarios));


	  
//	  3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
      System.out.println();
      System.out.println("Salário mínimo recebido por funcionário:");      
      for (Funcionario funcionario : funcionarios) {
          BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
          System.out.println("Nome: " + funcionario.getNome());
          System.out.println("Quantidade de Salários: " + salariosMinimos);
          System.out.println();
      }
	}

}
