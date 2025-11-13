package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Qg implements Je {
   public static final long q = 0L;
   public static final long RF = 1L;
   private static final Ef[] Uv = new Ef[0];
   public static final Qg xK = null;
   public static final String Dw = "UNPREDICTABLE";
   private String oW;
   private de gO = de.xW;
   private lH nf = OQ.q;
   private Qg.eo gP = null;
   private Ef[] za;
   private List lm;
   private String zz = null;
   private List JY = null;
   private List HF = null;

   public Qg(String var1, Ef... var2) {
      this(var1, false, var2);
   }

   public Qg(int var1, String var2, Ef... var3) {
      this(var2, false, var3);
   }

   public Qg(String var1, lH var2, Ef... var3) {
      this(var1, false, var3);
      this.q(var2);
   }

   public Qg(int var1, String var2, de var3, Ef... var4) {
      this(var2, false, var4);
      this.q(var3);
   }

   protected Qg(String var1, boolean var2, Ef... var3) {
      this.oW = var1;
      this.za = var3;
      if (var3 == null) {
         this.za = Uv;
      }
   }

   public Qg q(Qg.eo var1) {
      this.gP = var1;
      return this;
   }

   public Qg q() {
      return this.q(Qg.eo.q);
   }

   public Qg q(de var1) {
      this.gO = var1 == null ? de.xW : var1;
      return this;
   }

   public Qg q(lH var1) {
      this.nf = (lH)(var1 == null ? OQ.q : var1);
      return this;
   }

   public Qg RF() {
      return this.q(QI.RF);
   }

   public Qg q(String var1) {
      this.zz = var1;
      return this;
   }

   public Qg q(dD var1) {
      if (this.lm == null) {
         this.lm = new ArrayList();
      }

      this.lm.add(var1);
      return this;
   }

   public Qg q(dD var1, dD var2) {
      this.q(var1);
      this.q(var2);
      return this;
   }

   public Qg q(dD var1, dD var2, dD var3) {
      this.q(var1);
      this.q(var2);
      this.q(var3);
      return this;
   }

   public Qg q(Qg var1, Predicate var2) {
      if (this.HF == null) {
         this.HF = new ArrayList();
      }

      this.HF.add(new ag(var1, var2));
      return this;
   }

   @Override
   public long q(byte[] var1) {
      if (this.lm == null) {
         return 0L;
      } else {
         long var2 = 0L;

         for (dD var5 : this.lm) {
            Long var6 = (Long)var5.RF(var1);
            if (var6 != null) {
               var2 |= var6;
            }
         }

         return (var2 & 1L) != 0L ? 1L : var2;
      }
   }

   @Override
   public boolean RF(byte[] var1) {
      if (this.q(var1) != 1L && this.gO(var1)) {
         if (this.Uv(var1) == null) {
            return true;
         } else {
            for (Ef var5 : this.za) {
               if (var5 instanceof rR && !((rR)var5).Dw(var1)) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   private boolean gO(byte[] var1) {
      try {
         this.gP().getDataType(var1);
         return true;
      } catch (eK var2) {
         return false;
      }
   }

   @Override
   public boolean xK(byte[] var1) {
      return this.zz != null;
   }

   @Override
   public List Dw(byte[] var1) {
      ArrayList var2 = new ArrayList();
      if (this.zz != null) {
         var2.add(this.zz);
      }

      if (this.JY != null) {
         for (YB var4 : this.JY) {
            String var5 = var4.q(this, var1);
            if (var5 != null) {
               var2.add(var5);
            }
         }
      }

      for (Ef var6 : this.za) {
         String var7 = var6.xK(var1);
         if (var7 != null) {
            var2.add(var7);
         }
      }

      return var2.isEmpty() ? null : var2;
   }

   @Override
   public Op q(byte[] var1, mZ var2) {
      if (var2 == null) {
         var2 = mZ.q(65536);
      }

      String var3 = Strings.toString(this.gP().getSuffix(var1), null);
      String var4 = null;
      if (!this.oW() && var2.xK()) {
         var4 = var2.nf();
      }

      String var5;
      try {
         var5 = Strings.toString(this.gP().getDataType(var1), null);
      } catch (eK var6) {
         return null;
      }

      return new Op(this.Uv(var1), var3, this.gP().hasS(var1, var2), var4, this.gP().hasW(var1, var2), var5);
   }

   protected String Uv(byte[] var1) {
      return this.oW;
   }

   @Override
   public int xK() {
      return this.za.length;
   }

   @Override
   public Ef q(int var1) {
      return var1 < this.za.length ? this.za[var1] : null;
   }

   @Override
   public Ef Dw() {
      return this.za.length > 0 ? this.za[this.za.length - 1] : null;
   }

   @Override
   public CW[] q(byte[] var1, int var2) throws ProcessorException {
      ArrayList var3 = new ArrayList(this.za.length);

      for (Ef var7 : this.za) {
         CW var8 = var7.buildOperand(var1, var2);
         if (var8 != null) {
            var3.add(var8);
         }
      }

      return (CW[])var3.toArray(new CW[var3.size()]);
   }

   @Override
   public lH Uv() {
      return this.nf;
   }

   @Override
   public boolean oW() {
      return this.gP == null ? false : (this.gP.xK & 65536) != 0;
   }

   @Override
   public mZ oW(byte[] var1) {
      if (this.gP == null || this.oW()) {
         return mZ.q(65536);
      } else {
         return this.gP.RF == null ? mZ.q(this.gP.xK) : mZ.q(k.RF(var1, this.gP.RF) | this.gP.xK);
      }
   }

   private de gP() {
      return this.gO;
   }

   @Override
   public List gO() {
      return this.HF;
   }

   public static Je q(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is unallocated. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }

   public static Je q(Je[] var0, int var1, byte[] var2, String var3) throws ProcessorException {
      Je var4 = (Je)ArrayUtil.getSafe(var0, var1, null);
      if (var4 == null || var4 == xK) {
         q(var2, var3);
      }

      return var4;
   }

   public static Je q(Je[][] var0, int var1, int var2, byte[] var3, String var4) throws ProcessorException {
      Je var5 = (Je)ArrayUtil.getSafe2(var0, var1, var2, null);
      if (var5 == null || var5 == xK) {
         q(var3, var4);
      }

      return var5;
   }

   public static Qg q(String var0, Ef... var1) {
      return new Qg(var0, var1).q(QI.RF);
   }

   public Qg RF(int var1) {
      return this.q(var1, 1);
   }

   public Qg q(int var1, int var2) {
      return this.q(QI.RF(var1, var2));
   }

   public Qg q(IEncodedMemoryArea var1) {
      this.q(new dD(var1, dD.xK));
      return this;
   }

   public Qg RF(int var1, int var2) {
      return this.q(QI.q(var1, var2));
   }

   public Qg q(YB... var1) {
      if (this.JY == null) {
         this.JY = new ArrayList();
      }

      for (YB var5 : var1) {
         this.JY.add(var5);
      }

      return this;
   }

   public Qg nf() {
      return this.q(QI.RF);
   }

   public static class eo {
      public static final Qg.eo q = new Qg.eo(65536);
      private IEncodedMemoryArea RF = null;
      private int xK;

      public eo(int var1) {
         this.xK = var1;
      }

      public eo(IEncodedMemoryArea var1) {
         this.RF = var1;
      }

      public eo(IEncodedMemoryArea var1, int var2) {
         this.RF = var1;
         this.xK = var2;
      }
   }
}
