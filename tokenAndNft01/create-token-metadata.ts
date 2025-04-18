import * as dotenv from "dotenv";
import * as web3 from "@solana/web3.js";
import {
  getKeypairFromEnvironment,
  getExplorerLink,
} from "@solana-developers/helpers";
import { createCreateMetadataAccountV3Instruction } from "@metaplex-foundation/mpl-token-metadata";

dotenv.config();

const user = getKeypairFromEnvironment("SECRET_KEY");
const connection = new web3.Connection(
  web3.clusterApiUrl("devnet"),
  "confirmed"
);
console.log(
  `Connection established successfully with user: `,
  user.publicKey.toBase58()
);

const TOKEN_METADATA_PROGRAM_ID = new web3.PublicKey(
  "metaqbxxUerdq28cj1RbAWkYQm3ybzjb6a8bt518x1s"
);

const tokenMintAccount = new web3.PublicKey(
  "88iPY4Bbiq8NW2E42xRQGdcCwmDGtFDF6pYnWhvRFn7h"
);

const metadataData = {
  name: "Practise Token",
  symbol: "TRAINING",
  // Arweave / IPFS / Pinata etc link using metaplex standard for offchain data
  uri: "https://walrus.tusky.io/LS60JsqrR67hHHwmtuFydRQ67wmw9hOAla1BgHYS_8o",
  sellerFeeBasisPoints: 0,
  creators: null,
  collection: null,
  uses: null,
};

const metadataPDAAndBump = web3.PublicKey.findProgramAddressSync(
  [
    Buffer.from("metadata"),
    TOKEN_METADATA_PROGRAM_ID.toBuffer(),
    tokenMintAccount.toBuffer(),
  ],
  TOKEN_METADATA_PROGRAM_ID
);

const metadataPDA = metadataPDAAndBump[0];

const transaction = new web3.Transaction();

const createMetadataAccountInstruction =
  createCreateMetadataAccountV3Instruction(
    {
      metadata: metadataPDA,
      mint: tokenMintAccount,
      mintAuthority: user.publicKey,
      payer: user.publicKey,
      updateAuthority: user.publicKey,
    },
    {
      createMetadataAccountArgsV3: {
        collectionDetails: null,
        data: metadataData,
        isMutable: true,
      },
    }
  );

transaction.add(createMetadataAccountInstruction);

const transactionSignature = await web3.sendAndConfirmTransaction(
  connection,
  transaction,
  [user]
);

const transactionLink = getExplorerLink(
  "transaction",
  transactionSignature,
  "devnet"
);

console.log(`✅ Transaction confirmed, explorer link is: ${transactionLink}`);

const tokenMintLink = getExplorerLink(
  "address",
  tokenMintAccount.toString(),
  "devnet"
);

console.log(`✅ Look at the token mint again: ${tokenMintLink}`);
