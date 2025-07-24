//Diretório de model e arquivo adicionado

package com.padrao.crud.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

//Entity é uma anotação. Elas servem para especificar a utilidade de atributos, métodos e classes
//Especificar para a IDE que toda essa classe é para entidade
@Entity(name = "product")
//Especificar para a IDE que a classe terá uma tabela no banco com o nome product
@Table(name = "product")
public class Product {

    @Id //O próximo atributo que está na linha abaixo (tirando a @Generative) se trata de um ID
    //O ID terá a sua geração de valor de forma automática
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private Long price;

    public Product(){
    }

    public Product(Integer id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
