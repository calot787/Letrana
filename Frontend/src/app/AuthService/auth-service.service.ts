import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // LOGIN (Spring Security por defecto)
  login(email: string, password: string): Observable<string> {
    const body = new URLSearchParams();
    body.set('username', email);
    body.set('password', password);

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    return this.http.post(
      `${this.apiUrl}/auth/login`,
      body.toString(),
      {
        headers,
        withCredentials: true,
        responseType: 'text'
      }
    );
  }

  // REGISTRO
  registro(nombre: string, email: string, password: string): Observable<any> {
    return this.http.post(
      `${this.apiUrl}/usuarios/registro`,
      { nombre, email, password },
      { withCredentials: true }
    );
  }

  // PERFIL (usa tu @GetMapping("/perfil"))
  perfil(): Observable<any> {
    return this.http.get(
      `${this.apiUrl}/usuarios/perfil`,
      { withCredentials: true }
    );
  }
}
