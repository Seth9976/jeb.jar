package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.NotificationType;
import com.pnfsoftware.jeb.core.units.UnitNotification;
import com.pnfsoftware.jeb.core.units.code.android.DexParsingException;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDexFile;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserError;
import com.pnfsoftware.jeb.core.units.code.android.dex.DalvikParserErrorType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexMethodHandleType;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMap;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.SizeFormatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import com.pnfsoftware.jebglobal.bec;
import com.pnfsoftware.jebglobal.beg;
import com.pnfsoftware.jebglobal.beo;
import com.pnfsoftware.jebglobal.bes;
import com.pnfsoftware.jebglobal.bev;
import com.pnfsoftware.jebglobal.bfa;
import com.pnfsoftware.jebglobal.bfb;
import com.pnfsoftware.jebglobal.bfo;
import com.pnfsoftware.jebglobal.bfp;
import com.pnfsoftware.jebglobal.bfq;
import com.pnfsoftware.jebglobal.bfr;
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bfv;
import com.pnfsoftware.jebglobal.bfw;
import com.pnfsoftware.jebglobal.bfx;
import com.pnfsoftware.jebglobal.bfy;
import com.pnfsoftware.jebglobal.bfz;
import com.pnfsoftware.jebglobal.bga;
import com.pnfsoftware.jebglobal.bgb;
import com.pnfsoftware.jebglobal.bgd;
import com.pnfsoftware.jebglobal.bge;
import com.pnfsoftware.jebglobal.bgf;
import com.pnfsoftware.jebglobal.bgg;
import com.pnfsoftware.jebglobal.bgh;
import com.pnfsoftware.jebglobal.bgi;
import com.pnfsoftware.jebglobal.bgj;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.Adler32;

@Ser
@SerVersion(2)
public class KD implements IDexFile, bgb {
   private static final ILogger Nq = GlobalLog.getLogger(KD.class);
   @SerId(1)
   vi pC;
   @SerId(2)
   int A;
   @SerId(3)
   byte[] kS;
   @SerId(value = 4, version = 1)
   int wS;
   @SerId(value = 5, version = 1)
   int UT;
   @SerId(value = 6, version = 1)
   Endianness E;
   @SerId(value = 7, version = 1)
   int sY;
   @SerId(value = 8, version = 1)
   int ys;
   @SerId(value = 9, version = 1)
   int ld;
   @SerId(value = 10, version = 1)
   byte[] gp;
   @SerId(value = 11, version = 1)
   byte[] oT;
   @SerId(value = 12, version = 1)
   int fI;
   @SerId(value = 13, version = 1)
   int WR;
   @SerId(value = 14, version = 1)
   int NS;
   @SerId(value = 15, version = 1)
   int vP;
   @SerId(value = 16, version = 1)
   int xC;
   @SerId(value = 17, version = 1)
   RC ED;
   @SerId(value = 18, version = 1)
   int Sc;
   @SerId(value = 19, version = 1)
   int ah;
   @SerId(value = 20, version = 1)
   int eP;
   @SerId(value = 21, version = 1)
   int UO;
   @SerId(value = 22, version = 1)
   int Ab;
   @SerId(value = 23, version = 1)
   int rl;
   @SerId(value = 24, version = 1)
   int z;
   @SerId(value = 25, version = 1)
   int Ek;
   @SerId(value = 26, version = 1)
   int hK;
   @SerId(value = 27, version = 1)
   int Er;
   @SerId(value = 28, version = 1)
   int FE;
   @SerId(value = 29, version = 1)
   int Aj;
   @SerId(value = 30, version = 1)
   int EX;
   @SerId(value = 31, version = 1)
   int LM;
   @SerId(value = 32, version = 1)
   int mv;
   @SerId(value = 33, version = 1)
   int sO;
   @SerId(value = 34, version = 2)
   int os;
   @SerId(value = 35, version = 2)
   int Cu;
   @SerId(value = 36, version = 2)
   int hZ;
   @SerId(value = 37, version = 2)
   int UW;
   @SerId(value = 38, version = 2)
   int PR;
   @SerTransient
   int cX;
   @SerTransient
   beo DQ;
   @SerTransient
   boolean ZN;
   @SerTransient
   boolean OB;
   @SerTransient
   boolean pF;
   @SerTransient
   boolean Bc;
   @SerTransient
   int OI;
   @SerTransient
   List Bf = new ArrayList();
   @SerTransient
   List Pe = new ArrayList();
   @SerId(39)
   List ck = new ArrayList();
   @SerId(40)
   List RW = new ArrayList();
   @SerId(41)
   List e = new ArrayList();
   @SerId(42)
   List xM = new ArrayList();
   @SerId(43)
   List kU = new ArrayList();
   @SerId(44)
   List Kq = new ArrayList();
   @SerId(45)
   List go = new ArrayList();
   @SerId(46)
   List JF = new ArrayList();

