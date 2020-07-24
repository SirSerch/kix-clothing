import {ProductView} from './product.view.model';
export class Favorite {
    action: FavoriteAction;
    product: ProductView;

    constructor(action: FavoriteAction, product: ProductView){
        this.action = action;
        this.product = product;
    }
}

export enum FavoriteAction{
    ADD = 'ADD',
    REMOVE = 'REMOVE'
}