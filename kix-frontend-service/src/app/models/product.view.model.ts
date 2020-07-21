export class ProductView {
    productId: string;
    productName: string;
    productDescription: string;
    productPrice: number;
    productTags: ProductTags;
    productImages: Gallery;

    indexed: boolean;
    createdTime: Date;
    lastUpdateTime: Date;
    lastIndexedTime: Date;
    isFavorite: boolean;
}

class Gallery {
    galleryId: number;
    images: string[];
}

class ProductTags {
    category: string;
    color: string;
}