import { Injectable } from '@angular/core';
import { environment } from '../config';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { AireElement } from '../core/interface/aire-element';

@Injectable({
  providedIn: 'root',
})
export class SplitService {
  public modificacionExitosaSubject = new BehaviorSubject<boolean>(false);
  private url = `${environment.apiUrl}/split`;

  constructor(private http: HttpClient) {}

  getAllSplits(): Observable<AireElement[]> {
    let dir = this.url + '/all';
    return this.http.get<AireElement[]>(dir).pipe(
      tap((data) => {
        console.log('Datos recibidos:', data);
      }),
      catchError((error) => {
        console.error('Error fetching data:', error);
        return throwError('Error al obtener los centros');
      })
    );
  }

  deleteAire(id: any): Observable<string> {
    console.log(`${environment.apiUrl}/split/delete/${id}`);
    return this.http.delete<string>(
      `${environment.apiUrl}/aireVentana/delete/${id}`
    );
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
