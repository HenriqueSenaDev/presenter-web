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

export interface IParticipation {
    eventRole: string,
    event: {
        id: number,
        name: string,
        description: string
    }
}

export interface IEvent {
    id: number;
    name: string;
    description: string;
    teams: Team[];
}

export interface Team {
    id: number;
    name: string;
    project: string;
    classroom: string;
    presented: boolean;
    members: string[];
    average: number;
    avaliationsQuantity: number;
}

export interface IAvaliation {
    userId: number;
    teamId: number;
    value: number;
}