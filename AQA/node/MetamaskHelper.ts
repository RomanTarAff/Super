import {BigNumber, ethers, Wallet} from "ethers";
import * as faker from "faker";
import {WalletRequest} from "./wallet";

export class MetamaskHelper {

    private NETWORK = "rinkeby";
    private NETWORK_MAINNET = "mainnet";
    public TESTING_PRIVATE_KEY_BASE_ACCOUNT = "92688588d2a683dd9d0163ced8d0db4206bbbb9d695a85bef210facd494b7678";
    public WALLET_ADDRESS_BASE_ACCOUNT = "0xb2e41d98db19fb7052e07952e47d987946cd1fb0";
    public WALLET_ADDRESS_SECOND_ACCOUNT = "0x46f893c8ada8E87A6e14639a42c4b378d9662740";

    public WALLET_PRIVATE_KEY_MINT = "6eed43a0c5c052903158f0440d15b3b8dba7138d77f0a1c9cc28f67b96a7cfaf";
    public WALLET_PRIVATE_KEY_BUY = "a552403371a8987927061c90abece84022c85c39996f1682c9fbe03e18d47019";

    private provider = new ethers.providers.InfuraProvider(this.NETWORK);
    private walletPrivateKey = new Wallet(this.WALLET_PRIVATE_KEY_MINT);
    private user = this.walletPrivateKey.connect(this.provider);

    private providerStg = new ethers.providers.InfuraProvider(this.NETWORK_MAINNET);

    async createNewWalletMint(): Promise<WalletRequest> {
        const account = new ethers.Wallet(this.WALLET_PRIVATE_KEY_MINT, this.provider);
        const network = await (account.provider).getNetwork();
        const netIdFromWallet = network.chainId;
        //console.log(`... attempting to use address ${account.address} and get netId ${netIdFromWallet}...`);
        return {
            address: account.address,
            netId: netIdFromWallet,
            audience: 'user',
            useAccount: account
        }
    }

    async createNewWalletBuy(): Promise<WalletRequest> {
        const account = new ethers.Wallet(this.WALLET_PRIVATE_KEY_BUY, this.provider);
        const network = await (account.provider).getNetwork();
        const netIdFromWallet = network.chainId;
        //console.log(`... attempting to use address ${account.address} and get netId ${netIdFromWallet}...`);
        return {
            address: account.address,
            netId: netIdFromWallet,
            audience: 'user',
            useAccount: account
        }
    }

    async createNewWalletAdmin(): Promise<WalletRequest> {
        const account = new ethers.Wallet(this.WALLET_PRIVATE_KEY_MINT, this.provider);
        const network = await (account.provider).getNetwork();
        const netIdFromWallet = network.chainId;
        console.log(`... attempting to use address ${account.address} and get netId ${netIdFromWallet}...`);
        return {
            address: account.address,
            netId: netIdFromWallet,
            audience: 'admin',
            useAccount: account
        }
    }

    async createNewWalletMintStg(): Promise<WalletRequest> {
        const account = new ethers.Wallet(this.WALLET_PRIVATE_KEY_MINT, this.providerStg);
        const network = await (account.provider).getNetwork();
        const netIdFromWallet = network.chainId;
        //console.log(`... attempting to use address ${account.address} and get netId ${netIdFromWallet}...`);
        return {
            address: account.address,
            netId: netIdFromWallet,
            audience: 'user',
            useAccount: account
        }
    }

    async createNewWalletBuyStg(): Promise<WalletRequest> {
        const account = new ethers.Wallet(this.WALLET_PRIVATE_KEY_BUY, this.providerStg);
        const network = await (account.provider).getNetwork();
        const netIdFromWallet = network.chainId;
        //console.log(`... attempting to use address ${account.address} and get netId ${netIdFromWallet}...`);
        return {
            address: account.address,
            netId: netIdFromWallet,
            audience: 'user',
            useAccount: account
        }
    }

    async createNewWalletAdminStg(): Promise<WalletRequest> {
        const account = new ethers.Wallet(this.WALLET_PRIVATE_KEY_MINT, this.providerStg);
        const network = await (account.provider).getNetwork();
        const netIdFromWallet = network.chainId;
        console.log(`... attempting to use address ${account.address} and get netId ${netIdFromWallet}...`);
        return {
            address: account.address,
            netId: netIdFromWallet,
            audience: 'admin',
            useAccount: account
        }
    }

    async mint(name: string, description: string) {

        const abi = `[{
    "inputs": [
      {
        "internalType": "address",
        "name": "_to",
        "type": "address"
      },
      {
        "internalType": "uint256",
        "name": "_tokenId",
        "type": "uint256"
      },
      {
        "internalType": "string",
        "name": "_uri",
        "type": "string"
      }
    ],
    "name": "mint",
    "outputs": [],
    "stateMutability": "nonpayable",
    "type": "function"
  }, {
    "inputs": [
      {
        "internalType": "uint256",
        "name": "_tokenId",
        "type": "uint256"
      },
      {
        "internalType": "string",
        "name": "_uri",
        "type": "string"
      }
    ],
    "name": "changeMetadata",
    "outputs": [],
    "stateMutability": "nonpayable",
    "type": "function"
  }]`;

        const id_ = faker.datatype.hexaDecimal(42);
        const bigId = BigNumber.from(id_);

        const contract = new ethers.Contract('0x902Dfd8F9F9d72feEB2457B45F528cd873B7669b', abi, this.user);
        console.log(`Mint nft name: ${name}`);

        await contract.mint('0x2ACd471C427c7BEB720Cad6bDD2Fb50c2cFbC093', bigId, JSON.stringify({
            'image': `https://avatars.mds.yandex.net/get-zen_doc/60857/pub_5a7ecd2d5f496725d507264b_5a7eee3a1aa80cb1a6d6ce1a/scale_2400`,
            'name': `${name}`,
            'description': `${description}`,
            'collection': 'Random collection'
        }));
    }
}
