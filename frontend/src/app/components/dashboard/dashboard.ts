import { Component, OnInit } from '@angular/core';
import { BalanceResponse } from '../../models/balance-response.model';
import { AccountService } from '../../services/account.service';
import { DecimalPipe } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DecimalPipe, RouterModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  balance: BalanceResponse | null = null;
  loading: boolean = true;
  error: string = '';

  constructor(private accountService: AccountService){}

  ngOnInit(): void {
      this.loadBalance();
  }

  loadBalance():void{
    this.loading = true;
    this.error = '';

    this.accountService.getBalance().subscribe({

      next:(data) =>{
        this.balance = data;
        this.loading = false;
      },
      error:(err)=>{
        this.error = 'Failed to load balance';
        this.loading = false;
        console.error(err);
      }
    });
  }
}
