package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.LEDataInputStream;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerStaticOk;
import java.io.IOException;
import java.util.Arrays;

@Ser
public class tl extends Uz {
   private static final ILogger RF = GlobalLog.getLogger(tl.class);
   @SerStaticOk
   public static final int q = 78;
   @SerId(1)
   private byte[] xK;
   @SerId(2)
   private int Dw;
   @SerId(3)
   private int Uv;
   @SerId(4)
   private int oW;
   @SerId(5)
   private int gO;
   @SerId(6)
   private int nf;
   @SerId(7)
   private int gP;
   @SerId(8)
   private int za;
   @SerId(9)
   private int lm;
   @SerId(10)
   private int zz;
   @SerId(11)
   private int JY;
   @SerId(12)
   private int HF;
   @SerId(13)
   private long LK;
   @SerId(14)
   private long io;
   @SerId(15)
   private int qa;
   @SerId(16)
   private String Hk;
   @SerId(17)
   private String Me;
   @SerId(18)
   private String PV;
   @SerId(19)
   private int oQ;
   @SerId(20)
   private int xW;
   @SerId(21)
   private int KT;

   public static boolean q(byte[] var0) {
      int var1 = var0.length;
      if (var1 > 78 && EndianUtil.littleEndianBytesToShort(var0, 0) != 28784) {
         int var2 = EndianUtil.littleEndianBytesToInt(var0, 8);
         if (var2 == var1) {
            int var3 = EndianUtil.littleEndianBytesToInt(var0, 12);
            int var4 = EndianUtil.littleEndianBytesToInt(var0, 16);
            int var5 = EndianUtil.littleEndianBytesToInt(var0, 20);
            if (var3 < 65536 && var4 < 65536 && var5 < 65536) {
               return 78 + var3 + var4 + var5 == var2;
            }
         }
      }

      return false;
   }

   public static boolean q(IInput var0) {
      try {
         boolean var8;
         try (LEDataInputStream var1 = new LEDataInputStream(var0.getStream())) {
            long var2 = var0.getCurrentSize();
            if (var2 <= 78L || var1.readShort() == 28784) {
               return false;
            }

            var1.skipExact(6L);
            int var4 = var1.readInt();
            if (var4 != var2) {
               return false;
            }

            int var5 = var1.readInt();
            int var6 = var1.readInt();
            int var7 = var1.readInt();
            if (var5 >= 65536 || var6 >= 65536 || var7 >= 65536) {
               return false;
            }

            var8 = 78 + var5 + var6 + var7 == var4;
         }

         return var8;
      } catch (IOException var11) {
         return false;
      }
   }

   public tl(byte[] var1) {
      this.xK = var1;
      this.Dw = EndianUtil.littleEndianBytesToShort(var1, 0) & '\uffff';
      this.Uv = EndianUtil.littleEndianBytesToShort(var1, 2) & '\uffff';
      this.oW = EndianUtil.littleEndianBytesToShort(var1, 4) & '\uffff';
      this.gO = EndianUtil.littleEndianBytesToShort(var1, 6) & '\uffff';
      this.nf = EndianUtil.littleEndianBytesToInt(var1, 8);
      this.za = EndianUtil.littleEndianBytesToInt(var1, 12);
      this.gP = 78;
      this.zz = EndianUtil.littleEndianBytesToInt(var1, 16);
      this.lm = 78 + this.za;
      this.HF = EndianUtil.littleEndianBytesToInt(var1, 20);
      this.JY = 78 + this.za + this.zz;
      this.LK = this.RF(var1, 26);
      this.io = this.RF(var1, 32);
      this.qa = EndianUtil.littleEndianBytesToInt(var1, 38);
      this.PV = this.q(var1, 42);
      this.Me = this.q(var1, 50);
      this.Hk = this.q(var1, 58);
      this.oQ = var1[66] >> 4 & 15;
      this.xW = var1[66] & 15;
      this.KT = EndianUtil.littleEndianBytesToInt(var1, 68);
   }

   @Override
   public int getBlockFormatVersion() {
      return this.gO;
   }

