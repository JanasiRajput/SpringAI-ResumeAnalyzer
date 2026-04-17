import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AnalysisService, AnalysisResponse } from './analysis.service';
import { timeout, catchError, of } from 'rxjs';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  resumeText: string = '';
  jobDescription: string = '';
  loading: boolean = false;
  result: AnalysisResponse | null = null;

  constructor(private analysisService: AnalysisService, private cdr: ChangeDetectorRef) {}

  onAnalyze() {
    // Validation: Ensure fields are not empty
    if (!this.resumeText.trim() || !this.jobDescription.trim()) {
      alert('Please provide both your Resume and the Job Description to start the analysis.');
      return;
    }

    this.loading = true;
    this.result = null;

    this.analysisService.analyze({
      resumeText: this.resumeText,
      jobDescription: this.jobDescription
    }).pipe(
      timeout(60000),
      catchError(err => {
        console.error('API Error:', err);
        this.loading = false;
        this.cdr.detectChanges();
        alert('API Error: ' + (err.message || 'Unknown error'));
        return of(null);
      })
    ).subscribe({
      next: (res) => {
        console.log('API Response received:', res);
        if (res) {
          this.result = res;
          this.loading = false;
          this.cdr.detectChanges();
          console.log('Assigned result to component state:', this.result);
        } else {
          console.warn('Received empty or null response');
          this.loading = false;
          this.cdr.detectChanges();
        }
      },
      error: (err) => {
        console.error('Subscription error:', err);
        this.loading = false;
        this.cdr.detectChanges();
      }
    });
  }
}