   @SerCustomInitPostGraph
   private void UT() {
      if (this.PR == 0) {
         this.PR = this.A;
      }
   }

   public KD(vi var1, byte[] var2, int var3, int var4, int var5, int var6) {
      Assert.a(var1 != null && var2 != null);
      this.pC = var1;
      this.kS = var2;
      Assert.a(var3 >= 0 && var3 < var2.length);
      this.hZ = var3;
      Assert.a(var5 >= 0);
      Assert.a(var4 >= var5);
      Assert.a(var6 >= 0);
      Assert.a(var4 > 0 || var5 == 0 && var6 == 0);
      this.PR = var4;
      this.A = var5;
      this.UW = var6;
   }

   String pC() {
      String var1 = "#" + this.A;
      if (this.UW >= 1) {
         var1 = var1 + "-" + this.UW;
      }

      return var1;
   }

   private void pC(String var1, Object... var2) {
      if (!this.pC.ED()) {
         this.pC.logWarn(true, var1, var2);
      } else {
         String var3 = Strings.ff(var1, var2);
         this.pC.logWarn(true, "[Dex %s] %s", this.pC(), var3);
      }
   }

   private void A(String var1, Object... var2) {
      if (!this.pC.ED()) {
         this.pC.logError(true, var1, var2);
      } else {
         String var3 = Strings.ff(var1, var2);
         this.pC.logError(true, "[Dex %s] %s", this.pC(), var3);
      }
   }

   public vi A() {
      return this.pC;
   }

   @Override
   public int getVersion() {
      return this.wS;
   }

   @Override
   public int getHeaderSize() {
      return this.UT;
   }

   @Override
   public Endianness getEndianness() {
      return this.E;
   }

   @Override
   public int getExpectedSize() {
      return this.sY;
   }

   @Override
   public int getExpectedChecksum() {
      return this.ld;
   }

   @Override
   public int getActualChecksum() {
      return this.ys;
   }

   @Override
   public byte[] getExpectedSignature() {
      return this.oT;
   }

   @Override
   public byte[] getActualSignature() {
      return this.gp;
   }

   @Override
   public int getLinkSectionSize() {
      return this.WR;
   }

   @Override
   public int getLinkSectionOffset() {
      return this.fI;
   }

   @Override
   public int getDataSectionSize() {
      return this.vP;
   }

   @Override
   public int getDataSectionOffset() {
      return this.NS;
   }

   @Override
   public int getMapOffset() {
      return this.xC;
   }

   @Override
   public IDexMap getMap() {
      return this.ED;
   }

   @Override
   public int getStringsPoolSize() {
      return this.Sc;
   }

   @Override
   public int getStringsPoolOffset() {
      return this.ah;
   }

   @Override
   public int getTypesPoolSize() {
      return this.eP;
   }

   @Override
   public int getTypesPoolOffset() {
      return this.UO;
   }

   @Override
   public int getPrototypesPoolSize() {
      return this.Ab;
   }

   @Override
   public int getPrototypesPoolOffset() {
      return this.rl;
   }

   @Override
   public int getFieldsPoolSize() {
      return this.z;
   }

   @Override
   public int getFieldsPoolOffset() {
      return this.Ek;
   }

   @Override
   public int getMethodsPoolSize() {
      return this.hK;
   }

   @Override
   public int getMethodsPoolOffset() {
      return this.Er;
   }

   @Override
   public int getClassesPoolSize() {
      return this.FE;
   }

   @Override
   public int getClassesPoolOffset() {
      return this.Aj;
   }

