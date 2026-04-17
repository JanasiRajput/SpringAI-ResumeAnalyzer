import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AnalysisRequest {
  resumeText: string;
  jobDescription: string;
}

export interface AnalysisResponse {
  matchScore: number;
  missingSkills: string[];
  suggestions: string[];
}

@Injectable({
  providedIn: 'root'
})
export class AnalysisService {
  private apiUrl = 'http://localhost:8080/analyze';

  constructor(private http: HttpClient) {}

  analyze(request: AnalysisRequest): Observable<AnalysisResponse> {
    return this.http.post<AnalysisResponse>(this.apiUrl, request);
  }
}
