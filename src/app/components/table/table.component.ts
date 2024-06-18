import { AfterViewInit, ApplicationRef, ChangeDetectorRef, Component, EventEmitter, OnInit, Output, TemplateRef, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CenterService} from '../../service/center-service.service';
import { CenterElement } from '../../core/interface/center-element';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { EditDialogComponent } from '../edit-dialog/edit-dialog.component';
import { SliderComponent } from "../slider/slider.component";
import { NavbarComponent } from "../navbar/navbar.component";
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { RatingModule } from 'primeng/rating';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { TableRowCollapseEvent, TableRowExpandEvent } from 'primeng/table';
//import { ReactiveFormsModule, FormControl, FormGroup } from '@angular/forms';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DialogModule } from '@angular/cdk/dialog';

@Component({
  selector: 'app-table',
  standalone: true,
  providers: [CenterService, MessageService],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
  imports: [
    MatTableModule,
    MatPaginatorModule,
    HttpClientModule,
    MatIconModule,
    MatButtonModule,
    SliderComponent,
    NavbarComponent,
    TableModule,
    TagModule,
    ToastModule,
    RatingModule,
    ButtonModule,
    CommonModule,
    DialogModule,
    //ReactiveFormsModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class TableComponent implements OnInit {
  centers: any[] = [];
  modificacionExitosa: boolean = false;
  dialogVisible: boolean = false;
  displayedColumns: string[] = ['id', 'center', 'sitio', 'edit'];
  dataSource = new MatTableDataSource<CenterElement>();
  expandedRows = {};

  constructor(
    private centerService: CenterService,
    private dialog: MatDialog,
    private appRef: ApplicationRef,
    private messageService: MessageService
  ) {}

  @Output() modificationSuccess = new EventEmitter<void>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngOnInit(): void {
    this.getCenters();
    this.modificationSuccess.emit();
  }

  ngAfterViewInit() {
    this.dataSource = new MatTableDataSource<CenterElement>(this.centers); // Crea el dataSource aquí
    this.dataSource.paginator = this.paginator;
  }

  getCenters(): void {
    this.centerService.getAllCenters().subscribe({
      next: (data) => {
        this.centers = data;
        console.log('Recibido en componente Tabla', this.centers);
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });
  }

  openDialog(data: any, template: string, action: string): void {
    console.log('Editar centro:', data, template, action);

    let content: any;

    if (template === 'Centro') {
      switch(action){
        case 'edit':
          content = 'editarCentro';
          break;
        case 'new':
          content = 'newCentro';
          break;
      }
    }

    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '400px',
      data: {
        use: template,
        search: content,
        data: data,
      },
    });

    dialogRef.componentInstance.modificationSuccess.subscribe(() => {
      this.getCenters(); // Actualizar la tabla después de una modificación exitosa
    });
  }

  deleteCenter(id: any): void {
    this.centerService.deleteCenter(id.id).subscribe({
      next: (data) => {
        console.log(data);
        this.getCenters();
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });
  }

  editarCentro(center: any): void {
    // Implementa la lógica de edición aquí (por ejemplo, abrir un diálogo)
    console.log('Editar centro:', center);
  }

  /*expandAll() {
    this.expandedRows = this.products.reduce(
      (acc, p) => (acc[p.id] = true) && acc,
      {}
    );
  }*/

  collapseAll() {
    this.expandedRows = {};
  }

  getSeverity(status: string) {
    switch (status) {
      case 'INSTOCK':
        return 'success';
      case 'LOWSTOCK':
        return 'warning';
      case 'OUTOFSTOCK':
        return 'danger';
      default:
        return undefined;
    }
  }

  getStatusSeverity(status: string) {
    switch (status) {
      case 'PENDING':
        return 'warning';
      case 'DELIVERED':
        return 'success';
      case 'CANCELLED':
        return 'danger';
      default:
        return undefined;
    }
  }

  onRowExpand(event: TableRowExpandEvent) {
    this.messageService.add({
      severity: 'info',
      summary: 'Product Expanded',
      detail: event.data.name,
      life: 3000,
    });
  }

  onRowCollapse(event: TableRowCollapseEvent) {
    this.messageService.add({
      severity: 'success',
      summary: 'Product Collapsed',
      detail: event.data.name,
      life: 3000,
    });
  }

  showDialog() {
    this.dialogVisible = true;
  }
}
