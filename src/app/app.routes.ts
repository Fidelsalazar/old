import { Routes, provideRouter } from '@angular/router';
import { TableComponent } from './components/table/table.component';
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AireComponent, } from './components/aire/aire.component';
import { SplitComponent } from './components/split/split.component';
import { InversorComponent } from './components/inversor/inversor.component';
import { PresurizadorComponent } from './components/presurizador/presurizador.component';
import { PanelSolarComponent } from './components/panel-solar/panel-solar.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  //{ path: '', redirectTo: '/center', pathMatch: 'full' },
  { path: 'login', component: LoginComponent},
  { path: 'center', component: TableComponent },
  { path: 'split', component: SplitComponent },
  { path: 'aire', component: AireComponent },
  { path: 'inversor', component: InversorComponent },
  { path: 'presurizador', component: PresurizadorComponent },
  { path: 'panel-solar', component: PanelSolarComponent },
];

bootstrapApplication(AppComponent, {
  providers : [
    provideRouter(routes)
  ]
}).catch(err => console.error(err));
