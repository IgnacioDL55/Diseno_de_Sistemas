package com.um.tppersistencia.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallePedido extends BaseEntidad{
    private int cantidad;
    private Double subtotal;

    @ManyToOne()
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
