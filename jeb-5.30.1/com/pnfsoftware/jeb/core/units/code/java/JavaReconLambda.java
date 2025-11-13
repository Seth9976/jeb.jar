package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.List;
import java.util.Map;

@Ser(oldId = 65703)
public class JavaReconLambda {
   @SerId(1)
   IJavaType type;
   @SerId(value = 2, deprecated = true)
   private IJavaMethod lambdaImpl;
   @SerId(3)
   List lambdaParamPositions;
   @SerId(4)
   Map lambdaCaptures;
   @SerId(5)
   String lambdaImplMsig;

   @SerCustomInitPostGraph
   private void init() {
      if (this.lambdaImpl != null) {
         this.lambdaImplMsig = this.lambdaImpl.getSignature();
         this.lambdaImpl = null;
      }
   }

   public JavaReconLambda(IJavaType var1, String var2, List var3, Map var4) {
      this.type = var1;
      this.lambdaImplMsig = var2;
      this.lambdaParamPositions = var3;
      this.lambdaCaptures = var4;
   }

   public IJavaType getType() {
      return this.type;
   }

   public String getImplementedMsig() {
      return this.lambdaImplMsig;
   }

   public List getParameterPositions() {
      return this.lambdaParamPositions;
   }

   public Map getCapturedExpressions() {
      return this.lambdaCaptures;
   }
}
