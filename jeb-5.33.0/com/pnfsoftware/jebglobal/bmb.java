package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;

public class bmb extends AbstractJOptimizer {
   @Override
   public int perform() {
      return this.m == null ? 0 : this.pC(this.m.getBody());
   }

   int pC(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var4 instanceof IJavaTry var5 && this.pC(var5)) {
            var2++;
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.pC(var6);
            }
         }
      }

      return var2;
   }

   boolean pC(IJavaTry var1) {
      int var2 = 0;

      while (true) {
         IJavaBlock var3 = var1.getTryBody();
         if (var2 + 1 >= var3.size()) {
            return false;
         }

         IJavaIdentifier var4 = this.pC(var3.get(var2 + 1));
         if (var4 != null && this.pC(var3.get(var2), var4)) {
            var3.remove(var2 + 1);
            IJavaAssignment var5 = (IJavaAssignment)var3.remove(var2);
            IJavaExpression var6 = this.A(var5);
            if (var2 == 0) {
               var1.addResourceAcq(var6);
            } else {
               IJavaBlock var7 = this.jctx.createBlock();
               IJavaTry var8 = this.jctx.createTry(var7);
               JUtil.moveStatements(var3, var2, var3.size(), var7, 0);
               var3.insert(var2, var8);
               var8.addResourceAcq(var6);
            }

            this.pC(var3, var4);

            for (int var9 = 0; var9 < var1.getCatchCount(); var9++) {
               IJavaBlock var10 = var1.getCatchBody(var9);
               if (var10.size() > 0
                  && var10.get(0) instanceof IJavaCall
                  && ((IJavaCall)var10.get(0)).getMethodSignature().equals("Ljeb/synthetic/TWR;->moot$NT()V")) {
                  var10.remove(0);
               }
            }

            return true;
         }

         var2++;
      }
   }

   IJavaIdentifier pC(IJavaStatement var1) {
      if (var1 instanceof IJavaCall && ((IJavaCall)var1).getMethodSignature().equals("Ljeb/synthetic/TWR;->declareResource(Ljava/lang/AutoCloseable;)V")) {
         IJavaExpression var2 = ((IJavaCall)var1).getArgument(0);
         if (var2 instanceof IJavaIdentifier) {
            return (IJavaIdentifier)var2;
         }
      }

      return null;
   }

   boolean pC(IJavaStatement var1, IJavaIdentifier var2) {
      if (var1 instanceof IJavaAssignment && ((IJavaAssignment)var1).isSimpleAssignment()) {
         IJavaAssignment var3 = (IJavaAssignment)var1;
         IJavaIdentifier var4 = null;
         if (var3.getLeft() instanceof IJavaDefinition) {
            var4 = ((IJavaDefinition)var3.getLeft()).getIdentifier();
         } else if (var3.getLeft() instanceof IJavaIdentifier) {
            var4 = (IJavaIdentifier)var3.getLeft();
         }

         return var4 != null && var4 == var2;
      } else {
         return false;
      }
   }

   int pC(IJavaBlock var1, IJavaIdentifier var2) {
      int var3 = 0;
      int var4 = 0;

      while (var4 < var1.size()) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaCall && ((IJavaCall)var5).getMethodSignature().equals("Ljeb/synthetic/TWR;->useResource$NT(Ljava/lang/AutoCloseable;)V")) {
            IJavaExpression var6 = ((IJavaCall)var5).getArgument(0);
            if (var6 == var2) {
               var1.remove(var4);
               var3++;
               continue;
            }
         }

         if (var5 instanceof IJavaCompound) {
            for (IJavaBlock var7 : ((IJavaCompound)var5).getBlocks()) {
               var3 += this.pC(var7, var2);
            }
         }

         var4++;
      }

      return var3;
   }

   IJavaExpression A(IJavaStatement var1) {
      if (var1 instanceof IJavaAssignment && ((IJavaAssignment)var1).isSimpleAssignment()) {
         IJavaExpression var2 = ((IJavaAssignment)var1).getRight();
         if (var2 instanceof IJavaCall
            && ((IJavaCall)var2).getMethodSignature().equals("Ljeb/synthetic/TWR;->getResource$NT(Ljava/lang/Object;)Ljava/lang/AutoCloseable;")) {
            IJavaExpression var3 = ((IJavaCall)var2).getArgument(0);
            if (var3 == ((IJavaAssignment)var1).getLeft()) {
               return var3;
            }
         }
      }

      return var1;
   }
}
