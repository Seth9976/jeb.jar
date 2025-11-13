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
public class oP extends p {
   private static final ILogger A = GlobalLog.getLogger(oP.class);
   @SerStaticOk
   public static final int pC = 78;
   @SerId(1)
   private byte[] kS;
   @SerId(2)
   private int wS;
   @SerId(3)
   private int UT;
   @SerId(4)
   private int E;
   @SerId(5)
   private int sY;
   @SerId(6)
   private int ys;
   @SerId(7)
   private int ld;
   @SerId(8)
   private int gp;
   @SerId(9)
   private int oT;
   @SerId(10)
   private int fI;
   @SerId(11)
   private int WR;
   @SerId(12)
   private int NS;
   @SerId(13)
   private long vP;
   @SerId(14)
   private long xC;
   @SerId(15)
   private int ED;
   @SerId(16)
   private String Sc;
   @SerId(17)
   private String ah;
   @SerId(18)
   private String eP;
   @SerId(19)
   private int UO;
   @SerId(20)
   private int Ab;
   @SerId(21)
   private int rl;

   public static boolean pC(byte[] var0) {
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

   public static boolean pC(IInput var0) {
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

   public oP(byte[] var1) {
      this.kS = var1;
      this.wS = EndianUtil.littleEndianBytesToShort(var1, 0) & '\uffff';
      this.UT = EndianUtil.littleEndianBytesToShort(var1, 2) & '\uffff';
      this.E = EndianUtil.littleEndianBytesToShort(var1, 4) & '\uffff';
      this.sY = EndianUtil.littleEndianBytesToShort(var1, 6) & '\uffff';
      this.ys = EndianUtil.littleEndianBytesToInt(var1, 8);
      this.gp = EndianUtil.littleEndianBytesToInt(var1, 12);
      this.ld = 78;
      this.fI = EndianUtil.littleEndianBytesToInt(var1, 16);
      this.oT = 78 + this.gp;
      this.NS = EndianUtil.littleEndianBytesToInt(var1, 20);
      this.WR = 78 + this.gp + this.fI;
      this.vP = this.A(var1, 26);
      this.xC = this.A(var1, 32);
      this.ED = EndianUtil.littleEndianBytesToInt(var1, 38);
      this.eP = this.pC(var1, 42);
      this.ah = this.pC(var1, 50);
      this.Sc = this.pC(var1, 58);
      this.UO = var1[66] >> 4 & 15;
      this.Ab = var1[66] & 15;
      this.rl = EndianUtil.littleEndianBytesToInt(var1, 68);
   }

   @Override
   public int getBlockFormatVersion() {
      return this.sY;
   }

   @Override
   public int getSourceLanguageId() {
      return this.wS;
   }

   @Override
   public int getTypeId() {
      return this.UT;
   }

   @Override
   public int getNumber() {
      return this.E;
   }

   @Override
   public int getBlockSize() {
      return this.ys;
   }

   @Override
   public byte[] getBlockBytes() {
      return Arrays.copyOf(this.kS, this.ys);
   }

   @Override
   public int getBlockByte(int var1) {
      return this.kS[var1] & 0xFF;
   }

   @Override
   public int getTrailerOffset() {
      return this.getBlockSize();
   }

   @Override
   public int getPayloadOffset() {
      return this.ld;
   }

   @Override
   public int getPayloadSize() {
      return this.gp;
   }

   @Override
   public byte[] getPayloadBytes() {
      return Arrays.copyOfRange(this.kS, this.ld, this.ld + this.gp);
   }

   @Override
   public int getInterfaceOffset() {
      return this.oT;
   }

   @Override
   public int getInterfaceSize() {
      return this.fI;
   }

   @Override
   public byte[] getInterfaceBytes() {
      return Arrays.copyOfRange(this.kS, this.oT, this.oT + this.fI);
   }

   @Override
   public int getOtherOffset() {
      return this.WR;
   }

   @Override
   public int getOtherSize() {
      return this.NS;
   }

   @Override
   public byte[] getOtherBytes() {
      return Arrays.copyOfRange(this.kS, this.WR, this.WR + this.NS);
   }

   @Override
   public long getModificationTimestamp() {
      return this.vP;
   }

   @Override
   public long getInterfaceModificationTimestamp() {
      return this.xC;
   }

   @Override
   public int getKey() {
      return this.ED;
   }

   @Override
   public String getMetadataBlockName() {
      return this.Sc;
   }

   @Override
   public String getMetadataFamilyName() {
      return this.ah;
   }

   @Override
   public String getMetadataAuthorName() {
      return this.eP;
   }

   @Override
   public int[] getVersion() {
      return new int[]{this.UO, this.Ab};
   }

   @Override
   public int getCrc() {
      return this.rl;
   }

   @Override
   public INativeType generateNativeHeaderType(ITypeManager var1) {
      INativeType var2 = var1.getType("S7_BLOCK1_HEADER");
      if (var2 != null) {
         return var2;
      } else {
         new Pj(var1).pC();
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
