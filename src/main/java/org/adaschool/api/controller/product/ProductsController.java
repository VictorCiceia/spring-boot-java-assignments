package org.adaschool.api.controller.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.adaschool.api.exception.ProductNotFoundException;
import org.adaschool.api.repository.product.Product;
import org.adaschool.api.service.product.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products/")
@Tag(name = "Productos", description = "Endpoints para trabajar con Productos ")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(@Autowired ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtener por ID", description = "Obtener un Producto por el atributo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Product.class)))}
    )
    public ResponseEntity<Product> findById(@PathVariable("id") final String id) {
        final Optional<Product> product = this.productsService.findById(id);
        if (product.isEmpty())
            throw new ProductNotFoundException(id);
        return ResponseEntity.ok(product.get());
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Obtener todos los Producto", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Product.class)))}
    )
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productsService.all());
    }

    @PostMapping
    @Operation(summary = "Crear", description = "Crear un Producto nuevo", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Product.class)))}
    )
    public ResponseEntity<Product> createProduct(@RequestBody final Product product) {
        URI createdProductUri = URI.create("");
        return ResponseEntity.created(createdProductUri).body(this.productsService.save(product));
    }

    @PutMapping("{id}")
    @Operation(summary = "Actualizar", description = "Actualizar un Producto por el atributo ID", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Product.class)))}
    )
    public ResponseEntity<Product> updateProduct(@PathVariable("id") final String id, @RequestBody final Product product) {
        if (this.productsService.findById(id).isEmpty())
            throw new ProductNotFoundException(id);
        return ResponseEntity.ok(this.productsService.update(product, id));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Eliminar", description = "Eliminar un Producto por el atributo ID", responses = {
            @ApiResponse(responseCode = "202", description = "Operación exitosa")}
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") final String id) {
        if (this.productsService.findById(id).isEmpty())
            throw new ProductNotFoundException(id);
        this.productsService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
