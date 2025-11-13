package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.render.NumberFormatter;
import java.math.BigInteger;

public class apc {
   private IESlice q;

   public apc(IESlice var1) {
      this.q = var1;
   }

   public IEVar q(IERoutineContext var1, ICMethod var2) {
      IWildcardTypeManager var3 = var1.getWildcardTypeManager();
      ICTypeFactory var4 = var2.getTypeFactory();
      ICConstantFactory var5 = var2.getConstantFactory();
      ICElementFactory var6 = var2.getElementFactory();
      IEGeneric var7 = this.q.getWholeExpression();
      int var8 = this.q.getBitStart();
      int var9 = this.q.getBitsize();
      boolean var10 = var8 > 0;
      boolean var11 = false;
      boolean var12 = false;
      IWildcardType var13 = null;
      if (EUtil.hasTypeInfo(this.q) && this.q.getType().getEffectiveBitsize() > 0) {
         var13 = this.q.getType().resolveA();
         int var14 = var13.getEffectiveBitsize();
         var11 = var14 != var7.getBitsize();
         var12 = var14 != this.q.getBitsize();
      }

      if (var13 == null) {
         var13 = var7.getSafeType(var3);
         var13 = var13.updateProperties(this.q.getType());
         var11 = var13.getGroup() != null && var13.getGroup() != IWildcardType.Group.INTEGER;
         var12 = true;
         var13 = var13.resolveA();
      }

      Object var19 = (ICExpression)var7.generateC(var1, var2);
      if (var10) {
         var19 = var6.createOperation(COperatorType.USHR, (ICExpression)var19, var5.createInt(BigInteger.valueOf(var8), var7.getBitsize()));
      }

      if (var12) {
         IEImm var15 = EUtil.mask(var7.getBitsize(), var9);
         ICConstantInteger var16 = var5.createInt(var15.getUnsignedValue(), var7.getBitsize());
         var16.getFormatter().setBase(NumberFormatter.NumberBase.HEXADECIMAL);
         var19 = var6.createOperation(COperatorType.AND, (ICExpression)var19, var16);
      }

      if (var11) {
         ICType var20 = var4.create(var13);
         var19 = var6.createCast(var20, (ICExpression)var19);
      }

      return amy.q(var13.getBitsize(), var13, (ICElement)var19);
   }
}
