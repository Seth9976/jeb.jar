package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.EffectiveFinalityType;
import com.pnfsoftware.jeb.core.units.code.android.IDexContextInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexField;
import com.pnfsoftware.jeb.core.units.code.android.ir.DOpcodeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DUtil;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDArrayElt;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ccx {
   private static final ILogger E = bzz.pC;
   IDMethodContext pC;
   IDVar A;
   IDExpression kS;
   IDexContextInfoProvider wS;
   ccx.Av UT;

   public ccx(IDMethodContext var1, IDVar var2, IDExpression var3, IDInstruction var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.wS = var1.getDex().getContextInfoProvider();
   }

   public boolean pC() {
      this.UT = ccx.Av.pC(this.kS);
      if (!this.UT.A().isEmpty()) {
         return true;
      } else {
         for (IDField var2 : this.UT.pC()) {
            if (!this.pC(var2)) {
               return true;
            }
         }

         for (IDCallInfo var6 : this.UT.kS) {
            String var3 = var6.getMethodSignature();
            ContextAccessType var4 = this.wS.getMethodCAT(var3);
            if (!var4.isNoneLike()) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean pC(IDField var1) {
      if (var1.getIndex() == null) {
         return true;
      } else {
         IDexField var2 = this.pC.getDex().getField(var1.getIndex().getValue());
         if (var2.isInternal()) {
            return var2.getData().isFinal();
         } else {
            String var3 = var2.getSignature(false);
            EffectiveFinalityType var4 = this.wS.getFieldEFInfo(var3);
            return var4.isFinalLike();
         }
      }
   }

   public boolean pC(BasicBlock var1, int var2, int var3, boolean var4) {
      ContextAccessType var5 = ContextAccessType.NONE_SAFE;

      for (IDCallInfo var7 : this.UT.kS) {
         ContextAccessType var8 = this.wS.getMethodCAT(var7.getMethodSignature());
         var5 = var5.addAccess(var8);
         if (var5.isAllAccess()) {
            break;
         }
      }

      boolean var10 = !this.UT.wS.isEmpty();

      for (int var11 = var2; var11 < var3; var11++) {
         IDInstruction var13 = (IDInstruction)var1.get(var11);
         if (var13.hasUseSideEffects(true)) {
            return false;
         }

         if (!var5.isNoneLike()) {
            ContextAccessType var9 = DUtil.getCAT(var13, true);
            if (var9.writes()) {
               return false;
            }

            if (var5.writes() && !var9.isNoneLike()) {
               return false;
            }
         }

         if (var13.getOpcode() == DOpcodeType.IR_ASSIGN && var13.getOperand1() instanceof IDField && !this.UT.pC((IDField)var13.getOperand1(), this.wS)) {
            return false;
         }

         if (!var5.isSEF() && DUtil.usesReferences(var13)) {
            return false;
         }

         if (var13.getOpcode() == DOpcodeType.IR_ASSIGN && var13.getOperand1() instanceof IDArrayElt && var10) {
            return false;
         }
      }

      if (var4) {
         IDInstruction var12 = (IDInstruction)var1.get(var3);
         if (!this.pC(var12, this.A, var5)) {
            return false;
         }
      }

      return true;
   }

   private boolean pC(IDInstruction var1, IDVar var2, ContextAccessType var3) {
      return var1.visitInstructionPostOrder(new ccy(this, var2, var3), true);
   }

   static class Av implements IDVisitor {
      List pC = new ArrayList();
      List A = new ArrayList();
      List kS = new ArrayList();
      List wS = new ArrayList();

      public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
         if (var1 instanceof IDStaticField) {
            this.pC.add((IDStaticField)var1);
         } else if (var1 instanceof IDInstanceField) {
            this.A.add((IDInstanceField)var1);
         } else if (var1 instanceof IDCallInfo) {
            this.kS.add((IDCallInfo)var1);
         } else if (var1 instanceof IDArrayElt) {
            this.wS.add(((IDArrayElt)var1).getArray());
         }
      }

      public static ccx.Av pC(IDExpression var0) {
         ccx.Av var1 = new ccx.Av();
         var0.visitDepthPost(var1);
         return var1;
      }

      List pC() {
         ArrayList var1 = new ArrayList(this.pC.size() + this.A.size());
         var1.addAll(this.pC);
         var1.addAll(this.A);
         return Collections.unmodifiableList(var1);
      }

      List A() {
         return this.wS;
      }

      public boolean pC(IDField var1, IDexContextInfoProvider var2) {
         String var3 = var1.getFieldname();
         if (var3 == null) {
            return false;
         } else {
            List var4;
            if (var1 instanceof IDStaticField) {
               var4 = this.pC;
            } else {
               if (!(var1 instanceof IDInstanceField)) {
                  throw new RuntimeException();
               }

               var4 = this.A;
            }

            for (IDField var6 : var4) {
               String var7 = var6.getFieldname();
               if (var7 == null || var3.equals(var7)) {
                  return false;
               }
            }

            return true;
         }
      }
   }
}
