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

public class cnz {
   private static final ILogger xK = GlobalLog.getLogger(cnz.class);
   public static List q = new ArrayList();
   final abg RF;
   private final ITypeManager Dw;

   public cnz(abg var1) {
      this.RF = var1;
      this.Dw = var1.RF();
      this.RF();
   }

   private void RF() {
      cnx.q(this.Dw, this.q());
      cod.RF(this.Dw, this.q());
      coe.RF(this.Dw, this.q());
      cnw.q(this.Dw, this.q());
   }

   public boolean q(long var1, cnx var3, boolean var4) {
      if (this.Dw.getType(var3.oW()) == null) {
         return false;
      } else {
         if (var4 && this.q(var1)) {
            String var5 = var3.xK();
            this.RF.q(var1, this.Dw.getType(var3.oW()), var5, true, true);
         } else {
            this.RF.setDataAt(var1, this.Dw.getType(var3.oW()), null);
         }

         if (var3 instanceof coe var9) {
            long var6 = var9.gP;

            for (int var8 = 0; var8 < var9.nf; var8++) {
               this.RF.setDataAt(var6, this.Dw.getType("__base_class_type_info"), null);
               var6 += cnw.q(this);
            }
         }

         if (this.q(var3.gO()) && var4 && this.RF.setStringAt(var3.gO(), -1L, StringEncoding.ASCII_ZERO, -1, -1)) {
            String var10 = var3.Dw();
            axw var11 = (axw)this.RF.getCodeModel().getItemAt(var3.gO());
            var11.setName(var10);
            ((aae)this.RF.getCodeAnalyzer()).q(var11);
         }

         return true;
      }
   }

   public cnr q(Map var1) {
      cnr var2 = new cnr(this.RF);

      for (cnx var4 : var1.values()) {
         var2.q(var4.RF());
         if (var4 instanceof cod) {
            cnx var8 = (cnx)var1.get(((cod)var4).gO);
            if (var8 != null) {
               var2.q(var4.RF(), var8.RF(), true, false);
            }
         } else if (var4 instanceof coe) {
            for (cnw var6 : ((coe)var4).za) {
               cnx var7 = (cnx)var1.get(var6.RF);
               if (var7 != null) {
                  var2.q(var4.RF(), var7.RF(), var6.RF(), var6.q());
               }
            }
         }
      }

      return var2;
   }

   protected boolean q(long var1) {
      String var3 = this.RF.getCodeModel().getLabelManager().getLabel(var1, 0L, AutoLabelPolicy.OFF);
      if (var3 != null && !var3.startsWith("ptr_") && !((Nx)this.RF.getCodeModel().getLabelManager()).q(var3)) {
         INativeContinuousItem var4 = this.RF.getCodeModel().getItemAt(var1);
         return var4 instanceof axw && var3.startsWith("a") ? true : var4 instanceof INativeDataItem && ((INativeDataItem)var4).getType() instanceof bbt;
      } else {
         return true;
      }
   }

   public boolean q() {
      return this.RF.getProcessor().getDefaultMode() == 64;
   }

   static {
      q.add("_ZTVN10__cxxabiv117__class_type_infoE");
      q.add("_ZTVN10__cxxabiv120__si_class_type_infoE");
      q.add("_ZTVN10__cxxabiv121__vmi_class_type_infoE");
   }

   public static enum eo {
      q((String)cnz.q.get(0)),
      RF((String)cnz.q.get(1)),
      xK((String)cnz.q.get(2));

      final String Dw;

      private eo(String var3) {
         this.Dw = var3;
      }

      public cnx q(long var1, cnz var3) {
         try {
            return this.RF(var1, var3);
         } catch (MemoryException var4) {
            return null;
         }
      }

      public String q() {
         return this.Dw;
      }

      public abstract cnx RF(long var1, cnz var3) throws MemoryException;
   }
}
