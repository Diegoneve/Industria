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

//      3.1 � Inserir todos os funcion�rios, na mesma ordem e informa��es da tabela acima.       
  	  funcionarios.add(new Funcionario("Maria",  LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
	  funcionarios.add(new Funcionario("Jo�o",   LocalDate.of(1990,  5, 12), new BigDecimal("2284.38"), "Operador"));
	  funcionarios.add(new Funcionario("Caio",   LocalDate.of(1961,  5, 02), new BigDecimal("9836.14"), "Coordenador"));
	  funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
	  funcionarios.add(new Funcionario("Alice",  LocalDate.of(1995,  1, 05), new BigDecimal("2234.68"), "Recepcionista"));
	  funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
	  funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,  3, 31), new BigDecimal("4071.84"), "Contador"));	  
	  funcionarios.add(new Funcionario("Laura",  LocalDate.of(1994,  7, 8), new BigDecimal("3017.45"), "Gerente"));	  
	  funcionarios.add(new Funcionario("Helo�sa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));	  
	  funcionarios.add(new Funcionario("Helena",  LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));	  
	  
//	  3.2 � Remover o funcion�rio �Jo�o� da lista. 
	  funcionarios.removeIf(Funcionario -> Funcionario.getNome().equals("Jo�o"));	  
	  
//	  3.3 � Imprimir todos os funcion�rios com todas suas informa��es, sendo que: 
//	  � informa��o de data deve ser exibido no formato dd/mm/aaaa;	  
	  DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//	  � informa��o de valor num�rico deve ser exibida no formatado com separador de milhar como ponto e decimal como v�rgula.	  
      Locale locale = new Locale("pt", "BR");
      DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(locale);
      simbolos.setDecimalSeparator(',');
      simbolos.setGroupingSeparator('.');
      DecimalFormat formatNum = new DecimalFormat("#,##0.00", simbolos);
	  
	  System.out.println("Funcion�rios: ");
	  
	  for (Funcionario funcionario : funcionarios) {
   	    String salarioNovo = formatNum.format(funcionario.getSalario());   	    
	    System.out.print(" Nome: " + funcionario.getNome());  
	    System.out.print(" Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	    System.out.print(" Sal�rio R$: " +  funcionario.getSalario());
	    System.out.print(" Sal�rio R$: " +  salarioNovo);
	    System.out.println(" Fun��o: " + funcionario.getFuncao());	    
	  }
	  
//	  3.4 � Os funcion�rios receberam 10% de aumento de sal�rio, atualizar a lista de funcion�rios com novo valor.
	  for (Funcionario funcionario : funcionarios){
	    BigDecimal aumentoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
	    funcionario.setSalario(aumentoSalario);
	  }
	  
//	  3.5 � Agrupar os funcion�rios por fun��o em um MAP, sendo a chave a �fun��o� e o valor a �lista de funcion�rios�.
	  Map<String, List<Funcionario>> funcionariosFuncao = new HashMap<>();
	  
	  for (Funcionario funcionario : funcionarios) {
	    String funcao = funcionario.getFuncao();  
	    List<Funcionario> funcionariosNaFuncao = funcionariosFuncao.getOrDefault(funcao, new ArrayList<>());
	    funcionariosNaFuncao.add(funcionario);
	    funcionariosFuncao.put(funcao, funcionariosNaFuncao);
	  }
	  
//	  3.6 � Imprimir os funcion�rios, agrupados por fun��o.
	  System.out.println();
	  System.out.println("Lista de Funcion�rios por Fun��o");
	  
	  for (Map.Entry<String, List<Funcionario>> entry : funcionariosFuncao.entrySet()) {		  
		  String funcao = entry.getKey();
		  List<Funcionario> funcionariosNaFuncao = entry.getValue();
		  
		  System.out.println("Fun��o: " + funcao);
		  
		  for (Funcionario funcionario : funcionariosNaFuncao) {
              System.out.println("Nome: " + funcionario.getNome());
              System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatDate));
              System.out.println("Sal�rio: R$" + formatNum.format(funcionario.getSalario()));
              System.out.println();
          }
	  }
	  
//	  3.8 � Imprimir os funcion�rios que fazem anivers�rio no m�s 10 e 12.
	  System.out.println();
	  System.out.println("Lista de Funcion�rios por Fun��o");
	  System.out.println("Funcion�rios que fazem anivers�rio em outubro (10) e dezembro (12):");
	  
      for (Funcionario funcionario : funcionarios) {
          int mesNascimento = funcionario.getDataNascimento().getMonthValue();
          
          if (mesNascimento == 10 || mesNascimento == 12) {
              System.out.println("Nome: " + funcionario.getNome());
              System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatDate));
              System.out.println();
          }
      }
	  
//	  3.9 � Imprimir o funcion�rio com a maior idade, exibir os atributos: nome e idade.
      System.out.println();
	  System.out.println("Lista de Funcion�rios com maior Idade");
	  
	  Funcionario funcionarioMaiorIdade = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElse(null);

      if (funcionarioMaiorIdade != null) {
          Period diferenca = Period.between(funcionarioMaiorIdade.getDataNascimento(), LocalDate.now()); 
    	  
    	  int idade = diferenca.getYears();         
          
          System.out.println("Nome: " + funcionarioMaiorIdade.getNome());
          System.out.println("Idade: " + idade + " anos");
          System.out.println();
      }
	  
	  
//	  3.10 � Imprimir a lista de funcion�rios por ordem alfab�tica.
      System.out.println();	  
	  System.out.println("Lista de funcion�rios em ordem alfab�tica:");
      
	  List<Funcionario> funcionariosOrdenados = new ArrayList<>(funcionarios);
      funcionariosOrdenados.sort(Comparator.comparing(Funcionario::getNome));
      
      for (Funcionario funcionario : funcionariosOrdenados) {
          System.out.println("Nome: " + funcionario.getNome());
          System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(formatDate));
          System.out.println();
      }
	  
	  
//	  3.11 � Imprimir o total dos sal�rios dos funcion�rios.
      System.out.println();	  
	  BigDecimal totalSalarios = BigDecimal.ZERO;
      for (Funcionario funcionario : funcionarios) {
          totalSalarios = totalSalarios.add(funcionario.getSalario());
      }
      System.out.println("Total dos sal�rios dos funcion�rios: R$" + formatNum.format(totalSalarios));


	  
//	  3.12 � Imprimir quantos sal�rios m�nimos ganha cada funcion�rio, considerando que o sal�rio m�nimo � R$1212.00.
      System.out.println();
      System.out.println("Sal�rio m�nimo recebido por funcion�rio:");      
      for (Funcionario funcionario : funcionarios) {
          BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
          System.out.println("Nome: " + funcionario.getNome());
          System.out.println("Quantidade de Sal�rios: " + salariosMinimos);
          System.out.println();
      }
	}

}
