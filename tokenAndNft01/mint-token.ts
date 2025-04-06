import * as dotenv from "dotenv";
import * as web3 from "@solana/web3.js";
import {
  getKeypairFromEnvironment,
  getExplorerLink,
} from "@solana-developers/helpers";
import { getOrCreateAssociatedTokenAccount, mintTo } from "@solana/spl-token";

dotenv.config();

const connection = new web3.Connection(
  web3.clusterApiUrl("devnet"),
  "confirmed"
);
const authority = getKeypairFromEnvironment("SECRET_KEY");
console.log(
  `Connection successfully established: `,
  authority.publicKey.toBase58()
);

const MINOR_UNITS_PER_MAJOR_UNITS = Math.pow(10, 2);

const tokenMintAccount = new web3.PublicKey(
  "88iPY4Bbiq8NW2E42xRQGdcCwmDGtFDF6pYnWhvRFn7h"
);

const friendPubKey = new web3.PublicKey(
  "8weixq4yWSjXtwKZaAdo5Y6z9R6vqwd8LJhUJ6LCGMis"
);

const friendAssociatedTokenAccount = await getOrCreateAssociatedTokenAccount(
  connection,
  authority,
  tokenMintAccount,
  friendPubKey
);

const receipientAssociatedTokenAccount =
  await getOrCreateAssociatedTokenAccount(
    connection,
    authority,
    tokenMintAccount,
    authority.publicKey
  );

const transactionSignature = await mintTo(
  connection,
  authority,
  tokenMintAccount,
  friendAssociatedTokenAccount.address,
  authority,
  10 * MINOR_UNITS_PER_MAJOR_UNITS
);

const link = getExplorerLink("transaction", transactionSignature, "devnet");

console.log(`✅ Success! Mint Token Transaction: ${link}`);
