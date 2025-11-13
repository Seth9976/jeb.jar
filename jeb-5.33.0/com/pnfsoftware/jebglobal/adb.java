package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClassFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeClassItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IClassType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Ser
public class adb implements ICClassFactory {
   private static final StructuredLogger kS = aco.pC(adb.class);
   @SerId(1)
   ICGlobalContext pC;
   @SerId(2)
   Map A = new HashMap();

   public adb(adh var1) {
      this.pC = var1;
   }

   public adu pC(String var1) {
      return (adu)this.A.get(var1);
   }

   public boolean A(String var1) {
      return this.A.remove(var1) != null;
   }

   public adu kS(String var1) {
      adu var2 = (adu)this.A.get(var1);
      if (var2 == null) {
         var2 = new adu(this.pC, var1);
         this.A.put(var1, var2);
      }

      return var2;
   }

   public adu pC(INativeClassItem var1, boolean var2) {
      Assert.a(var1 != null);
      String var3 = var1.getAddress(false);
      adu var4 = (adu)this.A.get(var3);
      if (var4 != null && !var2) {
         return var4;
      } else {
         if (var4 == null) {
            var4 = this.kS(var3);
         }

         this.pC(var4, var1);
         return var4;
      }
   }

   private void pC(adu var1, INativeClassItem var2) {
      int var3 = var2.getIndex();
      IClassType var4 = var2.getClassType();
      boolean var5 = var4 == null;
      int var6 = ael.pC(var2.getGenericFlags());
      IWildcardTypeManager var7 = ((adh)this.pC).pC();
      ICType var8 = this.pC.getTypeFactory().create(var7.create(var2.getClassType()));
      ArrayList var9 = new ArrayList();

      for (IClassType var11 : var2.getSupertypes()) {
         var9.add(this.pC.getTypeFactory().create(var7.create(var11)));
      }

      var1.pC(var5, var3, var6, var8, var9);
   }
}
