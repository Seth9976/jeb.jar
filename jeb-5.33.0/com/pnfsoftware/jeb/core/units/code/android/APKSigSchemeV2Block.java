package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class APKSigSchemeV2Block {
   private static final ILogger logger = GlobalLog.getLogger(APKSigSchemeV2Block.class);
   public static final int SigAlgoId_RSASSA_PSS_SHA2_256 = 257;
   public static final int SigAlgoId_RSASSA_PSS_SHA2_512 = 258;
   public static final int SigAlgoId_RSASSA_PKCS1_v1_5_SHA2_256 = 259;
   public static final int SigAlgoId_RSASSA_PKCS1_v1_5_SHA2_512 = 260;
   public static final int SigAlgoId_ECDSA_SHA2_256 = 513;
   public static final int SigAlgoId_ECDSA_SHA2_512 = 514;
   public static final int SigAlgoId_DSA_SHA2_256 = 769;
   @SerTransient
   private ByteBuffer b;
   @SerId(1)
   List signers = new ArrayList();

   public static String algoIdToString(int var0) {
      switch (var0) {
         case 257:
            return "RSASSA-PSS-SHA2-256";
         case 258:
            return "RSASSA-PSS-SHA2-512";
         case 259:
            return "RSASSA-PKCS1-v1_5-SHA2-256";
         case 260:
            return "RSASSA-PKCS1-v1_5-SHA2-512";
         case 513:
            return "ECDSA-SHA2-256";
         case 514:
            return "ECDSA-SHA2-512";
         case 769:
            return "DSA-SHA2-256";
         default:
            return "Unknown-Algo-ID-" + var0;
      }
   }

   public APKSigSchemeV2Block(ByteBuffer var1) {
      this.b = var1;
      this.parse(var1.remaining());
   }

   public List getSigners() {
      return this.signers;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "Signers (%d):\n", this.signers.size());

      for (APKSigSchemeV2Block.Signer var3 : this.signers) {
         Strings.ff(var1, "%s\n", var3);
      }

      return var1.toString();
   }

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
         int var22 = this.readSize();
         var5 -= 4 + var22;

         while (var22 > 0) {
            int var23 = this.readSize();
            var22 -= 4 + var23;
            byte[] var25 = this.readBytes(var23);
            var3.certificates.add(new APKSigSchemeV2Block.Certificate(var25));
         }

         this.verifyZero(var22);
         int var24 = this.readSize();
         var5 -= 4 + var24;

         while (var24 > 0) {
            int var26 = this.readSize();
            var24 -= 4 + var26;
            int var29 = this.readInt();
            byte[] var11 = this.readBytes(var26 - 4);
            var3.attributes.add(new APKSigSchemeV2Block.Attribute(var29, var11));
         }

         this.verifyZero(var24);
         this.skipAndWarnIfNonZero(var5);
         int var27 = this.readSize();
         var4 -= 4 + var27;

         while (var27 > 0) {
            int var30 = this.readSize();
            var27 -= 4;
            int var32 = this.readInt();
            byte[] var12 = this.readBytes(var30 - 4);
            var27 -= var30;
            var3.signatures.add(new APKSigSchemeV2Block.Signature(var32, var12));
         }

         this.verifyZero(var27);
         int var31 = this.readSize();
         var4 -= 4 + var31;
         byte[] var33 = this.readBytes(var31);
         var3.publicKey = new APKSigSchemeV2Block.PublicKey(var33);
         this.signers.add(var3);
         this.verifyZero(var4);
      }

      this.verifyZero(var2);
      this.verifyZero(var1);
   }

   protected int readSize() {
      int var1 = this.b.getInt();
      if (this.b.remaining() < var1) {
         throw new BufferUnderflowException();
      } else {
         return var1;
      }
   }

   protected int readInt() {
      return this.b.getInt();
   }

   protected byte[] readBytes(int var1) {
      if (var1 < 0) {
         throw new BufferUnderflowException();
      } else {
         byte[] var2 = new byte[var1];
         this.b.get(var2);
         return var2;
      }
   }

   protected void skipBytes(int var1) {
      if (var1 < 0) {
         throw new BufferUnderflowException();
      } else {
         this.b.position(this.b.position() + var1);
      }
   }

   protected void verifyZero(int var1) {
      if (var1 != 0) {
         throw new RuntimeException("Expected end of record");
      }
   }

   protected void skipAndWarnIfNonZero(int var1) {
      if (var1 != 0) {
         logger.trace("[APK Sig v2] Unexpected slack instead of end of record; skipping %d bytes", var1);
         this.skipBytes(var1);
      }
   }

   @Ser
   public static class Attribute {
      @SerId(1)
      int id;
      @SerId(2)
      byte[] value;

      Attribute(int var1, byte[] var2) {
         this.id = var1;
         this.value = var2;
      }

      public int getId() {
         return this.id;
      }

      public byte[] getValue() {
         return this.value;
      }

      @Override
      public String toString() {
         return Strings.ff("id=%d,value=%s", this.id, Formatter.byteArrayToHex(this.value));
      }
   }

   @Ser
   public static class Certificate {
      @SerId(1)
      byte[] bytes;

      Certificate(byte[] var1) {
         this.bytes = var1;
      }

      public byte[] getBytes() {
         return this.bytes;
      }

      @Override
      public String toString() {
         return Strings.ff("%s", Formatter.byteArrayToHex(this.bytes));
      }
   }

   @Ser
   public static class Digest {
      @SerId(1)
      int algoId;
      @SerId(2)
      byte[] digestBytes;

      Digest(int var1, byte[] var2) {
         this.algoId = var1;
         this.digestBytes = var2;
      }

      public int getSignatureAlgorithmId() {
         return this.algoId;
      }

      public byte[] getDigestBytes() {
         return this.digestBytes;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%s", APKSigSchemeV2Block.algoIdToString(this.algoId), Formatter.byteArrayToHex(this.digestBytes));
      }
   }

   @Ser
   public static class PublicKey {
      @SerId(1)
      byte[] bytes;

      PublicKey(byte[] var1) {
         this.bytes = var1;
      }

      public byte[] getBytes() {
         return this.bytes;
      }

      @Override
      public String toString() {
         return Strings.ff("%s", Formatter.byteArrayToHex(this.bytes));
      }
   }

   @Ser
   public static class Signature {
      @SerId(1)
      int algoId;
      @SerId(2)
      byte[] signatureBytes;

      Signature(int var1, byte[] var2) {
         this.algoId = var1;
         this.signatureBytes = var2;
      }

      public int getSignatureAlgorithmId() {
         return this.algoId;
      }

      public byte[] getSignatureBytes() {
         return this.signatureBytes;
      }

      @Override
      public String toString() {
         return Strings.ff("%s:%s", APKSigSchemeV2Block.algoIdToString(this.algoId), Formatter.byteArrayToHex(this.signatureBytes));
      }
   }

   @Ser
   public static class Signer {
      @SerId(1)
      List digests = new ArrayList();
      @SerId(2)
      List certificates = new ArrayList();
      @SerId(3)
      List attributes = new ArrayList();
      @SerId(4)
      List signatures = new ArrayList();
      @SerId(5)
      APKSigSchemeV2Block.PublicKey publicKey;

      public List getDigests() {
         return this.digests;
      }

      public List getCertificates() {
         return this.certificates;
      }

      public List getAttributes() {
         return this.attributes;
      }

      public List getSignatures() {
         return this.signatures;
      }

      public APKSigSchemeV2Block.PublicKey getPublicKey() {
         return this.publicKey;
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         if (!this.digests.isEmpty()) {
            Strings.ff(var1, "Digests (%d):\n", this.digests.size());

            for (APKSigSchemeV2Block.Digest var3 : this.digests) {
               Strings.ff(var1, "- %s\n", var3);
            }
         }

         if (!this.certificates.isEmpty()) {
            Strings.ff(var1, "Certificates (%d):\n", this.certificates.size());

            for (APKSigSchemeV2Block.Certificate var7 : this.certificates) {
               Strings.ff(var1, "- %s\n", var7);
            }
         }

         if (!this.attributes.isEmpty()) {
            Strings.ff(var1, "Attributes (%d):\n", this.attributes.size());

            for (APKSigSchemeV2Block.Attribute var8 : this.attributes) {
               Strings.ff(var1, "- %s\n", var8);
            }
         }

         if (!this.signatures.isEmpty()) {
            Strings.ff(var1, "Signatures (%d):\n", this.signatures.size());

            for (APKSigSchemeV2Block.Signature var9 : this.signatures) {
               Strings.ff(var1, "- %s\n", var9);
            }
         }

         Strings.ff(var1, "Public Key:\n- %s\n", this.publicKey);
         return var1.toString();
      }
   }
}
