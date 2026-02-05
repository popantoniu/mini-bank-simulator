import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../../services/transaction.service';
import { TransactionResponse } from '../../models/transaction-response.model';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DatePipe, DecimalPipe } from '@angular/common';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-transaction-list',
  standalone: true,
  imports: [FormsModule, DatePipe, DecimalPipe, CommonModule],
  templateUrl: './transaction-list.html',
  styleUrl: './transaction-list.css',
})
export class TransactionList implements OnInit {
  transactions: TransactionResponse[] = [];
  loading: boolean = true;
  error: string = '';

  constructor(
    private transactionService: TransactionService, 
    private router: Router
  ){}

  ngOnInit(): void {
      this.loadTransactions();
  }

  loadTransactions():void{
    this.loading = true;
    this.error = '';

    this.transactionService.getTransactions().subscribe({
      next:(data) =>{
        this.transactions = data;
        this.loading = false;
      },
      error:(err) => {
        this.error = 'Failed to load transactions';
        this.loading = false;
        console.error(err);
      }
    });
  }

  goBack(): void{
    this.router.navigate(['/dashboard']);
  }

  getTransactionClass(type:string): string{
    return type === 'DEPOSIT' ? 'transaction-deposit' : 'transaction-withdrawal';
  }
}
