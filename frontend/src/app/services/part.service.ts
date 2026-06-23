import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PartService {
  constructor(private api: ApiService) {}

  list(): Observable<any> {
    return this.api.get('parts');
  }

  get(id: number): Observable<any> {
    return this.api.get(`parts/${id}`);
  }

  create(part: any): Observable<any> {
    return this.api.post('parts', part);
  }

  update(id: number, part: any): Observable<any> {
    return this.api.put(`parts/${id}`, part);
  }

  delete(id: number): Observable<any> {
    return this.api.delete(`parts/${id}`);
  }
}
