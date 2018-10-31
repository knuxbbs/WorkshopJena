package aula;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

public class Aula1 {

	public static void main(String args[]) throws IOException {

		String ns = "http://www.exemplo.com/grupo#";
		Model M_grupo = ModelFactory.createDefaultModel();

		// **** Recurso

		Resource R_grupo = M_grupo.createResource(ns + "TrabalhoJena");
		Resource R_joao = M_grupo.createResource(ns + "Joao");
		Resource R_maria = M_grupo.createResource(ns + "Maria");

		// **** Propriedades

		Property P_pertenceAoGrupo = M_grupo.createProperty(ns, "Participa_do_grupo");
		Property P_NumTelefone = M_grupo.createProperty(ns, "Numero_telefone");
		Property P_NomeCompleto = M_grupo.createProperty(ns, "NomeCompleto");
		Property P_NomeGrupo = M_grupo.createProperty(ns, "NomeGrupo");

		// **** Literal

		Literal L_NomeDoGrupo = M_grupo.createLiteral("Workshop sobre o Jena");
		Literal L_NomeMaria = M_grupo.createLiteral("Maria Andrade");
		Literal L_NomeJoao = M_grupo.createLiteral("Joao Souza");
		Literal L_TelefoneMaria = M_grupo.createLiteral("3330-1111");
		Literal L_TelefoneJoao = M_grupo.createLiteral("3330-2222");

		// **** Statements (Tripla)

		// Adicionando nome do grupo
		Statement T_nome_grupo = M_grupo.createStatement(R_grupo, P_NomeGrupo, L_NomeDoGrupo);
		M_grupo.add(T_nome_grupo);

		// Adicionando pessoas ao grupo
		Statement T_ParticipanteGrupo_Joao = M_grupo.createStatement(R_grupo, P_pertenceAoGrupo, R_joao);
		M_grupo.add(T_ParticipanteGrupo_Joao);

		Statement T_ParticipanteGrupo_Maria = M_grupo.createStatement(R_grupo, P_pertenceAoGrupo, R_maria);
		M_grupo.add(T_ParticipanteGrupo_Maria);

		// Adicionando nome das pessoas
		Statement T_Nome_Maria = M_grupo.createStatement(R_maria, P_NomeCompleto, L_NomeMaria);
		M_grupo.add(T_Nome_Maria);

		Statement T_Nome_Joao = M_grupo.createStatement(R_joao, P_NomeCompleto, L_NomeJoao);
		M_grupo.add(T_Nome_Joao);

		// Adicionando Telefone das pessoas
		Statement T_Telefone_Maria = M_grupo.createStatement(R_maria, P_NumTelefone, L_TelefoneMaria);
		M_grupo.add(T_Telefone_Maria);

		Statement T_Telefone_Joao = M_grupo.createStatement(R_joao, P_NumTelefone, L_TelefoneJoao);
		M_grupo.add(T_Telefone_Joao);

		M_grupo.write(System.out);

		// Escrever no arquivo

		String fileName = "RDFGrupo1.xml";
		FileWriter out = new FileWriter(fileName);
		M_grupo.write(out);

		// ------------------------------------------------------------------------------------

	}

}
