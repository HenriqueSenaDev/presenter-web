export interface IUserProfile {
    id: number,
    username: string
    role: string
}

export interface IUserCredentials {
    access_token: string,
    refresh_token: string,
    profile: IUserProfile
}

export interface IJWT {
    access_token: string,
    refresh_token: string,
}