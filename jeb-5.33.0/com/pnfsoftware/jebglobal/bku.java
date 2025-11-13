package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJStatementOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIf;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTry;

public class bku extends AbstractJStatementOptimizer {
   @Override
   public int optimizeStatement(IJavaStatement var1) {
      if (var1 instanceof IJavaIf && ((IJavaIf)var1).hasDefaultBlock() && ((IJavaIf)var1).getDefaultBlock().isEmpty()) {
         ((IJavaIf)var1).setDefaultBlock(null);
         return 1;
      } else if (var1 instanceof IJavaSwitch && ((IJavaSwitch)var1).hasDefaultBlock() && ((IJavaSwitch)var1).getDefaultBlock().isEmpty()) {
         ((IJavaSwitch)var1).setDefaultBlock(null);
         return 1;
      } else if (var1 instanceof IJavaTry && ((IJavaTry)var1).hasFinallyBlock() && ((IJavaTry)var1).getFinallyBlock().isEmpty()) {
         ((IJavaTry)var1).setFinallyBlock(null);
         return 1;
      } else {
         return 0;
      }
   }
}
