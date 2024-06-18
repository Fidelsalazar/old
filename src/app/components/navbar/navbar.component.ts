import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { BadgeModule } from 'primeng/badge';
import { AvatarModule } from 'primeng/avatar';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';
import { RippleModule } from 'primeng/ripple';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../service/auth.service';


@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MenubarModule,
    BadgeModule,
    AvatarModule,
    InputTextModule,
    RippleModule,
    CommonModule,
    RouterModule,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  items!: MenuItem[];

  constructor(
    private authService : AuthService
  ){}

  ngOnInit() {
    this.items = [
      {
        label: 'Centros',
        icon: 'pi pi-home',
        routerLink: '/center',
      },
      {
        label: 'Aires',
        icon: 'pi pi-slack',
        items: [
          {
            label: 'Split',
            shortcut: '⌘+S',
            routerLink: '/split',
          },
          {
            label: 'Aire de ventana',
            shortcut: '⌘+A',
            routerLink: '/aire',
          },
          {
            separator: true,
          },
        ],
      },
      {
        label: 'Inversor',
        icon: 'pi pi-bolt',
        routerLink: '/inversor',
      },
      {
        label: 'Presurizador',
        icon: 'pi pi-forward',
        routerLink: '/presurizador',
      },
      {
        label: 'Panel Solar',
        icon: 'pi pi-sun',
        routerLink: '/panel-solar',
      },
      {
        label: 'Contactanos',
        icon: 'pi pi-envelope',
      },
      {
        label: 'Salir',
        icon: 'pi pi-fw pi-power-off',
        command: () => this.close(),
      },
    ];
  }

  close() {
    this.authService.logout();
  }
}
