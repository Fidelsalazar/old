import {
  Component,
  Inject,
  OnInit,
  TemplateRef,
  ViewContainerRef,
  ViewChild,
  Output,
  EventEmitter,
  model
} from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MAT_DIALOG_DATA,
  MatDialogRef
} from '@angular/material/dialog';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  FormsModule,
  Validators
} from '@angular/forms';
import { InputGroupModule } from 'primeng/inputgroup';
import { InputGroupAddonModule } from 'primeng/inputgroupaddon';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CenterService } from '../../service/center-service.service';
import { HttpClientModule } from '@angular/common/http';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { TreeSelectModule } from 'primeng/treeselect';
import { AireService } from '../../service/aire-service.service';
import { ScrollTopModule } from 'primeng/scrolltop';
import { CalendarModule } from 'primeng/calendar';

@Component({
  selector: 'app-edit-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    InputGroupModule,
    InputGroupAddonModule,
    HttpClientModule,
    InputTextModule,
    ButtonModule,
    CommonModule,
    ScrollPanelModule,
    TreeSelectModule,
    ScrollTopModule,
    CalendarModule,
  ],
  providers: [CenterService, AireService],
  templateUrl: './edit-dialog.component.html',
  styleUrl: './edit-dialog.component.css',
})
export class EditDialogComponent implements OnInit {
  nodes!: any[];
  value: string | undefined;

  use: string;
  template: string;
  center: any;
  selectedNodes: any;

  centerForm: FormGroup = new FormGroup({});
  aireForm: FormGroup = new FormGroup({});

  @Output() modificationSuccess = new EventEmitter<void>();

  @ViewChild('container', { read: ViewContainerRef, static: true })
  container!: ViewContainerRef;

  @ViewChild('editarCentro', { static: true })
  editarContenidoTemplate!: TemplateRef<any>;
  @ViewChild('editarAire', { static: true })
  editarContenidoTemplateAire!: TemplateRef<any>;
  @ViewChild('newAire', { static: true })
  newAireContenidoTemplate!: TemplateRef<any>;

  constructor(
    private centerService: CenterService,
    private aireService: AireService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<EditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.use = data.use;
    this.template = data.search;
    this.center = data.data;

    this.centerService.getAllCenters().subscribe({
      next: (response) => {
        console.log('List de centros', response);
        this.nodes = response.map((center) => ({
          label: center.center,
          data: {
            sitio: center.sitio,
            id: center.id,
          },
        }));
      },
    });
  }

  ngOnInit(): void {
    console.log('Edit component', this.use, this.template, this.center);
    if (this.template === 'editarContenido') {
      this.container.createEmbeddedView(this.editarContenidoTemplate);
      this.initFormCenter();
    } else if (this.template === 'editarAire') {
      this.container.createEmbeddedView(this.editarContenidoTemplateAire);
      this.initFormAire();
    } else if (this.template === 'newAire') {
      this.container.createEmbeddedView(this.newAireContenidoTemplate);
      this.initFormAire();
    }

    this.centerService.getModificacionExitosaSubject().subscribe(() => {
      // Aquí puedes realizar las actualizaciones necesarias en el componente
      console.log(
        'La modificación se ha completado correctamente, actualizando el componente...'
      );

      this.modificationSuccess.emit();
    });
  }

  initFormCenter() {
    this.centerForm = this.fb.group({
      id: [this.center.id, Validators.required],
      nombreCentro: [''],
      ubicacionCentro: [''],
    });
  }

  initFormAire() {
    this.centerForm = this.fb.group({
      id: [this.center.id || ''],
      numActivo: [''],
      local: [''],
      category: [''],
      marca: [''],
      model: [''],
      country: [''],
      v: [''],
      refrigerante: [''],
      capacidad: [''],
      dateI: [''],
      tecnicalS: [''],
      priority: [''],
      center: [''],
    });
  }

  sendCenterMod() {
    console.log('Servicio modCenter', this.centerForm.value);
    console.log('sendCenter', this.center);
    if (this.centerForm.valid) {
      const datosFormulario = this.centerForm.value;
      if (datosFormulario.ubicacionCentro === '') {
        datosFormulario.ubicacionCentro = this.center.sitio;
      }
      if (datosFormulario.nombreCentro === '') {
        datosFormulario.nombreCentro = this.center.center;
      }
      console.log(datosFormulario);
      this.centerService.modCenter(datosFormulario).subscribe({
        next: (response) => {
          console.log('Datos enviados correctamente:', response.status);
          this.centerService.setModificacionExitosa(true);
          this.modificationSuccess.emit();
          this.close();
        },
        error: (error) => {
          console.error('El formulario no es válido:', error);
        },
      });
    }
  }

  sendCenterCreate() {
    console.log('Servicio createAire', this.centerForm.value);
    const formData = this.centerForm.value;

    const centerExist = this.nodes.find((data) => {
      console.log('Comparando:', data.label, 'con', formData.center.label);
      return data.label === formData.center.label;
    });

    console.log('Center exist', centerExist);

    const centerData = formData.center
      ? {
          id: centerExist.data.id,
          center: formData.center.label,
          sitio: centerExist.data.sitio,
        }
      : {};

    console.log(centerData);

    const modData = {
      ...formData,
      center: centerData,
    };

    delete modData.center.label;
    delete modData.center.data;

    console.log('Datos modificados para enviar:', modData);

    if (this.centerForm.valid) {
      this.aireService.newAire(modData).subscribe({
        next: (response) => {
          this.close();
          console.log('Datos enviados correctamente:', response.status);
        },
        error: (error) => {
          this.close();
          console.error('El formulario no es válido:', error);
        },
      });
    }
  }

  close(): void {
    this.dialogRef.close();
  }
}
