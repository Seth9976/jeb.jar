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
import com.pnfsoftware.jebglobal.bhw;
import com.pnfsoftware.jebglobal.bia;
import com.pnfsoftware.jebglobal.bii;
import com.pnfsoftware.jebglobal.bim;
import com.pnfsoftware.jebglobal.bip;
import com.pnfsoftware.jebglobal.biv;
import com.pnfsoftware.jebglobal.biw;
import com.pnfsoftware.jebglobal.bjj;
import com.pnfsoftware.jebglobal.bjk;
import com.pnfsoftware.jebglobal.bjl;
import com.pnfsoftware.jebglobal.bjm;
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bjq;
import com.pnfsoftware.jebglobal.bjr;
import com.pnfsoftware.jebglobal.bjs;
import com.pnfsoftware.jebglobal.bjt;
import com.pnfsoftware.jebglobal.bju;
import com.pnfsoftware.jebglobal.bjv;
import com.pnfsoftware.jebglobal.bjw;
import com.pnfsoftware.jebglobal.bjy;
import com.pnfsoftware.jebglobal.bjz;
import com.pnfsoftware.jebglobal.bka;
import com.pnfsoftware.jebglobal.bkb;
import com.pnfsoftware.jebglobal.bkc;
import com.pnfsoftware.jebglobal.bkd;
import com.pnfsoftware.jebglobal.bke;
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
public class qV implements IDexFile, bjw {
   private static final ILogger nY = GlobalLog.getLogger(qV.class);
   @SerId(1)
   bK q;
   @SerId(2)
   int RF;
   @SerId(3)
   byte[] xK;
   @SerId(value = 4, version = 1)
   int Dw;
   @SerId(value = 5, version = 1)
   int Uv;
   @SerId(value = 6, version = 1)
   Endianness oW;
   @SerId(value = 7, version = 1)
   int gO;
   @SerId(value = 8, version = 1)
   int nf;
   @SerId(value = 9, version = 1)
   int gP;
   @SerId(value = 10, version = 1)
   byte[] za;
   @SerId(value = 11, version = 1)
   byte[] lm;
   @SerId(value = 12, version = 1)
   int zz;
   @SerId(value = 13, version = 1)
   int JY;
   @SerId(value = 14, version = 1)
   int HF;
   @SerId(value = 15, version = 1)
   int LK;
   @SerId(value = 16, version = 1)
   int io;
   @SerId(value = 17, version = 1)
   PY qa;
   @SerId(value = 18, version = 1)
   int Hk;
   @SerId(value = 19, version = 1)
   int Me;
   @SerId(value = 20, version = 1)
   int PV;
   @SerId(value = 21, version = 1)
   int oQ;
   @SerId(value = 22, version = 1)
   int xW;
   @SerId(value = 23, version = 1)
   int KT;
   @SerId(value = 24, version = 1)
   int Gf;
   @SerId(value = 25, version = 1)
   int Ef;
   @SerId(value = 26, version = 1)
   int cC;
   @SerId(value = 27, version = 1)
   int sH;
   @SerId(value = 28, version = 1)
   int CE;
   @SerId(value = 29, version = 1)
   int wF;
   @SerId(value = 30, version = 1)
   int If;
   @SerId(value = 31, version = 1)
   int Dz;
   @SerId(value = 32, version = 1)
   int mI;
   @SerId(value = 33, version = 1)
   int jq;
   @SerId(value = 34, version = 2)
   int ui;
   @SerId(value = 35, version = 2)
   int TX;
   @SerId(value = 36, version = 2)
   int Rr;
   @SerId(value = 37, version = 2)
   int EB;
   @SerId(value = 38, version = 2)
   int Xo;
   @SerTransient
   int Bu;
   @SerTransient
   bii IN;
   @SerTransient
   boolean rL;
   @SerTransient
   boolean eJ;
   @SerTransient
   boolean YN;
   @SerTransient
   boolean Rv;
   @SerTransient
   int zx;
   @SerTransient
   List ZT = new ArrayList();
   @SerTransient
   List Ri = new ArrayList();
   @SerId(39)
   List GY = new ArrayList();
   @SerId(40)
   List Wx = new ArrayList();
   @SerId(41)
   List AB = new ArrayList();
   @SerId(42)
   List CY = new ArrayList();
   @SerId(43)
   List WI = new ArrayList();
   @SerId(44)
   List Tq = new ArrayList();
   @SerId(45)
   List Yp = new ArrayList();
   @SerId(46)
   List Gu = new ArrayList();

