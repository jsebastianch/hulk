import { Producto } from './producto';
import { ProductoService } from './producto.service';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/mergeMap'

import { Message } from 'primeng/components/common/api';

@Component({
  selector: 'app-productos',
  encapsulation: ViewEncapsulation.None, // para los estilos de PrimeNG
  templateUrl: './productos.component.html',
 
})
export class ProductosComponent implements OnInit {

  productos: Producto[];
  msgs: Message[] = [];

  productoSeleccionado: Producto;

  cantidad: number;
  codigo: string;
  nombre: string;
  tipo: string;
  valor: number;


  display = false;
  displaySalida = false;
  displayNuevo = false;

  constructor(private productoService: ProductoService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.productoService.getProductos())
      .subscribe(productos => this.productos = productos);
    this.productoSeleccionado = new Producto(null, null, null, null, null);
  }

  showDialog() {
    this.cantidad = null;
    this.display = true;
    this.displaySalida = false;
    this.displayNuevo = false;
  }

  showDialogSalida() {
    this.cantidad = null;
    this.display = false;
    this.displaySalida = true;
    this.displayNuevo = false;
  }

  showDialogNuevo() {
    this.cantidad = null;
    this.display = false;
    this.displaySalida = false;
    this.displayNuevo = true;
    this.codigo = null;
    this.nombre = null;
    this.tipo = null;
    this.valor = null;
  }


  aceptar() {
    this.msgs = [];
    if (this.cantidad < 1) {
      this.msgs.push({ severity: 'error', summary: 'Error', detail: 'El valor no puede ser menor que uno.' });
      return;
    }
    if (this.displaySalida) {
      if (this.cantidad > this.productoSeleccionado.existencias) {
        this.msgs.push({ severity: 'error', summary: 'Error', detail: 'No se puede registrar una salida mayor a las existencias actuales.' });
        return;
      }
      this.productoSeleccionado.existencias = +this.productoSeleccionado.existencias - +this.cantidad;

    } else {
      this.productoSeleccionado.existencias = +this.productoSeleccionado.existencias + +this.cantidad;
    }
    this.productoService.actualizar(this.productoSeleccionado);
    this.msgs.push({ severity: 'success', summary: '', detail: 'Se ha actualizado el producto correctamente.' });
    this.display = false;
    this.displaySalida = false;
    this.displayNuevo = false;

  }


  deshabilitar() {
    if (this.productoSeleccionado == null) {
      return true;
    } else {
      return this.productoSeleccionado.existencias <= 0;
    }
  }

  guardarNuevo() {
    this.msgs = [];
    if (this.valor <= 0) {
      this.msgs.push({ severity: 'error', summary: 'Error', detail: 'El valor no puede ser menor o igual a cero.' });
      return;
    }

    const producto = new Producto(this.codigo, this.nombre, this.cantidad, this.tipo, this.valor);
    this.productoService.crearNuevoProducto(producto)
      .flatMap(success => this.productoService.getProducto(this.codigo).map(res => res.json() as Producto))
      .subscribe((data: Producto) => {
        this.productos = [...this.productos, data];
      });
    this.msgs.push({ severity: 'success', summary: '', detail: 'Se ha creado el producto correctamente.' });
    this.display = false;
    this.displaySalida = false;
    this.displayNuevo = false;
  }

}
