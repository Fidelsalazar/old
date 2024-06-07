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
//import { ModalComponent } from './modal/modal.component';

@Component({
    selector: 'app-table',
    standalone: true,
    providers: [CenterService],
    templateUrl: './table.component.html',
    styleUrl: './table.component.css',
    imports: [
        MatTableModule,
        MatPaginatorModule,
        HttpClientModule,
        MatIconModule,
        MatButtonModule,
        SliderComponent
    ]
})
export class TableComponent implements OnInit {
  centers: any[] = [];
  modificacionExitosa: boolean = false;
  displayedColumns: string[] = ['id', 'center', 'sitio', 'edit'];
  dataSource = new MatTableDataSource<CenterElement>();

  constructor(
    private centerService: CenterService,
    private dialog: MatDialog,
    private appRef: ApplicationRef
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
        console.log('Recibido en componente Tabla', data);
        // Ahora que 'centers' tiene datos, crea el 'dataSource'
        this.dataSource = new MatTableDataSource<CenterElement>(this.centers);
        this.dataSource.paginator = this.paginator;
      },
      error: (error) => {
        console.error('Error fetching data:', error);
      },
    });
  }

  openDialog(data: any, template: string): void {
    console.log('Editar centro:', data, template);

    let contenido: any;

    if (template === 'centro') {
      contenido = 'editarContenido';
    }

    const dialogRef = this.dialog.open(EditDialogComponent, {
      width: '400px',
      data: {
        use: template,
        search: contenido,
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
}
