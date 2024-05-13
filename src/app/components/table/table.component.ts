import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { CenterService} from '../../service/center-service.service';
import { CenterElement } from '../../core/interface/center-element';
import { HttpClientModule } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { ModalComponent } from './modal/modal.component';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [
    MatTableModule,
    MatPaginatorModule,
    HttpClientModule,
    MatIconModule,
    MatButtonModule,
  ],
  providers: [CenterService],
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent implements OnInit {
  centers: any[] = [];
  displayedColumns: string[] = ['id', 'center', 'sitio', 'edit'];
  dataSource = new MatTableDataSource<CenterElement>();

  constructor(
    private centerService: CenterService,
    private dialog: MatDialog
  ) {}

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  ngOnInit(): void {
    this.getCenters();
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

  editarCentro(center: any): void {
    // Implementa la lógica de edición aquí (por ejemplo, abrir un diálogo)
    console.log('Editar centro:', center);
  }

  abrirVentanaModal(): void {
    const dialogRef = this.dialog.open(ModalComponent, {
      width: '400px', // Ajusta el ancho según tus necesidades
      // Puedes agregar más opciones aquí (por ejemplo, data para pasar datos al modal)
    })

  dialogRef.afterClosed().subscribe((result) => {
      // Lógica después de cerrar la ventana modal (si es necesario)
    });
  }
  
}
