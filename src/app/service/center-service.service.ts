import { Injectable } from '@angular/core';
import { environment } from '../config';
import { HttpClient } from '@angular/common/http';
import {
  BehaviorSubject,
  Observable,
  Subject,
  catchError,
  tap,
  throwError
} from 'rxjs';
import { CenterElement } from '../core/interface/center-element';

@Injectable({
  providedIn: 'root',
})
export class CenterService {
  public modificacionExitosaSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {}

  getAllCenters(): Observable<CenterElement[]> {
    let url = `${environment.apiUrl}/center/all`;
    return this.http.get<CenterElement[]>(url).pipe(
      tap((data) => {
        console.log('Datos recibidos:', data);
      }),
      catchError((error) => {
        console.error('Error fetching data:', error);
        return throwError('Error al obtener los centros');
      })
    );
  }
  newCenter(center: any): Observable<any>{
    console.log("CenterService", center)
    return this.http.post(`${environment.apiUrl}/center`, center).pipe(
      tap((res: any) => {
        console.log('Datos recibidos', res);
      }),
      catchError((error) => {
        console.error('Error fetching data:', error);
        return throwError('Error al obtener los inversores');
      })
    );
  }

  modCenter(datos: any): Observable<any> {
    const datosFormulario = {
      center: datos.nombreCentro,
      sitio: datos.ubicacionCentro,
    };

    return this.http
      .patch<any>(`${environment.apiUrl}/center/${datos.id}`, datosFormulario)
      .pipe(catchError(this.handleError));
  }

  deleteCenter(id:any):Observable<string>{
    return this.http
      .delete<string>(`${environment.apiUrl}/center/delete/${id}`)

  }

  private handleError(error: any) {
    if (error.error instanceof ErrorEvent) {
      // Error del cliente, como una red inalcanzable
      console.error('Ocurrió un error:', error.error.message);
    } else {
      // Error del servidor
      console.error(
        `Código de error ${error.status}, ` + `body: ${error.error}`
      );
    }
    // Retorna un observable con un mensaje de error legible
    return throwError(
      'Ocurrió un error, por favor intenta nuevamente más tarde.'
    );
  }

  getModificacionExitosaSubject(): Observable<boolean> {
    return this.modificacionExitosaSubject.asObservable();
  }

  setModificacionExitosa(modificacionExitosa: boolean): void {
    this.modificacionExitosaSubject.next(modificacionExitosa);
  }
}
