import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

export const AppConfig = {
  apiUrl: 'http://localhost:8081/api'
};

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
