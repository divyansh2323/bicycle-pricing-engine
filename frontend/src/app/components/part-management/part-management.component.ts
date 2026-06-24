import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PartService } from '../../services/part.service';

@Component({
  selector: 'app-part-management',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './part-management.component.html',
  styleUrls: ['./part-management.component.css']
})
export class PartManagementComponent implements OnInit {

  parts: any[] = [];

  newPart = {
    name: '',
    category: '',
    price: 0
  };

  constructor(private partService: PartService) {}

  ngOnInit(): void {
    this.loadParts();
  }

  loadParts(): void {
    this.partService.list().subscribe(data => {
      this.parts = data;
    });
  }

  addPart(): void {
    this.partService.create(this.newPart).subscribe(() => {
      this.newPart = {
        name: '',
        category: '',
        price: 0
      };
      this.loadParts();
    });
  }

  deletePart(id: number): void {
    this.partService.delete(id).subscribe(() => {
      this.loadParts();
    });
  }
}
