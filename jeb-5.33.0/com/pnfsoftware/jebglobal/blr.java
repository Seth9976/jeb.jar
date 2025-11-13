package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaArrayElt;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconEnummap;

public class blr extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         if (var5 instanceof IJavaSwitch && ((IJavaSwitch)var5).isSwitchOnInteger()) {
            IJavaSwitch var6 = (IJavaSwitch)var5;
            IJavaExpression var7 = var6.getSwitchedExpression();
            if (var7 instanceof IJavaArrayElt) {
               IJavaExpression var8 = ((IJavaArrayElt)var7).getArray();
               IJavaExpression var9 = ((IJavaArrayElt)var7).getIndex();
               if (var8 instanceof IJavaStaticField && var9 instanceof IJavaCall) {
                  IJavaType var10 = ((IJavaStaticField)var8).getClassType();
                  if (((IJavaCall)var9).getMethodName().equals("ordinal")) {
                     IJavaExpression var11 = ((IJavaCall)var9).getArgument(0);
                     IJavaClass var12 = this.jctx.getClassFactory().get(var10.getSignature());
                     if (var12 != null && var12.isBuilt()) {
                        if (var12.getReconEnummap() != null) {
                           JavaReconEnummap var13 = var12.getReconEnummap();
                           var6.convertToSwitchOnEnum(var11, var13.getMap());
                           var3++;
                        }
                     } else if (this.drcollector != null) {
                        this.drcollector.request(var10.getSignature());
                     }
                  }
               }
            }
         }
      }

      return var3;
   }
}
