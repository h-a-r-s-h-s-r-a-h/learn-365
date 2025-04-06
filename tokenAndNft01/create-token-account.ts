import * as dotenv from "dotenv";
import * as web3 from "@solana/web3.js";
import {
  getKeypairFromEnvironment,
  getExplorerLink,
} from "@solana-developers/helpers";
import { getOrCreateAssociatedTokenAccount } from "@solana/spl-token";

dotenv.config();

const authority = getKeypairFromEnvironment("SECRET_KEY");
const connection = new web3.Connection(
  web3.clusterApiUrl("devnet"),
  "confirmed"
);
console.log(
  `Connection successfully established with user: `,
  authority.publicKey.toBase58()
);

const tokenMintAccount = new web3.PublicKey(
  "88iPY4Bbiq8NW2E42xRQGdcCwmDGtFDF6pYnWhvRFn7h"
);

const receipient = authority.publicKey;

const tokenAccount = await getOrCreateAssociatedTokenAccount(
  connection,
  authority,
  tokenMintAccount,
  receipient
);

console.log(`Token Account: ${tokenAccount.address.toBase58()}`);

const link = getExplorerLink(
  "address",
  tokenAccount.address.toBase58(),
  "devnet"
);

console.log(`✅ Created token Account: ${link}`);
