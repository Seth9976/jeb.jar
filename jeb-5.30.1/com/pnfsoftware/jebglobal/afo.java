package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CElementType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CEnvironment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.simulator.CMethodState;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;

@Ser
public abstract class afo extends afg implements ICConstant {
   @SerId(1)
   protected ICType q;
   @SerId(2)
   protected Object RF;
   @SerId(3)
   String xK;

   protected afo(ICType var1, Object var2) {
      this.q = var1;
      this.RF = var2;
   }

   @Override
   public ICType getType() {
      return this.q;
   }

   @Override
   public Object getValue() {
      return this.RF;
   }

   @Override
   public List getSubElements() {
      return ahf.q();
   }

   @Override
   public boolean replaceSubElement(ICElement var1, ICElement var2) {
      return false;
   }

   protected abstract void xK(COutputSink var1);

   @Override
   public void generate(COutputSink var1) {
      this.q(var1);
      this.xK(var1);
      this.RF(var1);
   }

   protected void q(COutputSink var1, String var2, ItemClassIdentifiers var3, long var4) {
      if (var1.getSourceCustomizer() != null) {
         String var6 = var1.getSourceCustomizer().customizeRenderedConstant(this, var2);
         if (var6 != null) {
            var2 = var6;
         }
      }

      var1.appendAndRecord(var2, var3, var4);
   }

   @Override
   public CElementType getElementType() {
      return CElementType.Constant;
   }

   public afo RF() {
      return this;
   }

   @Override
   protected void q(afe var1) {
      super.q(var1);
      ((afo)var1).q = this.q;
      ((afo)var1).RF = this.RF;
      ((afo)var1).xK = this.xK;
   }

   @Override
   public Long evaluate(CMethodState var1, CEnvironment var2) {
      return CUtil.getConstantAsLong(this);
   }

   public static int q(long var0, boolean var2) {
      return afc.q(var0, var2);
   }

   @Deprecated
   static int q(long var0) {
      if (-20L <= var0 && var0 <= 20L) {
         return 10;
      } else {
         if (var0 < 0L) {
            var0 = -var0;
         }

         long var2 = var0;
         int var4 = 0;

         int var5;
         for (var5 = 0; var2 != 0L && var2 != -1L; var4++) {
            long var6 = var2 % 10L;
            if (var6 == 0L) {
               var5++;
            }

            var2 /= 10L;
         }

         var2 = var0;
         int var13 = 0;
         int var7 = 0;

         int var8;
         for (var8 = 0; var2 != 0L && var2 != -1L; var13++) {
            long var9 = var2 & 15L;
            if (var9 == 0L) {
               var7++;
            } else if (var9 == 15L) {
               var8++;
            }

            var2 >>= 4;
         }

         int var14 = var5 - (var4 - var5);
         int var10 = var7 + var8 - (var13 - var7 - var8);
         return var14 >= var10 ? 10 : 16;
      }
   }

   @Override
   public void setOrigin(String var1) {
      this.xK = var1;
   }

   @Override
   public String getOrigin() {
      return this.xK;
   }

   @Override
   public String toString() {
      return this.getValue() + "";
   }
}
