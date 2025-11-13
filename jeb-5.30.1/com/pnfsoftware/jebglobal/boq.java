package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JOptimizerType;

public class boq extends AbstractJOptimizer {
   public boq() {
      super(JOptimizerType.UNSAFE);
   }

   @Override
   public int perform() {
      return this.m == null ? 0 : this.q();
   }

   int q() {
      if (!this.m.isStatic() && this.m.getName().equals("<init>") && !this.m.isEmpty()) {
         int var1 = 0;
         if (this.RF()) {
            var1++;
         }

         int var2 = this.m.getParameterCount();
         if (var2 == 0) {
            return var1;
         } else {
            IDexClass var3 = this.dex.getClass(this.m.getClassType().getSignature());
            if (var3.isAnonymousClass()) {
               return var1;
            } else {
               IJavaBlock var4 = this.m.getBody();
               IJavaIdentifier var5 = null;
               if (var3.isNonStaticMemberClass()) {
                  if (var2 < 2) {
                     return var1;
                  }

                  var5 = this.m.getParameter(1).getIdentifier();
                  if (var4.get(0) instanceof IJavaAssignment) {
                     IJavaAssignment var6 = (IJavaAssignment)var4.get(0);
                     if (var6.getRight() == var5) {
                        var4.remove(0);
                        var1++;
                     }
                  }
               }

               if (this.m.isEmpty()) {
                  return var1;
               } else {
                  if (var4.get(0) instanceof IJavaCall var7 && var7.getMethodName().equals("<init>")) {
                     if (var7.getArgumentCount() == 1) {
                        var4.remove(0);
                        var1++;
                     } else if (var7.getArgumentCount() == 2 && var7.getArgument(1) == var5) {
                        var4.remove(0);
                        var1++;
                     }
                  }

                  return var1;
               }
            }
         }
      } else {
         return 0;
      }
   }

   private boolean RF() {
      if (this.m.isSecondParameterOuterClassReference()) {
         return false;
      } else {
         int var1 = this.m.getParameterCount();
         if (var1 < 2) {
            return false;
         } else {
            IJavaType var2 = this.m.getClassType();
            IDexClass var3 = this.dex.getClass(var2.getSignature());
            if (!var3.isNonStaticMemberClass()) {
               return false;
            } else {
               IDexClass var4 = DexUtil.getParentClass(this.dex, var3);
               if (var4 == null) {
                  return false;
               } else {
                  IJavaIdentifier var5 = this.m.getParameter(1).getIdentifier();
                  String var6 = var5.getType().getSignature();
                  if (this.dex.getClass(var6) != var4) {
                     return false;
                  } else {
                     this.m.markSecondParameterOuterClassReference();
                     if (this.m.getBody().get(0) instanceof IJavaAssignment) {
                        IJavaAssignment var7 = (IJavaAssignment)this.m.getBody().get(0);
                        if (var7.getRight() == var5 && var7.getLeft() instanceof IJavaInstanceField) {
                           IJavaInstanceField var8 = (IJavaInstanceField)var7.getLeft();
                           var8.getField().addFlags(64);
                        }
                     }

                     return true;
                  }
               }
            }
         }
      }
   }
}
