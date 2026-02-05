import { Routes } from '@angular/router';
import { Dashboard } from './components/dashboard/dashboard';
import { Deposit } from './components/deposit/deposit';
import { Withdraw } from './components/withdraw/withdraw';
import { TransactionList } from './components/transaction-list/transaction-list';

export const routes: Routes = [
    {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
    {path: 'dashboard', component: Dashboard},
    {path: 'deposit', component: Deposit},
    {path: 'withdraw', component: Withdraw},
    {path: 'transactions', component: TransactionList}
];
