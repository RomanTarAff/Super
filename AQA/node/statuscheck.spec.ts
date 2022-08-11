import * as faker from "faker";
import {test} from "./fixtures";
import {MetamaskHelper} from "./MetamaskHelper";
const shell = require('shelljs');

test.describe('Status check', async function () {

    const metamaskHelper: MetamaskHelper = new MetamaskHelper();

    test('Mint nft @mint', async () => {
        const name = `Lesley_${faker.random.alphaNumeric(5)}_${faker.datatype.hexaDecimal(42)}`
        const description = 'test';
        await metamaskHelper.mint(name, description);
    });

    test('Mint nft @change', async () => {
        await metamaskHelper.changeOwner();
    });

    test('Get token @tokenDev', async ({service, authModeDev}) => {
        console.log('INIT DEV TOKENS')
        shell.exec(`export MINT_TOKEN=${authModeDev.accessTokenMintDev}`);
        shell.exec(`export BUY_TOKEN=${authModeDev.accessTokenBuyDev}`);
    });

    test('Get token @tokenTest', async ({service, authModeTest}) => {
        console.log('INIT TEST TOKENS')
        process.env.MINT_TOKEN = authModeTest.accessTokenMintTest;
        process.env.BUY_TOKEN = authModeTest.accessTokenBuyTest;
    });
});
