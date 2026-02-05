export interface TransactionResponse {
  id: number;
  type: 'DEPOSIT' | 'WITHDRAW';
  amount: number;
  date: string;
}