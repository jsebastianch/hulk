import {Producto} from './producto';
import {Injectable} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {environment} from '../../environments/environment';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import 'rxjs/add/operator/map';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const API_URL = environment.apiUrl;
@Injectable()
export class ProductoService {

  private productosUrl = '/webresources/producto';  // URL to web api
  private headers = new Headers({'Content-Type': 'application/json'});


  resp: Promise<Producto[]>;

  constructor(private http: Http,
    private httpClient: HttpClient) {}

  getProductos(): Promise<Producto[]> {
    const url = API_URL + `${this.productosUrl}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json() as Producto[])
      .catch(this.handleError);
  }

  getProducto(codigoProducto: string) {
    const url = API_URL + `${this.productosUrl}/` + codigoProducto;
    return this.http.get(url);
  }


  actualizar(producto: Producto): Promise<Producto> {
    const url = API_URL + `${this.productosUrl}`;
    return this.http
      .post(url, JSON.stringify(producto), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as Producto)
      .catch(this.handleError);
  }

  crearNuevoProducto(producto: Producto) {
    const url = API_URL + `${this.productosUrl}`;
    return this.http
      .put(url, JSON.stringify(producto), {headers: this.headers});
  }

  private handleError(error: any): Promise<any> {
    console.error('Error al obtener los productos', error);
    return Promise.reject(error.message || error);
  }
}