   @Override
   public int getCallSitesPoolSize() {
      return this.EX;
   }

   @Override
   public int getCallSitesPoolOffset() {
      return this.LM;
   }

   @Override
   public int getMethodHandlesPoolSize() {
      return this.mv;
   }

   @Override
   public int getMethodHandlesPoolOffset() {
      return this.sO;
   }

   void kS() {
      if (this.kS.length - this.hZ < 112) {
         throw new DexParsingException("Not enough bytes to process a Dex header: " + this.kS.length);
      } else if (this.kS[this.hZ] == 100 && this.kS[this.hZ + 1] == 101 && this.kS[this.hZ + 2] == 120) {
         if (this.kS[this.hZ + 3] != 10) {
            this.pC(S.L("Modified Dex magic: %s"), Formatter.escapeBytes(this.kS, this.hZ, 4));
         }

         if (Character.isDigit(this.kS[this.hZ + 4])
            && Character.isDigit(this.kS[this.hZ + 5])
            && Character.isDigit(this.kS[this.hZ + 6])
            && this.kS[this.hZ + 7] == 0) {
            String var1 = Strings.decodeASCII(this.kS, this.hZ + 4, 3);
            var1 = Strings.ltrim(var1, '0');
            this.wS = Conversion.stringToInt(var1);
         }

         if (this.wS < 35 || this.wS > 41) {
            this.ZN = true;
            String var15 = S.L("Unknown DEX version") + ": " + this.wS;
            this.pC(var15);
            if (this.pC.pC) {
               throw new DexParsingException(var15);
            }
         }

         this.sY = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 32);
         if (this.sY < 0) {
            long var18 = this.sY & 4294967295L;
            throw new DexParsingException(Strings.ff("Dex size exceeds 2Gb: %s", SizeFormatter.formatByteSize(var18)));
         } else {
            if (this.hZ + this.sY > this.kS.length) {
               String var16 = Strings.ff(S.L("Dex size smaller than expected (%d bytes): missing %d bytes"), this.sY, this.hZ + this.sY - this.kS.length);
               this.pC(var16);
               this.Bc = true;
            }

            this.ld = this.kS[this.hZ + 8] & 255
               | (this.kS[this.hZ + 9] & 255) << 8
               | (this.kS[this.hZ + 10] & 255) << 16
               | (this.kS[this.hZ + 11] & 255) << 24;
            Adler32 var17 = new Adler32();
            var17.update(this.kS, this.hZ + 12, this.sY - 12);
            this.ys = (int)var17.getValue();
            if (this.ld != this.ys) {
               this.OB = true;
               String var2 = Strings.ff(S.L("Invalid DEX checksum (expected=%08X, actual=%08X)"), this.ld, this.ys);
               this.pC(var2);
               if (this.pC.A) {
                  throw new DexParsingException(var2);
               }
            }

            this.oT = Arrays.copyOfRange(this.kS, this.hZ + 12, this.hZ + 32);

            MessageDigest var19;
            try {
               var19 = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException var12) {
               throw new JebRuntimeException("Cannot retrieve SHA-1 algorithm");
            }

            var19.update(this.kS, this.hZ + 32, this.sY - 32);
            this.gp = var19.digest();
            if (!Arrays.equals(this.oT, this.gp)) {
               this.pF = true;
               String var3 = Strings.ff(
                  S.L("Invalid DEX signature (expected=%s, actual=%s)"), Formatter.formatBinaryLine(this.oT), Formatter.formatBinaryLine(this.gp)
               );
               Nq.debug(var3);
               if (this.pC.A) {
                  throw new DexParsingException(var3);
               }
            }

            this.UT = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 36);
            if (this.UT < 112) {
               String var20 = Strings.ff(S.L("Invalid DEX header size (%Xh)"), this.UT);
               this.pC(var20);
            }

            int var21 = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 40);
            if (var21 == 305419896) {
               this.E = Endianness.LITTLE_ENDIAN;
               this.WR = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 44);
               this.fI = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 48);
               this.xC = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 52);
               this.Sc = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 56);
               this.ah = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 60);
               this.eP = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 64);
               this.UO = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 68);
               this.Ab = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 72);
               this.rl = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 76);
               this.z = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 80);
               this.Ek = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 84);
               this.hK = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 88);
               this.Er = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 92);
               this.FE = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 96);
               this.Aj = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 100);
               this.vP = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 104);
               this.NS = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 108);
               if (this.wS >= 41) {
                  this.Cu = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 112);
                  this.os = DexUtil.bytearrayULEInt32ToInt(this.kS, this.hZ + 116);
                  if (this.os != this.hZ) {
                     String var4 = Strings.ff(S.L("Unexpected container offset (%Xh)"), this.os);
                     this.pC(var4);
                  }
               }

               try {
                  this.ED = RC.pC(this.kS, this.xC);
               } catch (RuntimeException var13) {
                  if (!this.pF) {
                     this.A(S.L("Error parsing the Dex map"));
                     JebCoreService.notifySilentExceptionToClient(var13);
                  }

                  this.ED = new RC();
               }

               this.EX = 0;
               this.LM = 0;
               IDexMap.Entry var22 = this.ED.getEntry(7);
               if (var22 != null) {
                  this.EX = var22.getCount();
                  this.LM = var22.getOffset();
               }

               this.mv = 0;
               this.sO = 0;
               var22 = this.ED.getEntry(8);
               if (var22 != null) {
                  this.mv = var22.getCount();
                  this.sO = var22.getOffset();
               }

               if (this.PR == 0) {
                  this.pC.E = new bgi(this.pC, this.Sc);
                  this.pC.sY = new bgj(this.pC, this.eP);
                  this.pC.ys = new bgh(this.pC, this.Ab);
                  this.pC.ld = new bga(this.pC, this.z);
                  this.pC.gp = new bgf(this.pC, this.hK);
                  this.pC.NS = new bfo(this.pC, this.EX);
                  this.pC.vP = new bge(this.pC, this.mv);
               }

               int var5 = this.pC.E.pC();
               int var6 = this.pC.sY.pC();
               int var7 = this.pC.ys.pC();
               int var8 = this.pC.ld.pC();
               int var9 = this.pC.gp.pC();
               int var10 = this.pC.NS.pC();
               int var11 = this.pC.vP.pC();
               this.pC(this.Sc, this.ah, this.pC.E);
               this.pC(this.eP, this.UO, this.pC.sY);
               this.pC(this.Ab, this.rl, this.pC.ys);
               this.pC(this.z, this.Ek, this.pC.ld);
               this.pC(this.hK, this.Er, this.pC.gp);
               this.pC(this.mv, this.sO, this.pC.vP);
               this.pC(this.EX, this.LM, this.pC.NS);
               Object[] var10000 = new Object[]{this.pC()};
               this.pC(this.pC.E, var5, this.Sc);
               this.pC(this.pC.sY, var6, this.eP);
               this.pC(this.pC.ys, var7, this.Ab);
               this.pC(this.pC.ld, var8, this.z);
               this.pC(this.pC.gp, var9, this.hK);
               this.pC(this.pC.NS, var10, this.EX);
               this.pC(this.pC.vP, var11, this.mv);
            } else if (var21 != 2018915346) {
               throw new DexParsingException(Strings.ff("Illegal endianness tag: 0x%08X", var21));
            } else {
               this.E = Endianness.BIG_ENDIAN;
               throw new DexParsingException("Big-endian Dex are not supported");
            }
         }
      } else {
         throw new DexParsingException("Illegal Dex magic: " + Formatter.escapeBytes(this.kS, this.hZ, 4));
      }
   }

   private void pC(bgg var1, int var2, int var3) {
      int var4 = var1.pC() - var2;
      Object[] var10000 = new Object[]{var1, var4, S.L("dupplicates"), var3 - var4};
   }

   private void pC(int var1, int var2, bgi var3) {
      HashSet var4 = new HashSet();
      int[] var5 = new int[1];

      for (int var6 = 0; var6 < var1; var6++) {
         int var7 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2);
         int var8 = DexUtil.bytearrayULEB128ToInt(this.kS, var7, var5);
         int var10 = var7 + var5[0];

         String var9;
         try {
            var9 = DexUtil.bytearrayMUTF8ToStringFast(this.kS, var10, var5, var8);
            if (var8 != var9.length()) {
               throw new DexParsingException(Strings.ff("Length mismatch: expecting %d, got %d", var8, var9.length()));
            }
         } catch (DexParsingException var12) {
            var9 = Strings.ff("__invalid_string_%d_%X", this.PR, var7);
            this.OI++;
         }

         bfx var11 = var3.pC(var9, false, this.PR >= 1);
         this.ck.add(var11.getIndex());
         if (!var4.add(var9)) {
            this.pC(S.L("Duplicate string found: \"%s\""), Formatter.escapeString(var9));
         }

         var2 += 4;
      }
   }

   private void pC(int var1, int var2, bgj var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 4 * var4);
         var5 = this.pC(var5);
         bfy var6 = var3.UT(this.pC.A(var5));
         this.RW.add(var6.getIndex());
      }
   }

   private void pC(int var1, int var2, bgh var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 12 * var4);
         var5 = this.pC(var5);
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 12 * var4 + 4);
         var6 = this.A(var6);
         int[] var7 = null;
         int var8 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 12 * var4 + 8);
         if (var8 > 0) {
            int var9 = DexUtil.bytearrayULEInt32ToInt(this.kS, var8);
            var7 = new int[var9];

            for (int var10 = 0; var10 < var9; var10++) {
               int var11 = DexUtil.bytearrayULEInt16ToInt(this.kS, var8 + 4 + 2 * var10);
               var7[var10] = this.A(var11);
            }
         }

         bfw var14 = var3.pC(var5, var6, var7);
         this.e.add(var14.pC());
      }
   }

   private void pC(int var1, int var2, bga var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var4);
         var5 = this.A(var5);
         int var6 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var4 + 2);
         var6 = this.A(var6);
         int var7 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 8 * var4 + 4);
         var7 = this.pC(var7);
         bft var8 = var3.pC(var5, var6, var7);
         this.xM.add(var8.getIndex());
      }
   }

   private void pC(int var1, int var2, bgf var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var4);
         int var6 = this.A(var5);
         int var7 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var4 + 2);
         int var8 = this.kS(var7);
         int var9 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 8 * var4 + 4);
         int var10 = this.pC(var9);
         bfu var11 = var3.pC(var6, var8, var10);
         this.kU.add(var11.getIndex());
      }
   }

   private void pC(int var1, int var2, bfo var3) {
      int[] var4 = new int[1];

      for (int var5 = 0; var5 < var1; var5++) {
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 4 * var5);
         beg[] var7 = beg.A(this, this.kS, var6, var4);
         if (var7.length < 3) {
            throw new DexParsingException("A callsite item value array must contain at least 3 elements");
         }

         bfr var8 = var3.pC(var7);
         this.Kq.add(var8.getIndex());
      }
   }

   private void pC(int var1, int var2, bge var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var4);
         DexMethodHandleType var6 = DexMethodHandleType.get(var5);
         int var7 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var4 + 4);
         int var8 = -1;
         if (var6.isFieldAccessor()) {
            var8 = this.wS(var7);
         } else if (var6.isMethodInvoker()) {
            var8 = this.UT(var7);
         } else {
            this.pC(S.L("Illegal method handle type: %d"), var5);
            JebCoreService.notifySilentExceptionToClient(new DexParsingException("Illegal method handle type: " + var5));
         }

         bfv var9 = var3.pC(var5, var8);
         this.go.add(var9.getIndex());
      }
   }

   int wS() {
      return this.pC(this.FE, this.Aj);
   }

   private int pC(int var1, int var2) {
      int var3 = 0;
      int[] var4 = new int[1];
      int var5 = 0;

      while (var5 < var1) {
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 24);
         if (var6 > 0) {
            DexUtil.bytearrayULEB128ToInt(this.kS, var6, var4);
            var6 += var4[0];
            DexUtil.bytearrayULEB128ToInt(this.kS, var6, var4);
            var6 += var4[0];
            var3 += DexUtil.bytearrayULEB128ToInt(this.kS, var6, var4);
            var6 += var4[0];
            var3 += DexUtil.bytearrayULEB128ToInt(this.kS, var6, var4);
            int var10000 = var4[0];
         }

         var5++;
         var2 += 32;
      }

      return var3;
   }

   void pC(beo var1) {
      this.DQ = var1;

      try {
         if (this.PR == 0) {
            this.pC.oT = new bfq(this.pC, this.FE);
         }

         this.pC(this.FE, this.Aj, this.pC.oT);
      } finally {
         this.DQ = null;
      }
   }

   private void pC(int var1, int var2, bfq var3) {
      int var4 = 0;

      while (var4 < var1) {
         int var5 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2);
         var5 = this.A(var5);
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 4);
         int var7 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 8);
         var7 = this.A(var7);
         int[] var8 = null;
         int var9 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 12);
         if (var9 > 0) {
            int var10 = DexUtil.bytearrayULEInt32ToInt(this.kS, var9);
            var8 = new int[var10];

            for (int var11 = 0; var11 < var10; var11++) {
               int var12 = DexUtil.bytearrayULEInt16ToInt(this.kS, var9 + 4 + 2 * var11);
               var8[var11] = this.A(var12);
            }
         }

         int var30 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 16);
         var30 = this.pC(var30);
         bec var32 = null;
         int var33 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 20);
         if (var33 > 0) {
            var32 = bec.pC(this.pC, this, this.kS, var33);
         }

         bfp var13 = null;
         int var14 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 24);
         if (var14 > 0) {
            var13 = this.pC(this.kS, var14);

            for (bfz var16 : var13.pC()) {
               int var17 = this.pC.E(var16.getFieldIndex()).getClassTypeIndex();
               if (var17 != var5) {
                  Nq.warn(
                     S.L("Unexpected field definition: declared type %s does not match the containing type %s"),
                     this.pC.wS(var17).getSignature(false),
                     this.pC.wS(var5).getSignature(false)
                  );
               }
            }

            for (bgd var36 : var13.kS()) {
               int var38 = this.pC.sY(var36.getMethodIndex()).getClassTypeIndex();
               if (var38 != var5) {
                  Nq.warn(
                     S.L("Unexpected method definition: declared type %s does not match the containing type %s"),
                     this.pC.wS(var38).getSignature(false),
                     this.pC.wS(var5).getSignature(false)
                  );
               }
            }
         }

         beg[] var35 = null;
         int var37 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 28);
         if (var37 > 0) {
            int[] var39 = new int[1];
            var35 = beg.A(this, this.kS, var37, var39);
            if (var13 != null) {
               List var18 = var13.getStaticFields();
               int var19 = 0;

               for (beg var23 : var35) {
                  if (var19 >= var18.size()) {
                     break;
                  }

                  int var24 = var23.getType();
                  bfz var25 = (bfz)var18.get(var19);
                  int var26 = var25.getFieldIndex();
                  if (var25.isFinal()) {
                     if (var24 == 0) {
                        this.pC.ah.setValue(var23.getByte(), var26);
                     } else if (var24 == 3) {
                        this.pC.ah.setValue(var23.getChar(), var26);
                     } else if (var24 == 2) {
                        this.pC.ah.setValue(var23.getShort(), var26);
                     } else if (var24 == 4) {
                        this.pC.ah.setValue(var23.getInt(), var26);
                     } else if (var24 == 6) {
                        this.pC.ah.setValue(var23.getLong(), var26);
                     } else if (var24 == 16) {
                        this.pC.ah.setValue(var23.getFloat(), var26);
                     } else if (var24 == 17) {
                        this.pC.ah.setValue(var23.getDouble(), var26);
                     }
                  }

                  if (var24 == 23) {
                     this.pC.WR.pC(var23.getStringIndex(), var26, DH.A);
                  }

                  var19++;
               }
            }
         }

         if (var32 != null) {
            try {
               this.pC.WR.pC(var5, var32);
            } catch (Exception var27) {
               JebCoreService.silentExcept(var27);
            }
         }

         if (this.pC.kS) {
            DexUtil.validateClassFlags(var6);
         }

         bfs var40 = var3.pC(var5, var6, var7, var8, var30, var32, var13, var35, this.PR);
         this.JF.add(var40.getIndex());
         var4++;
         var2 += 32;
      }
   }

   private bfp pC(byte[] var1, int var2) throws DexParsingException {
      int[] var3 = new int[1];
      int var4 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      int var5 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      int var6 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      int var7 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      bfz[] var8 = this.pC(var1, var4, var2, var3);
      var2 += var3[0];
      bfz[] var9 = this.pC(var1, var5, var2, var3);
      var2 += var3[0];
      bgd[] var10 = this.A(var1, var6, var2, var3);
      var2 += var3[0];
      bgd[] var11 = this.A(var1, var7, var2, var3);
      int var10000 = var3[0];
      return new bfp(var8, var9, var10, var11);
   }

   private bfz[] pC(byte[] var1, int var2, int var3, int[] var4) {
      bfz[] var5 = new bfz[var2];
      int[] var6 = new int[1];
      int var7 = 0;
      int var8 = var3;

      for (int var9 = 0; var9 < var2; var9++) {
         int var10 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         var7 += var10;
         int var11 = this.wS(var7);
         int var12 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         if (this.pC.kS) {
            DexUtil.validateFieldFlags(var12);
         }

         bfz var13 = new bfz(var11, var12, this.PR);
         var5[var9] = var13;
         this.pC.ld.pC(var11, var13);
      }

      var4[0] = var8 - var3;
      return var5;
   }

   private bgd[] A(byte[] var1, int var2, int var3, int[] var4) {
      bgd[] var5 = new bgd[var2];
      int[] var6 = new int[1];
      int var7 = 0;
      int var8 = var3;

      for (int var9 = 0; var9 < var2; var9++) {
         int var10 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         var7 += var10;
         int var11 = this.UT(var7);
         int var12 = ++this.pC.z;
         int var13 = this.pC.Ek;
         if (this.pC.rl != null && var13 > 0 && var12 <= var13) {
            String var14 = S.L("Parsing dex code");
            if (var12 < var13 && this.pC.rl.canSend()) {
               double var15 = var12 * 100.0 / var13;
               this.pC.rl.log("%s ... %.1f%%", var14, var15);
            } else if (var12 == var13) {
               this.pC.rl.forceLog("%s ... %s", var14, S.L("completed"));
            }
         }

         int var20 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         int var21 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         bev var16 = null;
         if (var21 > 0) {
            var16 = this.A(var21, var11);
         }

         if (this.pC.kS) {
            DexUtil.validateMethodFlags(var20);
         }

         bgd var17 = new bgd(var11, var20, var16, this.PR);
         var5[var9] = var17;
         this.pC.gp.pC(var11, var17);
      }

      var4[0] = var8 - var3;
      return var5;
   }

   private bev A(int var1, int var2) {
      int var3 = DexUtil.bytearrayULEInt16ToInt(this.kS, var1);
      int var4 = DexUtil.bytearrayULEInt16ToInt(this.kS, var1 + 2);
      int var5 = DexUtil.bytearrayULEInt16ToInt(this.kS, var1 + 4);
      int var6 = DexUtil.bytearrayULEInt16ToInt(this.kS, var1 + 6);
      int var7 = 0;

      try {
         var7 = DexUtil.bytearrayULEInt32ToInt(this.kS, var1 + 8);
      } catch (DexParsingException var21) {
         this.A(S.L("0x%X: Bad offset to debug item object"), var1 + 8);
      }

      int var8 = DexUtil.bytearrayULEInt32ToInt(this.kS, var1 + 12);
      int var9 = var1 + 16;
      int var10 = var9 + 2 * var8;
      bfb[] var11 = null;
      if (var6 > 0) {
         int var12 = var10 + (var6 > 0 && var8 % 2 != 0 ? 2 : 0);
         var11 = this.kS(var6, var12);
      }

      CFG var23 = null;
      Map var13 = null;
      bes var16 = null;

      Object var14;
      Object var15;
      try {
         beo.Av var17 = this.DQ.pC(this.kS, var9, 2 * var8, var3, var11, var2, this, this.wS);
         var23 = var17.pC;
         var13 = var17.A;
         var14 = var17.pC.getInstructions();
         var15 = var17.kS;
         if (var15 != null && !var15.isEmpty()) {
            if (this.Pe != null) {
               this.Pe.add(var2);
            }

            for (DalvikParserError var19 : var15) {
               if (var19.getErrorType().isHardFail()) {
                  if (this.Bf != null) {
                     this.Bf.add(var2);
                  }
                  break;
               }
            }
         }
      } catch (Exception var22) {
         if (this.Bf != null) {
            this.Bf.add(var2);
         }

         Nq.catching(var22);
         var14 = new ArrayList();
         var15 = new ArrayList();
         var15.add(new DalvikParserError(DalvikParserErrorType.UNKNOWN_ERROR));
         this.pC
            .addNotification(
               new UnitNotification(NotificationType.CORRUPTION, Strings.ff(S.L("Failed to parse some bytecode (dex:%s, method_index:%d)"), this.pC(), var2))
            );
      }

      if (var7 > 0) {
         try {
            var16 = new bes(this, this.kS, var7, var8);
         } catch (Exception var20) {
            var16 = null;
            this.pC
               .addNotification(
                  new UnitNotification(
                     NotificationType.CORRUPTION, String.format(S.L("Failed to parse the debug info (dex:%s, method_index:%d)"), this.pC(), var2)
                  )
               );
         }
      }

      bev var24 = new bev(var3, var4, var5, var16, var9, 2 * var8, (List)var15, (List)var14, var23, var13, var11, this.PR);
      var24.pC(var2);
      return var24;
   }

   private bfb[] kS(int var1, int var2) {
      bfb[] var3 = new bfb[var1];
      int var4 = var2 + 8 * var1;

      for (int var5 = 0; var5 < var1; var5++) {
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.kS, var2 + 8 * var5);
         int var7 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var5 + 4);
         int var8 = DexUtil.bytearrayULEInt16ToInt(this.kS, var2 + 8 * var5 + 6);
         int var9 = var4 + var8;
         int[] var10 = new int[1];
         int var11 = DexUtil.bytearraySLEB128ToInt(this.kS, var9, var10);
         var9 += var10[0];
         int var12 = var11 >= 0 ? var11 : -var11;
         ArrayList var13 = new ArrayList(var12 + 1);

         for (int var14 = 0; var14 < var12; var14++) {
            int var15 = DexUtil.bytearrayULEB128ToInt(this.kS, var9, var10);
            var15 = this.A(var15);
            var9 += var10[0];
            int var16 = DexUtil.bytearrayULEB128ToInt(this.kS, var9, var10);
            var9 += var10[0];
            var13.add(new bfa(var15, 2 * var16));
         }

         if (var11 <= 0) {
            int var19 = DexUtil.bytearrayULEB128ToInt(this.kS, var9, var10);
            var13.add(new bfa(-1, 2 * var19));
            int var10000 = var10[0];
         }

         bfb var20 = new bfb(2 * var6, 2 * var7, var13);
         var3[var5] = var20;
      }

      return var3;
   }

   @Override
   public byte[] getData() {
      return this.kS;
   }

   @Override
   public int getOffset() {
      return this.hZ;
   }

   @Override
   public int getFileIndex() {
      return this.A;
   }

   @Override
   public int getIndexInContainer() {
      return this.UW;
   }

   @Override
   public int pC(int var1) {
      if (var1 != -1 && this.ck != null) {
         int var2 = (Integer)this.ck.get(var1);
         bfx var3 = (bfx)this.pC.E.pC(var2);
         if (var3 != null) {
            var3.pC();
         }

         return var2;
      } else {
         return var1;
      }
   }

   @Override
   public int A(int var1) {
      return var1 != -1 && this.RW != null ? (Integer)this.RW.get(var1) : var1;
   }

   @Override
   public int kS(int var1) {
      return var1 != -1 && this.e != null ? (Integer)this.e.get(var1) : var1;
   }

   @Override
   public int wS(int var1) {
      return var1 != -1 && this.xM != null ? (Integer)this.xM.get(var1) : var1;
   }

   @Override
   public int UT(int var1) {
      return var1 != -1 && this.kU != null ? (Integer)this.kU.get(var1) : var1;
   }

   @Override
   public int E(int var1) {
      return var1 != -1 && this.Kq != null ? (Integer)this.Kq.get(var1) : var1;
   }

   @Override
   public int sY(int var1) {
      return var1 != -1 && this.go != null ? (Integer)this.go.get(var1) : var1;
   }
}
