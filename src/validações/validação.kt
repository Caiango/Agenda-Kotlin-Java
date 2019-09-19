package validações

import Entidades.*
import repositório.*
import java.lang.Exception

//funções que vão validar se está vazio ou não
class contato {
    private fun validardelete(nome: String, fone: String) {
        if (nome == "" || fone == "") {
            throw Exception("É necessário selecionar nome e contato para remover")
        }
    }


    private fun validate(nome: String, fone: String) {
        if (nome == "") {
            throw Exception("Nome obrigatório!")
        }
        if (fone == "") {
            throw Exception("Telefone obrigatório!")
        }
    }

    // funções para salvar e deletar os dados do caontato, são as funções principais
    fun salvar(nome: String, fone: String) {
        validate(nome, fone)
        val contato = ContactEntity(nome, fone)
        RepositorioContatos.salvar(contato)
    }

    fun deletar(nome: String, fone: String) {
        validardelete(nome, fone)
        val contato = ContactEntity(nome, fone)
        RepositorioContatos.deletar(contato)
    }

    //função para mostrar a lista de contatos, que peguei de RepositorioContatos
    fun getlist(): List<ContactEntity> {
        return RepositorioContatos.getlist()
    }
    //funçao para contar o numero  de contatos e mostrar na Contadorlabel
    fun getContadorList() : String{
        val list = getlist()
        if (list.isEmpty()){
            return "0 contatos"
        }else if (list.size == 1){
            return "1 contato"
        }else {
            return ("${list.size} contatos")
        }

    }
}