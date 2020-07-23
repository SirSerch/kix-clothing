import { MatSnackBar } from '@angular/material/snack-bar';
export class SnackBar {

    constructor(
        private snackBar: MatSnackBar
    ){
    }

    public openPanel(message: string, action: string): void{
        this.snackBar.open(message, action, {
          duration: 10000,
          verticalPosition: 'top',
          horizontalPosition: 'start',
          panelClass: action !== 'ERROR' ? 'panel-success' : 'panel-error'
        });
      }
}