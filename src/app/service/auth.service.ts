import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Usuarios y contraseñas predefinidos
  private users = [{ email: 'usuario@etecsa.cu', password: '1234' }];

  constructor(private router: Router) {}

  // Método para autenticar al usuario
  authenticate(email: string, password: string): boolean {
    const user = this.users.find(
      (u) => u.email === email && u.password === password
    );
    if (user) {
      this.router.navigate(['/center']); // Redirige al usuario si las credenciales son correctas
      return true;
    }
    return false;
  }

  logout() {
    this.router.navigate(['/']);
  }
}
