package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.ByteArray;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public abstract class bex {
   protected static final ILogger q = GlobalLog.getLogger(bex.class);
   public static final int RF = -589498891;
   public String xK;
   public int Dw;
   public bex Uv;
   public ByteArray oW;
   public ByteArray gO;
   public int nf;
   public beh gP;
   public long za;
   public long lm;
   public byte[] zz;
   public Set JY;
   public boolean HF;
   public boolean LK;
   public boolean io;
   public boolean qa;
   public boolean Hk;
   public boolean Me;
   public boolean PV;
   public boolean oQ;
   public boolean xW;
   public boolean KT;
   public boolean Gf;
   public boolean Ef;
   public boolean cC;
   public boolean sH;
   public boolean CE;
   public boolean wF;
   public long If;
   public long Dz;
   public List mI = new ArrayList();
   public int jq;
   public Map ui = new TreeMap();
   public Map TX = new TreeMap();
   public Set Rr = new LinkedHashSet();
   protected int EB;
   protected int Xo;
   protected long Bu;
   protected List IN = new ArrayList();

   public bex(int var1, byte[] var2, int var3, byte[] var4, int var5, bex var6) {
      this.Dw = var1;
      this.Uv = var6;
      this.oW = new ByteArray(Arrays.copyOfRange(var2, var3, var2.length));
      Assert.a(this.oW.available() >= 52);
      this.nf = var5;
      int var7 = this.oW.i32();
      Assert.a(var7 == -589498891);
      long var8 = this.oW.i64();
      Assert.a(var8 <= 2147483647L);
      this.oW.maxPosition(4 + (int)var8);
      this.za = var8;
      this.lm = this.oW.i64();
      this.zz = Formatter.hexStringToByteArray(Strings.decodeASCII(this.oW.get(32)));
      StringBuilder var10 = new StringBuilder();

      char var11;
      while ((var11 = (char)this.oW.get()) != 0) {
         var10.append(var11);
      }

      this.JY = new LinkedHashSet(Arrays.asList(var10.toString().split(" ")));
      Object[] var10000 = new Object[]{this.lm, this.za, Formatter.byteArrayToHex(this.zz), this.JY};
      this.gP = new beh(var1, this.JY);
      Assert.a(this.RF());
      this.HF = false;
      this.LK = false;
      this.io = true;
      this.qa = this.JY.contains("debug");
      this.Hk = this.JY.contains("release");
      this.Me = this.JY.contains("product");
      this.PV = this.io && this.Me;
      this.oQ = this.JY.contains("use_bare_instructions");
      this.xW = this.JY.contains("compressed-pointers");
      this.KT = this.LK || this.io;
      this.Gf = this.JY.contains("code-comments");
      this.Ef = this.gP.eJ;
      this.cC = this.gP.YN;
      this.sH = this.gP.Rv;
      this.CE = this.gP.zx;
      this.wF = this.cC || this.CE;
      long var12 = this.q(4L + this.za, this.gP.ZT);
      int var14 = (int)(var3 + var12);
      byte[] var15 = Arrays.copyOfRange(var2, var14, var2.length);
      this.gO = new ByteArray(var15);
      var10000 = new Object[]{var14};
   }

   protected final void q() {
   }

   protected abstract boolean RF();

   public int xK() {
      bex var1 = this;

      while (var1.Uv != null) {
         var1 = var1.Uv;
      }

      return (int)this.If;
   }

   public bew q(int var1) {
      return new bew(this, var1);
   }

   protected final bew q(int var1, String var2, Object var3) {
      bew var4 = this.q(var1);
      var4.xK = true;
      var4.q("name", var2);
      var4.q("data", var3);
      return var4;
   }

   protected final bew q(int var1, String var2) {
      return this.q(var1, var2, null);
   }

   public bew RF(int var1) {
      return var1 >= 0 && var1 < this.mI.size() ? (bew)this.mI.get(var1) : null;
   }

   public bew xK(int var1) {
      return (bew)this.mI.get(var1);
   }

   public void q(int var1, bew var2) {
      this.mI.set(var1, var2);
   }

   public void q(bew var1) {
      this.mI.add(var1);
      this.jq++;
      Assert.a(this.mI.size() == this.jq);
   }

   public void q(long var1, int var3) {
      for (long var4 = 0L; var4 < var1; var4++) {
         this.q(this.q(var3));
      }
   }

   public byte[] Dw(int var1) {
      return this.oW.get(var1);
   }

   public boolean Dw() {
      int var1 = this.oW.i8();
      if (var1 == 0) {
         return false;
      } else if (var1 == 1) {
         return true;
      } else {
         throw new RuntimeException("Illegal boolean value: " + var1);
      }
   }

   public long Uv() {
      long var1 = this.nf();
      this.q(var1);
      return var1;
   }

   protected final void q(long var1) {
      Assert.a(var1 >= 1L && var1 <= this.Dz, "Bad ref: " + var1 + " (max: " + this.Dz);
   }

   public int oW() {
      return this.lm();
   }

   protected final void RF(long var1) {
   }

   public int gO() {
      return this.lm();
   }

   public long nf() {
      return q(this.oW, true);
   }

   public int gP() {
      long var1 = q(this.oW, true);
      Assert.a(var1 >= 0L && var1 <= 4294967295L);
      return (int)var1;
   }

   public long za() {
      return q(this.oW, false);
   }

   public int lm() {
      long var1 = q(this.oW, false);
      Assert.a(var1 >= -2147483648L && var1 <= 2147483647L);
      return (int)var1;
   }

   public int zz() {
      return this.oW.get() & 0xFF;
   }

   public int JY() {
      return this.oW.get();
   }

   public int HF() {
      return this.gP();
   }

   public int LK() {
      return this.lm();
   }

   public int io() {
      return this.gP();
   }

   public int qa() {
      return this.lm();
   }

   public long Hk() {
      return this.za();
   }

   public float Me() {
      return Float.intBitsToFloat(this.lm());
   }

   public double PV() {
      return Double.longBitsToDouble(this.za());
   }

   public long oQ() {
      this.lm();
      if (this.wF) {
         this.lm();
      }

      return 0L;
   }

   public static long q(ByteArray var0, boolean var1) {
      return RF(var0, var1);
   }

   public static long RF(ByteArray var0, boolean var1) {
      long var2 = 0L;
      byte var4 = 0;
      byte var5 = 0;

      for (int var6 = 0; var4 >= 0; var6++) {
         if (var6 == 10) {
            throw new RuntimeException();
         }

         var4 = var0.get();
         if (var4 < 0 && !var1) {
            var2 |= (long)((var4 & 255) - 192) << var5;
         } else {
            var2 |= (long)(var4 & 127) << var5;
         }

         var5 += 7;
      }

      return var2;
   }

   public long q(long var1, long var3) {
      return (var1 + var3 - 1L) / var3 * var3;
   }

   public int xW() {
      return this.oW.position();
   }

   public boolean Uv(int var1) {
      return (var1 >> 1 & 1) == 1;
   }

   public int oW(int var1) {
      return var1 & 127;
   }

   public int gO(int var1) {
      return var1 >> 7 & 1;
   }

   public boolean RF(long var1, int var3) {
      return var3 >= 0 && var3 < 64 ? (var1 >> var3 & 1L) != 0L : false;
   }

   public abstract String nf(int var1);

   public abstract Map KT();

   public boolean gP(int var1) {
      return this.EB <= var1 && var1 < this.Xo;
   }

   public boolean za(int var1) {
      return this.gP(var1) && (var1 - this.EB) % this.gP.lm == 0L;
   }

   public boolean lm(int var1) {
      return this.gP(var1) && (var1 - this.EB) % this.gP.lm == 2L;
   }

   public boolean zz(int var1) {
      return this.gP(var1) && (var1 - this.EB) % this.gP.lm == 1L;
   }

   public boolean JY(int var1) {
      return this.gP(var1) && (var1 - this.EB) % this.gP.lm == 3L;
   }

   public int HF(int var1) {
      return io(this.LK(var1));
   }

   private int LK(int var1) {
      if (var1 == this.Xo) {
         return 1;
      } else if (0L < this.gP.lm && this.za(var1)) {
         return (int)(var1 - this.EB - 0L) / this.gP.lm;
      } else if (1L < this.gP.lm && this.zz(var1)) {
         return (int)(var1 - this.EB - 1L) / this.gP.lm;
      } else if (2L < this.gP.lm && this.lm(var1)) {
         return (int)(var1 - this.EB - 2L) / this.gP.lm;
      } else if (3L < this.gP.lm && this.JY(var1)) {
         return (int)(var1 - this.EB - 3L) / this.gP.lm;
      } else {
         throw new RuntimeException();
      }
   }

   private static int io(int var0) {
      return new int[]{1, 1, 1, 2, 2, 4, 4, 8, 8, 4, 8, 16, 16, 16}[var0];
   }

   protected void Gf() {
      this.Bu = this.Uv();
      if (this.KT) {
         for (int var1 = 0; var1 < this.gP.Hk; var1++) {
            this.IN.add(this.Uv());
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("size=0x%X, version_hash=%s, object_count=%d, features=%s", this.za, Formatter.byteArrayToHex(this.zz), this.Dz, this.JY);
   }

   public String Ef() {
      StringBuilder var1 = new StringBuilder("Dart AOT Snapshot");
      Strings.ff(var1, "- Data Size: %d bytes\n", this.za);
      Strings.ff(var1, "- Snapshot Version Hash: %s\n", Formatter.byteArrayToHex(this.zz));
      Strings.ff(var1, "- Matching Dart Version: %s\n", this.xK);
      Strings.ff(var1, "- Object Count: %s\n", this.Dz);
      Strings.ff(var1, "- Features: %s", Strings.join(", ", this.JY));
      return var1.toString();
   }
}
