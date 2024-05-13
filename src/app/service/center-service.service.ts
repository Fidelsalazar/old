import { Injectable } from '@angular/core';
import { environment } from '../config';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { CenterElement } from '../core/interface/center-element';

@Injectable({
  providedIn: 'root'
})

export class CenterService {

  private url = `${environment.apiUrl}/center/all`;

  constructor(private http: HttpClient) { }

  getAllCenters(): Observable<CenterElement[]>{
    return this.http.get<CenterElement[]>(this.url).pipe(
      tap((data) => {
        console.log('Datos recibidos:', data); // Acción secundaria (opcional)
      }),
      catchError((error) => {
        console.error('Error fetching data:', error);
        return throwError('Error al obtener los centros');
      })
    );
  }
}
