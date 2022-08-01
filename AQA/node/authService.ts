import {ethers} from "ethers";

import {ApiClient} from "./client";
import {WalletRequest, WalletResponse} from "./wallet";

export class AuthService extends ApiClient{

    async login(req: WalletRequest) {
        return (await this.send<WalletResponse>({
            method: 'POST',
            path: '/v1/auth/login/ethereum',
            body: req,
            type: 'json'
        }));
    }

    async signMessageAndGetCode(address: string, walletResp: WalletResponse, account: ethers.Wallet): Promise<string> {
        const challengeMessage = 'Sign in to SuperFarm!\n\n\n' +
            'Click ‘Sign’ to sign in. No password is needed!\n\n' +
            'By signing in you accept the SuperFarm Terms of Service: https://superfarm.com/tos\n\n' +
            `Wallet Address: ${address.toLowerCase()}\n\n` +
            `Nonce: ${walletResp.otp}`;

        return await account.signMessage(challengeMessage);
    }
}
