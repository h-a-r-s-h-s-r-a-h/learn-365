import * as dotenv from "dotenv";
import * as web3 from "@solana/web3.js";
import {
  getKeypairFromEnvironment,
  getExplorerLink,
} from "@solana-developers/helpers";
import { getOrCreateAssociatedTokenAccount, transfer } from "@solana/spl-token";

dotenv.config();

const sender = getKeypairFromEnvironment("SECRET_KEY");
const connection = new web3.Connection(
  web3.clusterApiUrl("devnet"),
  "confirmed"
);
console.log(
  `✅ Connection successfully established with sender: `,
  sender.publicKey.toBase58()
);

const receipient = new web3.PublicKey(
  "8weixq4yWSjXtwKZaAdo5Y6z9R6vqwd8LJhUJ6LCGMis"
);

const tokenMintAccount = new web3.PublicKey(
  "88iPY4Bbiq8NW2E42xRQGdcCwmDGtFDF6pYnWhvRFn7h"
);

const MINOR_UNITS_PER_MAJOR_UNITS = Math.pow(10, 2);

console.log(`💸 Attempting to send 10 token to ${receipient.toBase58()}...`);

// Get or create the source token account to store this token
const sourceTokenAccount = await getOrCreateAssociatedTokenAccount(
  connection,
  sender,
  tokenMintAccount,
  sender.publicKey
);

// Get or create the destination token account to store this token
const destinationTokenAccount = await getOrCreateAssociatedTokenAccount(
  connection,
  sender,
  tokenMintAccount,
  receipient
);

// Transfer the tokens
const signature = await transfer(
  connection,
  sender,
  sourceTokenAccount.address,
  destinationTokenAccount.address,
  sender,
  10 * MINOR_UNITS_PER_MAJOR_UNITS
);

const explorerLink = getExplorerLink("transaction", signature, "devnet");

console.log(`✅ Transaction confirmed, explorer link is: ${explorerLink}`);
