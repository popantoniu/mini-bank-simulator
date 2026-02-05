import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../../services/account.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-deposit',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './deposit.html',
  styleUrl: './deposit.css',
})
export class Deposit {
  amount: number = 0;
  loading : boolean = false;
  error: string = '';
  success: string = '';

  constructor(
    private accountService:AccountService,
    private router: Router
  ){}

  onSubmit():void{
    this.error = '';
    this.success = '';

    // Validation
    if(this.amount<= 0){
      this.error = 'Please enter a positive amount';
      return;
    }

    this.loading = true;

    this.accountService.deposit(this.amount).subscribe({
      next:(response) =>{
        this.success = `Successfully deposited $${this.amount.toFixed(2)}. New balance: $${response.balance.toFixed(2)}`;
        this.loading = false;
        this.amount = 0;

        // Redirect to dashboard after 2 seconds
        setTimeout(() => {
          this.router.navigate(['/dashboard']);
        }, 2000); 
      },
      error: (err) => {
        this.error = 'Failed to deposit money. Please try again.';
        this.loading = false;
        console.error(err);
      }
    });

  }
  goBack():void{
    this.router.navigate(['/dashboard']);
  }

}