   @SerCustomInitPostGraph
   private void Uv() {
      if (this.Xo == 0) {
         this.Xo = this.RF;
      }
   }

   public qV(bK var1, byte[] var2, int var3, int var4, int var5, int var6) {
      Assert.a(var1 != null && var2 != null);
      this.q = var1;
      this.xK = var2;
      Assert.a(var3 >= 0 && var3 < var2.length);
      this.Rr = var3;
      Assert.a(var5 >= 0);
      Assert.a(var4 >= var5);
      Assert.a(var6 >= 0);
      Assert.a(var4 > 0 || var5 == 0 && var6 == 0);
      this.Xo = var4;
      this.RF = var5;
      this.EB = var6;
   }

   String q() {
      String var1 = "#" + this.RF;
      if (this.EB >= 1) {
         var1 = var1 + "-" + this.EB;
      }

      return var1;
   }

   private void q(String var1, Object... var2) {
      if (!this.q.Ef()) {
         this.q.logWarn(true, var1, var2);
      } else {
         String var3 = Strings.ff(var1, var2);
         this.q.logWarn(true, "[Dex %s] %s", this.q(), var3);
      }
   }

   private void RF(String var1, Object... var2) {
      if (!this.q.Ef()) {
         this.q.logError(true, var1, var2);
      } else {
         String var3 = Strings.ff(var1, var2);
         this.q.logError(true, "[Dex %s] %s", this.q(), var3);
      }
   }

   public bK RF() {
      return this.q;
   }

   @Override
   public int getVersion() {
      return this.Dw;
   }

   @Override
   public int getHeaderSize() {
      return this.Uv;
   }

   @Override
   public Endianness getEndianness() {
      return this.oW;
   }

   @Override
   public int getExpectedSize() {
      return this.gO;
   }

   @Override
   public int getExpectedChecksum() {
      return this.gP;
   }

   @Override
   public int getActualChecksum() {
      return this.nf;
   }

   @Override
   public byte[] getExpectedSignature() {
      return this.lm;
   }

   @Override
   public byte[] getActualSignature() {
      return this.za;
   }

   @Override
   public int getLinkSectionSize() {
      return this.JY;
   }

   @Override
   public int getLinkSectionOffset() {
      return this.zz;
   }

   @Override
   public int getDataSectionSize() {
      return this.LK;
   }

   @Override
   public int getDataSectionOffset() {
      return this.HF;
   }

   @Override
   public int getMapOffset() {
      return this.io;
   }

   @Override
   public IDexMap getMap() {
      return this.qa;
   }

   @Override
   public int getStringsPoolSize() {
      return this.Hk;
   }

   @Override
   public int getStringsPoolOffset() {
      return this.Me;
   }

   @Override
   public int getTypesPoolSize() {
      return this.PV;
   }

   @Override
   public int getTypesPoolOffset() {
      return this.oQ;
   }

   @Override
   public int getPrototypesPoolSize() {
      return this.xW;
   }

   @Override
   public int getPrototypesPoolOffset() {
      return this.KT;
   }

   @Override
   public int getFieldsPoolSize() {
      return this.Gf;
   }

   @Override
   public int getFieldsPoolOffset() {
      return this.Ef;
   }

   @Override
   public int getMethodsPoolSize() {
      return this.cC;
   }

   @Override
   public int getMethodsPoolOffset() {
      return this.sH;
   }

   @Override
   public int getClassesPoolSize() {
      return this.CE;
   }

   @Override
   public int getClassesPoolOffset() {
      return this.wF;
   }

   @Override
   public int getCallSitesPoolSize() {
      return this.If;
   }

   @Override
   public int getCallSitesPoolOffset() {
      return this.Dz;
   }

   @Override
   public int getMethodHandlesPoolSize() {
      return this.mI;
   }

   @Override
   public int getMethodHandlesPoolOffset() {
      return this.jq;
   }

