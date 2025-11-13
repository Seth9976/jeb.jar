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

public abstract class bbd {
   protected static final ILogger pC = GlobalLog.getLogger(bbd.class);
   public String A;
   public int kS;
   public bbd wS;
   public ByteArray UT;
   public ByteArray E;
   public int sY;
   public bao ys;
   public long ld;
   public long gp;
   public byte[] oT;
   public Set fI;
   public boolean WR;
   public boolean NS;
   public boolean vP;
   public boolean xC;
   public boolean ED;
   public boolean Sc;
   public boolean ah;
   public boolean eP;
   public boolean UO;
   public boolean Ab;
   public boolean rl;
   public boolean z;
   public boolean Ek;
   public boolean hK;
   public boolean Er;
   public boolean FE;
   public long Aj;
   public long EX;
   public List LM = new ArrayList();
   public int mv;
   public Map sO = new TreeMap();
   public Map os = new TreeMap();
   public Set Cu = new LinkedHashSet();
   protected int hZ;
   protected int UW;
   protected long PR;
   protected List cX = new ArrayList();

   public bbd(int var1, byte[] var2, int var3, byte[] var4, int var5, bbd var6) {
      this.kS = var1;
      this.wS = var6;
      this.UT = new ByteArray(Arrays.copyOfRange(var2, var3, var2.length));
      Assert.a(this.UT.available() >= 52);
      this.sY = var5;
      int var7 = this.UT.i32();
      Assert.a(var7 == -589498891);
      long var8 = this.UT.i64();
      Assert.a(var8 <= 2147483647L);
      this.UT.maxPosition(4 + (int)var8);
      this.ld = var8;
      this.gp = this.UT.i64();
      this.oT = Formatter.hexStringToByteArray(Strings.decodeASCII(this.UT.get(32)));
      StringBuilder var10 = new StringBuilder();

      char var11;
      while ((var11 = (char)this.UT.get()) != 0) {
         var10.append(var11);
      }

      this.fI = new LinkedHashSet(Arrays.asList(var10.toString().split(" ")));
      Object[] var10000 = new Object[]{this.gp, this.ld, Formatter.byteArrayToHex(this.oT), this.fI};
      this.ys = new bao(var1, this.fI);
      Assert.a(this.pC());
      this.WR = false;
      this.NS = false;
      this.vP = true;
      this.xC = this.fI.contains("debug");
      this.ED = this.fI.contains("release");
      this.Sc = this.fI.contains("product");
      this.ah = this.vP && this.Sc;
      this.eP = this.fI.contains("use_bare_instructions");
      this.UO = this.fI.contains("compressed-pointers");
      this.Ab = this.NS || this.vP;
      this.rl = this.fI.contains("code-comments");
      this.z = this.ys.OB;
      this.Ek = this.ys.pF;
      this.hK = this.ys.Bc;
      this.Er = this.ys.OI;
      this.FE = this.Ek || this.Er;
      long var12 = this.pC(4L + this.ld, this.ys.Bf);
      int var14 = (int)(var3 + var12);
      byte[] var15 = Arrays.copyOfRange(var2, var14, var2.length);
      this.E = new ByteArray(var15);
      var10000 = new Object[]{var14};
   }

   protected abstract boolean pC();

   public bbc pC(int var1) {
      return new bbc(this, var1);
   }

   protected final bbc pC(int var1, String var2, Object var3) {
      bbc var4 = this.pC(var1);
      var4.kS = true;
      var4.pC("name", var2);
      var4.pC("data", var3);
      return var4;
   }

   protected final bbc pC(int var1, String var2) {
      return this.pC(var1, var2, null);
   }

   public bbc A(int var1) {
      return var1 >= 0 && var1 < this.LM.size() ? (bbc)this.LM.get(var1) : null;
   }

   public bbc kS(int var1) {
      return (bbc)this.LM.get(var1);
   }

   public void pC(bbc var1) {
      this.LM.add(var1);
      this.mv++;
      Assert.a(this.LM.size() == this.mv);
   }

   public void pC(long var1, int var3) {
      for (long var4 = 0L; var4 < var1; var4++) {
         this.pC(this.pC(var3));
      }
   }

   public byte[] wS(int var1) {
      return this.UT.get(var1);
   }

   public boolean A() {
      int var1 = this.UT.i8();
      if (var1 == 0) {
         return false;
      } else if (var1 == 1) {
         return true;
      } else {
         throw new RuntimeException("Illegal boolean value: " + var1);
      }
   }

   public long kS() {
      long var1 = this.E();
      this.pC(var1);
      return var1;
   }

