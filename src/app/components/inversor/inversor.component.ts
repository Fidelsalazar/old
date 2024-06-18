import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PrimeIcons, MenuItem } from 'primeng/api';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { AireService } from '../../service/aire-service.service';
import { MatDialog } from '@angular/material/dialog';
import { NavbarComponent } from "../navbar/navbar.component";
import { InversorService } from '../../service/inversor.service';

@Component({
    selector: 'app-inversor',
    standalone: true,
    providers: [InversorService],
    templateUrl: './inversor.component.html',
    styleUrl: './inversor.component.css',
    imports: [
        TableModule,
        CommonModule,
        ButtonModule,
        MatIconModule,
        HttpClientModule,
        NavbarComponent
    ]
})
export class InversorComponent {
  customers: any[] = [];
  items: MenuItem[] = [];

  first = 0;

  rows = 10;

  constructor(private inversorService: InversorService, private dialog: MatDialog) {}

  ngOnInit() {
    this.getInversor();
    this.items = [
      {
        label: 'New',
        icon: PrimeIcons.PLUS,
      },
      {
        label: 'Delete',
        icon: PrimeIcons.TRASH,
      },
    ];
  }

  getInversor(): void {
    this.inversorService.getAllInversor().subscribe({
      next: (response: any[]) => {
        this.customers = response;
        console.log('Recibido en componente Tabla', response);
      },
      error: (error: any) => {
        console.error('Error fetching data:', error);
      },
    });
  }

  openDialog(data: any, template: string, action: string): void {
    console.log('Editar Inversor:', data, template);

    let content: any;

    if (template === 'Inversor') {
      switch (action) {
        case 'edit':
          content = 'editarInversor';
          break;
        case 'new':
          content = 'newInversor';
          break;
      }
    }

    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '800px',
      data: {
        use: template,
        search: content,
        data: data,
      },
    });

    dialogRef.componentInstance.modificationSuccess.subscribe(() => {
      this.getInversor(); // Actualizar la tabla después de una modificación exitosa
    });
  }

  deleteAire(id: any): void {
    console.log(id.id);
    this.inversorService.deleteInversor(id.id).subscribe({
      next: (data: any) => {
        console.log(data);
        this.getInversor();
      },
      error: (error: any) => {
        console.error('Error fetching data:', error);
      },
    });
  }

  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.getInversor();
    this.first = 0;
  }

  pageChange(event: { first: number; rows: number }) {
    this.first = event.first;
    this.rows = event.rows;
  }

  isLastPage(): boolean {
    return this.customers
      ? this.first === this.customers.length - this.rows
      : true;
  }

  isFirstPage(): boolean {
    return this.customers ? this.first === 0 : true;
  }
}
