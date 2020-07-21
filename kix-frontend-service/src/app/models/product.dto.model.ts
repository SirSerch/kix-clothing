export class Product{
    productName: string;
    productDescription: string;
    productPrice: number;
    productTags: Map<string, string>;
    productImages: string[];

    constructor(json: any, productImages: string[]){
        this.productName = json.productName;
        this.productDescription = json.productDescription;
        this.productPrice = json.productPrice;
        this.productImages = productImages;
        this.productTags = new Map();
        this.productTags.set('color', json.productColor);
        this.productTags.set('category', json.productCategory);
    }
}