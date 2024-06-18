import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { ReactiveFormsModule, FormGroup, FormsModule, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, FormsModule],
  providers: [AuthService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm: FormGroup = new FormGroup({});

  constructor(private authService: AuthService, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.initLoginForm();
  }
  initLoginForm() {
    this.loginForm = this.fb.group({
      email: [''],
      pass: [''],
    });
  }

  // Método para manejar el envío del formulario
  onLogin(): void {
    console.log(this.loginForm.value);
    if (this.authService.authenticate(
      this.loginForm.controls['email'].value,
      this.loginForm.controls['pass'].value)) {
      // Autenticación exitosa
      console.log('Login exitoso');
    } else {
      // Autenticación fallida
      console.log('Credenciales incorrectas');
    }
  }
}
