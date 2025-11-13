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
public class aey implements ICFieldFactory {
   private static final StructuredLogger xK = aeg.q(aey.class);
   @SerId(1)
   ICGlobalContext q;
   @SerId(2)
   Map RF = new HashMap();

   public aey(afa var1) {
      this.q = var1;
   }

   public agd q(String var1) {
      return (agd)this.RF.get(var1);
   }

   public boolean RF(String var1) {
      return this.RF.remove(var1) != null;
   }

   public agd xK(String var1) {
      agd var2 = (agd)this.RF.get(var1);
      if (var2 == null) {
         var2 = new agd(this.q, var1);
         this.RF.put(var1, var2);
      }

      return var2;
   }

   public agd q(INativeFieldItem var1, boolean var2) {
      Assert.a(var1 != null);
      String var3 = var1.getAddress(false);
      agd var4 = this.q(var3);
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

   private void q(agd var1, INativeFieldItem var2) {
      IWildcardTypeManager var3 = ((afa)this.q).q();
      int var4 = age.q(var2.getGenericFlags());
      ICType var5 = this.q.getTypeFactory().create(var3.create(var2.getFieldType()));
      Couple var6 = var2.getStructureFieldDetails();
      if (var6 != null) {
         IStructureTypeField var7 = (IStructureTypeField)var6.getSecond();
         ICType var8 = this.q.getTypeFactory().create(var3.create((INativeType)var6.getFirst()));
         var1.q(var4, var8, var5, var7.getName(), var7.getOffset());
      } else if (var2.getData() != null) {
         INativeDataItem var10 = var2.getData();
         int var11 = var2.getIndex();
         boolean var9 = !var2.isInternal();
         var1.q(var9, var11, var4, var5, var10.getName(true), var10.getMemoryAddress());
      }
   }

   public agd q(IStructureType var1, IStructureTypeField var2) {
      String var3 = var1.getAddress(false) + "->" + var2.getName();
      agd var4 = this.q(var3);
      if (var4 != null) {
         return var4;
      } else {
         if (var4 == null) {
            var4 = this.xK(var3);
         }

         IWildcardTypeManager var5 = ((afa)this.q).q();
         ICType var6 = this.q.getTypeFactory().create(var5.create(var1));
         ICType var7 = this.q.getTypeFactory().create(var5.create(var2.getType()));
         var4.q(0, var6, var7, var2.getName(), var2.getOffset());
         return var4;
      }
   }
}
