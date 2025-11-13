package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstanceField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import java.util.Map;

class brh implements IDVisitor {
   brh(brg var1, Map var2, DCopyOptions var3) {
      this.kS = var1;
      this.pC = var2;
      this.A = var3;
   }

   public void pC(IDExpression var1, IDExpression var2, IVisitResults var3) {
      IJavaType var4 = var1.getType();
      if (!var4.isSmallInt() && !var4.isLong() && !var4.isBoolean()) {
         Object[] var19 = new Object[]{var4, var1};
         var3.interrupt(false);
      } else {
         JavaOperatorType var13;
         label77: {
            if (var1 instanceof IDImm) {
               this.kS.vP++;
            } else if (var1 instanceof IDVar var6) {
               this.kS.xC.add(var6.getId());
            } else if (var1 instanceof IDField var7) {
               if (var1 instanceof IDStaticField var9) {
                  if (var9.isTypeClass()) {
                     Object[] var10000 = new Object[]{var1};
                     var3.interrupt(false);
                     return;
                  }
               } else {
                  if (!(var1 instanceof IDInstanceField var8)) {
                     var3.interrupt(false);
                     return;
                  }

                  if (var8.isArrayLength()) {
                     Object[] var15 = new Object[]{var1};
                     var3.interrupt(false);
                     return;
                  }
               }

               IDVar var12 = (IDVar)this.pC.get(var7);
               if (var12 == null) {
                  var12 = ((bpx)this.kS.ys).retrieveTemporaryVariable(var7.getType(), this.kS.ED++);
                  this.pC.put(var7, var12);
                  this.kS.Sc.put(var12, var7);
               }

               this.A.replmap_eq.put(var7, var12);
               var3.skipChildren();
            } else if (var1 instanceof IDOperation var10) {
               var13 = var10.getOperatorType();
               switch (var13) {
                  case DIV:
                  case REM:
                     if (!brg.kS(var10.getOperand2())) {
                        break label77;
                     }
                  case ADD:
                  case SUB:
                  case MUL:
                  case NEG:
                  case NOT:
                  case AND:
                  case OR:
                  case XOR:
                  case SHL:
                  case SHR:
                  case USHR:
                  case LOG_OR:
                  case LOG_AND:
                  case LOG_NOT:
                  case GT:
                  case GE:
                  case LT:
                  case LE:
                  case EQ:
                  case NE:
                     this.kS.NS++;
                     break;
                  default:
                     break label77;
               }
            } else {
               if (!(var1 instanceof IDCallInfo var5)) {
                  Object[] var18 = new Object[]{var1};
                  var3.interrupt(false);
                  return;
               }

               if (var5.hasSideEffects(this.kS.ys, false)) {
                  Object[] var17 = new Object[]{var1};
                  var3.interrupt(false);
                  return;
               }

               IDExpression var11 = null;
               if (this.kS.fI) {
                  var11 = bpl.pC(this.kS.ys, var5, 30, 5, true);
               }

               if (var11 != null && bpl.pC(var11, null, Integer.MAX_VALUE)) {
                  this.A.replmap_id.put(var5, var11);
               } else {
                  IDVar var14 = ((bpx)this.kS.ys).retrieveTemporaryVariable(var4, this.kS.ED++);
                  if (brg.pC(var5.getMethodSignature())) {
                     this.kS.ah.add(var14.getId());
                  }

                  this.A.replmap_id.put(var5, var14);
                  this.kS.Sc.put(var14, var5);
               }

               var3.skipChildren();
            }

            return;
         }

         Object[] var16 = new Object[]{var13, var1};
         var3.interrupt(false);
      }
   }
}
