package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.base.Assert;
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
public class Nz extends Uz {
   private static final ILogger xK = GlobalLog.getLogger(Nz.class);
   @SerStaticOk
   public static final int q = 36;
   @SerStaticOk
   public static final int RF = 36;
   @SerId(1)
   private byte[] Dw;
   @SerId(2)
   private int Uv;
   @SerId(3)
   private int oW;
   @SerId(4)
   private int gO;
   @SerId(5)
   private int nf;
   @SerId(6)
   private int gP;
   @SerId(7)
   private int za;
   @SerId(8)
   private int lm;
   @SerId(9)
   private int zz;
   @SerId(10)
   private int JY;
   @SerId(11)
   private int HF;
   @SerId(12)
   private int LK;
   @SerId(13)
   private long io;
   @SerId(14)
   private long qa;
   @SerId(15)
   private int Hk;
   @SerId(16)
   private String Me;
   @SerId(17)
   private String PV;
   @SerId(18)
   private String oQ;
   @SerId(19)
   private int xW;
   @SerId(20)
   private int KT;
   @SerId(21)
   private int Gf;

   public static boolean q(byte[] var0) {
      int var1 = var0.length;
      if (var1 > 72 && EndianUtil.littleEndianBytesToShort(var0, 0) == 28784) {
         int var2 = EndianUtil.bigEndianBytesToInt(var0, 8);
         if (var2 == var1) {
            int var3 = EndianUtil.bigEndianBytesToShort(var0, 34) & 255;
            if (var3 <= var1 - 36 - 36) {
               return true;
            }
         }
      }

      return false;
   }

   public static boolean q(IInput var0) {
      try {
         boolean var6;
         try (LEDataInputStream var1 = new LEDataInputStream(var0.getStream())) {
            long var2 = var0.getCurrentSize();
            if (var2 <= 72L || var1.readShort() != 28784) {
               return false;
            }

            var1.skipExact(6L);
            int var4 = EndianUtil.swapInt(var1.readInt());
            if (var4 != var2) {
               return false;
            }

            var1.skipExact(22L);
            int var5 = EndianUtil.swapShort(var1.readShort()) & 255;
            if (var5 > var2 - 36L - 36L) {
               return false;
            }

            var6 = true;
         }

         return var6;
      } catch (IOException var9) {
         return false;
      }
   }

   public Nz(byte[] var1) {
      this.Dw = var1;
      int var2 = EndianUtil.bigEndianBytesToShort(var1, 0) & '\uffff';
      Assert.a(var2 == 28784);
      this.nf = EndianUtil.bigEndianBytesToShort(var1, 2) & '\uffff';
      this.Uv = var1[4] & 255;
      this.oW = var1[5] & 255;
      this.gO = EndianUtil.bigEndianBytesToShort(var1, 6) & '\uffff';
      this.gP = EndianUtil.bigEndianBytesToInt(var1, 8);
      this.Hk = EndianUtil.bigEndianBytesToInt(var1, 12);
      this.io = this.RF(var1, 16);
      this.qa = this.RF(var1, 22);
      int var3 = EndianUtil.bigEndianBytesToShort(var1, 28) & '\uffff';
      EndianUtil.bigEndianBytesToShort(var1, 30);
      EndianUtil.bigEndianBytesToShort(var1, 32);
      this.lm = EndianUtil.bigEndianBytesToShort(var1, 34) & '\uffff';
      this.za = 36;
      this.JY = var3;
      this.zz = 36 + this.lm;
      this.HF = 0;
      this.LK = 0;
      int var4 = this.gP - 36;
      this.oQ = this.q(var1, var4);
      this.PV = this.q(var1, var4 + 8);
      this.Me = this.q(var1, var4 + 16);
      this.xW = var1[var4 + 24] >> 4 & 15;
      this.KT = var1[var4 + 24] & 15;
      this.Gf = EndianUtil.bigEndianBytesToInt(var1, var4 + 26);
   }

   @Override
   public int getBlockFormatVersion() {
      return this.nf;
   }

   @Override
   public int getSourceLanguageId() {
      return this.Uv;
   }

   @Override
   public int getTypeId() {
      return this.oW;
   }

   @Override
   public int getNumber() {
      return this.gO;
   }

   @Override
   public int getBlockSize() {
      return this.gP;
   }