   @Override
   public int getSourceLanguageId() {
      return this.Dw;
   }

   @Override
   public int getTypeId() {
      return this.Uv;
   }

   @Override
   public int getNumber() {
      return this.oW;
   }

   @Override
   public int getBlockSize() {
      return this.nf;
   }

   @Override
   public byte[] getBlockBytes() {
      return Arrays.copyOf(this.xK, this.nf);
   }

   @Override
   public int getBlockByte(int var1) {
      return this.xK[var1] & 0xFF;
   }

   @Override
   public int getTrailerOffset() {
      return this.getBlockSize();
   }

   @Override
   public int getPayloadOffset() {
      return this.gP;
   }

   @Override
   public int getPayloadSize() {
      return this.za;
   }

   @Override
   public byte[] getPayloadBytes() {
      return Arrays.copyOfRange(this.xK, this.gP, this.gP + this.za);
   }

   @Override
   public int getInterfaceOffset() {
      return this.lm;
   }

   @Override
   public int getInterfaceSize() {
      return this.zz;
   }

   @Override
   public byte[] getInterfaceBytes() {
      return Arrays.copyOfRange(this.xK, this.lm, this.lm + this.zz);
   }

   @Override
   public int getOtherOffset() {
      return this.JY;
   }

   @Override
   public int getOtherSize() {
      return this.HF;
   }

   @Override
   public byte[] getOtherBytes() {
      return Arrays.copyOfRange(this.xK, this.JY, this.JY + this.HF);
   }

   @Override
   public long getModificationTimestamp() {
      return this.LK;
   }

   @Override
   public long getInterfaceModificationTimestamp() {
      return this.io;
   }

   @Override
   public int getKey() {
      return this.qa;
   }

   @Override
   public String getMetadataBlockName() {
      return this.Hk;
   }

   @Override
   public String getMetadataFamilyName() {
      return this.Me;
   }

   @Override
   public String getMetadataAuthorName() {
      return this.PV;
   }

   @Override
   public int[] getVersion() {
      return new int[]{this.oQ, this.xW};
   }

   @Override
   public int getCrc() {
      return this.KT;
   }

   @Override
   public INativeType generateNativeHeaderType(ITypeManager var1) {
      INativeType var2 = var1.getType("S7_BLOCK1_HEADER");
      if (var2 != null) {
         return var2;
      } else {
         new eM(var1).q();
         INativeType var3 = var1.getType("BYTE");
         INativeType var4 = var1.getType("WORD");
         INativeType var5 = var1.getType("DWORD");
         INativeType var6 = var1.getType("S7TIME");
         INativeType var7 = var1.getType("STRING_FIXED8");
         IStructureType var8 = var1.createStructure("S7_BLOCK1_HEADER");
         var1.addStructureField(var8, "blklang", var4);
         var1.addStructureField(var8, "blktype", var4);
         var1.addStructureField(var8, "blknum", var4);
         var1.addStructureField(var8, "blkformat", var4);
         var1.addStructureField(var8, "blksize", var5);
         var1.addStructureField(var8, "codesize", var5);
         var1.addStructureField(var8, "ifacesize", var5);
         var1.addStructureField(var8, "othersize", var5);
         var1.addStructureField(var8, "flags2", var4);
         var1.addStructureField(var8, "modtime", var6);
         var1.addStructureField(var8, "ifacemodtime", var6);
         var1.addStructureField(var8, "key", var5);
         var1.addStructureField(var8, "author", var7);
         var1.addStructureField(var8, "family", var7);
         var1.addStructureField(var8, "name", var7);
         var1.addStructureField(var8, "ver", var3);
         var1.addStructureField(var8, "unkb43", var3);
         var1.addStructureField(var8, "crc", var4);
         var1.addStructureField(var8, "unkw46", var4);
         var1.addStructureField(var8, "unkw48", var4);
         var1.addStructureField(var8, "unkw4A", var4);
         var1.addStructureField(var8, "unkw4C", var4);
         return var8;
      }
   }

   @Override
   public INativeType generateNativeTrailerType(ITypeManager var1) {
      return null;
   }
}
