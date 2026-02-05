import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TransactionResponse } from "../models/transaction-response.model";


@Injectable({
    providedIn: 'root'
})

export class TransactionService{
    private baseUrl = 'http://localhost:8081/api/accounts';
    private accountId = 1; // Default Account ID

    constructor(private http: HttpClient){}

    // Get all transactions
    getTransactions(): Observable<TransactionResponse[]>{
        return this.http.get<TransactionResponse[]>(`${this.baseUrl}/${this.accountId}/transactions`);
    }
}