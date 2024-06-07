import { Routes, provideRouter } from '@angular/router';
import { TableComponent } from './components/table/table.component';
import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AireComponent, } from './components/aire/aire.component';
import { SplitComponent } from './components/split/split.component';

export const routes: Routes = [
  { path: '', redirectTo: '/center', pathMatch: 'full' },
  { path: 'center', component: TableComponent },
  { path: 'split', component: SplitComponent },
  { path: 'aire', component: AireComponent },
  //{ path: 'inversor', component: InversorComponent },
  //{ path: 'presirizador', component: SplitComponent },
  //{ path: 'solarpanel', component: SplitComponent },
];

bootstrapApplication(AppComponent, {
  providers : [
    provideRouter(routes)
  ]
}).catch(err => console.error(err));
