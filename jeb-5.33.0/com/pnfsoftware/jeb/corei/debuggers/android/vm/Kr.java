package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.wX;
import java.io.IOException;

public class Kr implements IDebuggerVariable {
   private static final ILogger pC = GlobalLog.getLogger(Kr.class);
   private String A;
   private String kS;
   private ITypedValue wS;
   private int UT;
   private uX E;
   private Tb sY;
   private Kr.Av ys;

   public Kr(String var1, ITypedValue var2, int var3, uX var4) {
      this.A = var1;
      this.wS = var2;
      this.UT = var3;
      this.E = var4;
   }

   public Kr(String var1, ITypedValue var2, int var3, Tb var4, Kr.Av var5) {
      this.A = var1;
      this.wS = var2;
      this.UT = var3;
      this.sY = var4;
      this.ys = var5;
   }

   @Override
   public String getName() {
      return this.A;
   }

   void pC(String var1) {
      this.kS = var1;
   }

   @Override
   public String getAlternateName() {
      return this.kS;
   }

   @Override
   public int getFlags() {
      return this.UT;
   }

   @Override
   public ITypedValue getTypedValue() {
      return this.wS;
   }

   @Override
   public boolean setTypedValue(ITypedValue var1) {
      ITypedValue var2 = this.getTypedValue();
      boolean var3 = false;
      if (this.E != null) {
         this.wS = var1;
         var3 = this.E.setVariable(this);
      } else if (this.sY != null) {
         this.wS = var1;
         var3 = this.sY.pC(this);
      }

      if (!var3) {
         this.wS = var2;
      }

      return var3;
   }

   @Override
   public boolean canEditValue() {
      return this.sY != null && this.sY.pC() || this.E != null && this.E.pC.pC();
   }

   @Override
   public boolean canEditType() {
      return this.A() && this.A.startsWith("v");
   }

   @Override
   public boolean setTypeHint(String var1) {
      return this.A() ? this.E.pC(this.getName(), var1) : false;
   }

   public boolean A() {
      return this.E != null;
   }

   public Kr.Av kS() {
      return this.ys;
   }

   public rG pC() throws wX, IOException {
      if (this.sY != null) {
         return this.sY.pC(this.getTypedValue());
      } else {
         return this.E != null ? this.E.pC().pC().pC(this.getTypedValue()) : null;
      }
   }

   @Override
   public String format() {
      return Strings.ff("name=%s,value=%s", this.getName(), this.getTypedValue().format());
   }

   @Override
   public String toString() {
      return Strings.ff("name=%s,value=%s", this.getName(), this.getTypedValue());
   }

   public static class Av {
      long pC;
      long A;
      int kS = -1;

      public Av(long var1, long var3) {
         this.pC = var1;
         this.A = var3;
      }

      public Av(long var1, int var3) {
         this.pC = var1;
         this.kS = var3;
      }

      public boolean pC() {
         return this.pC != 0L && this.kS != -1;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.A ^ this.A >>> 32);
         var1 = 31 * var1 + this.kS;
         return 31 * var1 + (int)(this.pC ^ this.pC >>> 32);
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            Kr.Av var2 = (Kr.Av)var1;
            if (this.A != var2.A) {
               return false;
            } else {
               return this.kS != var2.kS ? false : this.pC == var2.pC;
            }
         }
      }
   }
}
