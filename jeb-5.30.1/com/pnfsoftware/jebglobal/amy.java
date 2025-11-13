package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.IVariable;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.EvaluationException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;

@Ser
public class amy extends ane implements IEVar {
   @SerId(1)
   private int q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private String xK;
   @SerId(4)
   private Long Dw;
   @SerId(5)
   private ICElement Uv;

   amy(int var1, String var2, int var3) {
      if (var3 <= 0) {
         throw new IllegalArgumentException("EVar size must be strictly positive");
      } else {
         this.q = var1;
         this.xK = var2;
         this.RF = var3;
      }
   }

   public static IEVar q(String var0, Long var1, int var2, IWildcardType var3, ICElement var4) {
      if (var2 != 0 && var3 != null) {
         amy var5 = new amy(-1, var0 != null ? var0 : "PSEUDO", var2);
         var5.q(var1);
         var5.setType(var3);
         var5.q(var4);
         return var5;
      } else {
         throw new IllegalArgumentException("Pseudo-var must have a valid bitsize and type, got: " + var2 + ", " + var3);
      }
   }

   public static IEVar q(int var0, IWildcardType var1, ICElement var2) {
      return q(null, null, var0, var1, var2);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.q;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         amy var3 = (amy)var1;
         if (this.q != var3.q) {
            return false;
         } else {
            Assert.a((this.xK == null && var3.xK == null || this.xK != null && this.xK.equals(var3.xK)) && this.RF == var3.RF);
            return true;
         }
      }
   }

   @Override
   public int getId() {
      return this.q;
   }

   @Override
   public Collection getIds() {
      ArrayList var1 = new ArrayList();
      this.q(var1);
      return var1;
   }

   public void q(Collection var1) {
      if (this.q >= 0) {
         for (int var2 = this.q; var2 < this.q + this.RF; var2++) {
            var1.add(var2);
         }
      } else {
         for (int var3 = -this.q; var3 < -this.q + this.RF; var3++) {
            var1.add(-var3);
         }
      }
   }

   @Override
   public int getBitFromId(int var1) {
      int var2;
      if (this.q >= 0) {
         var2 = var1 - this.q;
      } else {
         var2 = -(var1 - this.q);
      }

      if (var2 >= 0 && var2 < this.RF) {
         return var2;
      } else {
         throw new IllegalArgumentException("Cannot retrieve index for bit id: " + var1);
      }
   }

   @Override
   public int getIdFromBit(int var1) {
      if (var1 < 0 || var1 >= this.RF) {
         throw new IllegalArgumentException("Cannot retrieve id for bit index: " + var1);
      } else {
         return this.q >= 0 ? this.q + var1 : this.q - var1;
      }
   }

   @Override
   public String getName() {
      return this.xK;
   }

   public void q(String var1) {
      if (this.isRegister()) {
         throw new IllegalStateException("Name of a register var cannot be changed");
      } else {
         this.xK = var1;
      }
   }

   @Override
   public int getBitsize() {
      return this.RF;
   }

   @Override
   public int getPriority() {
      return 90;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      var1.addUsed(this);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      var1.addDefined(this);
   }

   @Override
   public boolean accessesMemory() {
      return this.q >= 16777216 && this.q < Integer.MAX_VALUE ? true : this.q <= -33554432 && this.q > -2147483647;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   @Override
   public boolean isTypeable() {
      return !this.xK();
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2) {
      return this.xK() ? false : super.setType(var1, var2);
   }

   @Override
   public Long getAddress() {
      return this.Dw;
   }

   public void q(Long var1) {
      if (this.xK()) {
         throw new RuntimeException("Cannot assign address to: " + this);
      } else {
         this.Dw = var1;
      }
   }

   public amy q() {
      if (this.q == -1) {
         throw new RuntimeException("The pseudo-variable cannot be duplicated; create another one.");
      } else {
         return this;
      }
   }

   @Override
   public void copyProperties(IEVar var1) {
      super.q((ane)((amy)var1));
      ((amy)var1).Dw = this.Dw;
      ((amy)var1).Uv = this.Uv;
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = var1.getValueSafe(this.q);
      if (var2 != null) {
         return var2;
      } else if (this.isGlobalReference()) {
         return alu.q(this.getAddress(), var1.getRoutineContext().getConverter().getAddressBitsize());
      } else if (var1.isSoftFailMode()) {
         return null;
      } else {
         throw new EvaluationException(Strings.ff("Variable is uninitialized or does not exist: %s", EUtil.formatVars(var1.getRoutineContext(), this.getId())));
      }
   }

   public boolean q(ICElement var1) {
      if (this.xK()) {
         Assert.debugFail("Cannot assign custom AST to: " + this);
         return false;
      } else {
         this.Uv = var1;
         return true;
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      return this.generateC(var1, var2, 0);
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2, int var3) {
      return this.generateC(var1, var2, var3, false);
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2, int var3, boolean var4) {
      if (this.Uv != null) {
         return this.Uv;
      } else {
         CIdentifierClass var5;
         if (this.isStackReference() || this.isStackVariable()) {
            var5 = CIdentifierClass.LOCAL;
         } else if (this.isGlobalReference() || this.isGlobalVariable()) {
            var5 = CIdentifierClass.GLOBAL;
         } else if (this.isRegister()) {
            var5 = CIdentifierClass.BUILTIN;
         } else {
            var5 = CIdentifierClass.SYNTHETIC;
         }

         IWildcardTypeManager var6 = var1.getWildcardTypeManager();
         int var7 = this.getId();
         String var8 = this.getName();
         IWildcardType var9 = this.getSafeType(var6);
         Long var10 = this.getAddress();
         boolean var11 = false;
         boolean var12 = this.isStackReference() || this.isGlobalReference();
         if (var12 && (var4 || var3 == 0)) {
            if (this.isStackReference()) {
               IEVar var13 = var1.getStackManager().getVariable(this.getAddress());
               if (var13 != null) {
                  var8 = var13.getName();
                  var7 = var13.getId();
                  var9 = var13.getSafeType(var6);
                  IWildcardType var14 = var1.getStackManager().getActualStackItemType(var13.getAddress());
                  if (var14 != null) {
                     var9 = var14;
                  }
               } else {
                  var8 = var8.substring(4);
                  var7 = ((aml)var1).q(this.getAddress());
                  var9 = null;
               }
            } else if (this.isGlobalReference()) {
               var8 = var8.substring(4);
               if (this.getAddress() != null) {
                  var7 = ((alr)var1.getGlobalContext()).q(this.getAddress());
               }
            }

            if (!var4) {
               var11 = true;
            }
         }

         Object var15 = q(
            var7,
            var8,
            var9,
            var10,
            var5,
            var2.getTypeFactory(),
            var2.getGlobalContext().getIdentifierManager(),
            var2.getIdentifierManager(),
            var2.getIndex(),
            var3
         );
         if (var11) {
            var15 = var2.getElementFactory().createOperation(COperatorType.REF, (ICExpression)var15);
         }

         return (ICElement)var15;
      }
   }

   public static ICLeftExpression q(
      int var0,
      String var1,
      IWildcardType var2,
      Long var3,
      CIdentifierClass var4,
      ICTypeFactory var5,
      ICIdentifierManager var6,
      ICIdentifierManager var7,
      int var8,
      int var9
   ) {
      Integer var10 = null;
      if (var9 >= 2) {
         var10 = var9 - 2;
      }

      ICType var11 = var5.create(var2);
      if (var4 == null) {
         var4 = CIdentifierClass.SYNTHETIC;
      }

      if (var3 == null && (var4 == CIdentifierClass.LOCAL || var4 == CIdentifierClass.GLOBAL)) {
         var4 = CIdentifierClass.SYNTHETIC;
      }

      ICIdentifierManager var12;
      if (var4.isLocal()) {
         var12 = var7;
      } else {
         var12 = var6;
      }

      if (var0 >= 0) {
         var9--;
      }

      ICDecl var13 = var12.getDeclaration(var0);
      if (var13 != null) {
         return var13.getIdentifier();
      } else {
         var13 = var12.create(var0, var11, var1, var4, var8, var3, var10);
         return (ICLeftExpression)(var9 <= 0 ? var13.getIdentifier() : var13);
      }
   }

   @Override
   public void q(and var1) {
      String var2 = "";
      if (var1.q()) {
         var2 = var2 + "s" + this.RF + ":";
      }

      if (this.xK != null) {
         var2 = var2 + this.xK;
      } else {
         var2 = var2 + "v" + this.q;
      }

      var1.append(var2, ItemClassIdentifiers.IDENTIFIER);
      var1.RF(this);
   }

   public boolean xK() {
      return this.isRegister();
   }

   public static boolean q(int var0) {
      return var0 >= 0 && var0 < 65536;
   }

   public static boolean RF(int var0) {
      return var0 >= 65536 && var0 < 131072;
   }

   public static boolean xK(int var0) {
      return q(var0) || RF(var0);
   }

   public static boolean Dw(int var0) {
      return -var0 >= 65536 && -var0 < 196608;
   }

   public static boolean Uv(int var0) {
      return -var0 >= 65536 && -var0 < 1769472;
   }

   public static boolean oW(int var0) {
      return xK(var0);
   }

   public static boolean gO(int var0) {
      return -var0 >= 33554432 && -var0 < Integer.MAX_VALUE;
   }

   public static boolean nf(int var0) {
      return -var0 >= 8388608 && -var0 < 8454144;
   }

   public static boolean gP(int var0) {
      return var0 >= 16777216 && var0 < Integer.MAX_VALUE;
   }

   public static boolean za(int var0) {
      return var0 >= 131072 && var0 < 196608;
   }

   public static int q(int var0, int var1) {
      return var0 >= 0 ? var0 + var1 : var0 - var1;
   }

   public static int lm(int var0) {
      return q(var0, 1);
   }

   public static int zz(int var0) {
      return q(var0, -1);
   }

   public int q(IVariable var1) {
      return this.Dw() - (Integer)var1.getBegin();
   }

   public Integer Dw() {
      return Math.abs(this.q);
   }

   public Integer Uv() {
      return Math.abs(this.q) + this.RF;
   }
}
