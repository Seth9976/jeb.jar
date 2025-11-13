package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.base.Assert;

public class alp {
   private IECompose pC;

   public alp(IECompose var1) {
      this.pC = var1;
   }

   public IEVar pC(IERoutineContext var1, ICMethod var2) {
      IWildcardTypeManager var3 = var1.getWildcardTypeManager();
      if (this.pC.getBitsize() <= 64 && this.pC.getPartsCount() == 2) {
         IEGeneric var4 = this.pC.getHighPart();
         if (var4 instanceof IEImm && ((IEImm)var4).getValueAsLong() == 0L) {
            IEGeneric var5 = this.pC.getLowPart();
            Assert.a(!(var5 instanceof IEImm));
            IWildcardType var6 = this.pC.getSafeType(var3);
            if (!var6.isDefined()) {
               var6 = var6.updateEffectiveBitsize(this.pC.getBitsize()).updateGroup(IWildcardType.Group.UINT);
            }

            ICTypeFactory var7 = var2.getGlobalContext().getTypeFactory();
            ICElementFactory var8 = var2.getElementFactory();
            ICExpression var9 = (ICExpression)var5.generateC(var1, var2);
            ICType var10 = var7.create(var6);
            ICOperation var11 = var8.createCast(var10, var9);
            return aku.pC(var6.getEffectiveBitsize(), var6, var11);
         }
      }

      return null;
   }
}
