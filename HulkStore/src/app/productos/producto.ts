export class Producto {
  public codigo: string;
  public nombre: string;
  public existencias: number;
  public tipo: string;
  public valorUnitario: number;

  constructor(codigo: string, nombre: string, existencias: number, tipo: string, valorUnitario: number) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.existencias = existencias;
    this.tipo = tipo;
    this.valorUnitario = valorUnitario;
  }
}
