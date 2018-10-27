
import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ProductosComponent} from './productos/productos.component';

const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'},
  {path: 'productos', component: ProductosComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModuleTs {}
