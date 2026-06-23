import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule],
  template: `
    <nav>
      <a routerLink="/">Dashboard</a> |
      <a routerLink="/parts">Parts</a> |
      <a routerLink="/builder">Configuration Builder</a> |
      <a routerLink="/pricing">Pricing Breakdown</a>
    </nav>

    <hr>

    <router-outlet></router-outlet>
  `
})
export class AppComponent {}
