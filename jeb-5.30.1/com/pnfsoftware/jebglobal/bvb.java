package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVisitor;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;
import com.pnfsoftware.jeb.util.base.Assert;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.Map;

public class bvb {
   private static final Map q = new IdentityHashMap();
   private IDMethodContext RF;
   private IDGlobalContext xK;
   private boolean Dw;
   private bvb.eo Uv;

   public bvb(IDMethodContext var1) {
      this(var1, false);
   }

   public bvb(IDMethodContext var1, boolean var2) {
      this.RF = var1;
      this.xK = var1.getGlobalContext();
      this.Dw = var2;
      this.Uv = new bvb.eo();
   }

   public IDExpression q(IDExpression var1) {
      bvb.CU var2 = new bvb.CU();
      var1.visitDepthPost(var2);
      if (var2.q == 0) {
         return null;
      } else {
         return var2.RF != null ? var2.RF : var1;
      }
   }

   private IDExpression RF(IDExpression var1) {
      return var1 instanceof IDOperation ? this.q((IDOperation)var1) : null;
   }

   private IDExpression q(IDOperation var1) {
      JavaOperatorType var2 = var1.getOperator().getOperatorType();
      switch (var2) {
         case ADD:
         case MUL:
         case AND:
         case OR:
         case XOR:
         case EQ:
         case NE:
         case GT:
         case GE:
         case LT:
         case LE:
            IDExpression var3 = var1.getLeft();
            IDExpression var4 = var1.getRight();
            if (var3.hasSideEffects(this.RF, false) && var4.hasSideEffects(this.RF, false)) {
               return null;
            } else {
               switch (var2) {
                  case ADD:
                  case MUL:
                     if (this.Uv.q(var3, var4) > 0) {
                        return this.xK.createOperation(var1.getType(), var2, var4, var3);
                     }
                     break;
                  case AND:
                  case OR:
                  case XOR:
                     if (this.Uv.q(var3, var4) > 0) {
                        return this.xK.createOperation(var1.getType(), var2, var4, var3);
                     }
                     break;
                  case EQ:
                  case NE:
                     if (this.Uv.q(var3, var4) > 0) {
                        return this.xK.createPredicate(var2, var4, var3);
                     }
                     break;
                  case GT:
                  case GE:
                  case LT:
                  case LE:
                     if (this.Uv.q(var3, var4) > 0) {
                        JavaOperatorType var5 = var2.getMirror();
                        if (var5 != null) {
                           return this.xK.createPredicate(var5, var4, var3);
                        }
                     }
               }

               return null;
            }
         default:
            return null;
      }
   }

   static {
      q.put(bty.class, 100);
      q.put(bum.class, 90);
      q.put(btt.class, 60);
      q.put(bua.class, 50);
      q.put(buj.class, 40);
      q.put(bue.class, 30);
      q.put(bts.class, 26);
      q.put(buf.class, 25);
      q.put(btu.class, 20);
      q.put(bug.class, 10);
      q.put(bui.class, 5);
      q.put(bub.class, 0);
   }

   private class CU implements IDVisitor {
      int q;
      IDExpression RF;

      public void q(IDExpression var1, IDExpression var2, IVisitResults var3) {
         IDExpression var4 = bvb.this.RF(var1);
         if (var4 != null) {
            var4.setType(var1.getType());
            if (var2 == null) {
               this.RF = var4;
               this.q++;
            } else if (var2.replaceSubExpression(var1, var4)) {
               this.q++;
            }
         }
      }
   }

   public static class eo implements Comparator {
      public int q(IDExpression var1, IDExpression var2) {
         if (var1 != null && var2 != null) {
            int var3 = (Integer)bvb.q.getOrDefault(var1.getClass(), 0);
            int var4 = (Integer)bvb.q.getOrDefault(var2.getClass(), 0);
            if (var3 != var4 || var3 == 0) {
               return var3 - var4;
            } else if (var1 instanceof IDImm) {
               Assert.a(var2 instanceof IDImm);
               return Long.compare(((IDImm)var1).getRawValue(), ((IDImm)var2).getRawValue());
            } else if (var1 instanceof IDVar) {
               Assert.a(var2 instanceof IDVar);
               return Long.compare(((IDVar)var1).getId(), ((IDVar)var2).getId());
            } else if (var1 instanceof IDOperation) {
               Assert.a(var2 instanceof IDOperation);
               JavaOperatorType var5 = ((IDOperation)var1).getOperatorType();
               JavaOperatorType var6 = ((IDOperation)var2).getOperatorType();
               int var7 = var5.ordinal() - var6.ordinal();
               if (var7 != 0) {
                  return var7;
               } else {
                  IDExpression var8 = ((IDOperation)var1).getLeft();
                  IDExpression var9 = ((IDOperation)var2).getLeft();
                  var7 = this.q(var8, var9);
                  if (var7 != 0) {
                     return var7;
                  } else {
                     var8 = ((IDOperation)var1).getRight();
                     var9 = ((IDOperation)var2).getRight();
                     if (var8 == null) {
                        return var9 == null ? 0 : -1;
                     } else if (var9 == null) {
                        return 1;
                     } else {
                        var7 = this.q(var8, var9);
                        return var7 != 0 ? var7 : 0;
                     }
                  }
               }
            } else {
               return 0;
            }
         } else {
            return 0;
         }
      }
   }
}
