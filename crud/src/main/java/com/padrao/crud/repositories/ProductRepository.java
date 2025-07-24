//Diretorio repositories adicionado e arquivo adicionado

//Para cada entidade que for entrar em contato com o banco, tem que ter um repository para ela

package com.padrao.crud.repositories;
import com.padrao.crud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

//Jpa Repository já tem os métodos necessários e padronizados e implementados responsáveis por efetuar o contato com o banco
//O Repository que vai conter os métodos para entrar em contato com o banco de dados e os métodos de CRUD
public interface ProductRepository extends JpaRepository<Product, Integer> {
}


