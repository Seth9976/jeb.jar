package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UC implements tz {
   private static final Hu[] A = new Hu[0];
   public static final UC pC = null;
   private String kS;
   private ji wS = ji.Ab;
   private dW UT = hg.pC;
   private UC.Av E = null;
   private Hu[] sY;
   private List ys;
   private String ld = null;
   private List gp = null;
   private List oT = null;

   public UC(String var1, Hu... var2) {
      this(var1, false, var2);
   }

   public UC(int var1, String var2, Hu... var3) {
      this(var2, false, var3);
   }

   public UC(String var1, dW var2, Hu... var3) {
      this(var1, false, var3);
      this.pC(var2);
   }

   public UC(int var1, String var2, ji var3, Hu... var4) {
      this(var2, false, var4);
      this.pC(var3);
   }

   protected UC(String var1, boolean var2, Hu... var3) {
      this.kS = var1;
      this.sY = var3;
      if (var3 == null) {
         this.sY = A;
      }
   }

   public UC pC(UC.Av var1) {
      this.E = var1;
      return this;
   }

   public UC pC() {
      return this.pC(UC.Av.pC);
   }

   public UC pC(ji var1) {
      this.wS = var1 == null ? ji.Ab : var1;
      return this;
   }

   public UC pC(dW var1) {
      this.UT = (dW)(var1 == null ? hg.pC : var1);
      return this;
   }

   public UC A() {
      return this.pC(cT.A);
   }

   public UC pC(String var1) {
      this.ld = var1;
      return this;
   }

   public UC pC(ZW var1) {
      if (this.ys == null) {
         this.ys = new ArrayList();
      }

      this.ys.add(var1);
      return this;
   }

   public UC pC(ZW var1, ZW var2) {
      this.pC(var1);
      this.pC(var2);
      return this;
   }

   public UC pC(ZW var1, ZW var2, ZW var3) {
      this.pC(var1);
      this.pC(var2);
      this.pC(var3);
      return this;
   }

   public UC pC(UC var1, Predicate var2) {
      if (this.oT == null) {
         this.oT = new ArrayList();
      }

      this.oT.add(new NH(var1, var2));
      return this;
   }

   @Override
   public long pC(byte[] var1) {
      if (this.ys == null) {
         return 0L;
      } else {
         long var2 = 0L;

         for (ZW var5 : this.ys) {
            Long var6 = (Long)var5.A(var1);
            if (var6 != null) {
               var2 |= var6;
            }
         }

         return (var2 & 1L) != 0L ? 1L : var2;
      }
   }

   @Override
   public boolean A(byte[] var1) {
      if (this.pC(var1) != 1L && this.sY(var1)) {
         if (this.UT(var1) == null) {
            return true;
         } else {
            for (Hu var5 : this.sY) {
               if (var5 instanceof gZ && !((gZ)var5).wS(var1)) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   private boolean sY(byte[] var1) {
      try {
         this.ld().getDataType(var1);
         return true;
      } catch (oJ var2) {
         return false;
      }
   }

   @Override
   public boolean kS(byte[] var1) {
      return this.ld != null;
   }

   @Override
   public List wS(byte[] var1) {
      ArrayList var2 = new ArrayList();
      if (this.ld != null) {
         var2.add(this.ld);
      }

      if (this.gp != null) {
         for (jp var4 : this.gp) {
            String var5 = var4.pC(this, var1);
            if (var5 != null) {
               var2.add(var5);
            }
         }
      }

      for (Hu var6 : this.sY) {
         String var7 = var6.kS(var1);
         if (var7 != null) {
            var2.add(var7);
         }
      }

      return var2.isEmpty() ? null : var2;
   }

   @Override
   public NC pC(byte[] var1, zj var2) {
      if (var2 == null) {
         var2 = zj.pC(65536);
      }

      String var3 = Strings.toString(this.ld().getSuffix(var1), null);
      String var4 = null;
      if (!this.E() && var2.kS()) {
         var4 = var2.ys();
      }

      String var5;
      try {
         var5 = Strings.toString(this.ld().getDataType(var1), null);
      } catch (oJ var6) {
         return null;
      }

      return new NC(this.UT(var1), var3, this.ld().hasS(var1, var2), var4, this.ld().hasW(var1, var2), var5);
   }

   protected String UT(byte[] var1) {
      return this.kS;
   }

   @Override
   public int kS() {
      return this.sY.length;
   }

   @Override
   public Hu pC(int var1) {
      return var1 < this.sY.length ? this.sY[var1] : null;
   }

   @Override
   public Hu wS() {
      return this.sY.length > 0 ? this.sY[this.sY.length - 1] : null;
   }

   @Override
   public Yg[] pC(byte[] var1, int var2) throws ProcessorException {
      ArrayList var3 = new ArrayList(this.sY.length);

      for (Hu var7 : this.sY) {
         Yg var8 = var7.buildOperand(var1, var2);
         if (var8 != null) {
            var3.add(var8);
         }
      }

      return (Yg[])var3.toArray(new Yg[var3.size()]);
   }

   @Override
   public dW UT() {
      return this.UT;
   }

   @Override
   public boolean E() {
      return this.E == null ? false : (this.E.kS & 65536) != 0;
   }

   @Override
   public zj E(byte[] var1) {
      if (this.E == null || this.E()) {
         return zj.pC(65536);
      } else {
         return this.E.A == null ? zj.pC(this.E.kS) : zj.pC(Gq.A(var1, this.E.A) | this.E.kS);
      }
   }

   private ji ld() {
      return this.wS;
   }

   @Override
   public List sY() {
      return this.oT;
   }

   public static tz pC(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is unallocated. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }

   public static tz pC(tz[] var0, int var1, byte[] var2, String var3) throws ProcessorException {
      tz var4 = (tz)ArrayUtil.getSafe(var0, var1, null);
      if (var4 == null || var4 == pC) {
         pC(var2, var3);
      }

      return var4;
   }

   public static tz pC(tz[][] var0, int var1, int var2, byte[] var3, String var4) throws ProcessorException {
      tz var5 = (tz)ArrayUtil.getSafe2(var0, var1, var2, null);
      if (var5 == null || var5 == pC) {
         pC(var3, var4);
      }

      return var5;
   }

   public static UC pC(String var0, Hu... var1) {
      return new UC(var0, var1).pC(cT.A);
   }

   public UC pC(int var1, int var2) {
      return this.pC(cT.A(var1, var2));
   }

   public UC pC(IEncodedMemoryArea var1) {
      this.pC(new ZW(var1, ZW.kS));
      return this;
   }

   public UC A(int var1, int var2) {
      return this.pC(cT.pC(var1, var2));
   }

   public UC pC(jp... var1) {
      if (this.gp == null) {
         this.gp = new ArrayList();
      }

      for (jp var5 : var1) {
         this.gp.add(var5);
      }

      return this;
   }

   public UC ys() {
      return this.pC(cT.A);
   }

   public static class Av {
      public static final UC.Av pC = new UC.Av(65536);
      private IEncodedMemoryArea A = null;
      private int kS;

      public Av(int var1) {
         this.kS = var1;
      }

      public Av(IEncodedMemoryArea var1) {
         this.A = var1;
      }

      public Av(IEncodedMemoryArea var1, int var2) {
         this.A = var1;
         this.kS = var2;
      }
   }
}
