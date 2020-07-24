import { UserView, Role } from '../models';

export class AuthUtil {
    static setSession(user: UserView): void{
        let session = user.id + '::' + user.name + '::' + user.role;
        localStorage.setItem('KSESSION', btoa(session));
    }

    static getSession(): string {
        if (localStorage.getItem('KSESSION')) {
        return atob(localStorage.getItem('KSESSION'));
        }
    }

    static isAuth(): boolean{
        if(this.getSession() !== null && this.getSession() !== undefined){
            return true;
        }
        return false;
    }

    static getRole(): Role {
        const session = this.getSession();
        const role = session.split('::')[2];
        if (role === 'ADMIN') { return Role.ADMIN; }
        return Role.CLIENT;
    }

    static getUserName(): string {
        const session = this.getSession();
        return session.split('::')[1];
    }

    static getId(): number {
        return parseInt(this.getSession().split('::')[0]);
    }

    static logOut(): boolean {
        localStorage.removeItem('KSESSION');
        return this.isAuth();
    }

    static isAdmin(): boolean {
        if(this.getRole() === Role.ADMIN) return true;
        return false;
    }

    static isClient(): boolean {
        if(this.getRole() === Role.CLIENT) return true;
        return false;
    }
}