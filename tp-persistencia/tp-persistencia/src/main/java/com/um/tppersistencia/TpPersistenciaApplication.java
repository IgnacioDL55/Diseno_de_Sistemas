package com.um.tppersistencia;

import com.um.tppersistencia.entidades.*;
import com.um.tppersistencia.enumeraciones.Estado;
import com.um.tppersistencia.enumeraciones.FormaPago;
import com.um.tppersistencia.enumeraciones.Tipo;
import com.um.tppersistencia.enumeraciones.TipoEnvio;
import com.um.tppersistencia.repositorios.ClienteRepository;
import com.um.tppersistencia.repositorios.ProductoRepository;
import com.um.tppersistencia.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class TpPersistenciaApplication {

	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpPersistenciaApplication.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(RubroRepository rubroRepository1, ClienteRepository clienteRepository1){
		return args -> {
			System.out.println("----------------ESTOY----FUNCIONANDO---------------------");
			//crear instancia rubro
			Rubro rubro1 = Rubro.builder()
					.denominacion("Hamburguesas")
					.build();
			//crear instancias de productos
			Producto producto1 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa con Cheddar")
					.precioVenta(2000.0)
					.precioCompra(1200.0)
					.stockActual(50)
					.stockMinimo(3)
					.unidadMedida("unidad1")
					.receta("receta1")
					.tipo(Tipo.Insumo)
					.build();
			Producto producto2 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa con Baccon")
					.precioVenta(2200.0)
					.precioCompra(1500.0)
					.stockActual(32)
					.stockMinimo(4)
					.unidadMedida("unidad2")
					.receta("receta2")
					.tipo(Tipo.Insumo)
					.build();
			//agregar productos al rubro
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			//guardar rubro
			rubroRepository.save(rubro1);
			//Crear instancia Cliente
			Cliente cliente1 = Cliente.builder()
					.nombre("Ignacio")
					.apellido("De Luca")
					.email("@mail")
					.telefono("telefono1")
					.build();
			//Crear instancia domicilio
			Domicilio domicilio1 = Domicilio.builder()
					.calle("San Martin")
					.numero(6538)
					.localidad("Lujan")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Las Heras")
					.numero(1223)
					.localidad("Ciudad")
					.build();
			//agregar domicilios a cliente
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			//Crear Instancia Detalle Pedido
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000.0)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2200.0)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(6000.0)
					.build();
			//configuracion fecha
			SimpleDateFormat formatoFecha = new SimpleDateFormat ("yyyy-MM-dd");
			String fechaString = "2023-09-09";
			// Parsear la cadena en un objeto Date
			Date fecha = formatoFecha.parse(fechaString);
			//Crear Instancia Pedido
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha)
					.total(6200.0)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.total(6000.0)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.build();
			//Crear instancias de factura
			Factura factura1 = Factura.builder()
					.fecha(fecha)
					.total(5800)
					.numero(1)
					.descuento(400.0)
					.formaPago(FormaPago.Efectivo)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha)
					.total(5400)
					.numero(2)
					.descuento(600.0)
					.formaPago(FormaPago.Efectivo)
					.build();
			//Agregar detalles al pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
			//agregar pedidos al cliente
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);
			//vincular el detalle pedido con el producto
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			//vincular factura con pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			//guardar cliente
			clienteRepository.save(cliente1);

			//recuperar objeto rubro desde la base de datos
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}
			//recuperar Cliente desde la base de Datos
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getEmail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}
		};
	}

}


