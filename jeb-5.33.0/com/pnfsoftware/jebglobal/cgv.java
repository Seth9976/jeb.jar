package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AutoLabelPolicy;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.type.StringEncoding;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class cgv {
   private static final ILogger kS = GlobalLog.getLogger(cgv.class);
   public static List pC = new ArrayList();
   final C A;
   private final ITypeManager wS;

   public cgv(C var1) {
      this.A = var1;
      this.wS = var1.A();
      this.A();
   }

   private void A() {
      cgt.pC(this.wS, this.pC());
      cgz.A(this.wS, this.pC());
      cha.A(this.wS, this.pC());
      cgs.pC(this.wS, this.pC());
   }

   public boolean pC(long var1, cgt var3, boolean var4) {
      if (this.wS.getType(var3.UT()) == null) {
         return false;
      } else {
         if (var4 && this.pC(var1)) {
            String var5 = var3.kS();
            this.A.pC(var1, this.wS.getType(var3.UT()), var5, true, true);
         } else {
            this.A.setDataAt(var1, this.wS.getType(var3.UT()), null);
         }

         if (var3 instanceof cha var9) {
            long var6 = var9.sY;

            for (int var8 = 0; var8 < var9.E; var8++) {
               this.A.setDataAt(var6, this.wS.getType("__base_class_type_info"), null);
               var6 += cgs.pC(this);
            }
         }

         if (this.pC(var3.E()) && var4 && this.A.setStringAt(var3.E(), -1L, StringEncoding.ASCII_ZERO, -1, -1)) {
            String var10 = var3.wS();
            avb var11 = (avb)this.A.getCodeModel().getItemAt(var3.E());
            var11.setName(var10);
            ((a)this.A.getCodeAnalyzer()).pC(var11);
         }

         return true;
      }
   }

   public cgn pC(Map var1) {
      cgn var2 = new cgn(this.A);

      for (cgt var4 : var1.values()) {
         var2.pC(var4.A());
         if (var4 instanceof cgz) {
            cgt var8 = (cgt)var1.get(((cgz)var4).UT);
            if (var8 != null) {
               var2.pC(var4.A(), var8.A(), true, false);
            }
         } else if (var4 instanceof cha) {
            for (cgs var6 : ((cha)var4).ys) {
               cgt var7 = (cgt)var1.get(var6.pC);
               if (var7 != null) {
                  var2.pC(var4.A(), var7.A(), var6.A(), var6.pC());
               }
            }
         }
      }

      return var2;
   }

   protected boolean pC(long var1) {
      String var3 = this.A.getCodeModel().getLabelManager().getLabel(var1, 0L, AutoLabelPolicy.OFF);
      if (var3 != null && !var3.startsWith("ptr_") && !((HM)this.A.getCodeModel().getLabelManager()).pC(var3)) {
         INativeContinuousItem var4 = this.A.getCodeModel().getItemAt(var1);
         return var4 instanceof avb && var3.startsWith("a") ? true : var4 instanceof INativeDataItem && ((INativeDataItem)var4).getType() instanceof ayt;
      } else {
         return true;
      }
   }

   public boolean pC() {
      return this.A.getProcessor().getDefaultMode() == 64;
   }

   static {
      pC.add("_ZTVN10__cxxabiv117__class_type_infoE");
      pC.add("_ZTVN10__cxxabiv120__si_class_type_infoE");
      pC.add("_ZTVN10__cxxabiv121__vmi_class_type_infoE");
   }

   public static enum Av {
      pC((String)cgv.pC.get(0)),
      A((String)cgv.pC.get(1)),
      kS((String)cgv.pC.get(2));

      final String wS;

      private Av(String var3) {
         this.wS = var3;
      }

      public cgt pC(long var1, cgv var3) {
         try {
            return this.A(var1, var3);
         } catch (MemoryException var4) {
            return null;
         }
      }

      public abstract cgt A(long var1, cgv var3) throws MemoryException;
   }
}
