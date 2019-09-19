package repositório

import Entidades.*

class RepositorioContatos {
    //esse companion faz com que qd eu chame a classe acima e os dados não sejam resetados e pegue de onde eu parei
    companion object {

        private val list = mutableListOf<ContactEntity>()
        fun salvar(contato: ContactEntity) {
           list.add(contato)
        }

        fun deletar(contato: ContactEntity) {
        var index = 0
            for (item in list.withIndex()){
                if (item.value.nome == contato.nome && item.value.fone == contato.fone){
                    index = item.index
                    break
                }
            }
            list.removeAt(index)
        }
        //função para mostrar a lista de contatos
        fun getlist() : List<ContactEntity>{
            return list
        }
    }
}