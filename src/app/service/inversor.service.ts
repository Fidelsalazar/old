import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, tap, throwError } from 'rxjs';
import { environment } from '../config';
import { HttpClient } from '@angular/common/http';
import { InversorElement } from '../core/interface/inversor-element';

@Injectable({
  providedIn: 'root',
})
export class InversorService {
  public modificacionExitosaSubject = new BehaviorSubject<boolean>(false);
  private url = `${environment.apiUrl}/inversor`;

  constructor(private http: HttpClient) {}

  newInversor(aire: any): Observable<any> {
    return this.http.post(this.url, aire).pipe(
      tap((res: any) => {
        console.log('Datos recibidos', res);
      }),
      catchError((error) => {
        console.error('Error fetching data:', error);
        return throwError('Error al obtener los inversores');
      })
    );
  }

  getAllInversor(): Observable<InversorElement[]> {
    let dir = this.url + '/all';

    return this.http.get<InversorElement[]>(dir).pipe(
      tap((data) => {
        console.log('Datos recibidos:', data);
      }),
      catchError((error) => {
        console.error('Error fetching data:', error);
        return throwError('Error al obtener los centros');
      })
    );
  }

  deleteInversor(id: any): Observable<string> {
    console.log(`${environment.apiUrl}/delete/${id}`);
    return this.http.delete<string>(`${environment.apiUrl}/inversor/delete/${id}`);
  }

  getModificacionExitosaSubject(): Observable<boolean> {
    return this.modificacionExitosaSubject.asObservable();
  }

  setModificacionExitosa(modificacionExitosa: boolean): void {
    this.modificacionExitosaSubject.next(modificacionExitosa);
  }
}
