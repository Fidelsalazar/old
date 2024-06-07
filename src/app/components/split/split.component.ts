import { Component } from '@angular/core';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { HttpClientModule } from '@angular/common/http';
import { AireService } from '../../service/aire-service.service';
import { MatIconModule } from '@angular/material/icon';
import { PrimeIcons, MenuItem } from 'primeng/api';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { SplitService } from '../../service/split-service';

@Component({
  selector: 'app-split',
  standalone: true,
  imports: [
    TableModule,
    CommonModule,
    ButtonModule,
    MatIconModule,
    HttpClientModule,
  ],
  providers: [SplitService],
  templateUrl: './split.component.html',
  styleUrl: './split.component.css',
})
export class SplitComponent {
  customers: any[] = [];
  items: MenuItem[] = [];

  first = 0;

  rows = 10;

  constructor(private aireService: SplitService, private dialog: MatDialog) {}

  ngOnInit() {
    this.getAire();
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

  getAire(): void {
    this.aireService.getAllSplits().subscribe({
      next: (response) => {
        this.customers = response;
        console.log('Recibido en componente Tabla', response);
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });
  }

  openDialog(data: any, template: string): void {
    console.log('Editar centro:', data, template);

    let content: any;

    if (template === 'Aire') {
      content = 'editarAire';
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
      this.getAire(); // Actualizar la tabla después de una modificación exitosa
    });
  }

  deleteAire(id: any): void {
    console.log(id.id);
    this.aireService.deleteAire(id.id).subscribe({
      next: (data) => {
        console.log(data);
        this.getAire();
      },
      error: (error) => {
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
