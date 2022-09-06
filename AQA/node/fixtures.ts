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
    verifyTest: VerifyService,

    authStg: AuthService,
    verifyStg: VerifyService
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

type AccessTokenStg = {
    accessTokenMintStg: string,
    accessTokenBuyStg: string,
    accessTokenAdminStg: string
}

type WorkerFixtures = {
    service: Service;
    authModeDev: AccessTokenDev;
    authModeTest: AccessTokenTest;
    authModeStg: AccessTokenStg;
};


export const test = base.extend<TestFixtures, WorkerFixtures>({

    service: [
        // eslint-disable-next-line no-empty-pattern
        async ({}, use) => {
            const yamlDevelop = load(await readFile(`config.develop.yml`, "utf8"));
            const yamlTest = load(await readFile(`config.test.yml`, "utf8"));
            const yamlStg = load(await readFile(`config.staging.yml`, "utf8"));
            // eslint-disable-next-line @typescript-eslint/ban-ts-comment
            // @ts-ignore
            const urlsDev = yamlDevelop.baseUrls;
            const urlsTest = yamlTest.baseUrls;
            const urlsStg = yamlStg.baseUrls;
            use({
                authDev: new AuthService(urlsDev.authBaseUrl),
                verifyDev: new VerifyService(urlsDev.authBaseUrl),
                authTest: new AuthService(urlsTest.authBaseUrl),
                verifyTest: new VerifyService(urlsTest.authBaseUrl),
                authStg: new AuthService(urlsStg.authBaseUrl),
                verifyStg: new VerifyService(urlsStg.authBaseUrl),
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
            console.log(`Signature MINT DEV ${signedMessage}`)

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
            const signedMessageAdmin = await service.authDev.signMessageAndGetCode(walletAdmin.address, sessionIdAndOtpAdmin.data, walletAdmin.useAccount);
            console.log(`Signature ADMIN DEV ${signedMessageAdmin}`)

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
            console.log(`Signature BUY DEV ${signedMessageTwo}`)

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
            console.log(`Signature MINT TEST ${signedMessage}`)

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
            const signedMessageAdmin = await service.authTest.signMessageAndGetCode(walletAdmin.address, sessionIdAndOtpAdmin.data, walletAdmin.useAccount);
            console.log(`Signature ADMIN TEST ${signedMessageAdmin}`)

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
            console.log(`Signature BUY TEST ${signedMessageTwo}`)

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

    authModeStg: [
        // eslint-disable-next-line no-empty-pattern
        async ({service}, use) => {
            const metamaskHelper: MetamaskHelper = new MetamaskHelper();

            //TEST
            //console.log('Login MINT DEV account')
            const wallet = await metamaskHelper.createNewWalletMintStg();
            const sessionIdAndOtp = await service.authStg.login(wallet);

            //sign message and get code for base account
            const signedMessage = await service.authStg.signMessageAndGetCode(wallet.address, sessionIdAndOtp.data, wallet.useAccount);
            console.log(`Signature MINT STG ${signedMessage}`)

            const verifyRequest: VerifyRequest = {
                sessionId: sessionIdAndOtp.data.sessionId,
                code: signedMessage
            }

            const verifyResponseMintAccount = await service.verifyStg.verify(verifyRequest);
            console.log(`Access token MINT STG ${verifyResponseMintAccount.data.accessToken}`)

            // // console.log('Login Admin DEV account')
            // //login on admin
            // const walletAdmin = await metamaskHelper.createNewWalletAdminStg();
            // const sessionIdAndOtpAdmin = await service.authStg.login(walletAdmin);
            //
            // //sign message and get code admin
            // const signedMessageAdmin = await service.authStg.signMessageAndGetCode(walletAdmin.address, sessionIdAndOtpAdmin.data, walletAdmin.useAccount);
            // console.log(`Signature ADMIN STG ${signedMessageAdmin}`)
            //
            // const verifyRequestAdmin: VerifyRequest = {
            //     sessionId: sessionIdAndOtpAdmin.data.sessionId,
            //     code: signedMessageAdmin
            // }
            //
            // const verifyResponseAdmin = await service.verifyStg.verify(verifyRequestAdmin);
            // console.log(`Access token ADMIN STG ${verifyResponseAdmin.data.accessToken}`)

            ///
            // console.log('Login BUY DEV account')
            const walletTwo = await metamaskHelper.createNewWalletBuyStg();
            const sessionIdAndOtpTwo = await service.authStg.login(walletTwo);

            //sign message and get code for second account
            const signedMessageTwo = await service.authStg.signMessageAndGetCode(walletTwo.address, sessionIdAndOtpTwo.data, walletTwo.useAccount);
            console.log(`Signature BUY STG ${signedMessageTwo}`)

            const verifyRequestTwo: VerifyRequest = {
                sessionId: sessionIdAndOtpTwo.data.sessionId,
                code: signedMessageTwo
            }

            const verifyResponseBuyAccount = await service.verifyStg.verify(verifyRequestTwo);
            console.log(`Access token BUY STG ${verifyResponseBuyAccount.data.accessToken}`)

            use({
                accessTokenMintStg: verifyResponseMintAccount.data.accessToken,
                accessTokenBuyStg: verifyResponseBuyAccount.data.accessToken,
                accessTokenAdminStg: "",
            });
        },
        {scope: 'worker'}
    ],
});

