package com.pnfsoftware.jebglobal;

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
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class asx {
   private static final ILogger pC = GlobalLog.getLogger(asx.class);
   private List A = new ArrayList();
   private StringBuilder kS = new StringBuilder();

   public void pC(IEGeneric var1) {
      if (var1 instanceof IEStatement var3) {
         this.pC(var3);
      } else if (var1 instanceof IECompose var4) {
         this.pC(var4);
      } else if (var1 instanceof IECond var5) {
         this.pC(var5);
      } else if (var1 instanceof IEImm var6) {
         this.pC(var6);
      } else if (var1 instanceof IEMem var7) {
         this.pC(var7);
      } else if (var1 instanceof akc var8) {
         this.pC((IEOperation)var8);
      } else if (var1 instanceof IESlice var9) {
         this.pC(var9);
      } else {
         if (!(var1 instanceof IEVar var2)) {
            throw new asz();
         }

         this.pC(var2);
      }
   }

   public void pC(IECompose var1) {
      this.kS.append("(concat");

      for (int var2 = var1.getPartsCount() - 1; var2 >= 0; var2--) {
         IEGeneric var3 = var1.getPart(var2);
         this.kS.append(" ");
         if (EUtil.isLogicalOperation(var3)) {
            this.A(var3);
         } else {
            this.pC(var3);
         }
      }

      this.kS.append(")");
   }

   public void A(IEGeneric var1) {
      this.kS.append("(ite ");
      this.pC(var1);
      this.kS.append(" #b1 #b0)");
   }

   public void pC(IECond var1) {
      if (!EUtil.isLogicalOperation(var1.getCondition())) {
         var1 = EUtil.buildStrictLogicalECond(var1);
      }

      this.kS.append("(ite ");
      this.pC(var1.getCondition());
      this.kS.append(" ");
      this.pC(var1.getExpressionTrue());
      this.kS.append(" ");
      this.pC(var1.getExpressionFalse());
      this.kS.append(")");
   }

   public void pC(IEImm var1) {
      this.kS.append(this.pC(var1.getUnsignedValue().toString(), var1.getBitsize()));
   }

   public void pC(IEMem var1) {
      if (!this.A.contains(var1)) {
         this.A.add(var1);
      }

      this.kS.append(this.kS(var1));
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void pC(IEOperation var1) {
      switch (var1.getOperationType()) {
         case ADD:
            this.kS.append("(bvadd ");
            this.A(var1);
            this.kS.append(")");
            break;
         case SUB:
            this.kS.append("(bvsub ");
            this.A(var1);
            this.kS.append(")");
            break;
         case MUL:
            this.kS.append("(bvmul ");
            this.A(var1);
            this.kS.append(")");
            break;
         case DIV_S:
            this.kS.append("(bvsdiv ");
            this.A(var1);
            this.kS.append(")");
            break;
         case DIV_U:
            this.kS.append("(bvudiv ");
            this.A(var1);
            this.kS.append(")");
            break;
         case REM_S:
            this.kS.append("(bvsrem ");
            this.A(var1);
            this.kS.append(")");
            break;
         case REM_U:
            this.kS.append("(bvurem ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LOG_NOT:
            if (!EUtil.isLogicalOperation(var1.getOperand1())) {
               var1 = EUtil.buildStrictLogicalOperation(var1);
            }

            this.kS.append("(not ");
            this.pC(var1.getOperand1());
            this.kS.append(")");
            break;
         case LOG_EQ:
            if (EUtil.isLogicalOperation(var1.getOperand1()) && !EUtil.isLogicalOperation(var1.getOperand2())
               || !EUtil.isLogicalOperation(var1.getOperand1()) && EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildLogicalOperation(var1);
            }

            this.kS.append("(= ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LOG_NEQ:
            if (EUtil.isLogicalOperation(var1.getOperand1()) && !EUtil.isLogicalOperation(var1.getOperand2())
               || !EUtil.isLogicalOperation(var1.getOperand1()) && EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildLogicalOperation(var1);
            }

            this.kS.append("(not (= ");
            this.A(var1);
            this.kS.append("))");
            break;
         case LOG_OR:
            if (!EUtil.isLogicalOperation(var1.getOperand1()) || !EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildStrictLogicalOperation(var1);
            }

            this.kS.append("(or ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LOG_AND:
            if (!EUtil.isLogicalOperation(var1.getOperand1()) || !EUtil.isLogicalOperation(var1.getOperand2())) {
               var1 = EUtil.buildStrictLogicalOperation(var1);
            }

            this.kS.append("(and ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LE_S:
            this.kS.append("(bvsle ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LT_S:
            this.kS.append("(bvslt ");
            this.A(var1);
            this.kS.append(")");
            break;
         case GE_S:
            this.kS.append("(bvsge ");
            this.A(var1);
            this.kS.append(")");
            break;
         case GT_S:
            this.kS.append("(bvsgt ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LE_U:
            this.kS.append("(bvule ");
            this.A(var1);
            this.kS.append(")");
            break;
         case LT_U:
            this.kS.append("(bvult ");
            this.A(var1);
            this.kS.append(")");
            break;
         case GE_U:
            this.kS.append("(bvuge ");
            this.A(var1);
            this.kS.append(")");
            break;
         case GT_U:
            this.kS.append("(bvugt ");
            this.A(var1);
            this.kS.append(")");
            break;
         case AND:
            this.kS.append("(bvand ");
            this.A(var1);
            this.kS.append(")");
            break;
         case OR:
            this.kS.append("(bvor ");
            this.A(var1);
            this.kS.append(")");
            break;
         case NOT:
            this.kS.append("(bvnot ");
            this.pC(var1.getOperand1());
            this.kS.append(")");
            break;
         case XOR:
            this.kS.append("(bvxor ");
            this.A(var1);
            this.kS.append(")");
            break;
         case SHL:
            this.kS.append("(bvshl ");
            this.kS(var1);
            this.kS.append(")");
            break;
         case SHR:
            this.kS.append("(bvlshr ");
            this.kS(var1);
            this.kS.append(")");
            break;
         case SAR:
            this.kS.append("(bvashr ");
            this.kS(var1);
            this.kS.append(")");
            break;
         default:
            throw new asz("No translation available for Operation: " + var1.getOperationType());
      }
   }

   public void pC(IEVar var1) {
      if (!this.A.contains(var1)) {
         this.A.add(var1);
      }

      this.kS.append(this.kS(var1));
   }

   public void pC(IESlice var1) {
      this.kS.append("((_ extract ");
      this.kS.append(var1.getBitEnd() - 1);
      this.kS.append(" ");
      this.kS.append(var1.getBitStart());
      this.kS.append(") ");
      this.pC(var1.getWholeExpression());
      this.kS.append(")");
   }

   public void pC(IEStatement var1) {
      if (var1 instanceof IEAssign var3) {
         IEGeneric var4 = var3.getDstOperand();
         IEGeneric var5 = var3.getSrcOperand();
         if (var4.getBitsize() != var5.getBitsize()) {
            throw new asz("Cannot translate assign with different sizes");
         }

         this.kS.append("(= ");
         this.pC(var4);
         this.kS.append(" ");
         this.pC(var5);
         this.kS.append(")");
      } else {
         if (!(var1 instanceof IEReturn var2)) {
            throw new asz("Cannot translate statement: %s" + var1);
         }

         if (var2.getValue() != null) {
            this.pC(var2.getValue());
         }
      }
   }

   private void A(IEOperation var1) {
      this.pC(var1.getOperand1());
      this.kS.append(" ");
      this.pC(var1.getOperand2());
   }

   private void kS(IEOperation var1) {
      IEGeneric var2 = var1.getOperand1();
      IEGeneric var3 = var1.getOperand2();
      int var4 = var1.getBitsize();
      Assert.a(var4 == var2.getBitsize());
      if (var4 == var3.getBitsize()) {
         this.A(var1);
      } else if (var4 > var3.getBitsize()) {
         this.pC(var2);
         this.kS.append(" ");
         this.pC(var3.zeroExtend(var4));
      } else {
         this.pC(var2);
         this.kS.append(" ");
         this.pC(var3.part(var4));
      }
   }

   private String pC(String var1, int var2) {
      return Strings.ff("(_ bv%s %d)", var1, var2);
   }

   private String kS(IEGeneric var1) {
      return Strings.ff("v%d", this.A.indexOf(var1));
   }

   public String[] pC() {
      StringBuilder var1 = new StringBuilder();

      for (IEGeneric var3 : this.A) {
         Strings.ff(var1, "(declare-const %s (_ BitVec %d))\n", this.kS(var3), var3.getBitsize());
      }

      String var4 = Strings.ff("%s(assert (and %s))\n", var1, this.kS);
      String var5 = Strings.ff("%s(assert (and (not %s)))\n", var1, this.kS);
      return new String[]{var4, var5};
   }
}
