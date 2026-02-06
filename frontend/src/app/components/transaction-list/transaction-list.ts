import { Component, OnInit, signal } from '@angular/core';
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
  transactions = signal<TransactionResponse[]> ([]);
  loading = signal<boolean>(true);
  error = signal<string | null>(null);

  constructor(
    private transactionService: TransactionService, 
    private router: Router
  ){}

  ngOnInit(): void {
      this.loadTransactions();
  }

  loadTransactions():void{
    this.loading.set(true);
    this.error.set(null);

    this.transactionService.getTransactions().subscribe({
      next:(data) =>{
        this.transactions.set(data);
        this.loading.set(false);
      },
      error:(err) => {
        this.error.set('Failed to load transactions');
        this.loading.set(false);
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
