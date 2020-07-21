export class ProductView {
    productId: string;
    productName: string;
    productDescription: string;
    productPrice: number;
    productTags: Map<string, string>;
    productImages: Gallery;

    isIndexed: boolean;
    createdTime: Date;
    lastUpdateTime: Date;
    lastIndexedTime: Date;
}

class Gallery {
    galleryId: number;
    images: string[];
}