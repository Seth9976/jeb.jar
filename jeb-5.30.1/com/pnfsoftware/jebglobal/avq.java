package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UntranslatedIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class avq extends alh {
   private List q = Lists.createArrayList();
   private StringBuilder RF = new StringBuilder();

   public avq() {
   }

   public avq(avq var1) {
      for (IEGeneric var3 : var1.q()) {
         this.q.add(var3);
      }

      this.RF = new StringBuilder(var1.RF().toString());
   }

   public List q() {
      return this.q;
   }

   public String RF(IEGeneric var1) {
      return "v" + this.q.indexOf(var1);
   }

   public StringBuilder RF() {
      return this.RF;
   }

   @Override
   public void q(IECompose var1) {
      this.RF.append("(concat");
      ListIterator var2 = new ArrayList(var1.getParts()).listIterator(var1.getPartsCount());

      while (var2.hasPrevious()) {
         IEGeneric var3 = (IEGeneric)var2.previous();
         this.RF.append(" ");
         if (EUtil.isLogicalOperation(var3)) {
            this.xK(var3);
         } else {
            this.q(var3);
         }
      }

      this.RF.append(")");
   }

   public void xK(IEGeneric var1) {
      this.RF.append("(ite ");
      this.q(var1);
      this.RF.append(" #b1 #b0)");
   }

   @Override
   public void q(IECond var1) {
      if (!EUtil.isLogicalOperation(var1.getCondition())) {
         var1 = EUtil.buildStrictLogicalECond(var1);
      }

      this.RF.append("(ite ");
      this.q(var1.getCondition());
      this.RF.append(" ");
      this.q(var1.getExpressionTrue());
      this.RF.append(" ");
      this.q(var1.getExpressionFalse());
      this.RF.append(")");
   }

   @Override
   public void q(IEImm var1) {
      this.RF.append(this.q(var1.getUnsignedValue().toString(), var1.getBitsize()));
   }

   @Override
   public void q(IEMem var1) {
      if (!this.q.contains(var1)) {
         this.q.add(var1);
      }

      this.RF.append(this.Dw(var1));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void q(IEOperation var1) {
      switch (avr.q[var1.getOperationType().ordinal()]) {
         case 1:
            this.RF.append("(bvadd ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 2:
            this.RF.append("(bvsub ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 3:
            this.RF.append("(bvmul ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 4:
            this.RF.append("(bvsdiv ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 5:
            this.RF.append("(bvudiv ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 6:
            this.RF.append("(bvsrem ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 7:
            this.RF.append("(bvurem ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 8:
            if (!EUtil.isLogicalOperation(var1.getOperand1())) {
               var1 = EUtil.buildStrictLogicalOperation(var1);
            }

            this.RF.append("(not ");
            this.q(var1.getOperand1());
            this.RF.append(")");
            break;
         case 9:
            if (EUtil.isLogicalOperation(var1.getOperand1()) && !EUtil.isLogicalOperation(var1.getOperand2())
               || !EUtil.isLogicalOperation(var1.getOperand1()) && EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildLogicalOperation(var1);
            }

            this.RF.append("(= ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 10:
            if (EUtil.isLogicalOperation(var1.getOperand1()) && !EUtil.isLogicalOperation(var1.getOperand2())
               || !EUtil.isLogicalOperation(var1.getOperand1()) && EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildLogicalOperation(var1);
            }

            this.RF.append("(not (= ");
            this.RF(var1);
            this.RF.append("))");
            break;
         case 11:
            if (!EUtil.isLogicalOperation(var1.getOperand1()) || !EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildStrictLogicalOperation(var1);
            }

            this.RF.append("(or ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 12:
            if (!EUtil.isLogicalOperation(var1.getOperand1()) || !EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildStrictLogicalOperation(var1);
            }

            this.RF.append("(and ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 13:
            this.RF.append("(bvsle ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 14:
            this.RF.append("(bvslt ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 15:
            this.RF.append("(bvsge ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 16:
            this.RF.append("(bvsgt ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 17:
            this.RF.append("(bvule ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 18:
            this.RF.append("(bvult ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 19:
            this.RF.append("(bvuge ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 20:
            this.RF.append("(bvugt ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 21:
            this.RF.append("(bvand ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 22:
            this.RF.append("(bvor ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 23:
            this.RF.append("(bvnot ");
            this.q(var1.getOperand1());
            this.RF.append(")");
            break;
         case 24:
            this.RF.append("(bvxor ");
            this.RF(var1);
            this.RF.append(")");
            break;
         case 25:
            this.RF.append("(bvshl ");
            this.xK(var1);
            this.RF.append(")");
            break;
         case 26:
            this.RF.append("(bvlshr ");
            this.xK(var1);
            this.RF.append(")");
            break;
         case 27:
            this.RF.append("(bvashr ");
            this.xK(var1);
            this.RF.append(")");
            break;
         default:
            throw new UntranslatedIntermediateExpressionException("No translation available for EOperation (%s)", var1.getOperationType());
      }
   }

   @Override
   public void q(IEVar var1) {
      if (!this.q.contains(var1)) {
         this.q.add(var1);
      }

      this.RF.append(this.Dw(var1));
   }

   @Override
   public void q(IESlice var1) {
      this.RF.append("((_ extract ");
      this.RF.append(var1.getBitEnd() - 1);
      this.RF.append(" ");
      this.RF.append(var1.getBitStart());
      this.RF.append(") ");
      this.q(var1.getWholeExpression());
      this.RF.append(")");
   }

   @Override
   public void q(IEStatement var1) {
      if (var1 instanceof IEAssign) {
         IEGeneric var2 = ((IEAssign)var1).getDstOperand();
         IEGeneric var3 = ((IEAssign)var1).getSrcOperand();
         if (var2.getBitsize() != var3.getBitsize()) {
            throw new UntranslatedIntermediateExpressionException("No translation available for EAssign with different sizes");
         }

         this.RF.append("(= ");
         this.q(var2);
         this.RF.append(" ");
         this.q(var3);
         this.RF.append(")");
      } else {
         if (!(var1 instanceof IEReturn)) {
            throw new UntranslatedIntermediateExpressionException("No translation available for such EStatement (%s)", var1);
         }

         if (((IEReturn)var1).getValue() != null) {
            this.q(((IEReturn)var1).getValue());
         }
      }
   }

   private void RF(IEOperation var1) {
      this.q(var1.getOperand1());
      this.RF.append(" ");
      this.q(var1.getOperand2());
   }

   private void xK(IEOperation var1) {
      IEGeneric var2 = var1.getOperand1();
      IEGeneric var3 = var1.getOperand2();
      int var4 = var1.getBitsize();
      Assert.a(var4 == var2.getBitsize());
      if (var4 == var3.getBitsize()) {
         this.RF(var1);
      } else if (var4 > var3.getBitsize()) {
         this.q(var2);
         this.RF.append(" ");
         this.q(var3.zeroExtend(var4));
      } else {
         this.q(var2);
         this.RF.append(" ");
         this.q(var3.part(var4));
      }
   }

   private void q(IEGeneric var1, int var2) {
      if (var2 == 0) {
         throw new IllegalArgumentException("Invalid number of bit to extend");
      } else {
         this.RF.append("(concat ");
         this.RF.append(this.q("0", var2));
         this.RF.append(" ");
         this.q(var1);
         this.RF.append(")");
      }
   }

   private String q(String var1, int var2) {
      return Strings.ff("(_ bv%s %d)", var1, var2);
   }

   private String Dw(IEGeneric var1) {
      return Strings.ff("v%d", this.q.indexOf(var1));
   }

   public String xK() {
      StringBuilder var1 = new StringBuilder();

      for (IEGeneric var3 : this.q) {
         Strings.ff(var1, "(declare-const %s (_ BitVec %d))\n", this.Dw(var3), var3.getBitsize());
      }

      var1.append("(assert ");
      var1.append("(and ");
      var1.append(this.RF.toString());
      var1.append(")");
      var1.append(")\n");
      return var1.toString();
   }
}
