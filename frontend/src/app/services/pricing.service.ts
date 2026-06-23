import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PricingService {
  constructor(private api: ApiService) {}

  breakdown(configurationId: number): Observable<any> {
    return this.api.get(`pricing/${configurationId}`);
  }
}
