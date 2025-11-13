package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.INativeDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.SourceCustomizerAdapter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CKeyword;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstant;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantIntegerLarge;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.format.Strings;
import java.math.BigInteger;
import java.util.List;

public class ST extends SourceCustomizerAdapter {
   INativeCodeUnit q;
   INativeDecompilerUnit RF;
   PY xK;
   private BigInteger Dw = BigInteger.ONE.shiftLeft(160).subtract(BigInteger.ONE);

   public ST(INativeDecompilerUnit var1) {
      this.RF = var1;
      this.q = (INativeCodeUnit)var1.getParent();
      this.xK = (PY)this.q.getParent();
   }

   @Override
   public boolean generateClassDeclarationLine(ICClass var1, COutputSink var2) {
      var2.appendKeyword("contract");
      var2.space();
      var1.getClasstype().generate(var2);
      if (!var1.getSupertypes().isEmpty()) {
         var2.space();
         var2.appendKeyword("is");
         var2.space();
         int var3 = 0;

         for (ICType var5 : var1.getSupertypes()) {
            if (var3 > 0) {
               var2.appendParameterSeparator();
            }

            var5.generate(var2);
            var3++;
         }
      }

      return true;
   }

   @Override
   public boolean preFieldsGeneration(ICClass var1, COutputSink var2) {
      return true;
   }

   @Override
   public boolean preMethodsGeneration(ICClass var1, COutputSink var2) {
      return true;
   }

   @Override
   public boolean generateFieldDeclarationLine(ICField var1, COutputSink var2) {
      return super.generateFieldDeclarationLine(var1, var2);
   }

   @Override
   public boolean generateMethodDeclarationLine(ICMethod var1, COutputSink var2) {
      var2.appendKeyword("function");
      var2.space();
      var1.generateName(var2, true);
      byte var3 = 0;
      List var4 = var1.getParameters();
      var2.append("(");

      for (int var5 = var3; var5 < var4.size(); var5++) {
         if (var5 > var3) {
            var2.appendParameterSeparator();
         }

         ((ICDecl)var4.get(var5)).generate(var2);
      }

      var2.append(")");
      int var9 = var1.getIndex();
      INativeMethodItem var6 = this.q.getMethodByIndex(var9);
      if (var6 != null && var6.getMemoryAddress() != null) {
         qx var7 = this.xK.xK(var6.getMemoryAddress());
         if (var7 != null) {
            if (var7 instanceof GA) {
               var2.space();
               var2.appendKeyword(CKeyword.PUBLIC);
               qx.eo var8 = var7.gP();
               if (var8 == null) {
                  var2.space();
                  var2.appendCommentAuto("/* State mutability undetermined -> using most permissive */");
                  var2.space();
                  var2.appendKeyword("payable");
               } else {
                  switch (var8) {
                     case q:
                        var2.space();
                        var2.appendKeyword("pure");
                        break;
                     case RF:
                        var2.space();
                        var2.appendKeyword("view");
                     case xK:
                        break;
                     case Dw:
                        var2.space();
                        var2.appendKeyword("payable");
                        break;
                     default:
                        throw new RuntimeException();
                  }
               }
            } else {
               if (!var7.RF()) {
                  var2.space();
                  var2.appendKeyword(CKeyword.PRIVATE);
               }

               qx.CU var11 = var7.gO();
               if (var11 != null) {
                  switch (var11) {
                     case q:
                        var2.space();
                        var2.appendKeyword("pure");
                        break;
                     case RF:
                        var2.space();
                        var2.appendKeyword("view");
                     case xK:
                        break;
                     default:
                        throw new RuntimeException();
                  }
               }
            }

            if (Boolean.TRUE.equals(var7.nf())) {
               var2.space();
               var2.appendComment("/*NON-PAYABLE*/");
            }
         }
      }

      ICType var10 = var1.getReturnType();
      if (var10 != null && !Strings.equals("void", var10.toString())) {
         var2.space();
         var2.appendKeyword("returns");
         var2.space();
         var2.append("(");
         var10.generate(var2);
         var2.append(")");
      }

      return true;
   }

   @Override
   public boolean generateOperation(ICOperation var1, COutputSink var2) {
      if (var1.getOperator().isCustom() && Strings.equals(var1.getOperator().toString(), "__pow__")) {
         var1.getFirstOperand().generate(var2);
         var2.space();
         var2.append("**");
         var2.space();
         var1.getSecondOperand().generate(var2);
         return true;
      } else if (var1.getOperatorType() == COperatorType.SIZEOF) {
         var1.getFirstOperand().generate(var2);
         var2.append(".length");
         return true;
      } else {
         if (var1.getOperatorType() == COperatorType.AND && var1.getSecondOperand() instanceof ICConstantIntegerLarge) {
            Object var3 = ((ICConstantIntegerLarge)var1.getSecondOperand()).getValue();
            if (this.Dw.equals(var3)) {
               var2.append("address(");
               var1.getFirstOperand().generate(var2);
               var2.append(")");
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public boolean generateNativeStatement(ICCustomStatement var1, COutputSink var2) {
      String var3 = var1.getCommandName();
      if (Strings.equals(var3, "sstore")) {
         var2.append("storage");
         var2.append("[");
         ((ICElement)var1.getInputElements().get(0)).generate(var2);
         var2.append("]");
         var2.append(" = ");
         ((ICElement)var1.getInputElements().get(1)).generate(var2);
         return true;
      } else if (Strings.equals(var3, "sload")) {
         ((ICElement)var1.getOutputElements().get(0)).generate(var2);
         var2.append(" = ");
         var2.append("storage");
         var2.append("[");
         ((ICElement)var1.getInputElements().get(0)).generate(var2);
         var2.append("]");
         return true;
      } else {
         return false;
      }
   }

   @Override
   public String customizeRenderedConstant(ICConstant var1, String var2) {
      return var1 instanceof ICConstantInteger && var2.endsWith("X") ? var2.substring(0, var2.length() - 1) : null;
   }
}
