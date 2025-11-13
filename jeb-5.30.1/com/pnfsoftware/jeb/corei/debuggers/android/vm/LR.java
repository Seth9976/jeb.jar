package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;

public class LR implements IDebuggerVariable {
   private static final ILogger q = GlobalLog.getLogger(LR.class);
   private String RF;
   private String xK;
   private ITypedValue Dw;
   private int Uv;
   private Xa oW;
   private CI gO;
   private LR.eo nf;

   public LR(String var1, ITypedValue var2, int var3, Xa var4) {
      this.RF = var1;
      this.Dw = var2;
      this.Uv = var3;
      this.oW = var4;
   }

   public LR(String var1, ITypedValue var2, int var3, CI var4, LR.eo var5) {
      this.RF = var1;
      this.Dw = var2;
      this.Uv = var3;
      this.gO = var4;
      this.nf = var5;
   }

   @Override
   public String getName() {
      return this.RF;
   }

   void q(String var1) {
      this.xK = var1;
   }

   @Override
   public String getAlternateName() {
      return this.xK;
   }

   @Override
   public int getFlags() {
      return this.Uv;
   }

   @Override
   public ITypedValue getTypedValue() {
      return this.Dw;
   }

   @Override
   public boolean setTypedValue(ITypedValue var1) {
      ITypedValue var2 = this.getTypedValue();
      boolean var3 = false;
      if (this.oW != null) {
         this.Dw = var1;
         var3 = this.oW.setVariable(this);
      } else if (this.gO != null) {
         this.Dw = var1;
         var3 = this.gO.q(this);
      }

      if (!var3) {
         this.Dw = var2;
      }

      return var3;
   }

   @Override
   public boolean canEditValue() {
      return this.gO != null && this.gO.RF() || this.oW != null && this.oW.q.RF();
   }

   @Override
   public boolean canEditType() {
      return this.RF() && this.RF.startsWith("v");
   }

   @Override
   public boolean setTypeHint(String var1) {
      return this.RF() ? this.oW.q(this.getName(), var1) : false;
   }

   public boolean RF() {
      return this.oW != null;
   }

   public LR.eo xK() {
      return this.nf;
   }

   public ch q() throws zy, IOException {
      if (this.gO != null) {
         return this.gO.q(this.getTypedValue());
      } else {
         return this.oW != null ? this.oW.q().q().q(this.getTypedValue()) : null;
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

   public static class eo {
      long q;
      long RF;
      int xK = -1;

      public eo(long var1, long var3) {
         this.q = var1;
         this.RF = var3;
      }

      public eo(long var1, int var3) {
         this.q = var1;
         this.xK = var3;
      }

      public eo(int var1) {
         this.xK = var1;
      }

      public boolean q() {
         return this.q != 0L && this.xK == -1;
      }

      public boolean RF() {
         return this.q != 0L && this.xK != -1;
      }

      public boolean xK() {
         return this.q == 0L && this.xK != -1;
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
         var1 = 31 * var1 + this.xK;
         return 31 * var1 + (int)(this.q ^ this.q >>> 32);
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
            LR.eo var2 = (LR.eo)var1;
            if (this.RF != var2.RF) {
               return false;
            } else {
               return this.xK != var2.xK ? false : this.q == var2.q;
            }
         }
      }
   }
}
