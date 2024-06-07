import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';
import { BadgeModule } from 'primeng/badge';
import { AvatarModule } from 'primeng/avatar';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';
import { RippleModule } from 'primeng/ripple';
import { RouterModule } from '@angular/router';


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
    RouterModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent implements OnInit {
  items!: MenuItem[];

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
      },
      {
        label: 'Presurizador',
        icon: 'pi pi-forward',
      },
      {
        label: 'Panel Solar',
        icon: 'pi pi-sun',
      },
      {
        label: 'Contactanos',
        icon: 'pi pi-envelope',
      },
    ];
  }
}
