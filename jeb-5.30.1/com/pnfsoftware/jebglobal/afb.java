package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabelFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethodFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

@Ser
public class afb implements ICMethodFactory {
   private static final StructuredLogger Dw = aeg.q(afb.class);
   public static final String q = "par";
   @SerId(1)
   ICGlobalContext RF;
   @SerId(2)
   Map xK = new IdentityHashMap();

   public afb(afa var1) {
      this.RF = var1;
   }

   public agp q(String var1) {
      return (agp)this.xK.get(var1);
   }

   public boolean RF(String var1) {
      return this.xK.remove(var1) != null;
   }

   public boolean q(INativeMethodItem var1) {
      return this.xK.remove(var1.getAddress(false)) != null;
   }

   public agp xK(String var1) {
      agp var2 = (agp)this.xK.get(var1);
      if (var2 == null) {
         var2 = new agp(this.RF, var1);
         this.xK.put(var1, var2);
      }

      return var2;
   }

   public agp q(IERoutineContext var1, boolean var2) {
      return this.q(var1.getRoutine(), var1, var1.getPrototype(), var2);
   }

   public agp q(INativeMethodItem var1, IWildcardPrototype var2, boolean var3) {
      return this.q(var1, null, var2, var3);
   }

   private agp q(INativeMethodItem var1, IERoutineContext var2, IWildcardPrototype var3, boolean var4) {
      Assert.a(var1 != null);
      String var5 = var1.getAddress(false);
      agp var6 = this.q(var5);
      if (var6 != null && !var4) {
         return var6;
      } else {
         if (var6 == null) {
            var6 = this.xK(var5);
         }

         this.q(var6, var1, var2, var3);
         return var6;
      }
   }

   private void q(agp var1, INativeMethodItem var2, IERoutineContext var3, IWildcardPrototype var4) {
      Assert.a(var1 != null && var2 != null);
      if (var3 != null) {
         Assert.a(var3.getRoutine() == var2);
      }

      int var5 = var2.getIndex();
      INativeMethodDataItem var6 = var2.getData();
      boolean var7 = var6 == null;
      Long var8 = var6 == null ? null : var6.getMemoryAddress();
      ICIdentifierManager var9 = this.RF.createLocalIdentifierManager();
      ICLabelFactory var10 = this.RF.createLabelFactory();
      Object var11 = null;
      ArrayList var12 = new ArrayList();
      ArrayList var13 = new ArrayList();
      boolean var14 = false;
      if (var3 != null && var3.getPrototype() != null) {
         var4 = var3.getPrototype();
         var14 = var4.isVariableArgument();
         List var27 = var3.getRoutine().getParameterNames();
         IEPrototypeHandler var29 = var3.getConverter().getPrototypeHandler(var3);
         ArrayList var32 = new ArrayList();
         ArrayList var34 = new ArrayList();
         var29.retrieveFromPrototype(var32, var34);
         int var36 = 0;

         for (IEGeneric var39 : var32) {
            ICLeftExpression var22 = null;
            if (var39 instanceof IEVar var23) {
               byte var24 = 0;
               String var25 = var23.getName();
               if (var27 != null && var36 < var27.size() && var27.get(var36) != null && ((String)var27.get(var36)).length() > 0) {
                  var25 = (String)var27.get(var36);
                  var24 |= 2;
               }

               var22 = amy.q(
                  var23.getId(),
                  var25,
                  var23.getType(),
                  var23.getAddress(),
                  CIdentifierClass.LOCAL,
                  this.RF.getTypeFactory(),
                  this.RF.getIdentifierManager(),
                  var9,
                  var5,
                  2 + var36
               );
               if (var22 instanceof ICDecl) {
                  ((ICDecl)var22).getIdentifier().setFlags(var24);
               }
            }

            if (!(var22 instanceof afz)) {
               Dw.error("Illegal parameter will not be generated: " + var22);
            } else {
               var12.add((afz)var22);
               var36++;
            }
         }

         for (IWildcardType var40 : var34) {
            var13.add(this.RF.getTypeFactory().create(var40));
         }
      } else {
         List var15 = var2.getParameterNames();
         if (var4 != null) {
            var14 = var4.isVariableArgument();
            int var16 = 0;

            for (IWildcardType var18 : var4.getParameterTypes()) {
               String var19 = var15 != null && var16 < var15.size() ? (String)var15.get(var16) : null;
               var19 = Strings.safe2(var19, "par" + var16);
               ICType var20 = this.RF.getTypeFactory().create(var18);
               ICDecl var21 = var9.create(var16, var20, var19, CIdentifierClass.SYNTHETIC, var5, null, var16);
               var12.add(var21);
               var16++;
            }

            for (IWildcardType var33 : var4.getReturnTypes()) {
               var13.add(this.RF.getTypeFactory().create(var33));
            }
         } else {
            var13.add(this.RF.getTypeFactory().getVoid());
         }
      }

      String var28 = var2.getName(true);
      int var30 = age.q(var2.getGenericFlags());
      var1.q(var7, var5, var9, var10, (ICType)var11, var28, var13, var12, var14, var30, var8);
   }

   public agp Dw(String var1) {
      Assert.a(var1 != null);
      agp var3 = this.q(var1);
      if (var3 != null) {
         return var3;
      } else {
         var3 = this.xK(var1);
         ICIdentifierManager var4 = this.RF.createLocalIdentifierManager();
         ICLabelFactory var5 = this.RF.createLabelFactory();
         Object var6 = null;
         ArrayList var7 = new ArrayList();
         ArrayList var8 = new ArrayList();
         boolean var9 = false;
         var3.q(true, -1, var4, var5, (ICType)var6, var1, var8, var7, var9, 0, null);
         return var3;
      }
   }
}
