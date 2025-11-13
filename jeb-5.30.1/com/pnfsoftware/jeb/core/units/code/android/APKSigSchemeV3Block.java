package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;

@Ser
public class APKSigSchemeV3Block extends APKSigSchemeV2Block {
   private static final ILogger logger = GlobalLog.getLogger(APKSigSchemeV3Block.class);
   public static final int PROOF_OF_ROTATION_ATTR_ID = 1000370060;
   public static final int ROTATION_MIN_SDK_VERSION_ATTR_ID = 1436519170;
   public static final int ROTATION_ON_DEV_RELEASE_ATTR_ID = -1029262406;
   @SerId(3)
   int minorVersion;

   public APKSigSchemeV3Block(ByteBuffer var1) {
      this(var1, 0);
   }

   public APKSigSchemeV3Block(ByteBuffer var1, int var2) {
      super(var1);
      this.minorVersion = var2;
   }

   public int getMinorVersion() {
      return this.minorVersion;
   }

   @Override
   protected void parse(int var1) {
      int var2 = this.readSize();
      var1 -= 4 + var2;

      while (var2 > 0) {
         APKSigSchemeV2Block.Signer var3 = new APKSigSchemeV2Block.Signer();
         int var4 = this.readSize();
         var2 -= 4 + var4;
         int var5 = this.readSize();
         var4 -= 4 + var5;
         int var6 = this.readSize();
         var5 -= 4 + var6;

         while (var6 > 0) {
            int var7 = this.readSize();
            var6 -= 4 + var7;
            int var8 = this.readInt();
            var7 -= 4;
            int var9 = this.readSize();
            var7 -= 4 + var9;
            byte[] var10 = this.readBytes(var9);
            var3.digests.add(new APKSigSchemeV2Block.Digest(var8, var10));
            this.verifyZero(var7);
         }

         this.verifyZero(var6);
         int var24 = this.readSize();
         var5 -= 4 + var24;

         while (var24 > 0) {
            int var25 = this.readSize();
            var24 -= 4 + var25;
            byte[] var27 = this.readBytes(var25);
            var3.certificates.add(new APKSigSchemeV2Block.Certificate(var27));
         }

         this.verifyZero(var24);
         this.readInt();
         this.readInt();
         var5 -= 8;
         int var26 = this.readSize();
         var5 -= 4 + var26;

         while (var26 > 0) {
            int var28 = this.readSize();
            var26 -= 4 + var28;
            int var31 = this.readInt();
            byte[] var11 = this.readBytes(var28 - 4);
            var3.attributes.add(new APKSigSchemeV2Block.Attribute(var31, var11));
            if (var31 == 1000370060) {
               Object[] var10000 = new Object[0];
            } else if (var31 == 1436519170) {
               Object[] var36 = new Object[0];
            } else if (var31 == -1029262406 && this.minorVersion != 0 && this.minorVersion == 1) {
               Object[] var37 = new Object[0];
            }
         }

         this.verifyZero(var26);
         this.skipAndWarnIfNonZero(var5);
         this.readInt();
         this.readInt();
         var4 -= 8;
         int var29 = this.readSize();
         var4 -= 4 + var29;

         while (var29 > 0) {
            int var32 = this.readSize();
            var29 -= 4;
            int var34 = this.readInt();
            byte[] var12 = this.readBytes(var32 - 4);
            var29 -= var32;
            var3.signatures.add(new APKSigSchemeV2Block.Signature(var34, var12));
         }

         this.verifyZero(var29);
         int var33 = this.readSize();
         var4 -= 4 + var33;
         byte[] var35 = this.readBytes(var33);
         var3.publicKey = new APKSigSchemeV2Block.PublicKey(var35);
         this.signers.add(var3);
         this.verifyZero(var4);
      }

      this.verifyZero(var2);
      this.verifyZero(var1);
   }
}