   @Override
   public byte[] getBlockBytes() {
      return Arrays.copyOf(this.Dw, this.gP);
   }

   @Override
   public int getBlockByte(int var1) {
      return this.Dw[var1] & 0xFF;
   }

   @Override
   public int getTrailerOffset() {
      return this.getBlockSize() - 36;
   }

   @Override
   public int getPayloadOffset() {
      return this.za;
   }

   @Override
   public int getPayloadSize() {
      return this.lm;
   }

   @Override
   public byte[] getPayloadBytes() {
      return Arrays.copyOfRange(this.Dw, this.za, this.za + this.lm);
   }

   @Override
   public int getInterfaceOffset() {
      return this.zz;
   }

   @Override
   public int getInterfaceSize() {
      return this.JY;
   }

   @Override
   public byte[] getInterfaceBytes() {
      return Arrays.copyOfRange(this.Dw, this.zz, this.zz + this.JY);
   }

   @Override
   public int getOtherOffset() {
      return this.HF;
   }

   @Override
   public int getOtherSize() {
      return this.LK;
   }

   @Override
   public byte[] getOtherBytes() {
      return Arrays.copyOfRange(this.Dw, this.HF, this.HF + this.LK);
   }

   @Override
   public long getModificationTimestamp() {
      return this.io;
   }

   @Override
   public long getInterfaceModificationTimestamp() {
      return this.qa;
   }

   @Override
   public int getKey() {
      return this.Hk;
   }

   @Override
   public String getMetadataBlockName() {
      return this.Me;
   }

   @Override
   public String getMetadataFamilyName() {
      return this.PV;
   }

   @Override
   public String getMetadataAuthorName() {
      return this.oQ;
   }

   @Override
   public int[] getVersion() {
      return new int[]{this.xW, this.KT};
   }

   @Override
   public int getCrc() {
      return this.Gf;
   }

   @Override
   public INativeType generateNativeHeaderType(ITypeManager var1) {
      INativeType var2 = var1.getType("S7_BLOCK2_HEADER");
      if (var2 != null) {
         return var2;
      } else {
         new eM(var1).q();
         INativeType var3 = var1.getType("BYTE");
         INativeType var4 = var1.getType("WORD");
         INativeType var5 = var1.getType("DWORD");
         INativeType var6 = var1.getType("S7TIME");
         IStructureType var7 = var1.createStructure("S7_BLOCK2_HEADER");
         var1.addStructureField(var7, "magic", var4);
         var1.addStructureField(var7, "blkformat", var4);
         var1.addStructureField(var7, "blklang", var3);
         var1.addStructureField(var7, "blktype", var3);
         var1.addStructureField(var7, "blknum", var4);
         var1.addStructureField(var7, "blksize", var5);
         var1.addStructureField(var7, "key", var5);
         var1.addStructureField(var7, "modtime", var6);
         var1.addStructureField(var7, "ifacemodtime", var6);
         var1.addStructureField(var7, "ifacesize", var4);
         var1.addStructureField(var7, "unklen1", var4);
         var1.addStructureField(var7, "unklen2", var4);
         var1.addStructureField(var7, "codesize", var4);
         return var7;
      }
   }

   @Override
   public INativeType generateNativeTrailerType(ITypeManager var1) {
      INativeType var2 = var1.getType("S7_BLOCK2_TRAILER");
      if (var2 != null) {
         return var2;
      } else {
         new eM(var1).q();
         INativeType var3 = var1.getType("BYTE");
         INativeType var4 = var1.getType("WORD");
         INativeType var5 = var1.getType("STRING_FIXED8");
         IStructureType var6 = var1.createStructure("S7_BLOCK2_TRAILER");
         var1.addStructureField(var6, "author", var5);
         var1.addStructureField(var6, "family", var5);
         var1.addStructureField(var6, "name", var5);
         var1.addStructureField(var6, "ver", var3);
         var1.addStructureField(var6, "unk1", var3);
         var1.addStructureField(var6, "crc", var4);
         var1.addStructureField(var6, "unk2", var4);
         var1.addStructureField(var6, "unk3", var4);
         var1.addStructureField(var6, "unk4", var4);
         var1.addStructureField(var6, "unk5", var4);
         return var6;
      }
   }
}
