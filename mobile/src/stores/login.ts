import { makeAutoObservable } from "mobx";

class LoginStore {
  username = "";
  password = "";
  isSubmitting = false;
  seePassword = false;

  constructor() {
    makeAutoObservable(this);
  }

  setUsername(value: string) {
    this.username = value;
  }

  setPassword(value: string) {
    this.password = value;
  }

  toggleSeePassword() {
    this.seePassword = !this.seePassword;
  }

  async handleLogin() {
    if (this.isFormValid) {
      this.isSubmitting = true;
      // Simulando uma requisição de login
      setTimeout(() => {
        this.isSubmitting = false;
        // Adicione a lógica de login aqui
      }, 2000);
    }
  }

  get isFormValid() {
    return this.username !== "" && this.password !== "";
  }
}

export const loginStore = new LoginStore();