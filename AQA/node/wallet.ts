import {ethers} from "ethers";

export type WalletRequest = {
    address: string
    netId: number
    audience: string,
    useAccount: ethers.Wallet;
}

export interface WalletResponse {
    sessionId: string;
    otp: string;
}
