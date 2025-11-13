package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class ahd implements ICTypeFactory {
   @SerId(1)
   private Map q = new HashMap();
   @SerId(2)
   private IWildcardType RF;
   @SerId(3)
   private IWildcardType xK;
   @SerId(4)
   private IWildcardType Dw;
   @SerId(5)
   private IWildcardType Uv;

   public ahd(IWildcardType var1, IWildcardType var2, IWildcardType var3, IWildcardType var4) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var3;
      this.Uv = var4;
   }

   @Override
   public ICType getVoid() {
      return this.create(this.RF);
   }

   @Override
   public ICType getInt() {
      return this.create(this.xK);
   }

   @Override
   public ICType getUnsignedInt() {
      return this.create(this.Dw);
   }

   @Override
   public ICType getChar() {
      return this.create(this.Uv);
   }

   @Override
   public ICType create(IWildcardType var1) {
      if (var1 == null) {
         return this.getVoid();
      } else {
         String var3 = null;
         byte var4 = 0;
         boolean var5 = var1.isPointer();
         if (var5) {
            var4 |= 4;
         }

         INativeType var6 = var1.getNativeType();
         if (var6 == null) {
            var1 = var1.resolveA();
            if (var1.isDefined()) {
               var6 = var1.getNativeType();
            }
         }

         String var2;
         if (var6 != null) {
            if (var6 instanceof IAliasType) {
               var4 |= 2;
            }

            if (var5) {
               IReferenceType var7 = (IReferenceType)TypeUtil.getNonAlias(var6);
               if (var7.getPointedType() instanceof IPrototypeItem) {
                  var4 |= 8;
               }
            }

            INativeType var8 = TypeUtil.getBaseType(var6);
            if (var8 != null) {
               var3 = var8.getSignature(false);
            }

            var2 = var6.getSignature();
         } else {
            var2 = "__SyntheticType" + var1.toString().replace("?", "Unknown");
            var4 |= 16;
         }

         ahc var9 = (ahc)this.q.get(var2);
         if (var9 == null) {
            var9 = new ahc(var2, var3, var4);
            this.q.put(var2, var9);
         }

         return var9;
      }
   }
}
