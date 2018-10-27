import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {DataTableModule, SharedModule} from 'primeng/primeng';
import {DialogModule} from 'primeng/dialog';
import {ButtonModule} from 'primeng/button';
import {InputTextModule} from 'primeng/inputtext';
import {GrowlModule} from 'primeng/growl';
import {AppComponent} from './app.component';
import {ProductosComponent} from './productos/productos.component';
import {AppRoutingModuleTs} from './app-routing.module';
import {ProductoService} from './productos/producto.service';
import {HttpModule} from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {InputMaskModule} from 'primeng/inputmask';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModuleTs,
    RouterModule,
    DataTableModule,
    GrowlModule,
    SharedModule,
    HttpModule,
    DialogModule,
    ButtonModule,
    InputTextModule,
    HttpClientModule,
    InputMaskModule
  ],
  declarations: [
    AppComponent,
    ProductosComponent
  ],
  providers: [ProductoService],
  bootstrap: [AppComponent],
})
export class AppModule {}
