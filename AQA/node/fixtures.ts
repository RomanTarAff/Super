import {test as base} from '@playwright/test';
import {readFile} from "fs/promises";
import {load} from "js-yaml";
import {AuthService} from "./authService";
import {MetamaskHelper} from "./MetamaskHelper";
import {VerifyService} from "./verifyService";
import {VerifyRequest} from "./verify";

type TestFixtures = {

};

type Service = {
    authDev: AuthService,
    verifyDev: VerifyService,

    authTest: AuthService,
    verifyTest: VerifyService
};

type AccessTokenDev = {
    accessTokenMintDev: string,
    accessTokenBuyDev: string,
    accessTokenAdminDev: string
}

type AccessTokenTest = {
    accessTokenMintTest: string,
    accessTokenBuyTest: string,
    accessTokenAdminTest: string
}

type WorkerFixtures = {
    service: Service;
    authModeDev: AccessTokenDev;
    authModeTest: AccessTokenTest;
};


export const test = base.extend<TestFixtures, WorkerFixtures>({

    service: [
        // eslint-disable-next-line no-empty-pattern
        async ({}, use) => {
            const yamlDevelop = load(await readFile(`config.develop.yml`, "utf8"));
            const yamlTest = load(await readFile(`config.test.yml`, "utf8"));
            // eslint-disable-next-line @typescript-eslint/ban-ts-comment
            // @ts-ignore
            const urlsDev = yamlDevelop.baseUrls;
            const urlsTest = yamlTest.baseUrls;
            use({
                authDev: new AuthService(urlsDev.authBaseUrl),
                verifyDev: new VerifyService(urlsDev.authBaseUrl),
                authTest: new AuthService(urlsTest.authBaseUrl),
                verifyTest: new VerifyService(urlsTest.authBaseUrl),
            });
        },
        {scope: 'worker'}
    ],

    authModeDev: [
        // eslint-disable-next-line no-empty-pattern
        async ({service}, use) => {
            const metamaskHelper: MetamaskHelper = new MetamaskHelper();

            //DEV
            //console.log('Login MINT DEV account')
            const wallet = await metamaskHelper.createNewWalletMint();
            const sessionIdAndOtp = await service.authDev.login(wallet);

            //sign message and get code for base account
            const signedMessage = await service.authDev.signMessageAndGetCode(wallet.address, sessionIdAndOtp.data, wallet.useAccount);

            const verifyRequest: VerifyRequest = {
                sessionId: sessionIdAndOtp.data.sessionId,
                code: signedMessage
            }

            const verifyResponseMintAccount = await service.verifyDev.verify(verifyRequest);
            console.log(`Access token MINT DEV ${verifyResponseMintAccount.data.accessToken}`)
            // console.log('Login Admin DEV account')
            //login on admin
            const walletAdmin = await metamaskHelper.createNewWalletAdmin();
            const sessionIdAndOtpAdmin = await service.authDev.login(walletAdmin);

            //sign message and get code admin
            const signedMessageAdmin = await service.authDev.signMessageAndGetCode(walletAdmin.address, sessionIdAndOtpAdmin.data, wallet.useAccount);

            const verifyRequestAdmin: VerifyRequest = {
                sessionId: sessionIdAndOtpAdmin.data.sessionId,
                code: signedMessageAdmin
            }

            const verifyResponseAdmin = await service.verifyDev.verify(verifyRequestAdmin);
            console.log(`Access token ADMIN DEV ${verifyResponseAdmin.data.accessToken}`)

            ///
            //console.log('Login BUY DEV account')
            const walletTwo = await metamaskHelper.createNewWalletBuy();
            const sessionIdAndOtpTwo = await service.authDev.login(walletTwo);

            //sign message and get code for second account
            const signedMessageTwo = await service.authDev.signMessageAndGetCode(walletTwo.address, sessionIdAndOtpTwo.data, walletTwo.useAccount);

            const verifyRequestTwo: VerifyRequest = {
                sessionId: sessionIdAndOtpTwo.data.sessionId,
                code: signedMessageTwo
            }

            const verifyResponseBuyAccount = await service.verifyDev.verify(verifyRequestTwo);
            console.log(`Access token BUY DEV ${verifyResponseBuyAccount.data.accessToken}`)

            use({
                accessTokenMintDev: verifyResponseMintAccount.data.accessToken,
                accessTokenBuyDev: verifyResponseBuyAccount.data.accessToken,
                accessTokenAdminDev: verifyResponseAdmin.data.accessToken,
            });

        },
        {scope: 'worker'}
    ],

    authModeTest: [
        // eslint-disable-next-line no-empty-pattern
        async ({service}, use) => {
            const metamaskHelper: MetamaskHelper = new MetamaskHelper();

            //TEST
            //console.log('Login MINT DEV account')
            const wallet = await metamaskHelper.createNewWalletMint();
            const sessionIdAndOtp = await service.authTest.login(wallet);

            //sign message and get code for base account
            const signedMessage = await service.authTest.signMessageAndGetCode(wallet.address, sessionIdAndOtp.data, wallet.useAccount);

            const verifyRequest: VerifyRequest = {
                sessionId: sessionIdAndOtp.data.sessionId,
                code: signedMessage
            }

            const verifyResponseMintAccount = await service.verifyTest.verify(verifyRequest);
            console.log(`Access token MINT TEST ${verifyResponseMintAccount.data.accessToken}`)

            // console.log('Login Admin DEV account')
            //login on admin
            const walletAdmin = await metamaskHelper.createNewWalletAdmin();
            const sessionIdAndOtpAdmin = await service.authTest.login(walletAdmin);

            //sign message and get code admin
            const signedMessageAdmin = await service.authTest.signMessageAndGetCode(walletAdmin.address, sessionIdAndOtpAdmin.data, wallet.useAccount);

            const verifyRequestAdmin: VerifyRequest = {
                sessionId: sessionIdAndOtpAdmin.data.sessionId,
                code: signedMessageAdmin
            }

            const verifyResponseAdmin = await service.verifyTest.verify(verifyRequestAdmin);
            console.log(`Access token ADMIN TEST ${verifyResponseAdmin.data.accessToken}`)

            ///
            // console.log('Login BUY DEV account')
            const walletTwo = await metamaskHelper.createNewWalletBuy();
            const sessionIdAndOtpTwo = await service.authTest.login(walletTwo);

            //sign message and get code for second account
            const signedMessageTwo = await service.authTest.signMessageAndGetCode(walletTwo.address, sessionIdAndOtpTwo.data, walletTwo.useAccount);

            const verifyRequestTwo: VerifyRequest = {
                sessionId: sessionIdAndOtpTwo.data.sessionId,
                code: signedMessageTwo
            }

            const verifyResponseBuyAccount = await service.verifyTest.verify(verifyRequestTwo);
            console.log(`Access token BUY TEST ${verifyResponseBuyAccount.data.accessToken}`)

            use({
                accessTokenMintTest: verifyResponseMintAccount.data.accessToken,
                accessTokenBuyTest: verifyResponseBuyAccount.data.accessToken,
                accessTokenAdminTest: verifyResponseAdmin.data.accessToken,
            });

        },
        {scope: 'worker'}
    ],
});

