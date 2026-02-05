import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { BalanceResponse } from "../models/balance-response.model";
import { TransactionRequest } from "../models/transaction-request.model";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})

export class AccountService{
    private baseUrl = 'http://localhost:8081/api/accounts'
    private accountId = 1; // Default Account ID

    constructor(private http:HttpClient){}

    // Get current balance

    getBalance(): Observable<BalanceResponse> {
        return this.http.get<BalanceResponse>(`${this.baseUrl}/${this.accountId}/balance`);
    }

    // Deposit money
    deposit(amount:number):Observable<BalanceResponse>{
        const request: TransactionRequest = {amount};
        return this.http.post<BalanceResponse>(`${this.baseUrl}/${this.accountId}/deposit`, request);
    }

    // Withdraw money
    Withdraw(amount:number): Observable<BalanceResponse>{
        const request: TransactionRequest = {amount};
        return this.http.post<BalanceResponse>(`${this.baseUrl}/${this.accountId}/withdraw`, request);
    }
}