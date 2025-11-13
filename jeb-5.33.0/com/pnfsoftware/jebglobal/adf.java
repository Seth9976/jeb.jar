package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICFieldFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeFieldItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class adf implements ICFieldFactory {
   private static final StructuredLogger kS = aco.pC(adf.class);
   @SerId(1)
   ICGlobalContext pC;
   @SerId(2)
   Map A = new HashMap();

   public adf(adh var1) {
      this.pC = var1;
   }

   public aek pC(String var1) {
      return (aek)this.A.get(var1);
   }

   public boolean A(String var1) {
      return this.A.remove(var1) != null;
   }

   public aek kS(String var1) {
      aek var2 = (aek)this.A.get(var1);
      if (var2 == null) {
         var2 = new aek(this.pC, var1);
         this.A.put(var1, var2);
      }

      return var2;
   }

   public aek pC(INativeFieldItem var1, boolean var2) {
      Assert.a(var1 != null);
      String var3 = var1.getAddress(false);
      aek var4 = this.pC(var3);
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

   private void pC(aek var1, INativeFieldItem var2) {
      IWildcardTypeManager var3 = ((adh)this.pC).pC();
      int var4 = ael.pC(var2.getGenericFlags());
      ICType var5 = this.pC.getTypeFactory().create(var3.create(var2.getFieldType()));
      Couple var6 = var2.getStructureFieldDetails();
      if (var6 != null) {
         IStructureTypeField var7 = (IStructureTypeField)var6.getSecond();
         ICType var8 = this.pC.getTypeFactory().create(var3.create((INativeType)var6.getFirst()));
         var1.pC(var4, var8, var5, var7.getName(), var7.getOffset());
      } else if (var2.getData() != null) {
         INativeDataItem var10 = var2.getData();
         int var11 = var2.getIndex();
         boolean var9 = !var2.isInternal();
         var1.pC(var9, var11, var4, var5, var10.getName(true), var10.getMemoryAddress());
      }
   }

   public aek pC(IStructureType var1, IStructureTypeField var2) {
      String var3 = var1.getAddress(false) + "->" + var2.getName();
      aek var4 = this.pC(var3);
      if (var4 != null) {
         return var4;
      } else {
         if (var4 == null) {
            var4 = this.kS(var3);
         }

         IWildcardTypeManager var5 = ((adh)this.pC).pC();
         ICType var6 = this.pC.getTypeFactory().create(var5.create(var1));
         ICType var7 = this.pC.getTypeFactory().create(var5.create(var2.getType()));
         var4.pC(0, var6, var7, var2.getName(), var2.getOffset());
         return var4;
      }
   }
}
