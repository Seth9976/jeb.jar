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
public class vi extends p {
   private static final ILogger kS = GlobalLog.getLogger(vi.class);
   @SerStaticOk
   public static final int pC = 36;
   @SerStaticOk
   public static final int A = 36;
   @SerId(1)
   private byte[] wS;
   @SerId(2)
   private int UT;
   @SerId(3)
   private int E;
   @SerId(4)
   private int sY;
   @SerId(5)
   private int ys;
   @SerId(6)
   private int ld;
   @SerId(7)
   private int gp;
   @SerId(8)
   private int oT;
   @SerId(9)
   private int fI;
   @SerId(10)
   private int WR;
   @SerId(11)
   private int NS;
   @SerId(12)
   private int vP;
   @SerId(13)
   private long xC;
   @SerId(14)
   private long ED;
   @SerId(15)
   private int Sc;
   @SerId(16)
   private String ah;
   @SerId(17)
   private String eP;
   @SerId(18)
   private String UO;
   @SerId(19)
   private int Ab;
   @SerId(20)
   private int rl;
   @SerId(21)
   private int z;

   public static boolean pC(byte[] var0) {
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

   public static boolean pC(IInput var0) {
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

   public vi(byte[] var1) {
      this.wS = var1;
      int var2 = EndianUtil.bigEndianBytesToShort(var1, 0) & '\uffff';
      Assert.a(var2 == 28784);
      this.ys = EndianUtil.bigEndianBytesToShort(var1, 2) & '\uffff';
      this.UT = var1[4] & 255;
      this.E = var1[5] & 255;
      this.sY = EndianUtil.bigEndianBytesToShort(var1, 6) & '\uffff';
      this.ld = EndianUtil.bigEndianBytesToInt(var1, 8);
      this.Sc = EndianUtil.bigEndianBytesToInt(var1, 12);
      this.xC = this.A(var1, 16);
      this.ED = this.A(var1, 22);
      int var3 = EndianUtil.bigEndianBytesToShort(var1, 28) & '\uffff';
      EndianUtil.bigEndianBytesToShort(var1, 30);
      EndianUtil.bigEndianBytesToShort(var1, 32);
      this.oT = EndianUtil.bigEndianBytesToShort(var1, 34) & '\uffff';
      this.gp = 36;
      this.WR = var3;
      this.fI = 36 + this.oT;
      this.NS = 0;
      this.vP = 0;
      int var4 = this.ld - 36;
      this.UO = this.pC(var1, var4);
      this.eP = this.pC(var1, var4 + 8);
      this.ah = this.pC(var1, var4 + 16);
      this.Ab = var1[var4 + 24] >> 4 & 15;
      this.rl = var1[var4 + 24] & 15;
      this.z = EndianUtil.bigEndianBytesToInt(var1, var4 + 26);
   }

   @Override
   public int getBlockFormatVersion() {
      return this.ys;
   }

   @Override
   public int getSourceLanguageId() {
      return this.UT;
   }

   @Override
   public int getTypeId() {
      return this.E;
   }

   @Override
   public int getNumber() {
      return this.sY;
   }

   @Override
   public int getBlockSize() {
      return this.ld;
   }

   @Override
   public byte[] getBlockBytes() {
      return Arrays.copyOf(this.wS, this.ld);
   }

   @Override
   public int getBlockByte(int var1) {
      return this.wS[var1] & 0xFF;
   }

   @Override
   public int getTrailerOffset() {
      return this.getBlockSize() - 36;
   }

   @Override
   public int getPayloadOffset() {
      return this.gp;
   }

   @Override
   public int getPayloadSize() {
      return this.oT;
   }

   @Override
   public byte[] getPayloadBytes() {
      return Arrays.copyOfRange(this.wS, this.gp, this.gp + this.oT);
   }

   @Override
   public int getInterfaceOffset() {
      return this.fI;
   }

   @Override
   public int getInterfaceSize() {
      return this.WR;
   }

   @Override
   public byte[] getInterfaceBytes() {
      return Arrays.copyOfRange(this.wS, this.fI, this.fI + this.WR);
   }

   @Override
   public int getOtherOffset() {
      return this.NS;
   }

   @Override
   public int getOtherSize() {
      return this.vP;
   }

   @Override
   public byte[] getOtherBytes() {
      return Arrays.copyOfRange(this.wS, this.NS, this.NS + this.vP);
   }

   @Override
   public long getModificationTimestamp() {
      return this.xC;
   }

   @Override
   public long getInterfaceModificationTimestamp() {
      return this.ED;
   }

   @Override
   public int getKey() {
      return this.Sc;
   }

   @Override
   public String getMetadataBlockName() {
      return this.ah;
   }

   @Override
   public String getMetadataFamilyName() {
      return this.eP;
   }

   @Override
   public String getMetadataAuthorName() {
      return this.UO;
   }

   @Override
   public int[] getVersion() {
      return new int[]{this.Ab, this.rl};
   }

   @Override
   public int getCrc() {
      return this.z;
   }

   @Override
   public INativeType generateNativeHeaderType(ITypeManager var1) {
      INativeType var2 = var1.getType("S7_BLOCK2_HEADER");
      if (var2 != null) {
         return var2;
      } else {
         new Pj(var1).pC();
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
         new Pj(var1).pC();
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
