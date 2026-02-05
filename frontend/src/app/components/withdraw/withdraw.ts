import { Component } from '@angular/core';
import { AccountService } from '../../services/account.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-withdraw',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './withdraw.html',
  styleUrl: './withdraw.css',
})
export class Withdraw {
  amount: number = 0;
  loading: boolean = false;
  error: string = '';
  succes: string = '';

  constructor(
    private accountService: AccountService,
    private router: Router
  ){}

  onSubmit():void{
    this.error = '';
    this.succes = '';
    // Validation
    if(this.amount <= 0){
      this.error = 'Please enter a positive amount';
      return;
    }

    this.loading = true;

    this.accountService.Withdraw(this.amount).subscribe({
      next: (response) => {
        this.succes = `Successfully withdrew $${this.amount.toFixed(2)}. New balance: $${response.balance.toFixed(2)}`;
        this.loading = false;
        this.amount = 0;

        //Redirect to dashboard after 2 seconds
        setTimeout(() =>{
          this.router.navigate(['/dashboard']);
        }, 2000);
      },
      error:(err) => {
        if(err.error && typeof err.error === 'string'){
          this.error = err.error;
        }else{
          this.error = 'Failed to withdraw money.Please try again';
        }
        this.loading = false;
        console.error(err);
      }
    });
  }
  goBack(): void{
    this.router.navigate(['/dashboard']);
  }
}
