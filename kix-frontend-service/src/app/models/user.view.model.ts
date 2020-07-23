import { Role } from '.';

export class UserView {
    id: number;
    role: Role;
    name: string;
    lastName: string;
    password: string;
    email: string;
    address: Address;
}

class Address {
    id: number;
    streetAddress: string;
    city: string;
    country: string;
    zipCode: number;
}

