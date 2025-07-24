//Diretório de controllers adicionado e arquivo adicionado

//Classe responsável por receber requisições HTTP e devolver informações para o cliente (user)

package com.padrao.crud.controllers;

import com.padrao.crud.dtos.ProductDto;
import com.padrao.crud.model.Product;
import com.padrao.crud.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Anotação para informar a IDE que quando ela compilar o projeto, toda essa classe será uma classe de controle
@RestController

//Anotação para que quando for requisitado o HTTP, para products, será utilizado todos os métodos contidos nessa classe
@RequestMapping("/products")

public class ProductController {

    //Anotação para instantiar o objeto abaixo fazendo com que ele faça todas as injeções de dependências para que seja possível utiliza-lo
    @Autowired
    //Instanciar o objeto do ProductRespository
    ProductRepository repository;

    //Pegar todos os registros
    //Informar que o método abaixo será do tipo get
    @GetMapping
    //Método público
    //ResponseEntity é uma classe padrão que contém métodos e atributos específicos para retornar dados
    //GetAll retorna todos os dados da base
    public ResponseEntity getAll(){
        //Trazer uma lista com todos os objetos do tipo produto da base de dados
        List<Product> listProducts = repository.findAll();
        //Retornar uma resposta 200 com a lista de produtos
        return ResponseEntity.status(HttpStatus.OK).body(listProducts);
    }

    //Pegar registro específico
    //O metodo abaixo, será GET mas ele irá passar um atributo do tipo id também
    @GetMapping("/{id}")
    //A notação @PathVariable fará com o que o id do value seja o mesmo do parâmetro acima, e esses dois ids serão o mesmo do que está no parâmetro
    public ResponseEntity getById(@PathVariable(value = "id") Integer id){
        Optional product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(product.get()); //tem que colocar o get por causa do optional
    }

    //Método para post
    @PostMapping
    //O RequestBody é para quando houver essa requisição post, terá que ter um objeto do tipo product no corpo da requisição
    //Puxando o ProductDto do ProductDto em dtos
    public ResponseEntity save(@RequestBody ProductDto dto){
        //Esse var e Bean é feito para não utilizar o objeto entidade para transicionar dados dentro da aplicação
        //Objeto do tipo Product sem nada instanciado
        var product = new Product();
        //Copiar as propriedades e valores que estiverem no atributo dto para product
        BeanUtils.copyProperties(dto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
    }

    //Método de delete
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id")Integer id){
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        repository.delete((Product) product.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    //Método de update
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id")Integer id, @RequestBody ProductDto dto){
        Optional<Product> product = repository.findById(id);
        if(product.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        //Variável para pegar todos os valores desse registro
        var productModel = product.get();
        BeanUtils.copyProperties(dto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(productModel));
    }
}
