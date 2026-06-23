import { Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { PartManagementComponent } from './components/part-management/part-management.component';
import { ConfigurationBuilderComponent } from './components/configuration-builder/configuration-builder.component';
import { PricingBreakdownComponent } from './components/pricing-breakdown/pricing-breakdown.component';
export const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: 'parts', component: PartManagementComponent },
  { path: 'builder', component: ConfigurationBuilderComponent },
  { path: 'pricing', component: PricingBreakdownComponent },
  { path: '**', redirectTo: '' }
];
