import { Component, OnInit, signal } from '@angular/core';
import { BalanceResponse } from '../../models/balance-response.model';
import { AccountService } from '../../services/account.service';
import { DecimalPipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DecimalPipe, RouterModule, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  balance = signal<BalanceResponse | null>(null);
  loading = signal<boolean>(true);
  error = signal<string | null> (null);

  constructor(private accountService: AccountService){}

  ngOnInit(): void {
      this.loadBalance();
  }

  loadBalance():void{
    this.loading.set(true);
    this.error.set(null);
  

    this.accountService.getBalance().subscribe({

      next:(data) =>{
        this.balance.set(data);
        this.loading.set(false);
      },
      error:(err)=>{
        this.error.set('Failed to load balance');
        this.loading.set(false);
        console.error(err);
      }
    });
  }

  // hasBalance():boolean{
  //   return this.balance() !== null && !this.loading() && this.error() === '';
  // }

  // hasError(): boolean{
  //   return this.error() !== ''  && !this.loading();
  // }
}
