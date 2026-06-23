import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ConfigurationService {
  constructor(private api: ApiService) {}

  list(): Observable<any> {
    return this.api.get('configurations');
  }

  get(id: number): Observable<any> {
    return this.api.get(`configurations/${id}`);
  }

  create(payload: any): Observable<any> {
    return this.api.post('configurations', payload);
  }

  update(id: number, payload: any): Observable<any> {
    return this.api.put(`configurations/${id}`, payload);
  }
}