   protected final void pC(long var1) {
      Assert.a(var1 >= 1L && var1 <= this.EX, "Bad ref: " + var1 + " (max: " + this.EX);
   }

   public int wS() {
      return this.ld();
   }

   public int UT() {
      return this.ld();
   }

   public long E() {
      return pC(this.UT, true);
   }

   public int sY() {
      long var1 = pC(this.UT, true);
      Assert.a(var1 >= 0L && var1 <= 4294967295L);
      return (int)var1;
   }

   public long ys() {
      return pC(this.UT, false);
   }

   public int ld() {
      long var1 = pC(this.UT, false);
      Assert.a(var1 >= -2147483648L && var1 <= 2147483647L);
      return (int)var1;
   }

   public int gp() {
      return this.UT.get() & 0xFF;
   }

   public int oT() {
      return this.UT.get();
   }

   public int fI() {
      return this.sY();
   }

   public int WR() {
      return this.ld();
   }

   public int NS() {
      return this.sY();
   }

   public int vP() {
      return this.ld();
   }

   public long xC() {
      return this.ys();
   }

   public double ED() {
      return Double.longBitsToDouble(this.ys());
   }

   public long Sc() {
      this.ld();
      if (this.FE) {
         this.ld();
      }

      return 0L;
   }

   public static long pC(ByteArray var0, boolean var1) {
      return A(var0, var1);
   }

   public static long A(ByteArray var0, boolean var1) {
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

   public long pC(long var1, long var3) {
      return (var1 + var3 - 1L) / var3 * var3;
   }

   public boolean UT(int var1) {
      return (var1 >> 1 & 1) == 1;
   }

   public int E(int var1) {
      return var1 & 127;
   }

   public boolean A(long var1, int var3) {
      return var3 >= 0 && var3 < 64 ? (var1 >> var3 & 1L) != 0L : false;
   }

   public abstract String sY(int var1);

   public abstract Map ah();

   public boolean ys(int var1) {
      return this.hZ <= var1 && var1 < this.UW;
   }

   public boolean ld(int var1) {
      return this.ys(var1) && (var1 - this.hZ) % this.ys.oT == 0L;
   }

   public boolean gp(int var1) {
      return this.ys(var1) && (var1 - this.hZ) % this.ys.oT == 2L;
   }

   public boolean oT(int var1) {
      return this.ys(var1) && (var1 - this.hZ) % this.ys.oT == 1L;
   }

   public boolean fI(int var1) {
      return this.ys(var1) && (var1 - this.hZ) % this.ys.oT == 3L;
   }

   public int WR(int var1) {
      return vP(this.NS(var1));
   }

   private int NS(int var1) {
      if (var1 == this.UW) {
         return 1;
      } else if (0L < this.ys.oT && this.ld(var1)) {
         return (int)(var1 - this.hZ - 0L) / this.ys.oT;
      } else if (1L < this.ys.oT && this.oT(var1)) {
         return (int)(var1 - this.hZ - 1L) / this.ys.oT;
      } else if (2L < this.ys.oT && this.gp(var1)) {
         return (int)(var1 - this.hZ - 2L) / this.ys.oT;
      } else if (3L < this.ys.oT && this.fI(var1)) {
         return (int)(var1 - this.hZ - 3L) / this.ys.oT;
      } else {
         throw new RuntimeException();
      }
   }

   private static int vP(int var0) {
      return new int[]{1, 1, 1, 2, 2, 4, 4, 8, 8, 4, 8, 16, 16, 16}[var0];
   }

   protected void eP() {
      this.PR = this.kS();
      if (this.Ab) {
         for (int var1 = 0; var1 < this.ys.Sc; var1++) {
            this.cX.add(this.kS());
         }
      }
   }

   @Override
   public String toString() {
      return Strings.ff("size=0x%X, version_hash=%s, object_count=%d, features=%s", this.ld, Formatter.byteArrayToHex(this.oT), this.EX, this.fI);
   }

   public String UO() {
      StringBuilder var1 = new StringBuilder("Dart AOT Snapshot");
      Strings.ff(var1, "- Data Size: %d bytes\n", this.ld);
      Strings.ff(var1, "- Snapshot Version Hash: %s\n", Formatter.byteArrayToHex(this.oT));
      Strings.ff(var1, "- Matching Dart Version: %s\n", this.A);
      Strings.ff(var1, "- Object Count: %s\n", this.EX);
      Strings.ff(var1, "- Features: %s", Strings.join(", ", this.fI));
      return var1.toString();
   }
}
