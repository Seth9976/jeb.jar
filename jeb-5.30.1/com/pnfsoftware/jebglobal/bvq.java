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

class bvq implements IDVisitor {
   bvq(bvp var1, Map var2, DCopyOptions var3) {
      this.xK = var1;
      this.q = var2;
      this.RF = var3;
   }

   public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
      IJavaType var4 = var1.getType();
      if (!var4.isSmallInt() && !var4.isLong() && !var4.isBoolean()) {
         Object[] var19 = new Object[]{var4, var1};
         var3.interrupt(false);
      } else {
         JavaOperatorType var13;
         label77: {
            if (var1 instanceof IDImm) {
               this.xK.io++;
            } else if (var1 instanceof IDVar var6) {
               this.xK.qa.add(var6.getId());
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

               IDVar var12 = (IDVar)this.q.get(var7);
               if (var12 == null) {
                  var12 = ((bud)this.xK.gP).retrieveTemporaryVariable(var7.getType(), this.xK.Hk++);
                  this.q.put(var7, var12);
                  this.xK.Me.put(var12, var7);
               }

               this.RF.replmap_eq.put(var7, var12);
               var3.skipChildren();
            } else if (var1 instanceof IDOperation var10) {
               var13 = var10.getOperatorType();
               switch (var13) {
                  case DIV:
                  case REM:
                     if (!bvp.Dw(var10.getOperand2())) {
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
                     this.xK.LK++;
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

               if (var5.hasSideEffects(this.xK.gP, false)) {
                  Object[] var17 = new Object[]{var1};
                  var3.interrupt(false);
                  return;
               }

               IDExpression var11 = null;
               if (this.xK.JY) {
                  var11 = bto.q(this.xK.gP, var5, 30, 5, true);
               }

               if (var11 != null && bto.q(var11, null, Integer.MAX_VALUE)) {
                  this.RF.replmap_id.put(var5, var11);
               } else {
                  IDVar var14 = ((bud)this.xK.gP).retrieveTemporaryVariable(var4, this.xK.Hk++);
                  if (bvp.q(var5.getMethodSignature())) {
                     this.xK.PV.add(var14.getId());
                  }

                  this.RF.replmap_id.put(var5, var14);
                  this.xK.Me.put(var14, var5);
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
