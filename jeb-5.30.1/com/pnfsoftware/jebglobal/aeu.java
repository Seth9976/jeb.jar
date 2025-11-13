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
public class aeu implements ICClassFactory {
   private static final StructuredLogger xK = aeg.q(aeu.class);
   @SerId(1)
   ICGlobalContext q;
   @SerId(2)
   Map RF = new HashMap();

   public aeu(afa var1) {
      this.q = var1;
   }

   public afn q(String var1) {
      return (afn)this.RF.get(var1);
   }

   public boolean RF(String var1) {
      return this.RF.remove(var1) != null;
   }

   public afn xK(String var1) {
      afn var2 = (afn)this.RF.get(var1);
      if (var2 == null) {
         var2 = new afn(this.q, var1);
         this.RF.put(var1, var2);
      }

      return var2;
   }

   public afn q(INativeClassItem var1, boolean var2) {
      Assert.a(var1 != null);
      String var3 = var1.getAddress(false);
      afn var4 = (afn)this.RF.get(var3);
      if (var4 != null && !var2) {
         return var4;
      } else {
         if (var4 == null) {
            var4 = this.xK(var3);
         }

         this.q(var4, var1);
         return var4;
      }
   }

   private void q(afn var1, INativeClassItem var2) {
      int var3 = var2.getIndex();
      IClassType var4 = var2.getClassType();
      boolean var5 = var4 == null;
      int var6 = age.q(var2.getGenericFlags());
      IWildcardTypeManager var7 = ((afa)this.q).q();
      ICType var8 = this.q.getTypeFactory().create(var7.create(var2.getClassType()));
      ArrayList var9 = new ArrayList();

      for (IClassType var11 : var2.getSupertypes()) {
         var9.add(this.q.getTypeFactory().create(var7.create(var11)));
      }

      var1.q(var5, var3, var6, var8, var9);
   }
}
