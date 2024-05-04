package org.adaschool.api.repository.product;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Objeto que representa a un Producto")
public class Product {

    @Schema(
            description = "Identificador unico",
            type = "string",
            example = "1")
    private final String id;

    @Schema(
            description = "Nombre",
            type = "string",
            example = "Tornillo")
    private String name;

    @Schema(
            description = "Descripcion",
            type = "string",
            example = "Rosca fina")
    private String description;

    @Schema(
            description = "Categoria",
            type = "string",
            example = "Ferreteria")
    private String category;

    @Schema(description = "Etiquetas",
            type = "List",
            example = "[\"Hierro\", \"Rosca\", \"Nuevo\"]")
    private List<String> tags;

    @Schema(
            description = "Precio",
            type = "double",
            example = "200.5")
    private double price;

    @Schema(
            description = "Url de la Imagen",
            type = "string",
            example = "/imagenes/img1")
    private String imageUrl;

    public Product() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.category = "";
        this.tags = null;
        this.price = 0.0;
        this.imageUrl = "";
    }

    public Product(String id, String name, String description, String category, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public Product(ProductDto productDto) {
        this.id = "";
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.category = productDto.getCategory();
        this.tags = productDto.getTags();
        this.price = productDto.getPrice();
        this.imageUrl = productDto.getImageUrl();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void update(ProductDto productDto) {
        this.name = productDto.getName();
        this.description = productDto.getDescription();
        this.category = productDto.getCategory();
        this.tags = productDto.getTags();
        this.price = productDto.getPrice();
        this.imageUrl = productDto.getImageUrl();
    }
}