   static boolean q(byte[] var0) {
      if (var0.length < 112) {
         return false;
      } else if (var0[0] == 100 && var0[1] == 101 && var0[2] == 120) {
         int var1 = 0;
         if (Character.isDigit(var0[4]) && Character.isDigit(var0[5]) && Character.isDigit(var0[6]) && var0[7] == 0) {
            String var2 = Strings.decodeASCII(var0, 4, 3);
            var2 = Strings.ltrim(var2, '0');
            var1 = Conversion.stringToInt(var2);
         }

         return var1 >= 35 && var1 <= 39;
      } else {
         return false;
      }
   }

   void xK() {
      if (this.xK.length - this.Rr < 112) {
         throw new DexParsingException("Not enough bytes to process a Dex header: " + this.xK.length);
      } else if (this.xK[this.Rr] == 100 && this.xK[this.Rr + 1] == 101 && this.xK[this.Rr + 2] == 120) {
         if (this.xK[this.Rr + 3] != 10) {
            this.q(S.L("Modified Dex magic: %s"), Formatter.escapeBytes(this.xK, this.Rr, 4));
         }

         if (Character.isDigit(this.xK[this.Rr + 4])
            && Character.isDigit(this.xK[this.Rr + 5])
            && Character.isDigit(this.xK[this.Rr + 6])
            && this.xK[this.Rr + 7] == 0) {
            String var1 = Strings.decodeASCII(this.xK, this.Rr + 4, 3);
            var1 = Strings.ltrim(var1, '0');
            this.Dw = Conversion.stringToInt(var1);
         }

         if (this.Dw < 35 || this.Dw > 41) {
            this.rL = true;
            String var15 = S.L("Unknown DEX version") + ": " + this.Dw;
            this.q(var15);
            if (this.q.q) {
               throw new DexParsingException(var15);
            }
         }

         this.gO = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 32);
         if (this.gO < 0) {
            long var18 = this.gO & 4294967295L;
            throw new DexParsingException(Strings.ff("Dex size exceeds 2Gb: %s", SizeFormatter.formatByteSize(var18)));
         } else {
            if (this.Rr + this.gO > this.xK.length) {
               String var16 = Strings.ff(S.L("Dex size smaller than expected (%d bytes): missing %d bytes"), this.gO, this.Rr + this.gO - this.xK.length);
               this.q(var16);
               this.Rv = true;
            }

            this.gP = this.xK[this.Rr + 8] & 255
               | (this.xK[this.Rr + 9] & 255) << 8
               | (this.xK[this.Rr + 10] & 255) << 16
               | (this.xK[this.Rr + 11] & 255) << 24;
            Adler32 var17 = new Adler32();
            var17.update(this.xK, this.Rr + 12, this.gO - 12);
            this.nf = (int)var17.getValue();
            if (this.gP != this.nf) {
               this.eJ = true;
               String var2 = Strings.ff(S.L("Invalid DEX checksum (expected=%08X, actual=%08X)"), this.gP, this.nf);
               this.q(var2);
               if (this.q.RF) {
                  throw new DexParsingException(var2);
               }
            }

            this.lm = Arrays.copyOfRange(this.xK, this.Rr + 12, this.Rr + 32);

            MessageDigest var19;
            try {
               var19 = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException var12) {
               throw new JebRuntimeException("Cannot retrieve SHA-1 algorithm");
            }

            var19.update(this.xK, this.Rr + 32, this.gO - 32);
            this.za = var19.digest();
            if (!Arrays.equals(this.lm, this.za)) {
               this.YN = true;
               String var3 = Strings.ff(
                  S.L("Invalid DEX signature (expected=%s, actual=%s)"), Formatter.formatBinaryLine(this.lm), Formatter.formatBinaryLine(this.za)
               );
               nY.debug(var3);
               if (this.q.RF) {
                  throw new DexParsingException(var3);
               }
            }

            this.Uv = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 36);
            if (this.Uv < 112) {
               String var20 = Strings.ff(S.L("Invalid DEX header size (%Xh)"), this.Uv);
               this.q(var20);
            }

            int var21 = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 40);
            if (var21 == 305419896) {
               this.oW = Endianness.LITTLE_ENDIAN;
               this.JY = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 44);
               this.zz = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 48);
               this.io = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 52);
               this.Hk = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 56);
               this.Me = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 60);
               this.PV = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 64);
               this.oQ = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 68);
               this.xW = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 72);
               this.KT = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 76);
               this.Gf = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 80);
               this.Ef = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 84);
               this.cC = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 88);
               this.sH = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 92);
               this.CE = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 96);
               this.wF = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 100);
               this.LK = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 104);
               this.HF = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 108);
               if (this.Dw >= 41) {
                  this.TX = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 112);
                  this.ui = DexUtil.bytearrayULEInt32ToInt(this.xK, this.Rr + 116);
                  if (this.ui != this.Rr) {
                     String var4 = Strings.ff(S.L("Unexpected container offset (%Xh)"), this.ui);
                     this.q(var4);
                  }
               }

               try {
                  this.qa = PY.q(this.xK, this.io);
               } catch (RuntimeException var13) {
                  if (!this.YN) {
                     this.RF(S.L("Error parsing the Dex map"));
                     JebCoreService.notifySilentExceptionToClient(var13);
                  }

                  this.qa = new PY();
               }

               this.If = 0;
               this.Dz = 0;
               IDexMap.Entry var22 = this.qa.getEntry(7);
               if (var22 != null) {
                  this.If = var22.getCount();
                  this.Dz = var22.getOffset();
               }

               this.mI = 0;
               this.jq = 0;
               var22 = this.qa.getEntry(8);
               if (var22 != null) {
                  this.mI = var22.getCount();
                  this.jq = var22.getOffset();
               }

               if (this.Xo == 0) {
                  this.q.oW = new bkd(this.q, this.Hk);
                  this.q.gO = new bke(this.q, this.PV);
                  this.q.nf = new bkc(this.q, this.xW);
                  this.q.gP = new bjv(this.q, this.Gf);
                  this.q.za = new bka(this.q, this.cC);
                  this.q.HF = new bjj(this.q, this.If);
                  this.q.LK = new bjz(this.q, this.mI);
               }

               int var5 = this.q.oW.q();
               int var6 = this.q.gO.q();
               int var7 = this.q.nf.q();
               int var8 = this.q.gP.q();
               int var9 = this.q.za.q();
               int var10 = this.q.HF.q();
               int var11 = this.q.LK.q();
               this.q(this.Hk, this.Me, this.q.oW);
               this.q(this.PV, this.oQ, this.q.gO);
               this.q(this.xW, this.KT, this.q.nf);
               this.q(this.Gf, this.Ef, this.q.gP);
               this.q(this.cC, this.sH, this.q.za);
               this.q(this.mI, this.jq, this.q.LK);
               this.q(this.If, this.Dz, this.q.HF);
               Object[] var10000 = new Object[]{this.q()};
               this.q(this.q.oW, var5, this.Hk);
               this.q(this.q.gO, var6, this.PV);
               this.q(this.q.nf, var7, this.xW);
               this.q(this.q.gP, var8, this.Gf);
               this.q(this.q.za, var9, this.cC);
               this.q(this.q.HF, var10, this.If);
               this.q(this.q.LK, var11, this.mI);
            } else if (var21 != 2018915346) {
               throw new DexParsingException(Strings.ff("Illegal endianness tag: 0x%08X", var21));
            } else {
               this.oW = Endianness.BIG_ENDIAN;
               throw new DexParsingException("Big-endian Dex are not supported");
            }
         }
      } else {
         throw new DexParsingException("Illegal Dex magic: " + Formatter.escapeBytes(this.xK, this.Rr, 4));
      }
   }

   private void q(bkb var1, int var2, int var3) {
      int var4 = var1.q() - var2;
      Object[] var10000 = new Object[]{var1, var4, S.L("dupplicates"), var3 - var4};
   }

   private void q(int var1, int var2, bkd var3) {
      HashSet var4 = new HashSet();
      int[] var5 = new int[1];

      for (int var6 = 0; var6 < var1; var6++) {
         int var7 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2);
         int var8 = DexUtil.bytearrayULEB128ToInt(this.xK, var7, var5);
         int var10 = var7 + var5[0];

         String var9;
         try {
            var9 = DexUtil.bytearrayMUTF8ToStringFast(this.xK, var10, var5, var8);
            if (var8 != var9.length()) {
               throw new DexParsingException(Strings.ff("Length mismatch: expecting %d, got %d", var8, var9.length()));
            }
         } catch (DexParsingException var12) {
            var9 = Strings.ff("__invalid_string_%d_%X", this.Xo, var7);
            this.zx++;
         }

         bjs var11 = var3.q(var9, false, this.Xo >= 1);
         this.GY.add(var11.getIndex());
         if (!var4.add(var9)) {
            this.q(S.L("Duplicate string found: \"%s\""), Formatter.escapeString(var9));
         }

         var2 += 4;
      }
   }

   private void q(int var1, int var2, bke var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 4 * var4);
         var5 = this.q(var5);
         bjt var6 = var3.Uv(this.q.RF(var5));
         this.Wx.add(var6.getIndex());
      }
   }

   private void q(int var1, int var2, bkc var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 12 * var4);
         var5 = this.q(var5);
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 12 * var4 + 4);
         var6 = this.RF(var6);
         int[] var7 = null;
         int var8 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 12 * var4 + 8);
         if (var8 > 0) {
            int var9 = DexUtil.bytearrayULEInt32ToInt(this.xK, var8);
            var7 = new int[var9];

            for (int var10 = 0; var10 < var9; var10++) {
               int var11 = DexUtil.bytearrayULEInt16ToInt(this.xK, var8 + 4 + 2 * var10);
               var7[var10] = this.RF(var11);
            }
         }

         bjr var14 = var3.q(var5, var6, var7);
         this.AB.add(var14.getIndex());
      }
   }

   private void q(int var1, int var2, bjv var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var4);
         var5 = this.RF(var5);
         int var6 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var4 + 2);
         var6 = this.RF(var6);
         int var7 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 8 * var4 + 4);
         var7 = this.q(var7);
         bjo var8 = var3.q(var5, var6, var7);
         this.CY.add(var8.getIndex());
      }
   }

   private void q(int var1, int var2, bka var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var4);
         int var6 = this.RF(var5);
         int var7 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var4 + 2);
         int var8 = this.xK(var7);
         int var9 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 8 * var4 + 4);
         int var10 = this.q(var9);
         bjp var11 = var3.q(var6, var8, var10);
         this.WI.add(var11.getIndex());
      }
   }

   private void q(int var1, int var2, bjj var3) {
      int[] var4 = new int[1];

      for (int var5 = 0; var5 < var1; var5++) {
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 4 * var5);
         bia[] var7 = bia.RF(this, this.xK, var6, var4);
         if (var7.length < 3) {
            throw new DexParsingException("A callsite item value array must contain at least 3 elements");
         }

         bjm var8 = var3.q(var7);
         this.Tq.add(var8.getIndex());
      }
   }

   private void q(int var1, int var2, bjz var3) {
      for (int var4 = 0; var4 < var1; var4++) {
         int var5 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var4);
         DexMethodHandleType var6 = DexMethodHandleType.get(var5);
         int var7 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var4 + 4);
         int var8 = -1;
         if (var6.isFieldAccessor()) {
            var8 = this.Dw(var7);
         } else if (var6.isMethodInvoker()) {
            var8 = this.Uv(var7);
         } else {
            this.q(S.L("Illegal method handle type: %d"), var5);
            JebCoreService.notifySilentExceptionToClient(new DexParsingException("Illegal method handle type: " + var5));
         }

         bjq var9 = var3.q(var5, var8);
         this.Yp.add(var9.getIndex());
      }
   }

   int Dw() {
      return this.q(this.CE, this.wF);
   }

   private int q(int var1, int var2) {
      int var3 = 0;
      int[] var4 = new int[1];
      int var5 = 0;

      while (var5 < var1) {
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 24);
         if (var6 > 0) {
            DexUtil.bytearrayULEB128ToInt(this.xK, var6, var4);
            var6 += var4[0];
            DexUtil.bytearrayULEB128ToInt(this.xK, var6, var4);
            var6 += var4[0];
            var3 += DexUtil.bytearrayULEB128ToInt(this.xK, var6, var4);
            var6 += var4[0];
            var3 += DexUtil.bytearrayULEB128ToInt(this.xK, var6, var4);
            int var10000 = var4[0];
         }

         var5++;
         var2 += 32;
      }

      return var3;
   }

   void q(bii var1) {
      this.IN = var1;

      try {
         if (this.Xo == 0) {
            this.q.lm = new bjl(this.q, this.CE);
         }

         this.q(this.CE, this.wF, this.q.lm);
      } finally {
         this.IN = null;
      }
   }

   private void q(int var1, int var2, bjl var3) {
      int var4 = 0;

      while (var4 < var1) {
         int var5 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2);
         var5 = this.RF(var5);
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 4);
         int var7 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 8);
         var7 = this.RF(var7);
         int[] var8 = null;
         int var9 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 12);
         if (var9 > 0) {
            int var10 = DexUtil.bytearrayULEInt32ToInt(this.xK, var9);
            var8 = new int[var10];

            for (int var11 = 0; var11 < var10; var11++) {
               int var12 = DexUtil.bytearrayULEInt16ToInt(this.xK, var9 + 4 + 2 * var11);
               var8[var11] = this.RF(var12);
            }
         }

         int var30 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 16);
         var30 = this.q(var30);
         bhw var32 = null;
         int var33 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 20);
         if (var33 > 0) {
            var32 = bhw.q(this.q, this, this.xK, var33);
         }

         bjk var13 = null;
         int var14 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 24);
         if (var14 > 0) {
            var13 = this.q(this.xK, var14);

            for (bju var16 : var13.q()) {
               int var17 = this.q.oW(var16.getFieldIndex()).getClassTypeIndex();
               if (var17 != var5) {
                  nY.warn(
                     S.L("Unexpected field definition: declared type %s does not match the containing type %s"),
                     this.q.Dw(var17).getSignature(false),
                     this.q.Dw(var5).getSignature(false)
                  );
               }
            }

            for (bjy var36 : var13.xK()) {
               int var38 = this.q.gO(var36.getMethodIndex()).getClassTypeIndex();
               if (var38 != var5) {
                  nY.warn(
                     S.L("Unexpected method definition: declared type %s does not match the containing type %s"),
                     this.q.Dw(var38).getSignature(false),
                     this.q.Dw(var5).getSignature(false)
                  );
               }
            }
         }

         bia[] var35 = null;
         int var37 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 28);
         if (var37 > 0) {
            int[] var39 = new int[1];
            var35 = bia.RF(this, this.xK, var37, var39);
            if (var13 != null) {
               List var18 = var13.getStaticFields();
               int var19 = 0;

               for (bia var23 : var35) {
                  if (var19 >= var18.size()) {
                     break;
                  }

                  int var24 = var23.getType();
                  bju var25 = (bju)var18.get(var19);
                  int var26 = var25.getFieldIndex();
                  if (var25.isFinal()) {
                     if (var24 == 0) {
                        this.q.Me.setValue(var23.getByte(), var26);
                     } else if (var24 == 3) {
                        this.q.Me.setValue(var23.getChar(), var26);
                     } else if (var24 == 2) {
                        this.q.Me.setValue(var23.getShort(), var26);
                     } else if (var24 == 4) {
                        this.q.Me.setValue(var23.getInt(), var26);
                     } else if (var24 == 6) {
                        this.q.Me.setValue(var23.getLong(), var26);
                     } else if (var24 == 16) {
                        this.q.Me.setValue(var23.getFloat(), var26);
                     } else if (var24 == 17) {
                        this.q.Me.setValue(var23.getDouble(), var26);
                     }
                  }

                  if (var24 == 23) {
                     this.q.JY.q(var23.getStringIndex(), var26, iZ.RF);
                  }

                  var19++;
               }
            }
         }

         if (var32 != null) {
            try {
               this.q.JY.q(var5, var32);
            } catch (Exception var27) {
               JebCoreService.silentExcept(var27);
            }
         }

         if (this.q.xK) {
            DexUtil.validateClassFlags(var6);
         }

         bjn var40 = var3.q(var5, var6, var7, var8, var30, var32, var13, var35, this.Xo);
         this.Gu.add(var40.getIndex());
         var4++;
         var2 += 32;
      }
   }

   private bjk q(byte[] var1, int var2) throws DexParsingException {
      int[] var3 = new int[1];
      int var4 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      int var5 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      int var6 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      int var7 = DexUtil.bytearrayULEB128ToInt(var1, var2, var3);
      var2 += var3[0];
      bju[] var8 = this.q(var1, var4, var2, var3);
      var2 += var3[0];
      bju[] var9 = this.q(var1, var5, var2, var3);
      var2 += var3[0];
      bjy[] var10 = this.RF(var1, var6, var2, var3);
      var2 += var3[0];
      bjy[] var11 = this.RF(var1, var7, var2, var3);
      int var10000 = var3[0];
      return new bjk(var8, var9, var10, var11);
   }

   private bju[] q(byte[] var1, int var2, int var3, int[] var4) {
      bju[] var5 = new bju[var2];
      int[] var6 = new int[1];
      int var7 = 0;
      int var8 = var3;

      for (int var9 = 0; var9 < var2; var9++) {
         int var10 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         var7 += var10;
         int var11 = this.Dw(var7);
         int var12 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         if (this.q.xK) {
            DexUtil.validateFieldFlags(var12);
         }

         bju var13 = new bju(var11, var12, this.Xo);
         var5[var9] = var13;
         this.q.gP.q(var11, var13);
      }

      var4[0] = var8 - var3;
      return var5;
   }

   private bjy[] RF(byte[] var1, int var2, int var3, int[] var4) {
      bjy[] var5 = new bjy[var2];
      int[] var6 = new int[1];
      int var7 = 0;
      int var8 = var3;

      for (int var9 = 0; var9 < var2; var9++) {
         int var10 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         var7 += var10;
         int var11 = this.Uv(var7);
         int var12 = ++this.q.Dz;
         int var13 = this.q.mI;
         if (this.q.If != null && var13 > 0 && var12 <= var13) {
            String var14 = S.L("Parsing dex code");
            if (var12 < var13 && this.q.If.canSend()) {
               double var15 = var12 * 100.0 / var13;
               this.q.If.log("%s ... %.1f%%", var14, var15);
            } else if (var12 == var13) {
               this.q.If.forceLog("%s ... %s", var14, S.L("completed"));
            }
         }

         int var20 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         int var21 = DexUtil.bytearrayULEB128ToInt(var1, var8, var6);
         var8 += var6[0];
         bip var16 = null;
         if (var21 > 0) {
            var16 = this.RF(var21, var11);
         }

         if (this.q.xK) {
            DexUtil.validateMethodFlags(var20);
         }

         bjy var17 = new bjy(var11, var20, var16, this.Xo);
         var5[var9] = var17;
         this.q.za.q(var11, var17);
      }

      var4[0] = var8 - var3;
      return var5;
   }

   private bip RF(int var1, int var2) {
      int var3 = DexUtil.bytearrayULEInt16ToInt(this.xK, var1);
      int var4 = DexUtil.bytearrayULEInt16ToInt(this.xK, var1 + 2);
      int var5 = DexUtil.bytearrayULEInt16ToInt(this.xK, var1 + 4);
      int var6 = DexUtil.bytearrayULEInt16ToInt(this.xK, var1 + 6);
      int var7 = 0;

      try {
         var7 = DexUtil.bytearrayULEInt32ToInt(this.xK, var1 + 8);
      } catch (DexParsingException var21) {
         this.RF(S.L("0x%X: Bad offset to debug item object"), var1 + 8);
      }

      int var8 = DexUtil.bytearrayULEInt32ToInt(this.xK, var1 + 12);
      int var9 = var1 + 16;
      int var10 = var9 + 2 * var8;
      biw[] var11 = null;
      if (var6 > 0) {
         int var12 = var10 + (var6 > 0 && var8 % 2 != 0 ? 2 : 0);
         var11 = this.xK(var6, var12);
      }

      CFG var23 = null;
      Map var13 = null;
      bim var16 = null;

      Object var14;
      Object var15;
      try {
         bii.eo var17 = this.IN.q(this.xK, var9, 2 * var8, var3, var11, var2, this, this.Dw);
         var23 = var17.q;
         var13 = var17.RF;
         var14 = var17.q.getInstructions();
         var15 = var17.xK;
         if (var15 != null && !var15.isEmpty()) {
            if (this.Ri != null) {
               this.Ri.add(var2);
            }

            for (DalvikParserError var19 : var15) {
               if (var19.getErrorType().isHardFail()) {
                  if (this.ZT != null) {
                     this.ZT.add(var2);
                  }
                  break;
               }
            }
         }
      } catch (Exception var22) {
         if (this.ZT != null) {
            this.ZT.add(var2);
         }

         nY.catching(var22);
         var14 = new ArrayList();
         var15 = new ArrayList();
         var15.add(new DalvikParserError(DalvikParserErrorType.UNKNOWN_ERROR));
         this.q
            .addNotification(
               new UnitNotification(NotificationType.CORRUPTION, Strings.ff(S.L("Failed to parse some bytecode (dex:%s, method_index:%d)"), this.q(), var2))
            );
      }

      if (var7 > 0) {
         try {
            var16 = new bim(this, this.xK, var7, var8);
         } catch (Exception var20) {
            var16 = null;
            this.q
               .addNotification(
                  new UnitNotification(
                     NotificationType.CORRUPTION, String.format(S.L("Failed to parse the debug info (dex:%s, method_index:%d)"), this.q(), var2)
                  )
               );
         }
      }

      bip var24 = new bip(var3, var4, var5, var16, var9, 2 * var8, (List)var15, (List)var14, var23, var13, var11, this.Xo);
      var24.q(var2);
      return var24;
   }

   private biw[] xK(int var1, int var2) {
      biw[] var3 = new biw[var1];
      int var4 = var2 + 8 * var1;

      for (int var5 = 0; var5 < var1; var5++) {
         int var6 = DexUtil.bytearrayULEInt32ToInt(this.xK, var2 + 8 * var5);
         int var7 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var5 + 4);
         int var8 = DexUtil.bytearrayULEInt16ToInt(this.xK, var2 + 8 * var5 + 6);
         int var9 = var4 + var8;
         int[] var10 = new int[1];
         int var11 = DexUtil.bytearraySLEB128ToInt(this.xK, var9, var10);
         var9 += var10[0];
         int var12 = var11 >= 0 ? var11 : -var11;
         ArrayList var13 = new ArrayList(var12 + 1);

         for (int var14 = 0; var14 < var12; var14++) {
            int var15 = DexUtil.bytearrayULEB128ToInt(this.xK, var9, var10);
            var15 = this.RF(var15);
            var9 += var10[0];
            int var16 = DexUtil.bytearrayULEB128ToInt(this.xK, var9, var10);
            var9 += var10[0];
            var13.add(new biv(var15, 2 * var16));
         }

         if (var11 <= 0) {
            int var19 = DexUtil.bytearrayULEB128ToInt(this.xK, var9, var10);
            var13.add(new biv(-1, 2 * var19));
            int var10000 = var10[0];
         }

         biw var20 = new biw(2 * var6, 2 * var7, var13);
         var3[var5] = var20;
      }

      return var3;
   }

   @Override
   public byte[] getData() {
      return this.xK;
   }

   @Override
   public int getOffset() {
      return this.Rr;
   }

   @Override
   public int getFileIndex() {
      return this.RF;
   }

   @Override
   public int getIndexInContainer() {
      return this.EB;
   }

   @Override
   public int q(int var1) {
      if (var1 != -1 && this.GY != null) {
         int var2 = (Integer)this.GY.get(var1);
         bjs var3 = (bjs)this.q.oW.q(var2);
         if (var3 != null) {
            var3.q();
         }

         return var2;
      } else {
         return var1;
      }
   }

   @Override
   public int RF(int var1) {
      return var1 != -1 && this.Wx != null ? (Integer)this.Wx.get(var1) : var1;
   }

   @Override
   public int xK(int var1) {
      return var1 != -1 && this.AB != null ? (Integer)this.AB.get(var1) : var1;
   }

   @Override
   public int Dw(int var1) {
      return var1 != -1 && this.CY != null ? (Integer)this.CY.get(var1) : var1;
   }

   @Override
   public int Uv(int var1) {
      return var1 != -1 && this.WI != null ? (Integer)this.WI.get(var1) : var1;
   }

   @Override
   public int oW(int var1) {
      return var1 != -1 && this.Tq != null ? (Integer)this.Tq.get(var1) : var1;
   }

   @Override
   public int gO(int var1) {
      return var1 != -1 && this.Yp != null ? (Integer)this.Yp.get(var1) : var1;
   }
}
