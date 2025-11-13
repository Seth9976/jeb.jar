package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Ser(oldId = 65700)
public class JavaReconAnon {
   @SerId(1)
   List superInitIndices = new ArrayList();
   @SerId(2)
   Map constParamIndexToField = new HashMap();

   public JavaReconAnon(List var1, Map var2) {
      this.superInitIndices = var1;
      this.constParamIndexToField = var2;
   }

   public List getArgumentIndicesUsedBySuper() {
      return this.superInitIndices;
   }

   public boolean isCapturedToSyntheticField(int var1) {
      return this.constParamIndexToField.containsKey(var1);
   }

   public IJavaInstanceField getCaptureSite(int var1) {
      return (IJavaInstanceField)this.constParamIndexToField.get(var1);
   }
}
