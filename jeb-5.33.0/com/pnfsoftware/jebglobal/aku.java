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
public class aku extends ala implements IEVar {
   @SerId(1)
   private int pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private String kS;
   @SerId(4)
   private Long wS;
   @SerId(5)
   private ICElement UT;

   aku(int var1, String var2, int var3) {
      if (var3 <= 0) {
         throw new IllegalArgumentException("EVar size must be strictly positive");
      } else {
         this.pC = var1;
         this.kS = var2;
         this.A = var3;
      }
   }

   public static IEVar pC(String var0, Long var1, int var2, IWildcardType var3, ICElement var4) {
      if (var2 != 0 && var3 != null) {
         aku var5 = new aku(-1, var0 != null ? var0 : "PSEUDO", var2);
         var5.pC(var1);
         var5.setType(var3);
         var5.pC(var4);
         return var5;
      } else {
         throw new IllegalArgumentException("Pseudo-var must have a valid bitsize and type, got: " + var2 + ", " + var3);
      }
   }

   public static IEVar pC(int var0, IWildcardType var1, ICElement var2) {
      return pC(null, null, var0, var1, var2);
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + this.pC;
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
         aku var3 = (aku)var1;
         if (this.pC != var3.pC) {
            return false;
         } else {
            Assert.a((this.kS == null && var3.kS == null || this.kS != null && this.kS.equals(var3.kS)) && this.A == var3.A);
            return true;
         }
      }
   }

   @Override
   public int getId() {
      return this.pC;
   }

   @Override
   public Collection getIds() {
      ArrayList var1 = new ArrayList();
      this.pC(var1);
      return var1;
   }

   public void pC(Collection var1) {
      if (this.pC >= 0) {
         for (int var2 = this.pC; var2 < this.pC + this.A; var2++) {
            var1.add(var2);
         }
      } else {
         for (int var3 = -this.pC; var3 < -this.pC + this.A; var3++) {
            var1.add(-var3);
         }
      }
   }

   @Override
   public int getBitFromId(int var1) {
      int var2;
      if (this.pC >= 0) {
         var2 = var1 - this.pC;
      } else {
         var2 = -(var1 - this.pC);
      }

      if (var2 >= 0 && var2 < this.A) {
         return var2;
      } else {
         throw new IllegalArgumentException("Cannot retrieve index for bit id: " + var1);
      }
   }

   @Override
   public int getIdFromBit(int var1) {
      if (var1 < 0 || var1 >= this.A) {
         throw new IllegalArgumentException("Cannot retrieve id for bit index: " + var1);
      } else {
         return this.pC >= 0 ? this.pC + var1 : this.pC - var1;
      }
   }

   @Override
   public String getName() {
      return this.kS;
   }

   public void pC(String var1) {
      if (this.isRegister()) {
         throw new IllegalStateException("Name of a register var cannot be changed");
      } else {
         this.kS = var1;
      }
   }

   @Override
   public int getBitsize() {
      return this.A;
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
      return this.pC >= 16777216 && this.pC < Integer.MAX_VALUE ? true : this.pC <= -33554432 && this.pC > -2147483647;
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
      return !this.kS();
   }

   @Override
   public boolean setType(IWildcardType var1, ETypeInfo var2) {
      return this.kS() ? false : super.setType(var1, var2);
   }

   @Override
   public Long getAddress() {
      return this.wS;
   }

   public void pC(Long var1) {
      if (this.kS()) {
         throw new RuntimeException("Cannot assign address to: " + this);
      } else {
         this.wS = var1;
      }
   }

   public aku pC() {
      if (this.pC == -1) {
         throw new RuntimeException("The pseudo-variable cannot be duplicated; create another one.");
      } else {
         return this;
      }
   }

   @Override
   public void copyProperties(IEVar var1) {
      super.pC((aku)var1);
      ((aku)var1).wS = this.wS;
      ((aku)var1).UT = this.UT;
   }

   @Override
   public IEImm evaluate(EState var1) {
      IEImm var2 = var1.getValueSafe(this.pC);
      if (var2 != null) {
         return var2;
      } else if (this.isGlobalReference()) {
         return ajr.pC(this.getAddress(), var1.getRoutineContext().getConverter().getAddressBitsize());
      } else if (var1.isSoftFailMode()) {
         return null;
      } else {
         throw new EvaluationException(Strings.ff("Variable is uninitialized or does not exist: %s", EUtil.formatVars(var1.getRoutineContext(), this.getId())));
      }
   }

   public boolean pC(ICElement var1) {
      if (this.kS()) {
         Assert.debugFail("Cannot assign custom AST to: " + this);
         return false;
      } else {
         this.UT = var1;
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
      if (this.UT != null) {
         return this.UT;
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
                  var7 = ((aki)var1).pC(this.getAddress());
                  var9 = null;
               }
            } else if (this.isGlobalReference()) {
               var8 = var8.substring(4);
               long var17 = var1.getGlobalContext().getNativeContext().getVirtualImageBase();
               long var15 = var1.getGlobalContext().getNativeContext().getImageSize();
               if (this.getAddress() != null && var17 <= this.getAddress() && this.getAddress() < var17 + var15) {
                  var7 = ((ajo)var1.getGlobalContext()).pC(this.getAddress());
               }
            }

            if (!var4) {
               var11 = true;
            }
         }

         Object var18 = pC(
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
            var18 = var2.getElementFactory().createOperation(COperatorType.REF, (ICExpression)var18);
         }

         return (ICElement)var18;
      }
   }

   public static ICLeftExpression pC(
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
   public void pC(akz var1) {
      String var2 = "";
      if (var1.pC()) {
         var2 = var2 + "s" + this.A + ":";
      }

      if (this.kS != null) {
         var2 = var2 + this.kS;
      } else {
         var2 = var2 + "v" + this.pC;
      }

      var1.append(var2, ItemClassIdentifiers.IDENTIFIER);
      var1.A(this);
   }

   public boolean kS() {
      return this.isRegister();
   }

   public static boolean pC(int var0) {
      return var0 >= 0 && var0 < 65536;
   }

   public static boolean A(int var0) {
      return var0 >= 65536 && var0 < 131072;
   }

   public static boolean kS(int var0) {
      return pC(var0) || A(var0);
   }

   public static boolean wS(int var0) {
      return -var0 >= 65536 && -var0 < 1769472;
   }

   public static boolean UT(int var0) {
      return -var0 >= 33554432 && -var0 < Integer.MAX_VALUE;
   }

   public static boolean E(int var0) {
      return var0 >= 16777216 && var0 < Integer.MAX_VALUE;
   }

   public static int pC(int var0, int var1) {
      return var0 >= 0 ? var0 + var1 : var0 - var1;
   }

   public int pC(IVariable var1) {
      return this.wS() - (Integer)var1.getBegin();
   }

   public Integer wS() {
      return Math.abs(this.pC);
   }

   public Integer UT() {
      return Math.abs(this.pC) + this.A;
   }
}
