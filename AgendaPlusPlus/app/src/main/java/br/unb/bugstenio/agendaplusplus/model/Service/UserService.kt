package br.unb.bugstenio.agendaplusplus.model.Service

import br.unb.bugstenio.agendaplusplus.model.Object.*

class UserService {
    /*
    * Esta classe deverá se utilizar do DAO para retornar informações.
    * Exemplo: aqui a gente chamaria a DAO procurando no BD pela existência
    * do usuário e nos retornaria o objeto. Caso o objeto seja um usuário
    * não nulo, vc retorna verdadeiro de usuário existe.
    * */

    /*
    * True -> Deu bom
    * False -> Erro
    * */
    fun login(user: User): Boolean = throw NotImplementedError("Implemente método de login")
    fun createUser(user: User): Boolean = throw NotImplementedError("Implemente cria usuário")
    fun updateUser(user: User): Boolean = throw NotImplementedError("Implemente edita usuário")
    fun deleteUser(user: User): Boolean = throw NotImplementedError("Implemente deleta usuário")
    fun getGroupUsers(group: Group): List<User> = throw NotImplementedError("Implemente pega usuários de grupo")
    fun getProjectUsers(project: Project): List<User> = throw NotImplementedError("Implemente pega usuários de projeto")
    fun getUserById(id: Long): User = throw NotImplementedError("Implemente pega usuário por id")
}