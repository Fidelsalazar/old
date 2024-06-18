import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";

@Component({
    selector: 'app-panel-solar',
    standalone: true,
    templateUrl: './panel-solar.component.html',
    styleUrl: './panel-solar.component.css',
    imports: [NavbarComponent]
})
export class PanelSolarComponent {

}
