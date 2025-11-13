package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;

public class bnd extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 1; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaTry) {
            IJavaBlock var6 = ((IJavaTry)var5).getTryBody();
            IJavaStatement var7 = var1.get(var4 - 1);
            if (var7 instanceof IJavaDefinition) {
               IJavaIdentifier var8 = ((IJavaDefinition)var7).getIdentifier();
               if (!var6.isEmpty()) {
                  IJavaStatement var9 = var6.get(0);
                  if (var9 instanceof IJavaAssignment
                     && ((IJavaAssignment)var9).isSimpleAssignment()
                     && ((IJavaAssignment)var9).getLeft() == var8
                     && ((IJavaAssignment)var9).getRight() instanceof IJavaConstant) {
                     IJavaAssignment var10 = (IJavaAssignment)var6.remove(0);
                     var10.setLeft((IJavaDefinition)var7);
                     var1.set(var4 - 1, var10);
                     var3++;
                  }
               }
            }
         }
      }

      return var3;
   }
}
