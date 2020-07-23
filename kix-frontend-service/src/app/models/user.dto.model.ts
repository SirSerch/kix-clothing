export class User {
    role: Role;
    name: string;
    lastName: string;
    password: string;
    email: string;
    address: Address;
}

export class Address {
    streetAddress: string;
    city: string;
    country: string;
    zipCode: number;
}

export enum Role {
    ADMIN = 'ADMIN',
    CLIENT = 'CLIENT'
}