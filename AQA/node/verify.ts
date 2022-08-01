
export type VerifyRequest = {
    sessionId: string;
    code: string;
}

export interface VerifyResponse {
    accessToken: string;
    refreshToken: string;
}
