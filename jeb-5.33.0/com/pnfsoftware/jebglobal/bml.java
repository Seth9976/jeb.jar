package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaFor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;

public class bml extends AbstractJOptimizer {
   @Override
   public int perform() {
      if (this.m == null) {
         return 0;
      } else {
         IJavaBlock var1 = this.m.getBody();
         return this.pC(var1);
      }
   }

   private int pC(IJavaBlock var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var1.size(); var3++) {
         IJavaStatement var4 = var1.get(var3);
         if (var3 >= 1 && var4 instanceof IJavaFor var5) {
            IJavaStatement var6 = var5.getInitializer();
            if (var6 instanceof IJavaAssignment
               && ((IJavaAssignment)var6).isSimpleAssignment()
               && ((IJavaAssignment)var6).getLeft() instanceof IJavaIdentifier var8) {
               IJavaStatement var9 = var1.get(var3 - 1);
               if (var9 instanceof IJavaDefinition && ((IJavaDefinition)var9).getIdentifier() == var8) {
                  boolean var10 = this.m.getBody().visitDepthPre(new bmm(this, var4, var9, var8));
                  if (var10 && var5.getInitializer().replaceSubElement(var8, var9)) {
                     var1.remove(var3 - 1);
                     var2++;
                  }
               }
            }
         }

         if (var4 instanceof IJavaCompound) {
            for (IJavaBlock var12 : ((IJavaCompound)var4).getBlocks()) {
               var2 += this.pC(var12);
            }
         }
      }

      return var2;
   }
}
